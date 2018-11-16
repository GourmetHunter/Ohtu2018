Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
		Given command login is selected
		When  username "pekka" and password "akkep" are entered
		Then  system will respond with "logged in"

	Scenario: user can not login with incorrect password
		Given command login is selected
		When  username "pekka" and password "2+035721+085732485" are entered
		Then  system will respond with "wrong username or password"

	Scenario: nonexistent user can not login to 
		Given command login is selected
		When  username "p_e::::12312khahahahaka12392929229" and password "akkep" are entered
		Then  system will respond with "wrong username or password"