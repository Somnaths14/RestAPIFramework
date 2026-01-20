package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuilder {

	public AddPlace AddPlacePayload(String name, String address, String phone) {
		AddPlace ap = new AddPlace();
		Location l = new Location();

		l.setLat(60);
		l.setLng(60);

		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number(phone);
		ap.setAddress(address);

		ArrayList<String> al = new ArrayList<>();
		al.add("shoe park");
		al.add("shop");
		ap.setTypes(al); // As Types is List of string
		
		return ap;
	}
}
