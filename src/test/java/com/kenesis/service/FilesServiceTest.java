package com.kenesis.service;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kenesis.domain.FilesVO;
import com.kenesis.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FilesServiceTest {

	@Inject
	UserService userService;
	
	@Inject
	FilesService filesService;
	
	@Before
	public void prepare()
	{
		UserVO vo = new UserVO();
		
		vo.setUserid("admin");
		vo.setUserpw("5gksghk!!");
		vo.setHomelocation("/home/myraous/");
		
		userService.signup(vo);
		
		FilesVO filesVO = new FilesVO();
		
		filesVO.setUserid("admin");
		filesVO.setLocation("/home/myraous");
		
		filesService.AddFiles(filesVO);
	}
	
	@Test
	public void testReadDirectory()
	{
		FilesVO filesVO = new FilesVO();
		
		filesVO.setUserid("admin");
		filesVO.setLocation("/");
		
		filesService.viewDirectory(filesVO);
	}
	
	@After
	public void testRemoveUser()
	{
		userService.signout("admin");
	}
	
}
