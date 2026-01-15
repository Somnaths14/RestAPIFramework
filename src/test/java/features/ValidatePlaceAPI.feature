Feature: Validating Place API

Scenario: Adding place by Add API
Given User add the payload json
When User calls the "AddPlace" API with http method
Then User get the response with status code 200
And User verify "status" is "OK"
And User verify "scope" is "APP"  