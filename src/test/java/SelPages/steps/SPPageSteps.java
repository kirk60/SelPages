/* ******************************************************************************************************

 Copyright 2018-2019  Kirk Larson

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is
 distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.

 ********************************************************************************************************/



package SelPages.steps;

import SelPages.SPField;
import SelPages.SPFieldFactory;
import SelPages.TestConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;


public class SPPageSteps {

    private WebDriver driver = null;

    @Before
    public void beforeScenario() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        this.driver = new ChromeDriver(chromeOptions);
    }
    private String fileUrl(String fileName) {
        return "file://" + TestConfig.resourcesDir() + "/SelPages" + "/" + fileName;
    }

    @After
    public void afterScenario() {
        this.driver.close();
    }


}
