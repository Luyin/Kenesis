package com.kenesis.persistence;

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
public class EncodeDAOTest {
	
	@Inject
	EncodeDAO encodeDAO;
	
	@Inject
	private FilesDAO filesDAO;
	
	@Inject
	private UserDAO userDAO;
	
	private String userid = "admin";
	private String userpw = "5tkatjd!";
	private String homelocation = "/home/myraous";
	private String filelocation = "htdocs/Movie/starwars";
	private int fileid;
	
	@Before
	public void testInsertEncode()	
	{
		userDAO.deleteUser(userid);
		
		UserVO userVO = new UserVO();
		
		userVO.setUserid(userid);
		userVO.setUserpw(userpw);
		userVO.setHomelocation(homelocation);
		
		userDAO.insertUser(userVO);
		
		FilesVO filesVO = new FilesVO();
		
		filesVO.setUserid(userid);
		filesVO.setLocation(filelocation);
		
		filesDAO.insertFiles(filesVO);
		
		filesVO = filesDAO.readFile(userid, filelocation);
		
		fileid = filesVO.getFileid();
		
		EncodeVO encodeVO = new EncodeVO();
		
		encodeVO.setFileid(fileid);
		encodeVO.setStatus("Ready");
		
		encodeVO.setProgress(100);
		
		encodeDAO.insertEncode(encodeVO);
	}
	
	@Test
	public void readEncode()
	{
		encodeDAO.readEncode(fileid);
	}
	
	@After
	public void deleteEncode()
	{
		encodeDAO.deleteEncode(fileid);
		userDAO.deleteUser(userid);
	}

}
