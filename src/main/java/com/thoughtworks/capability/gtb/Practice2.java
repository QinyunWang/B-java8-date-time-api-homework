package com.thoughtworks.capability.gtb;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {

  public static LocalDate getNextWorkDate(LocalDate date) {
    List<DayOfWeek> weekdays = Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
    return date.with(temporal -> {
      int i = 1;
      while (true) {
        if (weekdays.contains(DayOfWeek.of(temporal.plus(i, ChronoUnit.DAYS).get(ChronoField.DAY_OF_WEEK)))) {
          return temporal.plus(i, ChronoUnit.DAYS);
        }
        i++;
      }
    });
  }
}
