Feature: Underage Users cannot access the Success page
Check if the user upon inputting a valid Username and Birthdate are blocked to access the success page if their age are lesser than the legal age.

    Scenario Outline: Users with empty or null field name cannot be allowed into de success page. 
                      An error message should appear in the page informing the error.
        Given I want to access the success page
        And Today is "29/02/2004"
        When I input my name <Name>
        And I input my Birthdate "24/05/2010"
        And I click the submit button
        Then My access request will be <Result>
        And I will be shown the error message: <Error message>
        Examples:
        | Name           |  Result       | Error message                |
        | ""             |  "Denied"     | "Please enter a valid Name!" | 
    
    Scenario Outline: Users with invalid dates such as 30/02/2023, 01/13/2023 cannot be allowed into de success page.
                      Users with invalid date format cannot be allowed into de success page.
                      An error message should appear in the page informing the error.
        Given I want to access the success page
        And Today is "29/02/2004"
        When I input my name "Algo"
        And I input my Birthdate <Birthdate>
        And I click the submit button
        Then My access request will be <Result>
        And I will be shown the error message: <Error message>
        Examples:
        | Birthdate        |  Result        | Error message                                         |          
        | "24/13/2010"     |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |               
        | "29/02/2010"     |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |         
        | ""               |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |          
        | "abcde"          |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |           

    Scenario Outline: Underaged users cannot be allowed into the success page
                      An error message should appear in the page informing the error.  
        Given I want to access the success page
        And Today is "29/02/2004"
        When I input my name <Name>
        And I input my Birthdate <Birthdate>
        And I click the submit button
        Then My access request will be <Result>
        Then I will be shown the error message: <Error message>
        Examples:
        | Name          | Birthdate        |  Result        | Error message              |   
        | "Algo"        | "01/03/1986"     |  "Denied"      | "Minors are not allowed!"  |   
        | "Algo"        | "02/03/1986"     |  "Denied"      | "Minors are not allowed!"  |   
        | "Algo"        | "24/05/5100"     |  "Denied"      | "Minors are not allowed!"  |   




    Scenario Outline: Legal Age users are allowed into the success page
        Given I want to access the success page
        And Today is "29/02/2004"
        When I input my name <Name>
        And I input my Birthdate <Birthdate>
        And I click the submit button
        Then My access request will be <Result>
        Then The user has name <Name> and age <Age>

        Examples:
        | Name             | Birthdate        |  Result        | Age         |
        | "Algo"           | "28/02/1986"     |  "Approved"    | "18 Years"  | #0 year later
        | "Algo"           | "02/03/1900"     |  "Approved"    | "103 Years" | #old before birthday 
        | "Algo"           | "01/01/1900"     |  "Approved"    | "104 Years" | #old after birthday
        
