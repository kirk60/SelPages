package SelPages;


import java.util.Hashtable;

import lombok.Getter;
import lombok.Setter;


public class SPPage {

    @Getter
    @Setter
    private String url;
    private Hashtable<String, SPField> fields = new Hashtable<String, SPField>();

    public SPPage() {
        this.url = null;
    }

    public SPPage(String url) {
        setUrl(url);
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
