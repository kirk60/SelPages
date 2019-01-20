Feature: SPField

  Scenario Outline: Valid Field of type <fieldType>
    Given I create a new SPField named "<fieldName>" of type "<fieldType>"
    Then validate field exists on page "testSPfield1" and contains text "<textValue>" within "<timeout>"
    Then validate timeTaken around "0"


    Examples:
      | fieldType | fieldName                          | textValue    | timeout |
      | class     | classTest                          | classText    | 0       |
      | css       | body > span:nth-child(5) > ul > li | cssText      | 0       |
      | id        | idTest                             | idText       | 0       |
      | name      | nameTest                           | nameText     | 0       |
      | parttext  | parttext                           | parttextText | 0       |
      | tag       | button                             | tagText      | 0       |
      | text      | textText                           | textText     | 0       |
      | xpath     | /html/body/span[8]/span            | xpathText    | 0       |
      | class     | classTest                          | classText    | 1       |
      | css       | body > span:nth-child(5) > ul > li | cssText      | 1       |
      | id        | idTest                             | idText       | 1       |
      | name      | nameTest                           | nameText     | 1       |
      | parttext  | parttext                           | parttextText | 1       |
      | tag       | button                             | tagText      | 1       |
      | text      | textText                           | textText     | 1       |
      | xpath     | /html/body/span[8]/span            | xpathText    | 1       |

  Scenario Outline: Invalid Field of type <fieldType>
    Given I create a new SPField named "<fieldName>" of type "<fieldType>"
    Then validate field does not exist on page "testSPfield1" within "<timeout>"
    Then validate timeTaken around "<timeout>"

    Examples:
      | fieldType | fieldName                           | timeout |
      | class     | XclassTest                          | 0       |
      | css       | body > span:nth-child(5) > ul > lia | 0       |
      | id        | XidTest                             | 0       |
      | name      | XnameTest                           | 0       |
      | parttext  | Xparttext                           | 0       |
      | tag       | Xbutton                             | 0       |
      | text      | XtextText                           | 0       |
      | xpath     | /html/body/span[8]/Xspan            | 0       |
      | class     | XclassTest                          | 1       |
      | css       | body > span:nth-child(5) > ul > lia | 1       |
      | id        | XidTest                             | 1       |
      | name      | XnameTest                           | 1       |
      | parttext  | Xparttext                           | 1       |
      | tag       | Xbutton                             | 1       |
      | text      | XtextText                           | 1       |
      | xpath     | /html/body/span[8]/Xspan            | 1       |



