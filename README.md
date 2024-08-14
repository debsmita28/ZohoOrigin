# ZohoOrigin

Scope : 
Test the leads page of ZOHO platform as a basic smoke test approach.

Test Scenarios : 
1. Login to the portal
2. Go to the Leads section
3. Create a new lead
4. Edit a lead
5. Filter and fetch leads according to the filter choices provided
6. Filter and delete a lead.

Test Cases : 
1. Login to the portal and access the leads page
2. Create a new lead
3. Edit the newly created lead
4. Filter leads based on provided filters
5. Filter and delete any specific lead
6. Sign out of the portal

Test Reports Location : 
/test-output/emailable-report.html

Language & Framework Used :
Java with Selenium and TestNG in Page Object Model concept setup

All the webdrivers and page related functions are kept in Leads.java class in : src/main/java/POM/ZohoPom/pages/Leads.java
All the test cases and test executions are kept in LeadsTest.java class in : src/main/java/POM/ZohoPom/tests/LeadsTest.java

We have also kept the chromedriver used in this code and matched the chrome browser version with that and executed.



