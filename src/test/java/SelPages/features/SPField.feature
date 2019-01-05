Feature: SPField

  Scenario Outline: Valid Field of type <fieldType>
    Given I create a new SPField named "<fieldName>" of type "<fieldType>"
    Then validate field exists "testSPfield1" and contains text "<textValue>"


    Examples:
      | fieldType | fieldName                          | textValue    |
      | class     | classTest                          | classText    |
      | css       | body > span:nth-child(5) > ul > li | cssText      |
      | id        | idTest                             | idText       |
      | name      | nameTest                           | nameText     |
      | parttext  | parttext                           | parttextText |
      | tag       | button                             | tagText      |
      | text      | textText                           | textText     |
      | xpath     | /html/body/span[8]/span            | xpathText    |

  Scenario Outline: Invalid Field of type <fieldType>
    Given I create a new SPField named "<fieldName>" of type "<fieldType>"
    Then validate field does not exist "testSPfield1" and contains text "<textValue>"

    Examples:
      | fieldType | fieldName                           | textValue    |
      | class     | XclassTest                          | classText    |
      | css       | body > span:nth-child(5) > ul > lia | cssText      |
      | id        | XidTest                             | idText       |
      | name      | XnameTest                           | nameText     |
      | parttext  | Xparttext                           | parttextText |
      | tag       | Xbutton                             | tagText      |
      | text      | XtextText                           | textText     |
      | xpath     | /html/body/span[8]/Xspan            | xpathText    |



