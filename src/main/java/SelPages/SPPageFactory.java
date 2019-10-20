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

import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Kirk Larson
 * @version 0.0.2 (Pre prototype)
 * @since 2019-02-24
 */
public class SPPageFactory {

    private static SPPageFactory ourInstance = new SPPageFactory();

    private static Set<String> alreadyVisited;
    private static SPFieldFactory myFieldFactory ;

    static {
        alreadyVisited = new HashSet<String>();
        myFieldFactory = SPFieldFactory.getInstance();
    }

    private SPPageFactory() {
    }

    public static SPPageFactory getInstance() {
        return ourInstance;
    }


    /**
     * @param fileName name of file to read
     * @param baseUrl this is prepended to the the value of url in the definition file
     * @return a page object representing the defined page.
     * @throws Exception For the file extension perform the appropriate "readXXXX" method
     *                   this returns a map object
     */
    public SPPage newPageFromFile(String pageName , String fileName, String baseUrl) throws Exception {

        SPPage newPage = new SPPage(pageName);
        addFileDetailsToPage(newPage, getFileAsMap(fileName), baseUrl);

        if (newPage.getUrl() == null) {
            throw (new Exception("URL not specified for page " + fileName));
        }

        return newPage;
    }

    /**
     * Add details to the supplied page
     * - first process any included files
     * - then add all fields
     * - then set the url
     *
     * If a field is set in an included file, and it is defined in this file, it will be replaced.
     * If the URL has been set in an included file it will reset here.
     *
     * @param newPage   pageObject
     * @param fileAsMap the details to add, this is a map
     *                  details
     *                  - page
     *                  -- url : the url of the page (prepended with baseUrl)
     *                  -- includes[] : list of file names to include
     *                  - fields
     * @param baseUrl prepend this to the supplied url. append "/" to the string if needed
     *
     */
    private void addFileDetailsToPage(SPPage newPage, Map<String, Object> fileAsMap, String baseUrl) throws Exception {

        Map<String, Object> pageData = (Map<String, Object>) fileAsMap.get("page");
        Map<String, String> fieldData = (Map<String, String>) fileAsMap.get("fields");

        if (baseUrl.length() > 0 && (!baseUrl.endsWith("/"))) {
            baseUrl = baseUrl + "/";
        }

        List<String> includeList = (List<String>) pageData.get("includes");
        for (String includeFile : includeList) {
            if (!alreadyVisited.contains(includeFile)) {
                alreadyVisited.add(includeFile);
                addFileDetailsToPage(newPage, getFileAsMap(includeFile), baseUrl);
            }
        }

        for (String fieldName : fieldData.keySet()) {
            addField(newPage, fieldName, fieldData.get(fieldName));
        }

        String url = baseUrl + pageData.get("url");
        newPage.setUrl(url.replace("//", "/"));
    }

    /**
     * add fieldName, if it already exists, blow away the old one !
     *
     * @param newPage page to add the field
     * @param fieldName suppliend name of field
     * @param fieldValue text string releating to the specifics of the field
     */
    private void addField(SPPage newPage, String fieldName, String fieldValue) throws Exception {
        List<SPField> fields = myFieldFactory.newFields(fieldName, fieldValue);
        for (SPField field : fields) {
            newPage.addField(field);
        }
    }

    /**
     * @param fileName file containing details
     * @return dataMap including pageData + includes, see newPageFromFile for details
     * @throws Exception read the specified file into a dataMap.
     *                   Deal with different file formats (typically using the extension)
     */
    private Map<String, Object> getFileAsMap(String fileName) throws Exception {
        File myFile = new File(fileName);
        Map<String, Object> details;

        if (!myFile.isFile()) {
            throw new FileNotFoundException(fileName);
        }

        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (extension.toLowerCase()) {
            case "toml":
                details = this.readTOML(myFile);
                break;
            default:
                throw new Exception(String.format("invalid file '%s'", fileName));
        }
        return details;
    }

    /**
     * @param myFile name of TOML file
     * @return "details"
     */
    public Map<String, Object> readTOML(File myFile) {
        return new Toml().read(myFile).toMap();
    }


    /**
     * @param myFieldFactory instance of fieldFactory to use ...
     *                       must extend SPFieldFactory
     */
    public void setFieldFactory( SPFieldFactory myFieldFactory ){
        this.myFieldFactory = myFieldFactory;
    }
}
