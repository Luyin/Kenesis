package com.kenesis.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kenesis.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	private final String  namespace = "com.kenesis.mappers.UserMapper"; 
	
	@Inject
	private SqlSession sqlSession; 
	
	@Override
	public void insertUser(UserVO vo){
		 sqlSession.insert(namespace + ".insertUser", vo);
	}
	
	@Override
	public void deleteUser(String userid) {						
		sqlSession.selectOne(namespace + ".deleteUser", userid);
	}

	@Override
	public UserVO readUser(String userid) {
		return (UserVO) sqlSession.selectOne(namespace + ".readUser", userid);
	}

	@Override
	public UserVO readWithPW(String userid, String userpw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return (UserVO) sqlSession.selectOne(namespace + ".readWithPW", paramMap);
	}
}
