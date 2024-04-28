/* LanguageTool, a natural language style checker
 * Copyright (C) 2014 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.sr;

import org.languagetool.rules.AbstractDateCheckFilter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Serbian localization of {@link org.languagetool.rules.AbstractDateCheckFilter}.
 *
 * @since 4.0
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.forLanguageTag("sr"));
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("по") || "понедељак".equals(day)) return Calendar.MONDAY;
    if (day.startsWith("ут")) return Calendar.TUESDAY;
    if (day.startsWith("ср")) return Calendar.WEDNESDAY;
    if (day.startsWith("че") || "четвртак".equals(day)) return Calendar.THURSDAY;
    if (day.startsWith("пе") || "петак".equals(day)) return Calendar.FRIDAY;
    if (day.startsWith("су") || "субота".equals(day)) return Calendar.SATURDAY;
    if (day.startsWith("не") || "недеља".equals(day)) return Calendar.SUNDAY;
    throw new RuntimeException("Редни број дана у недељи за '" + dayStr + "' не постоји.");
  }

  @Override
  protected String getDayOfWeek(Calendar date) {
    return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("sr"));
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if ("јануар".equals(mon) || "I".equals(monthStr) || "јануара".equals(mon) || "јан".equals(mon)) return 1;
    if ("фебруар".equals(mon) || "II".equals(monthStr) || "фебруара".equals(mon) || "феб".equals(mon)) return 2;
    if ("март".equals(mon) || "III".equals(monthStr) || "марта".equals(mon) || "мар".equals(mon)) return 3;
    if ("април".equals(mon) || "IV".equals(monthStr) || "априла".equals(mon) || "апр".equals(mon)) return 4;
    if ("мај".equals(mon) || "V".equals(monthStr) || "маја".equals(mon)) return 5;
    if ("јун".equals(mon) || "VI".equals(monthStr) || "јуна".equals(mon) || "јун".equals(mon)) return 6;
    if ("јул".equals(mon) || "VII".equals(monthStr) || "јула".equals(mon) || "јул".equals(mon)) return 7;
    if ("август".equals(mon) || "VIII".equals(monthStr) || "августа".equals(mon) || "авг".equals(mon)) return 8;
    if ("септембар".equals(mon) || "IX".equals(monthStr) || "септембра".equals(mon) || "сеп".equals(mon)) return 9;
    if ("октобар".equals(mon) || "X".equals(monthStr) || "октобра".equals(mon) || "окт".equals(mon)) return 10;
    if ("новембар".equals(mon) || "XI".equals(monthStr) || "новембра".equals(mon) || "нов".equals(mon)) return 11;
    if ("децембар".equals(mon) || "XII".equals(monthStr) || "децембра".equals(mon) || "дец".equals(mon)) return 12;
    throw new RuntimeException("Месец '" + monthStr + "' не постоји.");
  }
}
