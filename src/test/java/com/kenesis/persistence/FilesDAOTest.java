package com.kenesis.persistence;

import java.util.List;

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
public class FilesDAOTest {

	@Inject
	private FilesDAO filesDAO;
	
	@Inject
	private UserDAO userDAO;
	
	@Before
	public void testInsertFiles()	
	{
		userDAO.deleteUser("admin");
		
		UserVO userVO = new UserVO();
		
		userVO.setUserid("admin");
		userVO.setUserpw("5gksghk!");
		userVO.setHomelocation("/home/myraous");
		
		userDAO.insertUser(userVO);
		
		FilesVO filesVO = new FilesVO();
		
		filesVO.setUserid("admin");
		filesVO.setLocation("/home/myraous");
		
		filesDAO.insertFiles(filesVO);
	}
	
	@Test
	public void testReadFiles()
	{
		FilesVO vo = new FilesVO();
		
		vo.setUserid("admin");
		vo.setLocation("/");
		
		List<FilesVO> mList = filesDAO.readFiles(vo);
		
		for(int i=0; i<mList.size(); i++)
		{
			System.out.println(mList.get(i).toString());
		}
	}
	
	@Test
	public void testReadFile()
	{
		filesDAO.readFile("admin", "/home/myraous");
	}
	
	
	@After
	public void testRemoveUser()
	{	
		userDAO.deleteUser("admin");
	}
}
