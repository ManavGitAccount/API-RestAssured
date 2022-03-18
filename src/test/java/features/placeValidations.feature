Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
  Given Add place payload with "<name>" "<language>" "<address>"
  When user calls "AddPlaceAPI" with "POST" Http request
  Then the API call is success with the status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_Id created maps to "<name>" using "GetPlaceAPI"


  Examples:
      |name                | language    | address               |
      |Crax Vialli House   | FR          | Via Vialli Road,Italy |
      |Ronaldo House       | POR         | Lisbon, Brazil        |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "POST" Http request
    Then the API call is success with the status code 200
    And "status" in response body is "OK"


