Feature: Is user allowed?
Check if the user is allowed to proceed

    Scenario Outline: user is (Dis)allowed in the specific dates conting from today
        Given the name "<input_name>" and days interval <input_days> 
        When we want to abstract the user date input and check if the day intervals provide valid reply
        Then user allowed value is "<answer>"

        Examples:
        | input_name     | input_days     |  answer |
        | Algo           | 36525          |  True   | #100 years
        | Sunday         | 6574           |  True   | #18 years
        | John           | 7670           |  True   | #21 years
        | Algo           | 5478           |  False  | #15 years
        | Algo           | -1             |  False  |
        | Algo           | 30             |  False  | #0 years
        |                | 36525          |  False  | 
        | Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  | 36526          |  True  | #100 years