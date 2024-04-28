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
package org.languagetool.rules.eo;

import org.languagetool.rules.AbstractDateCheckFilter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Esperanto localization of {@link AbstractDateCheckFilter}.
 * @since 2.7
 */
public class DateCheckFilter extends AbstractDateCheckFilter {

  @Override
  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.UK);
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfMonth(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.endsWith("n")) {
      // Removing final n if any.
      day = day.substring(0, day.length() - 1);
    }
    int n = 0;
    if (day.startsWith("dek")) {
      n = 10;
      day = day.substring(3);
    } else if (day.startsWith("dudek")) {
      n = 20;
      day = day.substring(5);
    } else if (day.startsWith("tridek")) {
      n = 30;
      day = day.substring(6);
    }
    if (n > 0 && day.startsWith("-")) {
      // Remove hyphen as in "dudek-trian".
      day = day.substring(1);
    }
    if ("unua".equals(day))  n += 1;
    if ("dua".equals(day))   n += 2;
    if ("tria".equals(day))  n += 3;
    if ("kvara".equals(day)) n += 4;
    if ("kvina".equals(day)) n += 5;
    if ("sesa".equals(day))  n += 6;
    if ("sepa".equals(day))  n += 7;
    if ("oka".equals(day))   n += 8;
    if ("naŭa".equals(day))  n += 9;
    if ("nauxa".equals(day)) n += 9;
    if ("naua".equals(day))  n += 9;
    return n;
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.startsWith("dim")) return Calendar.SUNDAY;
    if (day.startsWith("lun")) return Calendar.MONDAY;
    if (day.startsWith("mar")) return Calendar.TUESDAY;
    if (day.startsWith("mer")) return Calendar.WEDNESDAY;
    if (day.startsWith("ĵaŭ")) return Calendar.THURSDAY;
    if (day.startsWith("jau")) return Calendar.THURSDAY;
    if (day.startsWith("jhau")) return Calendar.THURSDAY;
    if (day.startsWith("jxau")) return Calendar.THURSDAY;
    if (day.startsWith("ven")) return Calendar.FRIDAY;
    if (day.startsWith("sab")) return Calendar.SATURDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected String getDayOfWeek(Calendar date) {
    String englishDay=date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.UK);
    if ("Sunday".equals(englishDay))    return "dimanĉo";
    if ("Monday".equals(englishDay))    return "lundo";
    if ("Tuesday".equals(englishDay))   return "mardo";
    if ("Wednesday".equals(englishDay)) return "merkredo";
    if ("Thursday".equals(englishDay))  return "jaŭdo";
    if ("Friday".equals(englishDay))    return "vendredo";
    if ("Saturday".equals(englishDay))  return "sabato";
    return "";
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if (mon.startsWith("jan")) return 1;
    if (mon.startsWith("feb")) return 2;
    if (mon.startsWith("mar")) return 3;
    if (mon.startsWith("apr")) return 4;
    if (mon.startsWith("maj")) return 5;
    if (mon.startsWith("jun")) return 6;
    if (mon.startsWith("jul")) return 7;
    if (mon.startsWith("aŭg")) return 8;
    if (mon.startsWith("sep")) return 9;
    if (mon.startsWith("okt")) return 10;
    if (mon.startsWith("nov")) return 11;
    if (mon.startsWith("dec")) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
