package SelPages;


import java.util.Hashtable;
import java.util.Map;


public class SPPage {

    private String url;
    private Hashtable<String, SPField> fields = new Hashtable<String, SPField>();

    public SPPage(String url) {
        this.url = url;
    }

    public void addField(SPField field) {
        fields.put(field.getName(), field);
    }

    public Boolean validateFields() {
        return this.validateFields(10);
    }

    public Boolean validateFields(int timeout) {
        for (SPField field : this.fields.values()) {

        }
        return true;
    }
}
