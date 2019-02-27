package SelPages;

/* *******************************************************************************************************

 Copyright 2018  Kirk Larson

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is
 distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.

 ********************************************************************************************************/

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kirk Larson
 * @version 1
 * @since 2018-12-02
 *
 * <h1>SPHelpers</h1>
 * Definitions and helper functions
 */

public class SPFieldHelpers {

    /**
     * All the base selenium selectors mapto one of these
     */
    static final public String CLASS = "class";
    static final public String ID = "id";
    static final public String CSS = "css";

    static final public String NAME = "name";
    static final public String PART_TEXT = "parttext";
    static final public String TAG = "tag";
    static final public String TEXT = "text";
    static final public String XPATH = "xpath";

    static final List<String> simpleTypes = Arrays.asList(
            CLASS, ID, CSS, NAME, PART_TEXT, TAG, TEXT, XPATH
    );

    /**
     * @param type The field type (EG CLASS ...)
     * @return true if the field type is valid for an SPField
     */
    static boolean simpleFieldType(String type) {

        return simpleTypes.contains(type.toLowerCase());
    }

    /**
     * @param type  Field matching to use (eg CLASS .. )
     * @param match string to match eg if css then something like "body > span:nth-child(5) > ul > lia"
     * @return a selenuim "By" which, to quote the docco
     * "Provides a mechanism by which to find elements within a document."
     */
    static By getLocator(String type, String match) {

        switch (type.toLowerCase()) {
            case SPFieldHelpers.CLASS:
                return By.className(match);
            case SPFieldHelpers.CSS:
                return By.cssSelector(match);
            case SPFieldHelpers.ID:
                return By.id(match);
            case SPFieldHelpers.NAME:
                return By.name(match);
            case SPFieldHelpers.PART_TEXT:
                return By.partialLinkText(match);
            case SPFieldHelpers.TAG:
                return By.tagName(match);
            case SPFieldHelpers.TEXT:
                return By.linkText(match);
            case SPFieldHelpers.XPATH:
                return By.xpath(match);
        }
        throw new IllegalArgumentException("requested search locator type '" + type + "' is not known");

    }
}
