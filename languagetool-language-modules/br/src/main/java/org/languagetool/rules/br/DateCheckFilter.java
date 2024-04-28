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
package org.languagetool.rules.br;

import org.languagetool.rules.AbstractDateCheckFilter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Breton localization of {@link AbstractDateCheckFilter}.
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
    if (day.charAt(0) == 't') day = 'd' + day.substring(1);
    if (day.charAt(0) == 'p') day = 'b' + day.substring(1);
    if (day.endsWith("vet")) {
      // Removing final vet if any.
      day = day.substring(0, day.length() - 3);
    }

    if ("c’hentañ".equals(day) || "unan".equals(day))    return 1;
    if ("daou".equals(day)     || "eil".equals(day))     return 2;
    if ("dri".equals(day)      || "drede".equals(day) || "deir".equals(day)) return 3;
    if ("bevar".equals(day))                             return 4;
    // bemp or bemvet (vet has been removed).
    if ("bemp".equals(day)     || "bem".equals(day))     return 5;
    if ("c’hwerc’h".equals(day))                         return 6;
    if ("seizh".equals(day))                             return 7;
    if ("eizh".equals(day))                              return 8;
    // nav and navet (vet has been removed).
    if ("nav".equals(day)      || "na".equals(day))     return 9;
    if ("dek".equals(day))                              return 10;
    if ("unnek".equals(day))                            return 11;
    if ("daouzek".equals(day))                          return 12;
    if ("drizek".equals(day))                           return 13;
    if ("bevarzek".equals(day))                         return 14;
    if ("bemzek".equals(day))                           return 15;
    if ("c’hwezek".equals(day))                         return 16;
    if ("seitek".equals(day))                           return 17;
    if ("driwec’h".equals(day))                         return 18;
    if ("naontek".equals(day))                          return 19;
    if ("ugent".equals(day))                            return 20;
    if ("dregont".equals(day))                          return 30;
    return 0;
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected int getDayOfWeek(String dayStr) {
    String day = dayStr.toLowerCase();
    if (day.endsWith("sul"))      return Calendar.SUNDAY;
    if (day.endsWith("lun"))      return Calendar.MONDAY;
    if (day.endsWith("meurzh"))   return Calendar.TUESDAY;
    if (day.endsWith("merc’her")) return Calendar.WEDNESDAY;
    if ("yaou".equals  (day))     return Calendar.THURSDAY;
    if ("diriaou".equals  (day))  return Calendar.THURSDAY;
    if (day.endsWith("gwener"))   return Calendar.FRIDAY;
    if (day.endsWith("sadorn"))   return Calendar.SATURDAY;
    throw new RuntimeException("Could not find day of week for '" + dayStr + "'");
  }

  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  @Override
  protected String getDayOfWeek(Calendar date) {
    String englishDay = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.UK);
    if ("Sunday".equals(englishDay))    return "Sul";
    if ("Monday".equals(englishDay))    return "Lun";
    if ("Tuesday".equals(englishDay))   return "Meurzh";
    if ("Wednesday".equals(englishDay)) return "Merc’her";
    if ("Thursday".equals(englishDay))  return "Yaou";
    if ("Friday".equals(englishDay))    return "Gwener";
    if ("Saturday".equals(englishDay))  return "Sadorn";
    return "";
  }

  @SuppressWarnings({"ControlFlowStatementWithoutBraces", "MagicNumber"})
  @Override
  protected int getMonth(String monthStr) {
    String mon = monthStr.toLowerCase();
    if ("genver".equals(mon))                            return 1;
    if ("c’hwevrer".equals(mon))                         return 2;
    if ("meurzh".equals(mon))                            return 3;
    if ("ebrel".equals(mon))                             return 4;
    if ("mae".equals(mon))                               return 5;
    if ("mezheven".equals(mon) || "even".equals(mon))    return 6;
    if ("gouere".equals(mon)   || "gouhere".equals(mon)) return 7;
    if ("eost".equals(mon))                              return 8;
    if ("gwengolo".equals(mon))                          return 9;
    if ("here".equals(mon))                              return 10;
    if ("du".equals(mon))                                return 11;
    if ("kerzu".equals(mon))                             return 12;
    throw new RuntimeException("Could not find month '" + monthStr + "'");
  }
}
