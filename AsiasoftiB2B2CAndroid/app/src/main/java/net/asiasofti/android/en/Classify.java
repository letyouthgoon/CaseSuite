package net.asiasofti.android.en;

import org.json.JSONObject;

public class Classify implements JsonSerializable<Classify>{
	
	private String gc_id;
	private String gc_name;
	private String image;
	private String text;
	private String class_list;


	public String getClass_list() {
		return class_list;
	}

	public void setClass_list(String class_list) {
		this.class_list = class_list;
	}

	public String getGc_id() {
		return gc_id;
	}

	public void setGc_id(String gc_id) {
		this.gc_id = gc_id;
	}

	public String getGc_name() {
		return gc_name;
	}

	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classify deserialize(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		this.gc_id=jsonObject.optString("gc_id");
		this.gc_name=jsonObject.optString("gc_name");
		this.image=jsonObject.optString("image");
		this.text=jsonObject.optString("text");
		this.class_list=jsonObject.optString("class_list");
		return this;
	}
    
}
