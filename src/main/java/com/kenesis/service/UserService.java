package com.kenesis.service;

import com.kenesis.domain.UserVO;

public interface UserService {
	public void signup(UserVO vo);
	
	public void signout(String userid);
	
	public UserVO userinfo(String userid);
}
