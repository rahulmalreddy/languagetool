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
  package org.languagetool.rules.pl;
    import org.languagetool.rules.AbstractDateCheckFilter;
    import java.util.Calendar;
    import java.util.Locale;

/**
 * Polish localization of {@link org.languagetool.rules.AbstractDateCheckFilter}.
 * @since 2.7
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.forLanguageTag("pl"));
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("pon")) return Calendar.MONDAY;
    if (day.startsWith("wt")) return Calendar.TUESDAY;
    if (day.startsWith("śr")) return Calendar.WEDNESDAY;
    if (day.startsWith("czw")) return Calendar.THURSDAY;
    if ("pt".equals(day) || day.startsWith("piątk") || "piątek".equals(day)) return Calendar.FRIDAY;
    if (day.startsWith("sob")) return Calendar.SATURDAY;
    if (day.startsWith("niedz")) return Calendar.SUNDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @Override
  protected String getDayOfWeek(Calendar date) {
    return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("pl"));
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if ("stycznia".equals(mon) || "I".equals(monthStr)) return 1;
    if ("lutego".equals(mon) || "II".equals(monthStr)) return 2;
    if ("marca".equals(mon) || "III".equals(monthStr)) return 3;
    if ("kwietnia".equals(mon) || "IV".equals(monthStr)) return 4;
    if ("maja".equals(mon) || "V".equals(monthStr)) return 5;
    if ("czerwca".equals(mon) || "VI".equals(monthStr)) return 6;
    if ("lipca".equals(mon) || "VII".equals(monthStr)) return 7;
    if ("sierpnia".equals(mon) || "VIII".equals(monthStr)) return 8;
    if ("września".equals(mon) || "IX".equals(monthStr)) return 9;
    if ("października".equals(mon) || "X".equals(monthStr)) return 10;
    if ("listopada".equals(mon) || "XI".equals(monthStr)) return 11;
    if ("grudnia".equals(mon) || "XII".equals(monthStr)) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
