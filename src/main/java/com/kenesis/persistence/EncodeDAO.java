package com.kenesis.persistence;

import com.kenesis.domain.EncodeVO;

public interface EncodeDAO {
	public void insertEncode(EncodeVO vo);
	public EncodeVO readEncode(int fileid);
	public void deleteEncode(int fileid);
	public void updateEncode(EncodeVO vo);
}
