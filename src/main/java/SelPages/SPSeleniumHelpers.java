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

/**
 * @author Kirk Larson
 * @version 1
 * @since 2018-12-02
 *
 * <h1>SPSeleniumHelpers</h1>
 * Definitions and helper functions
 */

public class SPSeleniumHelpers {

    static final public String CLASS = "class";
    static final public String CSS = "css";
    static final public String ID = "id";
    static final public String NAME = "name";
    static final public String PART_TEXT = "partText";
    static final public String TAG = "tag";
    static final public String TEXT = "text";
    static final public String XPATH = "xpath";

    static By getLocator(String type, String match) {

        switch (type.toLowerCase()) {
            case SPSeleniumHelpers.CLASS:
                return By.className(match);
            case SPSeleniumHelpers.CSS:
                return By.cssSelector(match);
            case SPSeleniumHelpers.ID:
                return By.id(match);
            case SPSeleniumHelpers.NAME:
                return By.name(match);
            case SPSeleniumHelpers.PART_TEXT:
                return By.partialLinkText(match);
            case SPSeleniumHelpers.TAG:
                return By.tagName(match);
            case SPSeleniumHelpers.TEXT:
                return By.linkText(match);
            case SPSeleniumHelpers.XPATH:
                return By.xpath(match);
        }
        throw new IllegalArgumentException("requested search locator type '" + type + "' is not known");

    }
}
