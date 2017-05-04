package com.kenesis.service;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kenesis.domain.EncodeVO;
import com.kenesis.domain.FilesVO;
import com.kenesis.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class EncodeServiceTest {

	@Inject
	EncodeService encodeService;
	
	@Inject
	UserService userService;
	
	@Inject
	FilesService filesService;
	
	private String userid = "admin";
	private String userpw = "5tkatjd!";
	private String homelocation = "/home/myraous";
	private String filelocation = "htdocs/Movie/starwars";
	private int fileid;
	
	@Before
	public void requestEncode()
	{
		userService.signout(userid);
		
		UserVO vo = new UserVO();
		
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		vo.setHomelocation(homelocation);
		
		userService.signup(vo);
		
		FilesVO filesVO = new FilesVO();
		
		filesVO.setUserid(userid);
		filesVO.setLocation(filelocation);
		
		filesService.AddFiles(filesVO);
		
		filesVO = filesService.readFile(userid, filelocation);
		
		fileid = filesVO.getFileid();
		
		EncodeVO encodeVO = new EncodeVO();
		
		encodeVO.setFileid(fileid);
		encodeVO.setStatus("Ready");
		encodeVO.setProgress(100);
		
		encodeService.requestEncode(encodeVO);
	}
	
	@Test
	public void statusEncode()
	{
		encodeService.statusEncode(fileid);
	}
	
	@After
	public void cancleEncode()
	{
		encodeService.cancleEncode(fileid);
		userService.signout(userid);
	}
}
