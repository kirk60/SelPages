
# SPField

## Given I create a new SPField named "xxx" of type "class"

As expected this creates a new Field named "xxxName".

The type of the field is (css, class...), in this case class, but can be
any field type.

**NOTE** This is a bit brain F'd as the "match" pattern is also name, so
the above would create a field to match to **\<p class=xxx/>**

In The real world the names are useless EG 

"body > span:nth-child(5) > ul > liName"

But it saved me some typing (and typically caused me some confusion
later on, there may be a lesson to learn here !)

## Given I create a new SPField using Factory
"someName,tag,button,false"

Creates a field "someName" that matches any element that is tagged as a
button.

**NOTE** this is Not a required field (the final false field)

## Then validate field exists on page "somePage" and contains text "fred" within "1"

This should (perhaps) be split into 2 ... but not today.

- The file **TestConfig.resourcesDir() + "/SelPages/somePage.html"** is
  opened headless in a chrome (headless) browser.
- Validate that the previously created field (given I create ...) is on
  the displayed page.
- Validate that the field contains the text "fred"

## Then validate field does not exist on page "somePage" within "1"

as with previous this opens "somePage.html"

It then looks for the previously created field, and passes if the field
is not found.

**NOTE** This is used for NOT required field.

**NOTE** This should be followed by *validate timeTaken around "n"*, as
it starts the timer used therein. 

## Then validate field does not exist on page "somePage" and is required within "1"

as with previous this opens "somePage.html"

It then looks for the previously created field, and passes if the field
is not found and it is a required field.

**NOTE** This should be followed by *validate timeTaken around "n"*, as
it starts the timer used therein.

## Then validate timeTaken around "5"

This test should be used after the "validate field does not exist"
tests. These tests start a timer, and this test ensures that the time
between the start of the timer and now is within 300 ms of that time.