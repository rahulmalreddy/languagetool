/* LanguageTool, a natural language style checker
 * Copyright (C) 2012 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.commandline;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.Languages;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Parser for the command line arguments.
 */
class CommandLineParser {

  CommandLineOptions parseOptions(String[] args) {
    if (args.length < 1 || args.length > 14) {
      throw new WrongParameterNumberException();
    }
    CommandLineOptions options = new CommandLineOptions();
    for (int i = 0; i < args.length; i++) {
      if ("--version".equals(args[i])) {
        options.setPrintVersion(true);
      } else if ("--list".equals(args[i])) {
        options.setPrintLanguages(true);
      } else if ("-h".equals(args[i]) || "-help".equals(args[i]) || "--help".equals(args[i]) || "--?".equals(args[i])) {
        options.setPrintUsage(true);
      } else if ("-adl".equals(args[i]) || "--autoDetect".equals(args[i])) {    // set autoDetect flag
        options.setAutoDetect(true);
      } else if ("-v".equals(args[i]) || "--verbose".equals(args[i])) {
        options.setVerbose(true);
      } else if ("--line-by-line".equals(args[i])) {
        options.setLineByLine(true);
      } else if ("--enable-temp-off".equals(args[i])) {
        options.setEnableTempOff(true);
      } else if ("--clean-overlapping".equals(args[i])) {
        options.setCleanOverlapping(true);
      } else if ("--level".equals(args[i])) {
        String level = args[++i];
        try {
          options.setLevel(JLanguageTool.Level.valueOf(level));
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Unknown level '" + level + "' - currently, only 'PICKY' is supported");
        }
      } else if ("-t".equals(args[i]) || "--taggeronly".equals(args[i])) {
        options.setTaggerOnly(true);
        if (options.isListUnknown()) {
          throw new IllegalArgumentException("You cannot list unknown words when tagging only");
        }
        if (options.isApplySuggestions()) {
          throw new IllegalArgumentException("You cannot apply suggestions when tagging only");
        }
      } else if ("-r".equals(args[i]) || "--recursive".equals(args[i])) {
        options.setRecursive(true);
      } else if ("-b2".equals(args[i]) || "--bitext".equals(args[i])) {
        options.setBitext(true);
      } else if ("-eo".equals(args[i]) || "--enabledonly".equals(args[i])) {
        if (options.getDisabledRules().size() > 0) {
          throw new IllegalArgumentException("You cannot specify both disabled rules and enabledonly");
        }
        options.setUseEnabledOnly();
      } else if ("-d".equals(args[i]) || "--disable".equals(args[i])) {
        if (options.isUseEnabledOnly()) {
          throw new IllegalArgumentException("You cannot specify both disabled rules and enabledonly");
        }
        checkArguments("-d/--disable", i, args);
        String rules = args[++i];
        options.setDisabledRules(Arrays.asList(rules.split(",")));
      } else if ("-e".equals(args[i]) || "--enable".equals(args[i])) {
        checkArguments("-e/--enable", i, args);
        String rules = args[++i];
        options.setEnabledRules(Arrays.asList(rules.split(",")));
      } else if ("--enablecategories".equals(args[i])) {
        checkArguments("--enablecategories", i, args);
        String categories = args[++i];
        options.setEnabledCategories(Arrays.asList(categories.split(",")));
      } else if ("--disablecategories".equals(args[i])) {
        checkArguments("--disablecategories", i, args);
        String categories = args[++i];
        options.setDisabledCategories(Arrays.asList(categories.split(",")));
      } else if ("-l".equals(args[i]) || "--language".equals(args[i])) {
        checkArguments("-l/--language", i, args);
        options.setLanguage(getLanguage(args[++i]));
      } else if ("-m".equals(args[i]) || "--mothertongue".equals(args[i])) {
        checkArguments("-m/--mothertongue", i, args);
        options.setMotherTongue(getLanguage(args[++i]));
      } else if ("--languagemodel".equals(args[i])) {
        checkArguments("--languagemodel", i, args);
        options.setLanguageModel(new File(args[++i]));
      } else if ("--fasttextmodel".equals(args[i])) {
        checkArguments("--fasttextmodel", i, args);
        options.setFasttextModel(new File(args[++i]));
      } else if ("--fasttextbinary".equals(args[i])) {
        checkArguments("--fasttextbinary", i, args);
        options.setFasttextBinary(new File(args[++i]));
      } else if ("--rulefile".equals(args[i])) {
        checkArguments("--rulefile", i, args);
        options.setRuleFile(args[++i]);
      } else if ("--remoterules".equals(args[i])) {
        checkArguments("--remoterules", i, args);
        options.setRemoteRulesFile(args[++i]);
      } else if ("--falsefriends".equals(args[i])) {
        checkArguments("--falsefriends", i, args);
        options.setFalseFriendFile(args[++i]);
      } else if ("--bitextrules".equals(args[i])) {
        checkArguments("--bitextrules", i, args);
        options.setBitextRuleFile(args[++i]);
      } else if ("-c".equals(args[i]) || "--encoding".equals(args[i])) {
        checkArguments("-c/--encoding", i, args);
        options.setEncoding(args[++i]);
      } else if ("-u".equals(args[i]) || "--list-unknown".equals(args[i])) {
        options.setListUnknown(true);
        if (options.isTaggerOnly()) {
          throw new IllegalArgumentException("You cannot list unknown words when tagging only");
        }
      } else if ("-b".equals(args[i])) {
        options.setSingleLineBreakMarksParagraph(true);
      } else if ("--json".equals(args[i])) {
        options.setJsonFormat();
        if (options.isApplySuggestions()) {
          throw new IllegalArgumentException("JSON output format makes no sense for automatic application of suggestions");
        }
        if (options.isLineByLine()) {
          throw new IllegalArgumentException("JSON output format is not implemented for \"line by line\" analysis");
        }
        if (options.isBitext()) {
          throw new IllegalArgumentException("JSON output format is not implemented for Bitext");
        }
        if (options.isListUnknown()) {
          throw new IllegalArgumentException("You cannot list unknown words in JSON output format");
        }
      } else if ("-a".equals(args[i]) || "--apply".equals(args[i])) {
        options.setApplySuggestions(true);
        if (options.isTaggerOnly()) {
          throw new IllegalArgumentException("You cannot apply suggestions when tagging only");
        }
        if (options.isJsonFormat()) {
          throw new IllegalArgumentException("JSON output format makes no sense for automatic application of suggestions");
        }
      } else if ("-p".equals(args[i]) || "--profile".equals(args[i])) {
        options.setProfile(true);
        if (options.isJsonFormat()) {
          throw new IllegalArgumentException("JSON output format makes no sense for profiling");
        }
        if (options.isApplySuggestions()) {
          throw new IllegalArgumentException("Applying suggestions makes no sense for profiling");
        }
        if (options.isTaggerOnly()) {
          throw new IllegalArgumentException("Tagging makes no sense for profiling");
        }
      } else if ("--xmlfilter".equals(args[i])) {
        options.setXmlFiltering(true);
      } else if (i == args.length - 1) {
        options.setFilename(args[i]);
      } else {
        throw new UnknownParameterException("Unknown parameter: " + args[i]);
      }
    }
    return options;
  }

