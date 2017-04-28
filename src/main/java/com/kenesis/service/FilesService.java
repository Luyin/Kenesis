package com.kenesis.service;

import java.util.List;

import com.kenesis.domain.FilesVO;

public interface FilesService {
	public List<FilesVO> viewDirectory(FilesVO vo);
	public void AddFiles(FilesVO vo);
}
