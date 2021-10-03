package platform.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateConverter {

    private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String convertDateToString(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertStringToDate(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
