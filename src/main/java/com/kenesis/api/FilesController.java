package com.kenesis.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenesis.domain.FileListVO;
import com.kenesis.domain.FilesVO;
import com.kenesis.domain.UserVO;
import com.kenesis.service.FilesService;
import com.kenesis.service.UserService;

@Controller
@RequestMapping(value = "/v1")
public class FilesController {
	
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);
	
	@Inject
	UserService userService;
	
	@Inject
	FilesService filesService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/files/{userid}", method = RequestMethod.GET )
	public @ResponseBody List<FileListVO> getFilelists(@PathVariable("userid")String userid, @ModelAttribute("location")String querylocation)
	{
		logger.info("/files/" + userid);
		List<FileListVO> mfileList = new ArrayList<FileListVO>();
		
		UserVO userVO = (UserVO)userService.read(userid);
		FilesVO filesVO = new FilesVO();
		filesVO.setUserid(userid);
		filesVO.setLocation(querylocation);
		
		List<FilesVO> mEncodedList = filesService.viewDirectory(filesVO);
		
		try {
			String source = userVO.getHomelocation() + "/" + querylocation;
			logger.info("location :" + source);
			File dir = new File(source);
			
			logger.info("isDirectory : " + dir.isDirectory());
			logger.info("isFile : " + dir.isFile());

			if(dir.isDirectory())
			{
				File[] filesList = dir.listFiles();
				
				for(int i=0; i<filesList.length; i++)
				{
					FileListVO fileListVO = new FileListVO();
					
					// 1. Set Path
					fileListVO.setPath(filesList[i].getPath());
					
					// 2. Set Type
					if(filesList[i].isDirectory())
					{
						fileListVO.setType("Directory");
					}
					else
					{
						String strFileName = filesList[i].getName();
						int pos = strFileName.lastIndexOf( "." );
						String ext = strFileName.substring( pos + 1 );

						fileListVO.setType(ext);
					}
					
					// 3. Set FileID
					fileListVO.setFileid(String.valueOf(-1));
					for(int j=0; j<mEncodedList.size(); j++)
					{
						FilesVO vo = mEncodedList.get(j);
						if(filesList[i].getPath().equals(userVO.getHomelocation() + "/" + vo.getLocation()))
						{
							fileListVO.setFileid(String.valueOf(vo.getFileid()));
							break;
						}
					}
					
					
					logger.info(fileListVO.toString());
					
					mfileList.add(fileListVO);
				}
			}
			else if(dir.isFile())// source is file
			{
				
				FileListVO fileListVO = new FileListVO();
				// 1. Set Path
				fileListVO.setPath(dir.getPath());
				
				// 2. Set Type
				String strFileName = dir.getName();
				int pos = strFileName.lastIndexOf( "." );
				String ext = strFileName.substring( pos + 1 );
				fileListVO.setType(ext);
				
				// 3. Set FileID
				fileListVO.setFileid(String.valueOf(-1));
				for(int j=0; j<mEncodedList.size(); j++)
				{
					FilesVO vo = mEncodedList.get(j);
					if(dir.getPath().equals(userVO.getHomelocation() + "/" + vo.getLocation()))
					{
						fileListVO.setFileid(String.valueOf(vo.getFileid()));
						break;
					}
				}
				
				mfileList.add(fileListVO);
			}
			
			
			return mfileList;	
		}catch(Exception e){
			return mfileList; 
		}
	}
	
	@RequestMapping(value = "/files", method = RequestMethod.GET )
	public @ResponseBody List<FileListVO> getFilelists(@ModelAttribute("location")String querylocation)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		
		logger.info("/files/" + userid);
		List<FileListVO> mfileList = new ArrayList<FileListVO>();
		
		UserVO userVO = (UserVO)userService.read(userid);
		FilesVO filesVO = new FilesVO();
		filesVO.setUserid(userid);
		filesVO.setLocation(querylocation);
		
		List<FilesVO> mEncodedList = filesService.viewDirectory(filesVO);
		
		try {
			String source = userVO.getHomelocation() + "/" + querylocation;
			logger.info("location :" + source);
			File dir = new File(source);
			
			logger.info("isDirectory : " + dir.isDirectory());
			logger.info("isFile : " + dir.isFile());

			if(dir.isDirectory())
			{
				File[] filesList = dir.listFiles();
				
				for(int i=0; i<filesList.length; i++)
				{
					FileListVO fileListVO = new FileListVO();
					
					// 1. Set Path
					fileListVO.setPath(filesList[i].getPath());
					
					// 2. Set Type
					if(filesList[i].isDirectory())
					{
						fileListVO.setType("Directory");
					}
					else
					{
						String strFileName = filesList[i].getName();
						int pos = strFileName.lastIndexOf( "." );
						String ext = strFileName.substring( pos + 1 );

						fileListVO.setType(ext);
					}
					
					// 3. Set FileID
					fileListVO.setFileid(String.valueOf(-1));
					for(int j=0; j<mEncodedList.size(); j++)
					{
						FilesVO vo = mEncodedList.get(j);
						if(filesList[i].getPath().equals(userVO.getHomelocation() + "/" + vo.getLocation()))
						{
							fileListVO.setFileid(String.valueOf(vo.getFileid()));
							break;
						}
					}
					
					
					logger.info(fileListVO.toString());
					
					mfileList.add(fileListVO);
				}
			}
			else if(dir.isFile())// source is file
			{
				
				FileListVO fileListVO = new FileListVO();
				// 1. Set Path
				fileListVO.setPath(dir.getPath());
				
				// 2. Set Type
				String strFileName = dir.getName();
				int pos = strFileName.lastIndexOf( "." );
				String ext = strFileName.substring( pos + 1 );
				fileListVO.setType(ext);
				
				// 3. Set FileID
				fileListVO.setFileid(String.valueOf(-1));
				for(int j=0; j<mEncodedList.size(); j++)
				{
					FilesVO vo = mEncodedList.get(j);
					if(dir.getPath().equals(userVO.getHomelocation() + "/" + vo.getLocation()))
					{
						fileListVO.setFileid(String.valueOf(vo.getFileid()));
						break;
					}
				}
				
				mfileList.add(fileListVO);
			}
			
			
			return mfileList;	
		}catch(Exception e){
			return mfileList; 
		}
	}
}
