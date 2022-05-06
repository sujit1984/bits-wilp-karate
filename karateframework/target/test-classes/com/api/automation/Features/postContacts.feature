Feature: To test the creation of a new contacts in the contacts service
Background:
* def testdata = read("testData.csv")
* url 'http://34.133.197.237:9000/'
* headers {"Content-Type":"application/json"}


Scenario: To post a new record to the contacts-api microservice
	Given path 'jpa/contacts'
	And request '{"firstName": "Lionel1","lastName": "Messi1","phone": "232323434","email": "Andres.Iniesta@invalidemail.com"}'
	When method post
	Then status 201


Scenario Outline: To post multiple records to the contacts-api microservice using data table
	Given url 'http://34.133.197.237:9000/jpa/contacts'
	And request '{"firstName": "<firstName>","lastName": "<lastName>","phone": "<phoneNo>","email": "<emailId>"}'
	When method post
	Then status 201
	
	Examples:
	| firstName | lastName | phoneNo | emailId |
	| Denis | Bergkamp | 389483744 | denis.bergkamp@invalidemail.com |
	| Mesut | Ozil | 3874384733 | mesul.ozil@invalidemail.com | 
	| Michel | Ballack| 8934723893 | michel.b@invalidemail.com |
	
	
Scenario Outline: To post multiple records to the contacts-api microservice using a csv file -  <TC_Num>
	Given path 'jpa/contacts'
	And request '{"firstName": "<firstName>","lastName": "<lastName>","phone": "<phoneNo>","email": "<emailID>"}'
	When method post
	Then status 201
	
	Examples:
	|testdata| 
	
 