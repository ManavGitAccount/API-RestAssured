package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+11)984 334 8975");
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("bar");
        myList.add("mall");

        addPlace.setTypes(myList);
        Location allLocation = new Location();
        allLocation.setLat(-45.89765);
        allLocation.setLng(37.347589);
        addPlace.setLocation(allLocation);

        return addPlace;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
    }
}
