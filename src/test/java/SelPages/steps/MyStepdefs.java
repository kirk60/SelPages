package SelPages.steps;

import SelPages.SPField;
import SelPages.testConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;


public class MyStepdefs {

    private SPField fld = null;
    private WebDriver driver = new ChromeDriver();


    private String fileUrl(String fileName) {
        return "file://" + testConfig.resourcesDir + "/SelPages" + "/" + fileName;
    }


    @Given("^I create a new SPField named \"([^\"]*)\" of type \"([^\"]*)\"$")
    public void i_create_a_new_SPField_named_of_type(String arg1, String arg2) {
        this.fld = new SPField(arg2 + "Name", arg1, arg2, false);
    }


    @Then("^validate field exists \"([^\"]*)\" and contains text \"([^\"]*)\"$")
    public void validate_field_exists_and_contains_text(String arg1, String arg2) throws Throwable {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        assertNotNull(this.fld.getElement(driver));
        assertEquals(this.fld.getText(driver), arg2);
        driver.close();
    }


}
