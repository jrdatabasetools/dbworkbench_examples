/*
 * Copyright (c) Jan Richter, www.jr-database-tools.com, Switzerland, 2015-2024. All rights reserved.
 */

package plsql_workbench_examples.timer;

public class TimerCounter {
  private long start;

  public TimerCounter() {
    start = System.currentTimeMillis();
  }

  private String toTimeString(long diff)
  {
    StringBuffer sb = new StringBuffer();
    sb.append(String.format("%03d", diff % 1000));
    sb.insert(0, '.');
    diff = diff / 1000;
    sb.insert(0, String.format("%02d", diff % 60));
    sb.insert(0, ':');
    diff = diff / 60;
    sb.insert(0, String.format("%02d", diff % 60));
    sb.insert(0, ':');
    diff = diff / 60;
    sb.insert(0, String.format("%02d", diff));

    return sb.toString();
  }

  public String diff(String what)
  {
    long diff = System.currentTimeMillis() - start;
    start = System.currentTimeMillis();
    return String.format("%s [%s]", what, toTimeString(diff));
  }

  public String perSecond(String what, int count)
  {
    long diff = System.currentTimeMillis() - start;
    start = System.currentTimeMillis();
    return String.format("%s [count %d] [%1.0f per second] [running time %s]",
                         what,
                         count,
                         1. / diff * 1000L * count,
                         toTimeString(diff));
  }
}
