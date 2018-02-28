package com.globantu.automation.salvador_ortuno;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalCurrentDate {
    @Test
    public void test1(){
        System.out.println ( "Date:  --> "+getCurrentDay () );
        System.out.println ( "Date:  --> "+getCurrentMonth () );
        System.out.println ( "Date:  --> "+getCurrentMonth2 () );
    }
    public static final String getCurrentMonth(){
        Calendar cal=Calendar.getInstance ();
        //cal.add ( Calendar.DAY_OF_MONTH,0 );
        SimpleDateFormat dateformat = new SimpleDateFormat ( "MMM dd" );
        String date = dateformat.format ( cal.getTime () );
        return date;
    }
    public static final String getCurrentDay(){
        Calendar cal=Calendar.getInstance ();
        SimpleDateFormat dateformat = new SimpleDateFormat ( "dd" );
        String date = dateformat.format ( cal.getTime () );
        return date;
    }

    public static final String getCurrentMonth2() {
        Calendar cal = Calendar.getInstance ();
        cal.add ( Calendar.MONTH, 2 );
        SimpleDateFormat dateformat = new SimpleDateFormat ( "MMM" );
        String date = dateformat.format ( cal.getTime () );
        return date;
    }
}
