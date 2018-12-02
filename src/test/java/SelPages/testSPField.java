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


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Kirk Larson
 * @version 1
 * @since 2018-12-02
 *
 * <h1>testSPField</h1>
 * Test class for SPField
 */

public class testSPField {


    private WebDriver driver;
    private String resourceHome;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        resourceHome = testConfig.resourcesDir + "/SelPages";
        driver.get(this.fileUrl("testSPfield1.html"));
    }

    private String fileUrl(String fileName) {
        return "file://" + this.resourceHome + "/" + fileName;
    }

    private Object [] [] testData = {
            {SPHelpers.ID,"idTest"},
            {SPHelpers.CLASS,"classTest"},
            {SPHelpers.CSS,"body > span:nth-child(5) > ul > li"},
            {SPHelpers.NAME,"nameTest"},

    } ;

    private void runTest(Object [] workingData){
        SPField fld = new SPField((String)workingData[0] +"Name", (String)workingData[1], (String)workingData[0], false);
        List<WebElement> e1 = fld.findElements(driver);
        assertNotNull(e1);
        assertEquals(fld.getText(driver), workingData[0] + "Text");
        driver.close();
    }

    @Test
    public void getFieldById() {
        runTest(testData[0]);
    }

    @Test
    public void getFieldByClass() {
        runTest(testData[1]);
    }

    @Test
    public void getFieldByCss() {
        runTest(testData[2]);
    }
    @Test
    public void getFieldByName() {
        runTest(testData[3]);
    }
}