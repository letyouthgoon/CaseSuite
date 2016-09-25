package net.asiasofti.android.en;


public class Order {
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
	public Order(String gc_id, String gc_name) {
		super();
		this.gc_id = gc_id;
		this.gc_name = gc_name;
		
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
}
