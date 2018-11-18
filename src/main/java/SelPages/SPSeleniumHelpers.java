package SelPages;


import org.openqa.selenium.By;

public class SPSeleniumHelpers {

    static final public String CLASS = "class";
    static final public String CSS = "css";
    static final public String ID = "id";
    static final public String NAME = "name";
    static final public String PART_TEXT = "partText";
    static final public String TAG = "tag";
    static final public String TEXT = "text";
    static final public String XPATH = "xpath";

    static By getLocator(String type , String match){

        switch (type.toLowerCase()){
            case SPSeleniumHelpers.CLASS :
                return By.className(match);
            case SPSeleniumHelpers.CSS :
                return By.cssSelector(match);
            case SPSeleniumHelpers.ID :
                return By.id(match);
            case SPSeleniumHelpers.NAME :
                return By.name(match);
            case SPSeleniumHelpers.PART_TEXT :
                return By.partialLinkText(match);
            case SPSeleniumHelpers.TAG :
                return By.tagName(match);
            case SPSeleniumHelpers.TEXT :
                return By.linkText(match);
            case SPSeleniumHelpers.XPATH :
                return By.xpath(match);
        }
        throw new IllegalArgumentException( "requested search locator type '" + type + "' is not known");

    }
}
