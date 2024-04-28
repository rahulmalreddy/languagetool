/* LanguageTool, a natural language style checker
 * Copyright (C) 2018 Fabian Richter
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
package org.languagetool.rules.pt;

import org.languagetool.tools.StringTools;

import java.util.Calendar;
import java.util.Locale;

/**
 * @since 4.3
 */
class DateFilterHelper {

  protected Calendar getCalendar() {
    return Calendar.getInstance(Locale.UK);
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  protected int getDayOfWeek(String dayStr) {
    String day = StringTools.trimSpecialCharacters(dayStr).toLowerCase();  // quickfix for special characters like soft hyphens
    if (day.startsWith("dom")) return Calendar.SUNDAY;
    if (day.startsWith("seg")) return Calendar.MONDAY;
    if (day.startsWith("ter")) return Calendar.TUESDAY;
    if (day.startsWith("qua")) return Calendar.WEDNESDAY;
    if (day.startsWith("qui")) return Calendar.THURSDAY;
    if (day.startsWith("sex")) return Calendar.FRIDAY;
    if (day.startsWith("sáb")) return Calendar.SATURDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  protected String getDayOfWeek(Calendar date) {
    String englishDay = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.UK);
    if ("Sunday".equals(englishDay)) return "domingo";
    if ("Monday".equals(englishDay)) return "segunda-feira";
    if ("Tuesday".equals(englishDay)) return "terça-feira";
    if ("Wednesday".equals(englishDay)) return "quarta-feira";
    if ("Thursday".equals(englishDay)) return "quinta-feira";
    if ("Friday".equals(englishDay)) return "sexta-feira";
    if ("Saturday".equals(englishDay)) return "sábado";
    return "";
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  protected int getMonth(String monthStr) {
    String mon = StringTools.trimSpecialCharacters(monthStr).toLowerCase();  // quickfix for special characters like soft hyphens
    if (mon.startsWith("jan")) return 1;
    if (mon.startsWith("fev")) return 2;
    if (mon.startsWith("mar")) return 3;
    if (mon.startsWith("abr")) return 4;
    if (mon.startsWith("mai")) return 5;
    if (mon.startsWith("jun")) return 6;
    if (mon.startsWith("jul")) return 7;
    if (mon.startsWith("ago")) return 8;
    if (mon.startsWith("set")) return 9;
    if (mon.startsWith("out")) return 10;
    if (mon.startsWith("nov")) return 11;
    if (mon.startsWith("dez")) return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
