@login
Feature: Login to Application 
  As a PO 
  I want to login to application 
  So that verify login function work well 
@register
Scenario: Register and Login to application 
  Given I open application 
  And I get Login page Url 
  And I click to here link
  And I input to email textbox with data ​"selenium_07_nttvy" 
  And I click to Submit button at Register page 
  Then I get UserID infor 
  And I get Password infor 
  When I open Login page again 
  And I input to Username textbox 
  And I input to Password textbox 
  And I click to Login button at Login page
  Then Verify Home page displayed with message ​"Welcome To Manager's Page of Guru99 Bank"