package net.asiasofti.android.en;


import org.json.JSONObject;



public interface JsonSerializable<T> {
	
	public JSONObject serialize() ;

	public T deserialize(JSONObject jsonObject);
	
}
