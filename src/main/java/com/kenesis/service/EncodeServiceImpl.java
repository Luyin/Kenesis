package com.kenesis.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kenesis.domain.EncodeVO;
import com.kenesis.persistence.EncodeDAO;

@Service
public class EncodeServiceImpl implements EncodeService {

	@Inject
	EncodeDAO dao;
	
	@Override
	public void requestEncode(EncodeVO vo) {
		dao.insertEncode(vo);
	}

	@Override
	public void cancleEncode(int fileid) {
		dao.deleteEncode(fileid);
	}

	@Override
	public EncodeVO statusEncode(int fileid) {
		return dao.readEncode(fileid);
	}

	@Override
	public void updateEncode(EncodeVO vo) {
		dao.updateEncode(vo);
	}
}
