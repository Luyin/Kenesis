package com.kenesis.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kenesis.domain.UserVO;
import com.kenesis.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	UserDAO dao;

	@Override
	public UserVO userinfo(String userid) {
		return (UserVO) dao.readUser(userid);
	}

	@Override
	public void signup(UserVO vo) {
		// TODO Auto-generated method stub
		dao.insertUser(vo);
	}

	@Override
	public void signout(String userid) {
		// TODO Auto-generated method stub
		dao.deleteUser(userid);
	}

}
