package com.kenesis.domain;

public class FileListVO {
	private String path;
	private String type;
	private String fileid;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	@Override
	public String toString() {
		return "FileListVO [path=" + path + ", type=" + type + ", fileid=" + fileid + "]";
	}
}
