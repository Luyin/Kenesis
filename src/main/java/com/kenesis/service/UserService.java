package com.kenesis.service;

import com.kenesis.domain.UserVO;

public interface UserService {
	public void signup(UserVO vo);
	
	public void signout(String userid);
	
	public void update(UserVO vo);
	
	public UserVO read(String userid);
}
