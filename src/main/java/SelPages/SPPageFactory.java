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

    private static Set alreadyVisited;

    static {
        alreadyVisited = new HashSet<String>();
    }

    private SPPageFactory() {
    }

    public static SPPageFactory getInstance() {
        return ourInstance;
    }


    /**
     * @param fileName name of file to read
     * @param baseUrl
     * @return
     * @throws Exception For the file extension perform the appropriate "readXXXX" method
     *                   this returns a map object
     */
    public SPPage newPageFromFile(String fileName, String baseUrl) throws Exception {

        SPPage newPage = new SPPage();
        addFileDetailsToPage(newPage, getFileAsMap(fileName), baseUrl);

        if (newPage.getUrl() == null) {
            throw (new Exception("URL not specified for page " + fileName));
        }

        return newPage;
    }

    /**
     * Add details to the supplied page
     *
     * @param newPage   pageObject
     * @param fileAsMap the details to add, this is a map
     *                  details
     *                  - page
     *                  -- url : the url of the page (prepended with baseUrl)
     *                  -- includes[] : list of file names to include
     *                  - fields
     * @param baseUrl
     */
    private void addFileDetailsToPage(SPPage newPage, Map<String, Object> fileAsMap, String baseUrl) throws Exception {

        Map<String, Object> pageData = (Map<String, Object>) fileAsMap.get("page");
        Map<String, String> fieldData = (Map<String, String>) fileAsMap.get("fields");

        //
        //  NOTE : process all the includes first, this means that
        // anything in the current file will over-ride anything that is included
        //
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

        newPage.setUrl(baseUrl + pageData.get("url"));
    }

    /**
     * add fieldName, if it already exists, blow away the old one !
     *
     * @param newPage    page to add the field
     * @param fieldName
     * @param fieldValue
     */
    private void addField(SPPage newPage, String fieldName, String fieldValue) {
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


}
