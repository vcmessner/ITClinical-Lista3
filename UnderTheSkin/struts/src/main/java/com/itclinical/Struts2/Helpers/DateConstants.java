package com.itclinical.struts2.helpers;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateConstants {
    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.UTC;
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    
}
