package together.news.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {  
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";  
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";  
      
    public static final int C_ONE_SECOND = 1000;  
    public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;  
    public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;  
    public static final long C_ONE_DAY = 24 * C_ONE_HOUR;  
    public static int findMaxDayInMonth() {  
        return findMaxDayInMonth(0, 0);  
    }  
    public static int findMaxDayInMonth(Date date) {  
        if (date == null) {  
            return 0;  
        }  
        return findMaxDayInMonth(date2Calendar(date));  
    }  
      
    public static int findMaxDayInMonth(Calendar calendar) {  
        if (calendar == null) {  
            return 0;  
        }  
          
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
    }  
      
    public static int findMaxDayInMonth(int month) {  
        return findMaxDayInMonth(0, month);  
    }  
      
    public static int findMaxDayInMonth(int year, int month) {  
        Calendar calendar = Calendar.getInstance();  
        if (year > 0) {  
            calendar.set(Calendar.YEAR, year);  
        }  
          
        if (month > 0) {  
            calendar.set(Calendar.MONTH, month - 1);  
        }  
          
        return findMaxDayInMonth(calendar);  
    }  
      
    public static Date calendar2Date(Calendar calendar) {  
        if (calendar == null) {  
            return null;  
        }  
        return calendar.getTime();  
    }  
      
    public static Calendar date2Calendar(Date date) {  
        if (date == null) {  
            return null;  
        }  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        return calendar;  
    }  
      
    public static SimpleDateFormat getSimpleDateFormat() {  
        return getSimpleDateFormat(null);  
    }  
      
    public static SimpleDateFormat getSimpleDateFormat(String format) {  
        SimpleDateFormat sdf;  
        if (format == null) {  
            sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);  
        } else {  
            sdf = new SimpleDateFormat(format);  
        }  
          
        return sdf;  
    }  
      
    public static String formatDate2Str() {  
        return formatDate2Str(new Date());  
    }  
      
    public  static String formatDate2Str(Date date) {  
        return formatDate2Str(date, C_TIME_PATTON_DEFAULT);  
    }  
      
    public static String formatDate2Str(Date date, String format) {  
        if (date == null) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
        SimpleDateFormat sdf = getSimpleDateFormat(format);  
        return sdf.format(date);  
    }  
      
    public static Date formatStr2Date(String dateStr) {  
        return formatStr2Date(dateStr, null);  
    }  
      
    public static Date formatStr2Date(String dateStr, String format) {  
        if (dateStr == null) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
        SimpleDateFormat sdf = getSimpleDateFormat(format);  
        return sdf.parse(dateStr, new ParsePosition(0));  
    }  
      
    public static String formatDefault2Define(String dateStr, String defineFormat) {  
        return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);  
    }  
      
    public static String formatSource2Target(String dateStr, String sourceFormat, String targetFormat) {  
        Date date = formatStr2Date(dateStr, sourceFormat);  
        return formatDate2Str(date, targetFormat);  
    }  
      
    public static int findWeeksNoInYear() {  
        return findWeeksNoInYear(new Date());  
    }  
      
    public static int findWeeksNoInYear(Date date) {  
        if (date == null) {  
            return 0;  
        }  
        return findWeeksNoInYear(date2Calendar(date));  
    }  
      
    public static int findWeeksNoInYear(Calendar calendar) {  
        if (calendar == null) {  
            return 0;  
        }  
        return calendar.get(Calendar.WEEK_OF_YEAR);  
    }  
      
    public static Date findDateInWeekOnYear(int year, int weekInYear, int dayInWeek) {  
        Calendar calendar = Calendar.getInstance();  
        if (year > 0) {  
            calendar.set(Calendar.YEAR, year);  
        }  
          
        calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);  
        calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);  
          
        return calendar.getTime();  
    }  
      
    
    public static Date dayBefore2Date(int days) {   
        Date date = new Date();  
        return dayBefore2Object(days, null, date);  
    }  
      
   
    public static String dayBefore2Str(int days) {  
        String string = formatDate2Str();  
        return dayBefore2Object(days, null, string);  
    }  
      
    
    @SuppressWarnings("unchecked")  
    public static <T extends Object> T dayBefore2Object(int days, String format, T instance) {  
        Calendar calendar = Calendar.getInstance();  
        if (days == 0) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
          
        calendar.add(Calendar.DATE, days);  
        if (instance instanceof Date) {  
            return (T)calendar.getTime();  
        } else if (instance instanceof String) {  
            return (T)formatDate2Str(calendar2Date(calendar), format);  
        }  
        return null;  
    }  
      
   
    public static Date defineDayBefore2Date(Date date, int days) {  
        Date dateInstance = new Date();  
        return defineDayBefore2Object(date, days, null, dateInstance);  
    }  
   
    public static String defineDayBefore2Str(Date date, int days) {  
        String stringInstance = formatDate2Str();  
        return defineDayBefore2Object(date, days, null, stringInstance);  
    }  
      
   
    @SuppressWarnings("unchecked")  
    public static <T extends Object> T defineDayBefore2Object(Date date,   
            int days, String format, T instance) {  
        if (date == null || days == 0) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
          
        Calendar calendar = date2Calendar(date);  
        calendar.add(Calendar.DATE, days);  
        if (instance instanceof Date) {  
            return (T)calendar.getTime();  
        } else if (instance instanceof String) {  
            return (T)formatDate2Str(calendar2Date(calendar), format);  
        }  
        return null;  
    }  
      
   
    public static Date monthBefore2Date(int months) {  
        Date date = new Date();  
        return monthBefore2Object(months, null, date);  
    }  
      
   
    public static String monthBefore2Str(int months) {  
        String string = formatDate2Str();  
        return monthBefore2Object(months, null, string);  
    }  
      
   
    @SuppressWarnings("unchecked")  
    public static <T extends Object> T monthBefore2Object(int months, String format, T instance) {  
        if (months == 0) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
        Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.MONTH, months);  
          
        if (instance instanceof Date) {  
            return (T)calendar.getTime();  
        } else if (instance instanceof String) {  
            return (T)formatDate2Str(calendar2Date(calendar), format);  
        }  
          
        return null;  
    }  
    
    public static Date defineMonthBefore2Date(Date date, int months) {  
        Date dateInstance = new Date();  
        return defineMonthBefore2Object(date, months, null, dateInstance);  
    }  
      
   
    public static String defineMonthBefore2Str(Date date, int months) {  
        String stringInstance = formatDate2Str();  
        return defineMonthBefore2Object(date, months, null, stringInstance);  
    }  
      
   
    @SuppressWarnings("unchecked")  
    public static <T extends Object> T defineMonthBefore2Object(Date date,  
            int months, String format, T instance) {  
        if (months == 0) {  
            return null;  
        }  
          
        if (format == null || format.equals("")) {  
            format = C_TIME_PATTON_DEFAULT;  
        }  
          
        Calendar calendar = date2Calendar(date);  
        calendar.add(Calendar.MONTH, months);  
          
        if (instance instanceof Date) {  
            return (T)calendar.getTime();  
        } else if (instance instanceof String) {  
            return (T)formatDate2Str(calendar2Date(calendar), format);  
        }  
          
        return null;  
    }  
      
   
    public static int caculate2Days(Date firstDate, Date secondDate) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(firstDate);  
        int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);  
        calendar.setTime(secondDate);  
        int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);  
        return Math.abs(dayNum1 - dayNum2);  
    }  
      
  
    public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {  
        if (firstCalendar.after(secondCalendar)) {  
            Calendar calendar = firstCalendar;  
            firstCalendar = secondCalendar;  
            secondCalendar = calendar;  
        }  
          
        long calendarNum1 = firstCalendar.getTimeInMillis();  
        long calendarNum2 = secondCalendar.getTimeInMillis();  
        return Math.abs((int)((calendarNum1 - calendarNum2)/C_ONE_DAY));  
    }  
      
}  