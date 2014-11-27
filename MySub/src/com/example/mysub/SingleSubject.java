package com.example.mysub;

public class SingleSubject {
	
	
	private String _name;
	private int _imgId = -1;
	
	public SingleSubject(String name,int imgId){
		this.setName(name);
		this.setImgId(imgId);
	}
	
	

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public int getImgId() {
		return _imgId;
	}

	public void setImgId(int _imgId) {
		this._imgId = _imgId;
	}
	

}
