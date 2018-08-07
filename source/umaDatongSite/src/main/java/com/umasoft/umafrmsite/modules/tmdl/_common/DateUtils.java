package com.umasoft.umafrmsite.modules.tmdl._common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static SimpleDateFormat sf=null;

    public static Date getSomeDate(int i){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        return c.getTime();
    }


    public static String DateparseString(Date date,String par){
       sf= new SimpleDateFormat(par);
        return  sf.format(date);

    }

    public static Date StringparseDate(String date,String par){
        sf=new SimpleDateFormat(par);
        Date dates=null;
        try {
            dates= sf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  dates;
    }

    public static String  month(Date date,String par){
        sf=new SimpleDateFormat(par);
        Calendar  g = Calendar.getInstance();
        g.setTime(date);
        g.add(Calendar.MONTH,-1);
        Date d2 = g.getTime();
        String dates=sf.format(d2);
        return dates;

    }


}
