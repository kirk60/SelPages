package SelPages;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirk Larson
 * @version 0.0.2 (Pre prototype)
 * @since 2018-12-02
 *
 * <h1>SPFieldFactory</h1>
 * This is a low level implementation of an html "field" using Selenium
 * <h2>Warning V 0.2 : Still "playing"</h2>
 * <B>This is WIP</B>
 * <ul>
 * <li>Currently using findElements all the time, it will make a few tasks easier</li>
 * <li>If an element is required, then an exception is raised if it is not there</li>
 * <li>If it is not required then I return null ... not sure how this will work in the long run</li>
 * </ul>
 * <p>
 */
public class SPFieldFactory {
    private static SPFieldFactory ourInstance = new SPFieldFactory();

    private SPFieldFactory() {
    }

    public static SPFieldFactory getInstance() {
        return ourInstance;
    }

    /**
     *
     * build a structure that can be easily used by SPPageFactory, to add 1 (or more) fields to
     *
     * @param request this is the request string in the format "name,type,*details*" see "Details" below
     * @return a map of <String, SPField>, this matches the format required in SPPageFactory to add fields
     * @throws Exception basic exception with error message ..
     *
     * Details
     * The details text depends on it's type
     * - if type is defined in SPFieldHelpers.simpleTypes then
     *      this is a simple field, call SPField
     *
     */
    public List<SPField> newFields(String request) throws Exception {
        String[] fields = request.split(",", 2);
        return newFields(fields[0], fields[1]);
    }

    public List<SPField> newFields(String name, String request) throws Exception {
        String[] fields = request.split(",", 2);

        return newFields(name, fields[0], fields[1]);

    }

    public List<SPField> newFields(String name, String type, String request) throws Exception {
        List<SPField> retVal = new ArrayList<>();
        SPField workingField;

        String[] fields = request.split(",");

        if (SPFieldHelpers.simpleTypes.contains(type)) {
            if (fields.length == 0) {
                throw new Exception("request format for " + type + " must have at lease a matchText field");
            }

            boolean required = false;

            if (fields.length == 2) {
                required = fields[1].equalsIgnoreCase("true");
            }
            workingField = new SPField(name, fields[0], type, required);

            retVal.add(workingField);

        }

        if (retVal.size() == 0) {
            throw new Exception("un-recognised field type : " + type + " for field " + name);
        }

        return retVal;
    }
}
