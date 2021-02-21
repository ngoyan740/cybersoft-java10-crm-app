package com.myclass.utility;

public class Conversion {
	
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        
        return sDate;
    }
	
}
