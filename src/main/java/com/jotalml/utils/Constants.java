package com.jotalml.utils;

import java.util.regex.Pattern;

public class Constants {

  public static final String NUMBER_VALIDATION =
      "This argument must be greater than 0, can't be '${validatedValue}'";
  public static final String PAGE_VALIDATION =
      "Error in argument page with value: '${validatedValue}'";
  public static final String ARRAY_IN_VALIDATION =
      "The format of the field with value '${validatedValue}' is incorrect, you must enter a format according to the example: 1,2,3";
  public static final String DATE_VALIDATION =
      "The date must have the format '{pattern}' and value can't be '${validatedValue}'";
  public static final String BOOLEAN_VALIDATION =
      "This argument must be true or false, can't be '${validatedValue}'";
  public static Pattern NUMBER_REGEX = Pattern.compile("^([1-9]\\d*)+?$");
  public static Pattern NUMBER_WITH_NEGATIVE_REGEX = Pattern.compile("(^-?[1-9]\\d*|^0)+?$");
  public static Pattern NUMBER_WITH_DECIMAL_NEGATIVE_REGEX = Pattern.compile("^-?\\d*\\.{0,1}\\d+$");
  public static Pattern NUMBER_WITH_DECIMAL_REGEX = Pattern.compile("^[0-9]+([.][0-9]+)?$");
  public static Pattern ARRAY_STRING_REGEX = Pattern.compile("(^([\\w\\W]*,?)*)+?$");
  public static Pattern ARRAY_NUMBERS_REGEX = Pattern.compile("(^([1-9]\\d*,?)*)+?$");
  public static Pattern ARRAY_NUMBERS_WITH_NEGATIVE_REGEX = Pattern.compile("^(-?(([1-9]\\d*)|0(?!\\d)),?)+?$");
  public static Pattern BOOLEAN_REGEX = Pattern.compile("^true$|^false$");
  public static Pattern ARRAY_NUMBERS_WITH_DECIMAL_REGEX = Pattern.compile("(^(\\d*\\.{0,1}?[0-9]\\d*,?)*)+?$");
  public static Pattern ARRAY_NUMBERS_WITH_DECIMAL_NEGATIVE_REGEX = Pattern.compile("(^(-?\\d*\\.{0,1}?[0-9]\\d*,?)*)+?$");

}
