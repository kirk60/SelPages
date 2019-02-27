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

import static org.hamcrest.Matchers.closeTo;

import java.util.List;

import static org.junit.Assert.*;


public class SPFieldSteps {

    private SPField fld = null;
    private int startTime;
    private WebDriver driver = null;

    @Before
    public void beforeScenario() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        this.driver = new ChromeDriver(chromeOptions);
    }
    private String fileUrl(String fileName) {
        return "file://" + TestConfig.resourcesDir + "/SelPages" + "/" + fileName;
    }


    @Given("^I create a new SPField named \"([^\"]*)\" of type \"([^\"]*)\"$")
    public void i_create_a_new_SPField_named_of_type(String arg1, String arg2) {
        this.fld = new SPField(arg2 + "Name", arg1, arg2, false);
    }

    @After
    public void afterScenario() {
        this.driver.close();
    }

    @Given("^I create a new SPField using Factory \"([^\"]*)\"$")
    public void i_create_a_new_SPField_using_FactoIsCloseTory(String arg1) {
        this.fld = SPFieldFactory.getInstance().newField(arg1);
    }

    @Then("^validate field exists on page \"([^\"]*)\" and contains text \"([^\"]*)\" within \"([^\"]*)\"$")
    public void validate_field_exists_on_page_and_contains_text_within(String arg1, String arg2, String arg3) {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        int timeout = 1000 * Integer.parseInt(arg3);
        this.startTimer();
        assertNotNull(this.fld.findElements(driver, timeout));
        assertNotNull(this.fld.getElement(driver));
        assertEquals(this.fld.getText(driver), arg2);

    }

    @Then("^validate field does not exist on page \"([^\"]*)\" within \"([^\"]*)\"$")
    public void validate_field_does_not_exist_on_page_within(String arg1, String arg2) {
        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        int timeout = 1000 * Integer.parseInt(arg2);
        this.startTimer();
        List<WebElement> x = this.fld.findElements(driver, timeout);
        assertEquals(0, x.size());
        assertNull(this.fld.getElement(driver));
    }

    @Then("^validate field does not exist on page \"([^\"]*)\" and is required within \"([^\"]*)\"$")
    public void validate_field_does_not_exist_on_page_and_is_required_within(String arg1, String arg2) {

        String currentUrl = this.fileUrl(arg1 + ".html");
        driver.get(currentUrl);
        int timeout = 1000 * Integer.parseInt(arg2);
        try {
            this.startTimer();
            this.fld.findElements(driver, timeout).size();
            fail("Expected findElements to fail");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            String s1 = e.getMessage();
            String s2 = this.fld.toString();
            assertTrue(s1.startsWith(s2));
        }

    }

    @Then("^validate timeTaken around \"([^\"]*)\"$")
    public void validate_timeTaken(String arg1) {
        double timeout = 1000 * Double.parseDouble(arg1);
        assertThat(timeout, closeTo((double) this.elapsedTime(), 300));
    }

    private void startTimer() {
        this.startTime = (int) System.currentTimeMillis();
    }

    private int elapsedTime() {
        return (int) System.currentTimeMillis() - this.startTime;
    }

}
