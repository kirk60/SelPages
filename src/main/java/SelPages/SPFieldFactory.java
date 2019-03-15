package SelPages;

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
     * @param request this is the request string in the format "name,type,matchText,required"
     * @return an SPField
     * <p>
     * request params
     * name : name of the field
     * type : field type (class .... )
     * matchText : for the specified field type the text to match
     * --EG if type=css then matchText = "body > span:nth-child(5) > ul > lia"
     * required : true or false .. if true then raise an exception if the field is not found
     *
     * TODO ... unless thisgets more complex then blow it away !
     * i think that this will end up having some sort of a switch based on the "type",
     * and that multiple fields could result from specific types.
     *
     * EG
     * type = inputField, where there is a description, input Field, and Error Field
     * could cause 3 fields to be produced
     * if name = fred, we could have
     * fredTitle : the field where the description for fred would be
     * fred      : the actual value for fred
     * fredError : freds error field
     *
     */
    public SPField newField(String request) {
        String[] fields = request.split(",");

        if (fields.length == 3) {
            return new SPField(fields[0], fields[2], fields[1]);
        } else {
            Boolean tmp = fields[3].equalsIgnoreCase("true");
            return new SPField(fields[0], fields[2], fields[1], tmp);
        }

    }
}
