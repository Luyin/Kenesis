package com.kenesis.persistence;

import com.kenesis.domain.UserVO;

public interface UserDAO {
	public void insertUser(UserVO vo);
	public UserVO readWithPW(String userid, String userpw);
	public void deleteWithPW(String userid, String userpw);
}
