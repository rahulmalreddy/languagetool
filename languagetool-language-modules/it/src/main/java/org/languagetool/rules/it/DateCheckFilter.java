/* LanguageTool, a natural language style checker
 * Copyright (C) 2020 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.rules.it;

import org.languagetool.rules.AbstractDateCheckFilter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Italian localization of {@link AbstractDateCheckFilter}.
 * @since 5.0
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.UK);
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("do") || "domenica".equals(day)) return Calendar.SUNDAY;
    if (day.startsWith("lu") || "lunedì".equals(day)) return Calendar.MONDAY;
    if (day.startsWith("ma") || "martedì".equals(day)) return Calendar.TUESDAY;
    if (day.startsWith("me") || "mercoledì".equals(day)) return Calendar.WEDNESDAY;
    if (day.startsWith("gi") || "giovedì".equals(day)) return Calendar.THURSDAY;
    if (day.startsWith("ve") || "venerdì".equals(day)) return Calendar.FRIDAY;
    if (day.startsWith("sa") || "sabato".equals(day)) return Calendar.SATURDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected String getDayOfWeek(Calendar date) {
    String englishDay = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.UK);
    if ("Sunday".equals(englishDay)) return "domenica";
    if ("Monday".equals(englishDay)) return "lunedì";
    if ("Tuesday".equals(englishDay)) return "martedì";
    if ("Wednesday".equals(englishDay)) return "mercoledì";
    if ("Thursday".equals(englishDay)) return "giovedì";
    if ("Friday".equals(englishDay)) return "venerdì";
    if ("Saturday".equals(englishDay)) return "sabato";
    return "";
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if (mon.startsWith("gen")) return 1;
    if (mon.startsWith("feb")) return 2;
    if (mon.startsWith("mar")) return 3;
    if (mon.startsWith("apr")) return 4;
    if (mon.startsWith("mag")) return 5;
    if (mon.startsWith("giu")) return 6;
    if (mon.startsWith("lug")) return 7;
    if (mon.startsWith("ago")) return 8;
    if (mon.startsWith("set")) return 9;
    if (mon.startsWith("ott")) return 10;
    if (mon.startsWith("nov")) return 11;
    if (mon.startsWith("dic")) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
