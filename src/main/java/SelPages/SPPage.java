package SelPages;



import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.util.*;

/**
 * @author Kirk Larson
 * @version 0.1
 * @since 0.1
 *
 * <h1>SPPage</h1>
 * SPPage is essentially
 * - collection of fields
 * - url
 * - default timeout for the page
 */
public class SPPage {

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    int timeout = 10;


    private Hashtable<String, SPField> fields = new Hashtable<>();

    public SPPage() {
        this.url = null;
    }

    public SPPage(String url) {
        setUrl(url);
    }


    public void addField(SPField field) {
        fields.put(field.getName(), field);
    }

    /**
     * @param driver Selenium web driver
     * @return a set of SPFields that are not in the current page, but should be
     */
    public Set<SPField> getInvalidFields(@org.jetbrains.annotations.NotNull WebDriver driver) {
        return this.getInvalidFields(driver,this.getTimeout());
    }

    /**
     * @param driver Selenium web driver
     * @param timeout maximum number of seconds
     * @return a set of SPFields that are not in the current page, but should be
     */
    public Set<SPField> getInvalidFields(@org.jetbrains.annotations.NotNull WebDriver driver,int timeout) {

        // If we get to this time then raise an error
        long endTime = System.currentTimeMillis() + timeout * 1000 ;
        // Full set of keys that have not yet passed validation
        Set<String> notYetValidatedKeys = this.fields.keySet();
        
        while( endTime <  System.currentTimeMillis() && notYetValidatedKeys.size() > 0 ){
            Set<String> justPassedValidation = new HashSet<>();

            // wait for up to 1 second, but only for the first field
            int workingTimeout = 1;
            for (String key : notYetValidatedKeys ) {
                SPField fld = this.fields.get(key);
                if(fld.validateField(driver,workingTimeout)){
                    justPassedValidation.add(key);
                }
                workingTimeout = 0;
            }
            notYetValidatedKeys.removeAll(justPassedValidation);
        }
        Set<SPField> returnValue = new HashSet<>();
        for( String key : notYetValidatedKeys){
            returnValue.add(this.fields.get(key));
        }
        return returnValue;
    }
}
