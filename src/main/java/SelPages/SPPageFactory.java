package SelPages;

import com.moandjiezana.toml.Toml;
import org.jf.dexlib2.util.DexUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


/**
 * @author Kirk Larson
 * @version 0.0.2 (Pre prototype)
 * @since 2019-02-24
 */
public class SPPageFactory {
    private static SPPageFactory ourInstance = new SPPageFactory();

    private SPPageFactory() {
    }

    public static SPPageFactory getInstance() {
        return ourInstance;
    }

    public Map<String, Object> detailsFromTOML(File myFile) {
        return new Toml().read(myFile).toMap();
    }

    public SPPage newPageFromFile(String fileName, String baseUrl) throws Exception {

        File myFile = new File(fileName);
        Map<String, Object> details;

        boolean a = myFile.isFile();

        if (!myFile.isFile()) {
            throw new FileNotFoundException(fileName);
        }

        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (extension.toLowerCase()) {
            case "toml":
                details = this.detailsFromTOML(myFile);
                break;
            default:
                throw new Exception(String.format("invalid file '%s'", fileName));
        }

        Map<String, Object> pageData = (Map<String, Object>) details.get("page");
        List<String> includes = (List<String>) pageData.get("includes");

        SPPage newPage = new SPPage(baseUrl + "/" + pageData.get("url"));

        return newPage;
    }

}
