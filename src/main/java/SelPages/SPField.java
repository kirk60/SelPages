package SelPages;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import org.openqa.selenium.By;

@ToString @EqualsAndHashCode
public class SPField  {

    public SPField( String name , String searchValue , String searchType , Boolean required ){
        this.setName(name);
        this.setSearchValue(searchValue);
        this.setSearchType(searchType);
        this.setRequired(required);
        try {
            this.locator = SPSeleniumHelpers.getLocator(this.getSearchType(),this.getSearchValue());
        } catch(Exception e) {
            throw new ExceptionInInitializerError( "Field Name '" + name + "', " + e.getLocalizedMessage() );
        }

    }

    @Setter @Getter
    private String name = null;
    @Setter @Getter
    private Boolean required = null;
    @Setter @Getter
    private String searchValue = null;
    @Setter @Getter
    private String searchType = null;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private By locator = null;

}
