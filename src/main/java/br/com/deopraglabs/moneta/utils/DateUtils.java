package br.com.deopraglabs.moneta.utils;

import br.com.deopraglabs.moneta.exceptions.InvalidDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String[] EXPECTED_DATE_FORMATS = {
            "M/d/yy, h:mm a", "MM/dd/yy, h:mm a",
            "M/d/yyyy, h:mm a", "MM/dd/yyyy, h:mm a",
            "M/d/yy, hh:mm a", "MM/dd/yy, hh:mm a",
            "M/d/yyyy, hh:mm a", "MM/dd/yyyy, hh:mm a",

            "M-d-yy, h:mm a", "MM-dd-yy, h:mm a",
            "M-d-yyyy, h:mm a", "MM-dd-yyyy, h:mm a",
            "M-d-yy, hh:mm a", "MM-dd-yy, hh:mm a",
            "M-d-yyyy, hh:mm a", "MM-dd-yyyy, hh:mm a",

            "Mdyy, h:mm a", "MMddyy, h:mm a",
            "Mdyyyy, h:mm a", "MMddyyyy, h:mm a",

            "M/d/yy, H:mm", "MM/dd/yy, H:mm",
            "M/d/yyyy, H:mm", "MM/dd/yyyy, H:mm",
            "M-d-yy, H:mm", "MM-dd-yy, H:mm",
            "M-d-yyyy, H:mm", "MM-dd-yyyy, H:mm",

            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm'Z'",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",

            "M/d/yy", "MM/dd/yy", "M/d/yyyy", "MM/dd/yyyy",
            "M-d-yy", "MM-dd-yy", "M-d-yyyy", "MM-dd-yyyy",
            "yyyyMMdd", "yyMMdd"
    };

    public static Date formatStringToDate(String date) {
        if (date == null || date.trim().isEmpty())
            throw new InvalidDateFormat("Date string cannot be null or empty");

        try {
            final Instant instant = Instant.parse(date);
            return Date.from(instant);
        } catch (DateTimeParseException ignored) {}

        for (final String format : EXPECTED_DATE_FORMATS) {
            try {
                final SimpleDateFormat formatter = new SimpleDateFormat(format);
                formatter.setLenient(false);
                return formatter.parse(date);
            } catch (ParseException ignored) {}
        }

        throw new InvalidDateFormat("Could not parse date '" + date + "'", Arrays.toString(EXPECTED_DATE_FORMATS));
    }

    public static Date resetTime(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
