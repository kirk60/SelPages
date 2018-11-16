package SelPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // declaration and instantiation of objects/variables
        //System.setProperty("webdriver.firefox.marionette","/usr/local/bin/geckodriver");
        //WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
        //System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        WebDriver driver = new HtmlUnitDriver();

        String baseUrl = "http://google.com";
        driver.get(baseUrl);
        driver.close();

    }
}
