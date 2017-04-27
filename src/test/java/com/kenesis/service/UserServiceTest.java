package com.kenesis.service;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kenesis.domain.UserVO;
import com.kenesis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserServiceTest {
	@Inject
	UserService service;
	
	@Before
	public void testSignUp()
	{
		UserVO vo = new UserVO();
		
		vo.setUserid("admin");
		vo.setUserpw("5gksghk!!");
		vo.setHomelocation("/home/myraous/");
		
		service.signup(vo);
	}
	
	@Test
	public void testViewInfo()
	{
		service.read("admin");
	}
	
	@Test
	public void testUpdateInfo()
	{
		UserVO vo = new UserVO();
		
		vo.setUserid("admin");
		vo.setUserpw("5tkatjd!");
		vo.setHomelocation("/home/myraous/");
		
		service.update(vo);
	}
	
	@After
	public void testSignOut()
	{
		service.signout("admin");
	}
}