  void printUsage() {
    printUsage(System.out);
  }

  void printUsage(PrintStream stream) {
    stream.println("Usage: java -jar languagetool-commandline.jar [OPTION]... FILE\n"
            + " FILE                      plain text file to be checked\n"
            + " Available options:\n"
            + "  -r, --recursive          work recursively on directory, not on a single file\n"
            + "  -c, --encoding ENC       character set of the input text, e.g. utf-8 or latin1\n"
            + "  -b                       assume that a single line break marks the end of a paragraph\n"
            + "  -l, --language LANG      the language code of the text, e.g. en for English, en-GB for British English\n"
            + "  --list                   print all available languages and exit\n"
            + "  -adl, --autoDetect       auto-detect the language of the input text - note this will not detect\n"
            + "                           variants like 'English (US)', so you will not get spell checking for\n"
            + "                           languages with variants\n"
            + "  -m, --mothertongue LANG  the language code of your first language, used to activate false-friend checking\n"
            + "  -d, --disable RULES      a comma-separated list of rule ids to be disabled (use no spaces between ids)\n"
            + "  -e, --enable RULES       a comma-separated list of rule ids to be enabled (use no spaces between ids)\n"
            + "  -eo, --enabledonly       disable all rules except those enabled explicitly in -e\n"
            + "  --enablecategories CAT   a comma-separated list of category ids to be enabled (use no spaces between ids)\n"
            + "  --disablecategories CAT  a comma-separated list of category ids to be disabled (use no spaces between ids)\n"
            + "  -t, --taggeronly         don't check, but only print text analysis (sentences, part-of-speech tags)\n"
            + "  -u, --list-unknown       also print a summary of words from the input that LanguageTool doesn't know\n"
            + "  -b2, --bitext            check bilingual texts with a tab-separated input file,\n"
            + "                           see http://languagetool.wikidot.com/checking-translations-bilingual-texts\n"
            + "  --json                   print results as JSON (see https://languagetool.org/http-api/#!/default/post_check)\n"
            + "  -p, --profile            print performance measurements\n"
            + "  -v, --verbose            print text analysis (sentences, part-of-speech tags) to STDERR\n"
            + "  --version                print LanguageTool version number and exit\n"
            + "  -a, --apply              automatically apply suggestions if available, printing result to STDOUT\n"
            + "                           NOTE: only use with very robust rules, as this will otherwise introduce new errors\n"
            + "  --rulefile FILE          use an additional grammar file; if the filename contains a known language code,\n"
            + "                           it is used in addition of standard rules\n"
            + "  --remoterules FILE       configure rules depending on external services via a JSON file (optional)\n"
            + "  --falsefriends FILE      use external false friend file to be used along with the built-in rules\n"
            + "  --bitextrules  FILE      use external bitext XML rule file (useful only in bitext mode)\n"
            + "  --languagemodel DIR      a directory with e.g. 'en' sub directory (i.e. a language code) that contains\n"
            + "                           '1grams'...'3grams' sub directories with Lucene indexes with\n"
            + "                           ngram occurrence counts; activates the confusion rule if supported;\n"
            + "                           see https://dev.languagetool.org/finding-errors-using-n-gram-data\n"
            + "  --fasttextmodel FILE     fasttext language detection model (optional), see https://fasttext.cc/docs/en/language-identification.html\n"
            + "  --fasttextbinary FILE    fasttext executable (optional), see https://fasttext.cc/docs/en/support.html\n"
            + "  --xmlfilter              [deprecated] remove XML/HTML elements from input before checking\n"
            + "  --line-by-line           work on file line by line (for development, e.g. inside an IDE)\n"
            + "  --enable-temp-off        enable all temp_off rules (for testing and development)\n"
            + "  --clean-overlapping      clean overlapping matches (show only the highest priority match)\n"
            + "  --level level            enable the given level (currently only 'PICKY')"
    );
  }

  private void checkArguments(String option, int argParsingPos, String[] args) {
    if (argParsingPos + 1 >= args.length) {
      throw new IllegalArgumentException("Missing argument to " + option + " command line option.");
    }
  }

  private Language getLanguage(String userSuppliedLangCode) {
    return Languages.getLanguageForShortCode(userSuppliedLangCode);
  }

}
