package SelPages;


import org.junit.Test;
import SelPages.TestConfig;


import java.io.File;

public class TestSPPageFactory {

    @Test
    public void createNewPage() {
        String page = new File(TestConfig.resourcesDir, "page1.toml").toString();
        try {
            SPPage workingPage = SPPageFactory.getInstance().newPageFromFile(page, TestConfig.resourcesDir + "/SelPages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}