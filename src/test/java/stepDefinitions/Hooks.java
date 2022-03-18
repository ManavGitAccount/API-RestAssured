package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    MainTestClass mainCLass = new MainTestClass();

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        if(mainCLass.place_id == null){
            mainCLass.add_place_payload_with("Manv", "Hindi", "America");
            mainCLass.user_calls_with_http_request("AddPlaceAPI", "POST");
            mainCLass.verify_place_id_created_maps_to_using("Manv", "GetPlaceAPI");
        }
    }
}
