package com.kenesis.api;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenesis.domain.EncodeVO;
import com.kenesis.domain.FilesVO;
import com.kenesis.domain.UserVO;
import com.kenesis.service.EncodeService;
import com.kenesis.service.FilesService;
import com.kenesis.service.UserService;

@Controller
@RequestMapping(value = "/v1")
public class EncodeController {
	private static final Logger logger = LoggerFactory.getLogger(EncodeController.class);
	private static final String ENCODE_QUEUE_NAME = "encode";
	private static final String DELETE_QUEUE_NAME = "delete";

	@Inject
    private RabbitTemplate rabbitTemplate;
	
	@Inject
	private FilesService filesService;
	
	@Inject
	private EncodeService encodeService;
	
	@Inject
	private UserService userSerivice;
	
	@RequestMapping(value = "/encode", method = RequestMethod.POST )
	public @ResponseBody String EncodeVideo(@RequestParam("location")String location)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		
		logger.info("POST /encode form: location=" + location);
		
		FilesVO filesVO = new FilesVO();
		
		filesVO.setLocation(location);
		filesVO.setUserid(userid);
		
		//tbl_file ���̺� �߰�
		filesService.AddFiles(filesVO);
		
		//fileid ��������
		filesVO = filesService.readFile(userid, location);
		
		// Start of encode ���̺� ���� ����
		EncodeVO encodeVO = new EncodeVO();
		encodeVO.setFileid(filesVO.getFileid());
		encodeVO.setProgress(100);
		encodeVO.setStatus("Read");
		encodeService.requestEncode(encodeVO);
		// End of encode ���̺� ���� ����
		
		UserVO userVO = userSerivice.read(userid);
		
		JSONObject obj = new JSONObject();
		obj.put("fileid", Integer.toString(filesVO.getFileid()));
		obj.put("abslocation", userVO.getHomelocation() + "/" + location);
	
		rabbitTemplate.convertAndSend(ENCODE_QUEUE_NAME, obj.toJSONString().getBytes()); 
		
		logger.info(obj.toJSONString() + " message(s) sent successfully.");
		
		CachingConnectionFactory cachingConnectionFactory = (CachingConnectionFactory) rabbitTemplate.getConnectionFactory();
		cachingConnectionFactory.destroy();
		
		return Integer.toString(filesVO.getFileid());
	}
	
	@RequestMapping(value = "/encode/{fileid}", method = RequestMethod.DELETE )
	public void CancelEncode(@PathVariable("fileid")String fileid)
	{
		logger.info("DELETE /encode/" + fileid);
		rabbitTemplate.convertAndSend(DELETE_QUEUE_NAME, fileid.getBytes());
		logger.info(fileid + " message(s) sent successfully.");
		
		//RabbitMQ���� �޽��� ����
		
		//���� �������� ���ڵ� �۾� �ߴ�
	}
	
	@RequestMapping(value = "/encode/{fileid}", method = RequestMethod.GET)
	public @ResponseBody JSONObject EncodeProgress(@PathVariable("fileid")String fileid)
	{
		logger.info("GET /encode/" + fileid);
		//encode ���̺� �ִ��� Ȯ��
		
		EncodeVO vo = encodeService.statusEncode(Integer.parseInt(fileid));
		
		JSONObject obj = new JSONObject();
		obj.put("status", vo.getStatus());
		obj.put("progress", vo.getProgress());
		
		return obj;
	}
}
