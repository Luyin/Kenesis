package com.kenesis.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kenesis.domain.FilesVO;
import com.kenesis.persistence.FilesDAO;

@Service
public class FilesServiceImpl implements FilesService {
	
	@Inject
	FilesDAO dao;
	
	@Override
	public List<FilesVO> viewDirectory(FilesVO vo) {
		return dao.readFiles(vo);
	}

	@Override
	public void AddFiles(FilesVO vo) {
		dao.insertFiles(vo);
	}

	@Override
	public FilesVO readFile(String userid, String location) {
		return dao.readFile(userid, location);
	}
}
