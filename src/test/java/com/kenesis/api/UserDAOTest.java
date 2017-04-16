package com.kenesis.api;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kenesis.domain.UserVO;
import com.kenesis.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOTest {
	@Inject
	private UserDAO dao;
	
	@Before
	public void testInsertMember() throws Exception{
		UserVO vo = new UserVO();
		
		vo.setUserid("user00");
		vo.setUserpw("user00");
		vo.setHomelocation("/home/myraous");
		
		dao.insertUser(vo);
	}	
	
	@Test
	public void testReadWithPW() throws Exception{
		dao.readWithPW("user00", "user00");
	}
	
	@After
	public void testDeleteWithPW() throws Exception{
		dao.deleteWithPW("user00", "user00");
	}
}
