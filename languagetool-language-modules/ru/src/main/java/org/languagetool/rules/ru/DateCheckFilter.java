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
  package org.languagetool.rules.ru;
    import org.languagetool.rules.AbstractDateCheckFilter;
    import java.util.Calendar;
    import java.util.Locale;

/**
 * Russian localization of {@link org.languagetool.rules.AbstractDateCheckFilter}.
 * @since 2.7
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.forLanguageTag("ru"));
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("пн") || "понедельник".equals(day)) return Calendar.MONDAY;
    if (day.startsWith("вт")) return Calendar.TUESDAY;
    if (day.startsWith("ср")) return Calendar.WEDNESDAY;
    if (day.startsWith("чт") || "четверг".equals(day)) return Calendar.THURSDAY;
    if ("пт".equals(day) || day.startsWith ("пятниц")) return Calendar.FRIDAY;
    if (day.startsWith("сб") || day.startsWith ("суббот")) return Calendar.SATURDAY;
    if (day.startsWith("вс") || "воскресенье".equals(day)) return Calendar.SUNDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @Override
  protected String getDayOfWeek(Calendar date) {
    return date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("ru"));
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if ("январь".equals(mon) || "I".equals(monthStr) || "января".equals(mon) || "янв".equals(mon)) return 1;
    if ("февраль".equals(mon) || "II".equals(monthStr) ||  "февраля".equals(mon) || "фев".equals(mon)) return 2;
    if ("март".equals(mon) || "III".equals(monthStr) || "марта".equals(mon) || "мар".equals(mon)) return 3;
    if ("апрель".equals(mon) || "IV".equals(monthStr) || "апреля".equals(mon) || "апр".equals(mon)) return 4;
    if ("май".equals(mon) || "V".equals(monthStr) || "мая".equals(mon)) return 5;
    if ("июнь".equals(mon) || "VI".equals(monthStr) || "июня".equals(mon) || "ин".equals(mon)) return 6;
    if ("июль".equals(mon) || "VII".equals(monthStr) || "июля".equals(mon) || "ил".equals(mon)) return 7;
    if ("август".equals(mon) || "VIII".equals(monthStr) || "августа".equals(mon) || "авг".equals(mon)) return 8;
    if ("сентябрь".equals(mon) || "IX".equals(monthStr) || "сентября".equals(mon) || "сен".equals(mon)) return 9;
    if ("октябрь".equals(mon) || "X".equals(monthStr) || "октября".equals(mon) || "окт".equals(mon)) return 10;
    if ("ноябрь".equals(mon) || "XI".equals(monthStr) || "ноября".equals(mon) || "ноя".equals(mon)) return 11;
    if ("декабрь".equals(mon) || "XII".equals(monthStr) || "декабря".equals(mon) || "дек".equals(mon)) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
