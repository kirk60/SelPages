/* ******************************************************************************************************

 Copyright 2018-2019  Kirk Larson

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is
 distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.

 ********************************************************************************************************/

package SelPages;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Kirk Larson
 * @version 0.1
 * @since 0.1
 *
 * <h1>SPField</h1>
 * This is a low level implementation of an html "field" using Selenium
 * SPField is the simplest possible implementation of using a field,
 * While it uses selenium getElements, it only allows access to the 0th element,
 * SPField should only be used if there is only 1 element.
 * <p>
 * HOWEVER : as the "simplest" case it provides a simple framework
 * - to extend
 * - to test
 * - to develop from
 * <p>
 * I intentionally do not use the selenium getElement, as using it here would make
 * this difficult to extend
 */
@ToString
@EqualsAndHashCode
public class SPField {


    @Setter
    @Getter
    public Object currentIdentifier;
    @Setter
    @Getter
    private String name = null;
    @Setter
    @Getter
    private Boolean required = null;
    @Setter
    @Getter
    private String searchValue = null;
    @Setter
    @Getter
    private String searchType = null;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter
    private By locator;

    @Setter
    @Getter
    private Integer maxElementCount;

    @Setter
    @Getter
    private String matchText;

    /**
     * Minimum constructor for a field is
     * - How do i find myself ( searchValue / searchType)
     * - is this field required.
     * @param name        Field Name
     * @param searchValue search selector text
     * @param searchType  search selector type
     * @param required    is this field required or not
     */
    public SPField(String name, String searchValue, String searchType, Boolean required) {
        this.setMatchText(null);
        this.setName(name);
        this.setSearchValue(searchValue);
        this.setSearchType(searchType);
        this.setRequired(required);
        this.setCurrentIdentifier(0);
        this.maxElementCount = 1;
        try {
            this.locator = SPFieldHelpers.getLocator
                    (this.getSearchType(), this.getSearchValue());
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Field Name '" + name + "', " + e.getLocalizedMessage());
        }

    }


    /**
     * @param driver selenium driver
     * @return a list of matches (WebElements) within the current page
     */
    public List<WebElement> findElements(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return findElements(driver, null);
    }

    /**
     * @param driver selenium driver
     * @param timout timeout in timout
     * @return a list of matches (WebElements) within the current page
     */
    public List<WebElement> findElements(@org.jetbrains.annotations.NotNull WebDriver driver, Integer timout) {

        if (timout != null) {
            this.setWaitTime(driver, timout);
        }

        //
        // If the element is "required" then raising an exception is appropriate,
        // otherwise return null (no element found) is appropriate
        //
        List<WebElement> retVal = driver.findElements(this.getLocator());
        //
        // reset the wait time !
        //
        this.setWaitTime(driver, 0);
        if (retVal.size() == 0 && this.required) {
            throw new org.openqa.selenium.NoSuchElementException(this.toString());
        }

        if (this.maxElementCount != null && retVal.size() > this.maxElementCount) {
            throw new org.openqa.selenium.InvalidSelectorException(String.format(
                    "%s : only 1 element allowed, matched %d", this.getName(), retVal.size()));
        }

        if(this.getMatchText() == null){
            return retVal;
        }

        //
        //  filter out records that dont match
        //
        List<WebElement>  tmp = new ArrayList<>();
        for( WebElement e : retVal){
            if(e.getText().contains(this.getMatchText())){
                tmp.add(e);
            }
        }

        return tmp;

    }

    /**
     * @param driver selenuim WebDrivernull
     * @return individnxual web Element, based on the current identifier
     */
    public WebElement getElement(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return this.getElement(driver, this.getCurrentIdentifier());
    }


    /**
     * Return the requested (single) Web Element
     * <p>
     * NOTE : simplest possible implementation
     *
     * @param driver     selenuim WebDriver
     * @param identifier the object that is used to uniquely identify the required object
     * @return individual web Element
     */
    public WebElement getElement(@org.jetbrains.annotations.NotNull WebDriver driver, Object identifier) {
        List<WebElement> retVal = this.findElements(driver);
        return (retVal.size() == 0) ?
                null : retVal.get((int) identifier);
    }

    /**
     * @param driver selenuim WebDriver
     * @return The text value of the currently selected instance of this element
     */
    public String getText(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return getText(driver, this.getCurrentIdentifier());
    }

    /**
     * @param driver     selenuim WebDriver
     * @param identifier unique identifier
     * @return The text value of the instance of the element matching the identifier
     * NOTE : the simples case is SPField, where this is ALWAYS the 0'th element 'cos
     * SPField assumes the field wiil have only a single matching element
     */
    public String getText(@org.jetbrains.annotations.NotNull WebDriver driver, Object identifier) {
        WebElement item = this.getElement(driver, identifier);
        return (item == null) ?
                null : item.getText();
    }

    public void setWaitTime(@org.jetbrains.annotations.NotNull WebDriver driver, Integer timout) {
        driver.manage().timeouts().implicitlyWait(timout, TimeUnit.MILLISECONDS);
    }


    /**
     * @param driver  selenium web driver for this page
     * @param timeout timeout in seconds
     * @return true if the field is required & it exists or if the field is not required
     */
    public boolean validateField(@org.jetbrains.annotations.NotNull WebDriver driver, Integer timeout) {
        if (!this.getRequired()) {
            return true;
        }
        try {
            return this.findElements(driver, timeout).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}


