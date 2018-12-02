package SelPages;

/* ******************************************************************************************************

 Copyright 2018  Kirk Larson

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is
 distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.

 ********************************************************************************************************/


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Kirk Larson
 * @version 1
 * @since 2018-12-02
 *
 * <h1>SPField</h1>
 * This is a low level imeplementation of an html "field" using Selenium
 * <h2>Warning<</h2>
 * <B>This is WIP</B>
 * <ul>
 * <li>Currently looking at using findElements all the time, it will make a few tasks easier</li>
 * <li>If an element is required, then an exception is raised if it is not there</li>
 * <li>If it is not required then I return null ... not sure how this will work in the long run</li>
 * </ul>
 * <p>
 */
@ToString
@EqualsAndHashCode
public class SPField {

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
    public SPField(String name, String searchValue, String searchType) {
        this(name, searchValue, searchType, false);
    }

    public SPField(String name, String searchValue, String searchType, Boolean required) {
        this.setName(name);
        this.setSearchValue(searchValue);
        this.setSearchType(searchType);
        this.setRequired(required);
        try {
            this.locator = SPHelpers.getLocator
                    (this.getSearchType(), this.getSearchValue());
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Field Name '" + name + "', " + e.getLocalizedMessage());
        }

    }

    //
    //
    //  implement findElement & findElements.
    //  these are pretty much the same as as the Selenium
    //  versions !
    //
    //
    public WebElement ZZfindElement(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return ZZfindElement(driver, 0);
    }

    public WebElement ZZfindElement(@org.jetbrains.annotations.NotNull WebDriver driver, Integer millisecs) {

        this.setWaitTime(driver, millisecs);

        //
        // If the element is "required" then raising an exception is appropriate,
        // otherwise return null (no element found) is appropriate
        //
        try {
            return driver.findElement(this.getLocator());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (this.required) {
                throw (e);
            }
            return null;
        }
    }


    public List<WebElement> findElements(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return findElements(driver, 0);
    }

    public List<WebElement> findElements(@org.jetbrains.annotations.NotNull WebDriver driver, Integer millisecs) {

        this.setWaitTime(driver, millisecs);

        //
        // If the element is "required" then raising an exception is appropriate,
        // otherwise return null (no element found) is appropriate
        //
        try {
            return driver.findElements(this.getLocator());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (this.required) {
                throw (e);
            }
            return null;
        }
    }

    private Object getCurrentIdentifier(){
        /**
         * for a simple element there is only 1, index = 0
         */
        return 0;
    }

    public WebElement getValue(@org.jetbrains.annotations.NotNull WebDriver driver){
        return this.getValue(driver , this.getCurrentIdentifier());
    }

    public WebElement getValue(@org.jetbrains.annotations.NotNull WebDriver driver,Object identifier){
        List<WebElement> retVal = this.findElements(driver);
        return (retVal == null) ?
                null : retVal.get((int)identifier);
    }

    public String getText(@org.jetbrains.annotations.NotNull WebDriver driver){
        return getText(driver, this.getCurrentIdentifier());
    }

    public String getText(@org.jetbrains.annotations.NotNull WebDriver driver,Object identifier){
        WebElement item = this.getValue(driver,identifier);
        return (item == null) ?
                null : item.getText();
    }

    private void setWaitTime(@org.jetbrains.annotations.NotNull WebDriver driver, Integer millisecs) {
        driver.manage().timeouts().implicitlyWait(millisecs, TimeUnit.MILLISECONDS);
    }
}
