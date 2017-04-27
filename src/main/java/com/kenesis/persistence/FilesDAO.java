package com.kenesis.persistence;

import java.util.List;

import com.kenesis.domain.FilesVO;

public interface FilesDAO {
	public List<FilesVO> readFiles(FilesVO vo);
	public void insertFiles(FilesVO vo);
}
