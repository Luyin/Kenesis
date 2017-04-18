package com.kenesis.persistence;

import com.kenesis.domain.UserVO;

public interface UserDAO {
	public void insertUser(UserVO vo);
	public void deleteUser(String userid);
	public UserVO readUser(String userid);
	public UserVO readWithPW(String userid, String userpw);
}
