/* ******************************************************************************************************

 Copyright 2018-2019  Kirk Larson

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under the License is
 distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.

 ********************************************************************************************************/

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
            workingPage = SPPageFactory.getInstance().newPageFromFile("dummy",page, TestConfig.resourcesDir() + "/SelPages");
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
            workingPage = SPPageFactory.getInstance().newPageFromFile("dummy",page, TestConfig.resourcesDir() + "/SelPages");
        } catch (Exception e) {
            assert ("un-recognised field type : cssxx for field cssTest".equals(e.getLocalizedMessage()) );
            thrown = true;
        }
        assert (thrown);
    }

}