package com.kenesis.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kenesis.domain.UserVO;
import com.kenesis.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	UserDAO dao;
	
	@Resource(name="passwordEncoder")
 	private ShaPasswordEncoder passwordEncoder;

	@Override
	public void update(UserVO vo) {
		String rawPassword = vo.getUserpw();
		String encodedPassword = passwordEncoder.encodePassword(rawPassword, null);
		vo.setUserpw(encodedPassword);
		dao.updateUser(vo);
	}

	@Override
	public UserVO read(String userid) {
		return (UserVO) dao.readUser(userid); 
	}

	@Override
	public void signup(UserVO vo) {
		String rawPassword = vo.getUserpw();
		String encodedPassword = passwordEncoder.encodePassword(rawPassword, null);
		vo.setUserpw(encodedPassword);
		dao.insertUser(vo);
	}

	@Override
	public void signout(String userid) {
		// TODO Auto-generated method stub
		dao.deleteUser(userid);
	}

}
