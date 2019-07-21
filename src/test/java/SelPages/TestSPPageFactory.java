package SelPages;


import org.junit.Test;


import java.io.File;

public class TestSPPageFactory {

    @Test
    public void createGoodPage() {
        boolean thrown = false;
        String page = new File(TestConfig.resourcesDir(), "page_good.toml").toString();
        SPPage workingPage = null;
        try {
            workingPage = SPPageFactory.getInstance().newPageFromFile(page, TestConfig.resourcesDir() + "/SelPages");
        } catch (Exception e) {
            e.printStackTrace();
            thrown = true;
        }
        assert ( !thrown );
    }
    @Test
    public void createGoodBad() {
        boolean thrown = false;
        String page = new File(TestConfig.resourcesDir(), "page_bad.toml").toString();
        SPPage workingPage = null;
        try {
            workingPage = SPPageFactory.getInstance().newPageFromFile(page, TestConfig.resourcesDir() + "/SelPages");
        } catch (Exception e) {
            assert ("un-recognised field type : cssxx for field cssTest".equals(e.getLocalizedMessage()) );
            thrown = true;
        }
        assert (thrown);
    }

}