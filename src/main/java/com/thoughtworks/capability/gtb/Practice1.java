package com.thoughtworks.capability.gtb;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

  public static long getDaysBetweenNextLaborDay(LocalDate date) {

    System.out.println(date.until(LocalDate.of(date.getYear(), 4, 1), ChronoUnit.DAYS));
    return date.until(LocalDate.of(date.getYear() + 1, 5, 1), ChronoUnit.DAYS) > 365 ?
      date.until(LocalDate.of(date.getYear(), 5, 1), ChronoUnit.DAYS) :
      date.until(LocalDate.of(date.getYear() + 1, 5, 1), ChronoUnit.DAYS);
  }
}
