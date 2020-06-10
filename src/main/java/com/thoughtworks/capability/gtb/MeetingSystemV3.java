package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 *   场景：
 *   a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 *   b:而我们在当前时区(北京时区)使用系统
 *   c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

  public static void main(String[] args) {
    String timeStr = "2020-04-01 14:30:00";

    ZoneId london = ZoneId.of("Europe/London");
    ZoneId shanghai = ZoneId.of("Asia/Shanghai");
    ZoneId chicago = ZoneId.of("America/Chicago");

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 从字符串解析得到中国会议时间
    ZonedDateTime londonMeetingTime = LocalDateTime.parse(timeStr, formatter).atZone(london);
    ZonedDateTime shanghaiMeetingTime = londonMeetingTime.withZoneSameInstant(shanghai);

    ZonedDateTime now = LocalDateTime.now().atZone(shanghai);

    // 判断本地时间是否超过会议时间
    if (now.isAfter(shanghaiMeetingTime)) {

      // 重新安排会议时间为系统使用地（中国）的明日同一时间
      ZonedDateTime tomorrow = now.plusDays(1);
      int newDayOfYear = tomorrow.getDayOfYear();
      shanghaiMeetingTime = shanghaiMeetingTime.withDayOfYear(newDayOfYear);

      // 计算芝加哥对应的时间
      ZonedDateTime chicagoMeetingTime = shanghaiMeetingTime.withZoneSameInstant(chicago);

      // 格式化新会议时间
      String showTimeStr = formatter.format(chicagoMeetingTime);
      System.out.println(showTimeStr);
    } else {
      System.out.println("会议还没开始呢");
    }
  }
}
