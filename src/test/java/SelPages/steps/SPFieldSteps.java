package SelPages.steps;

import SelPages.SPField;
import SelPages.SPFieldFactory;
import SelPages.testConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;


public class SPFieldSteps {

    private SPField fld = null;
    private WebDriver driver = new ChromeDriver();


    private String fileUrl(String fileName) {
        return "file://" + testConfig.resourcesDir + "/SelPages" + "/" + fileName;
    }


    @Given("^I create a new SPField named \"([^\"]*)\" of type \"([^\"]*)\"$")
    public void i_create_a_new_SPField_named_of_type(String arg1, String arg2) {
        this.fld = new SPField(arg2 + "Name", arg1, arg2, false);
    }

    @Given("^I create a new SPField using Factory \"([^\"]*)\"$")
    public void i_create_a_new_SPField_using_Factory(String arg1) throws Throwable {
        this.fld = SPFieldFactory.getInstance().newField(arg1);
    }


    @Then("^validate field exists \"([^\"]*)\" and contains text \"([^\"]*)\"$")
    public void validate_field_exists_and_contains_text(String arg1, String arg2) throws Throwable {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        assertNotNull(this.fld.findElements(driver));
        assertNotNull(this.fld.getElement(driver));
        assertEquals(this.fld.getText(driver), arg2);
        driver.close();
    }

    @Then("^validate field does not exist \"([^\"]*)\"$")
    public void validate_field_does_not_exist(String arg1) throws Throwable {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        assert (this.fld.findElements(driver).size() == 0);
        assertNull(this.fld.getElement(driver));
        driver.close();
    }


    @Then("^validate field does not exist \"([^\"]*)\" and is required$")
    public void validate_field_does_not_exist_and_is_required(String arg1) throws Throwable {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        try {
            this.fld.findElements(driver).size();
            driver.close();
            assert (false);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            String s1 = e.getMessage();
            String s2 = this.fld.toString();
            driver.close();
            assert (s1.startsWith(s2));
        }

    }


}
