Feature: Validating Place API
@AddPlace
Scenario Outline: Adding place by Add API
Given User add the payload json with "<name>" , "<Address>" and "<PhoneNo>"
When User calls the "<API>" API with http "<httpmethod>" method
Then User get the response with status code 200
And User verify "status" is "OK"
And User verify "scope" is "APP"
Then User validate the "place_id" has same "<name>" , "<Address>" and "<PhoneNo>"

Examples:
|name    |Address|PhoneNo  |API        |httpmethod|
|Parata|One2020  |735879850|addPlaceAPI|POST      |

@DeletePlace
Scenario: Delete place by deleteAPI
Given User calls the "deletePlaceAPI" API with http "POST" method with place_id
Then User get the response with status code 200
And User verify "status" is "OK"

