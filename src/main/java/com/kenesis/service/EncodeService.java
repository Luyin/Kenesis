package com.kenesis.service;

import com.kenesis.domain.EncodeVO;

public interface EncodeService {
	public void requestEncode(EncodeVO vo);
	public void cancleEncode(int fileid);
	public EncodeVO statusEncode(int fileid);
	public void updateEncode(EncodeVO vo);
}
