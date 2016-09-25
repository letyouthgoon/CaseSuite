package net.asiasofti.android.en;

import org.json.JSONObject;

public class Classify_O implements JsonSerializable<Classify_O>{
	
	private String gc_id;
	private String gc_name;


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


	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classify_O deserialize(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		this.gc_id=jsonObject.optString("gc_id");
		this.gc_name=jsonObject.optString("gc_name");
		return this;
	}
    
}
