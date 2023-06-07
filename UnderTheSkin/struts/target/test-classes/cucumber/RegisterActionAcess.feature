Feature: Underage Users cannot access the Success page
Check if the user upon inputting a valid Username and Birthdate are blocked to access the success page if their age are lesser than the legal age.

**Acceptance Criteria**
Name cannot be a empty or null field
Birthdate should reject invalid days such as 30/02/2023, 01/13/2023, etc
Birthdate accept inputs in format DD/MM/YYYY
Birthdate accept inputs in format D/M/YYYY
If the age of the user is lesser than 18 years, the user blocked to to see the success page

    Scenario Outline: Users with invalid names cannot be allowed into de success page
        Given i want to access the success page
        And today is "29/02/2004"
        When i input my name <Name>
        And i input my Birthdate "24/05/2010"
        And i click the submit button
        Then my access request will be <Result>
        And I will be shown the error message: <Error message>
        Examples:
        | Name           |  Result       | Error message                |
        | ""             |  "Denied"     | "Please enter a valid Name!" | 
    
    Scenario Outline: Users with invalid Birthdates cannot be allowed into de success page
        Given i want to access the success page
        And today is "29/02/2004"
        When i input my name "Algo"
        And i input my Birthdate <Birthdate>
        And i click the submit button
        Then my access request will be <Result>
        And I will be shown the error message: <Error message>
        Examples:
        | Birthdate        |  Result        | Error message                                         |          
        | "24/13/2010"     |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |               
        | "29/02/2010"     |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |         
        | ""               |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |          
        | "abcde"          |  "Denied"      | "Invalid Date. Date should be in format DD/MM/YYYY!"  |           

    Scenario Outline: Underaged users cannot be allowed into the success page
        Given i want to access the success page
        And today is "29/02/2004"
        When i input my name <Name>
        And i input my Birthdate <Birthdate>
        And i click the submit button
        Then my access request will be <Result>
        Then I will be shown the error message: <Error message>
        Examples:
        | Name          | Birthdate        |  Result        | Error message              |   
        | "Algo"        | "01/03/1986"     |  "Denied"      | "Minors are not allowed!"  |   
        | "Algo"        | "02/03/1986"     |  "Denied"      | "Minors are not allowed!"  |   
        | "Algo"        | "24/05/5100"     |  "Denied"      | "Minors are not allowed!"  |   




    Scenario Outline: Legal Age users are allowed into the success page
        Given i want to access the success page
        And today is "29/02/2004"
        When i input my name <Name>
        And i input my Birthdate <Birthdate>
        And i click the submit button
        Then my access request will be <Result>
        Then i must be see a page containing my <Name> and <Age>

        Examples:
        | Name             | Birthdate        |  Result        | Age         |
        | "Algo"           | "28/02/1986"     |  "Approved"    | "18 Years"  | #0 year later
        | "Algo"           | "02/03/1900"     |  "Approved"    | "103 Years" | #old before birthday 
        | "Algo"           | "01/01/1900"     |  "Approved"    | "104 Years" | #old after birthday
        
