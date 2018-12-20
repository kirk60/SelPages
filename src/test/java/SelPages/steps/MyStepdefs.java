package SelPages.steps;

import SelPages.SPField;
import SelPages.testConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {

    private SPField fld = null;

    private String fileUrl(String fileName) {
        return "file://" + testConfig.resourcesDir + "/SelPages" + "/" + fileName;
    }


    @Given("^I create a new SPField named \"([^\"]*)\" of type \"([^\"]*)\"$")
    public void i_create_a_new_SPField_named_of_type(String arg1, String arg2) {
        this.fld = new SPField(arg2 + "Name", arg1, arg2, false);
    }

    @Then("^falidate field exists$")
    public void falidate_field_exists() {
        WebDriver driver = new ChromeDriver();
        String resourceHome = testConfig.resourcesDir + "/SelPages";
        driver.get(this.fileUrl("testSPfield1.html"));
        driver.close();

    }


}
