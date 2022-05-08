#@ignore
Feature: To test the get endpoint of the contacts api 
Background:
Given url 'http://34.133.197.237:9000/'
* def schemas = {firstName : "#string", lastName: "#string", contactId: "#number", phone: "#string",  email: "#string"} 

Scenario: To get all the data from the contacts-api 
	Given path 'jpa/contacts'
	When method get
	Then status 200
	And print response 
	And match response contains 
	"""
	{
    "firstName": "Marc",
    "lastName": "Antoine",
    "contactId": 111115,
    "phone": "2223232311",
    "email": "marc.antoine@gmail.com"
  },
  {
    "firstName": "Claude",
    "lastName": "Gauthier",
    "contactId": 111116,
    "phone": "232323223",
    "email": "claude.gauthier@gmail.com"
  }
  """
 
 
 Scenario: To validate the existence of a specific value in the JSON response
	Given path 'jpa/contacts'
	When method get
	Then status 200
	And print response 
	#And match response contains 
	* match response contains deep {"email" : "ruth.gomez@gmail.com"}
	
Scenario: To search for a particular id (contactId) among the set of ids
	Given path 'jpa/contacts/111116'
	When method get
	Then status 200
	And print response 
	#And match response contains 
	* match response.email == "claude.gauthier@gmail.com" 
	
	
Scenario: To search for a particular id (contactId) among the set of ids
	Given path 'jpa/contacts/'
	When method get
	Then status 200
	And print response 
	#And match response contains 
	* match response ==
	"""
	'#[] ##(schemas)'
	"""
 