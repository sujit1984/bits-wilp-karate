Feature: To test deletion of a contact selected by contact ID
Background:
Given url 'http://34.133.197.237:9000/'
* headers {"Content-Type":"application/json"}
* def id = 8

#@ignore
Scenario: To delete the a single contact based on the given id
	Given path 'jpa/contacts/'+ id
	#And param <id>
	When method delete
	Then status 200

@ignore
Scenario Outline: To delete multiple contacts based on the ids passed to the api
	Given path 'jpa/contacts/'+ <id>
	When method delete
	Then status 200
	
	Examples:
	|id|
	|10|
	|11|
	|12|
	|15|
	|19|