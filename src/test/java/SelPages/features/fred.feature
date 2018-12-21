Feature: SPField

  Scenario Outline: Create SPField objects
    Given I create a new SPField named "<fieldName>" of type "<fieldType>"
    Then validate field exists


    Examples:
      | fieldType | fieldName                          |
      | class     | classTest                          |
      | css       | body > span:nth-child(5) > ul > li |
      | id        | idTest                             |
      | name      | nameTest                           |
      | parttext  | parttext                           |
      | tag       | button                             |
      | text      | texttext                           |
      | xpath     | /html/body/span[8]/span            |


