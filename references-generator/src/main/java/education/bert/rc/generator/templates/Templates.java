package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Author;
import education.bert.rc.utils.repository.Bibliography;
import lombok.NonNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Templates {

    private final static String initialsRegex = "\\b[A-ZА-Я](?:(?<intermediate>\\W*)[A-ZА-Я])*\\b";
    private final static Pattern initialsPattern = Pattern.compile(initialsRegex);
    private final static String secondNameRegex = "\\b[A-ZА-Я][a-zа-я]+\\b";
    private final static Pattern firstAuthorPattern =
            Pattern.compile("<\\s*(?i)firstAuthor(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern titlePattern =
            Pattern.compile("<\\s*(?i)title(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern authorsPattern =
            Pattern.compile("<\\s*(?i)authors(?:\\s*=\\s*\"(?<template>[^\"]*)\")?(?<and>\\s*and(?:\\s*=\\s*\"(?<andValue>[^\"]*)\")?)?\\s*>");
    private final static Pattern journalPattern =
            Pattern.compile("<\\s*(?i)journal(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern yearPattern =
            Pattern.compile("<\\s*(?i)year(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern volumePattern =
            Pattern.compile("<\\s*(?i)vol(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern numberPattern =
            Pattern.compile("<\\s*(?i)num(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern otherPattern =
            Pattern.compile("<\\s*(?i)other(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");
    private final static Pattern pagePattern =
            Pattern.compile("<\\s*(?i)pages(?:\\s*=\\s*\"(?<template>[^\"]*)\")?\\s*>");

    public static String generate(@NonNull Bibliography bibliography, @NonNull String template) {
        String result = fulfillFirstAuthor(bibliography.getAuthors(), template);
        result = fulfillTitle(bibliography.getTitle().getText(), result);
        result = fulfillAuthors(bibliography.getAuthors(), result);
        result = fulfillJournal(bibliography.getJournal().getText(), result);
        result = fulfillYear(bibliography.getYear().getText(), result);
        result = fulfillVolume(bibliography.getVolEntry().getValue(), result);
        result = fulfillNumber(bibliography.getNumEntry().getValue(), result);
        result = fulfillOther(bibliography.getOther().getText(), result);
        result = fulfillPage(bibliography.getPageEntry().getValue(), result);
        return result;
    }

    private static String fulfillFirstAuthor(@NonNull List<Author> authors, @NonNull String template) {
        String result = template;
        final Matcher matcher = firstAuthorPattern.matcher(template);
        while (matcher.find()) {
            final String innerTemplate = matcher.group("template");
            result = result.replaceFirst(
                    firstAuthorPattern.pattern(),
                    prepareFirstAuthor(authors, innerTemplate == null ? "Ivanov I.I." : innerTemplate)
            );
        }
        return result;
    }

    private static String fulfillTitle(@NonNull String title, @NonNull String template) {
        return fulfillSome(title, template, titlePattern);
    }

    private static String fulfillAuthors(@NonNull List<Author> authors, @NonNull String template) {
        String result = template;
        final Matcher matcher = authorsPattern.matcher(template);
        while (matcher.find()) {
            final String innerTemplate = matcher.group("template");
            final String andAttribute = matcher.group("and");
            String andTemplate = matcher.group("andValue");
            if (andAttribute != null && andTemplate == null) andTemplate = "and";
            result = result.replaceFirst(
                    authorsPattern.pattern(),
                    prepareAuthors(authors, innerTemplate == null ? "Ivanov I.I." : innerTemplate, andTemplate)
            );
        }
        return result;
    }

    private static String fulfillJournal(@NonNull String journal, @NonNull String template) {
        return fulfillSome(journal, template, journalPattern);
    }

    private static String fulfillYear(@NonNull String year, @NonNull String template) {
        return fulfillSome(year, template, yearPattern);
    }

    private static String fulfillVolume(@NonNull String volume, @NonNull String template) {
        return fulfillSome(volume, template, volumePattern);
    }

    private static String fulfillNumber(@NonNull String number, @NonNull String template) {
        return fulfillSome(number, template, numberPattern);
    }

    private static String fulfillOther(@NonNull String other, @NonNull String template) {
        return fulfillSome(other, template, otherPattern);
    }

    private static String fulfillPage(@NonNull String page, @NonNull String template) {
        return fulfillSome(page, template, pagePattern);
    }

    private static String fulfillSome(@NonNull String value, @NonNull String template, @NonNull Pattern pattern) {
        String result = template;
        Pattern newPattern = pattern;
        if (value.endsWith(".")) {
            newPattern = Pattern.compile(pattern.pattern() + "\\.?");
        }
        final Matcher matcher = newPattern.matcher(template);
        while (matcher.find()) {
            final String innerTemplate = matcher.group("template");
            result = result.replaceFirst(
                    newPattern.pattern(),
                    prepareSome(value, innerTemplate == null ? "?" : innerTemplate)
            );
        }
        return result;
    }

    private static String prepareFirstAuthor(@NonNull List<Author> authors, @NonNull String authorTemplate) {
        if (!authors.isEmpty()) {
            return prepareAuthor(authors.get(0), authorTemplate);
        }
        return "";
    }

    private static String prepareAuthors(@NonNull List<Author> authors, @NonNull String authorTemplate,
                                         String andString) {
        if (!authors.isEmpty()) {
            StringBuilder authorsResult = new StringBuilder(prepareAuthor(authors.get(0), authorTemplate));
            for (int i = 1; i < authors.size(); i++) {
                if (andString != null && i == authors.size() - 1) {
                    authorsResult.append(" ").append(andString).append(" ");
                } else {
                    authorsResult.append(", ");
                }
                authorsResult.append(prepareAuthor(authors.get(i), authorTemplate));
            }
            return authorsResult.toString();
        }
        return "";
    }

    private static String prepareAuthor(@NonNull Author author, @NonNull String authorTemplate) {
        final Matcher matcher = initialsPattern.matcher(authorTemplate);
        String intermediate = matcher.find() ? matcher.group("intermediate") : "";
        if (intermediate == null) intermediate = "";
        StringBuilder initialsResult = new StringBuilder();
        if (!author.getInitials().isEmpty()) {
            initialsResult = new StringBuilder(author.getInitials().get(0));
            for (int i = 1; i < author.getInitials().size(); i++) {
                initialsResult.append(intermediate).append(author.getInitials().get(i));
            }
        }
        final String str = authorTemplate.replaceFirst(secondNameRegex, "#secondName#")
                .replaceFirst(initialsRegex, "#initials#");
        return str.replaceFirst("#secondName#", author.getSecondName())
                .replaceFirst("#initials#", initialsResult.toString());
    }

    private static String prepareSome(@NonNull String value, @NonNull String someTemplate) {
        if (!value.isEmpty()) {
            return someTemplate.replaceFirst("\\?", value);
        }
        return "";
    }
}
