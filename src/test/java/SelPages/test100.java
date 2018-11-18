package SelPages;

import SelPages.SPField;


public class test100 {
    public static void main( String[] args )
    {
        SPField fred = null;
        // String name , String searchValue , String searchType , Boolean required ){
        try {
            fred = new SPField("fred","AA","id", true);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println(fred.toString());

    }
}
