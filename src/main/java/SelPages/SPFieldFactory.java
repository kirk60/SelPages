package SelPages;

public class SPFieldFactory {
    private static SPFieldFactory ourInstance = new SPFieldFactory();

    private SPFieldFactory() {
    }

    public static SPFieldFactory getInstance() {
        return ourInstance;
    }

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
