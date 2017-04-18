package com.kenesis.api;

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
		
		vo.setUserid("test00");
		vo.setUserpw("15321");
		vo.setHomelocation("/home/jingukim/");
		
		service.signup(vo);
	}
	
	@Test
	public void testUserInfo()
	{
		service.userinfo("test00");
	}
	
	@After
	public void testSignOut()
	{
		service.signout("test00");
	}
}
