package resources;

public enum EnumClass {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;
	EnumClass(String resource) {
		// TODO Auto-generated constructor stub
		this.resource =resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	
	

}
