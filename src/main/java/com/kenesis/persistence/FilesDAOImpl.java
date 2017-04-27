package com.kenesis.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kenesis.domain.FilesVO;

@Repository
public class FilesDAOImpl implements FilesDAO {
	private final String  namespace = "com.kenesis.mappers.FilesMapper"; 
	
	@Inject
	private SqlSession sqlSession; 

	@Override
	public List<FilesVO> readFiles(FilesVO vo) {
		List<FilesVO> outputs = sqlSession.selectList(namespace + ".readFiles", vo);
		return outputs;
	}

	@Override
	public void insertFiles(FilesVO vo) {
		sqlSession.insert(namespace + ".insertFiles", vo);
	}
	
}
