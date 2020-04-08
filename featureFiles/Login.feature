@smoke
Feature: Login test cases

Background:
	Given User is on Loginpage
  @TC1
  Scenario: validate the fields in login page
  
    Then all fields should be displayed including CopyRight
    Then Login to Dashboard button should be displayed in dark bule color
    
    @TC2
   Scenario: Validate the error message for invalid username
   
   When user enters invalid email
   Then proper error message is displayed
   
   @TC3
   Scenario: Validate the Forgot passoword
   
   When user clicks on Forgot Password
   Then email address textbox is diplayed
   When user enters email id and click Recover password
   
		
