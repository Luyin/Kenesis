package com.kenesis.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenesis.domain.UserVO;
import com.kenesis.service.UserService;

@Controller
@RequestMapping(value = "/v1")
public class FilesController {
	
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);
	
	@Inject
	UserService service;
	
	@RequestMapping(value = "/files/{userid}/{location}", method = RequestMethod.GET )
	public @ResponseBody Map<String, String> getFilelists(@PathVariable("userid")String userid, @PathVariable("location")String location)
	{
		logger.info("/files/" + userid);
		Map<String, String> mfiles = new HashMap<String, String>();
		UserVO vo = (UserVO)service.read(userid);
		
		try {
			String source = vo.getHomelocation() + location;
			logger.info("location :" + source);
			File dir = new File(source);
			File[] filesList = dir.listFiles();
			
			for(int i=0; i<filesList.length; i++)
			{
				if(filesList[i].isDirectory())
				{
					//Directory
					mfiles.put(filesList[i].getPath(), "Directory");
				}
				else
				{
					String strFileName = filesList[i].getName();
					int pos = strFileName.lastIndexOf( "." );
					String ext = strFileName.substring( pos + 1 );

					//Not Directory
					mfiles.put(filesList[i].getPath(), ext);
				}
			}
			
			return mfiles;	
		}catch(Exception e){
			return mfiles; 
		}
	}
}
