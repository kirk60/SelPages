# Some design decisions

## SPField

The intent is that SPField will be extended into classes that basically replace the getElement
method call with calls that are specific to their "collection" or "specific" type, IE select List, radio buttons ...

Selecting the correct element would be used by calling setCurrentIdentifier, or simply passing in the identifier to
getElement.  


Lowest level internal call is getElements, which has 2 interfaces

1. *findElements( WebDriver driver)*
        1. this calls the version below with milisecs set to null
1. *findElements( WebDriver driver, Integer millisecs)*
        1. if milisecs is not null, then set the timeout for selenium findElements to milisecs.
        1. call selenium findElements (payload)
        1. set the timeout for selenium findElements to 0
1. **NOTE** Selenium supports *findElement* & *findElements*, however for the sake of simplicity
    I only use findElements. Where the actual field only has 1 element (as is the base SPField class), then then
    internal method getCurrentIdentifier, will return an array index of 0.
  
getElement returns the current "selected" element (whatever that means)
1. if this were a select list, and you wanted the 3rd element then the call might be
    1. *getElement( driver, 3 )* 
        1. assuming you used class SPSelectListByID.. which extended SPField and supported the index
1. if this were a select list, and you wanted the element with value "Boris"
    1. *getElement( driver, "Boris" )* 
        1. assuming you used class SPSelectListByID.. which extended SPField and supported the index
                
### NOTE about "required"

1. If a field is required and not found then we raise a NoSuchElementException exception.
1. If a field is NOT required and not found then we simplyreturn an empty set, or null.

### NOTE about "timeout"

Timeout resets to 0 after each call to getElement 
1. if the item is there then makes no difference.
1. if not there, then another call will not wait around for element (unless asked to). 

If you want a timeout on the field then either call *findElements()* with the timeout OR call *setWaitTime()*.

My current thoughts are that the timeout would probably only ever be used when an entire page is being validated.

EG

    public boolean validate_page()
        setEndTime( now() + 10 ) // allow 10 seconds to validate page
        result = true
    
        for x in AllFieldsOnPage
            try
                x.findELement(driver , getEndTime() - now() )   // allow however many seconds left
            catch NoSuchElementException:
                result = false
                print ( required field x is missing )
        return result
  

1. I did not want to have a default timeout
1. I don't want multiple versions of htesame mothod passing timout all over the place !
1. I'm prepared to change my mind :-)

 


