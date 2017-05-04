package com.kenesis.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kenesis.domain.EncodeVO;

@Repository
public class EncodeDAOImpl implements EncodeDAO {
	private final String namespace = "com.kenesis.mappers.EncodeMapper"; 
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void insertEncode(EncodeVO vo) {
		sqlSession.insert(namespace + ".insertEncode", vo); 
	}

	@Override
	public EncodeVO readEncode(int fileid) {
		return sqlSession.selectOne(namespace + ".readEncode", fileid);
	}

	@Override
	public void deleteEncode(int fileid) {
		sqlSession.delete(namespace + ".deleteEncode", fileid);
	}

	@Override
	public void updateEncode(EncodeVO vo) {
		sqlSession.update(namespace + ".updateEncode", vo);
	}
}
