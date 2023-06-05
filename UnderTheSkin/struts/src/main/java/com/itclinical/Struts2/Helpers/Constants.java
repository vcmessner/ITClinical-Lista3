package com.itclinical.Struts2.Helpers;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Constants {
    public static final int LEGAL_AGE = 18;
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final int RANDOM_AGE_LOWERBOUND = 200;
    public static final int DEFAULT_UPPERCASE_PROBABILITY = 50;
    public static final int DEFAULT_RANDOM_STR_LEN = 10;
    public static final int DEFAULT_NUMBER_JUNIT_REPEAT = 3000;
    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.UTC;
    public static final String REGEX_ALPHA = "[a-zA-Z]+";
    public static final String REGEX_NUM ="[0-9]+";
    public static final int NAME_TEXT_FIELD_SIZE = 40;
    public static final String REGISTER_ACTION_URI = "/struts/RegisterAction.action";
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    
}
