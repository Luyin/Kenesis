package com.kenesis.persistence;

import com.kenesis.domain.UserVO;

public interface UserDAO {
	public void insertUser(UserVO vo);
	public void deleteUser(String userid);
	public void updateUser(UserVO vo);
	public UserVO readUser(String userid);
}
