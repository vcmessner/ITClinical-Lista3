Feature: Is user allowed?
Check if the user input are valid, the resulting allowed status and the user age. User age Should be "" if no valid data input is found.

    Scenario Outline: user inputs are valid
        Given user submits name "<inputName>" and date "<inputDate>"
        When i ask whether user the input is valid
        Then input is valid = "<isValid>"

        Examples:
        | inputName      | inputDate      |  isValid |
        | Algo           | 24/05/2000     |  success |
        | Sunday         | 24/05/2010     |  input   |
        | John           | 25/05/2005     |  success |
        | Algo           | 24/13/2010     |  input   |
        | Algo           | 29/02/2010     |  input   |
        | Algo           | 24/05/2023     |  input   |
        |                | 24/05/2010     |  input   |