package com.kenesis.domain;

public class FileListVO {
	private String path;
	private String type;
	private boolean encoded;

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
	public boolean isEncoded() {
		return encoded;
	}
	public void setEncoded(boolean encoded) {
		this.encoded = encoded;
	}
}
