package education.bert.rc.generator.templates;

import education.bert.rc.utils.repository.Author;
import education.bert.rc.utils.repository.Bibliography;
import lombok.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor()
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseTemplate extends Template {

    private String bibTemplate;
    private String authorTemplate;
    private String firstAuthorTemplate;
    private boolean andRequired;
    private String volumeTemplateLatin;
    private String volumeTemplateCyrillic;
    private String numberTemplateLatin;
    private String numberTemplateCyrillic;
    private String otherTemplate;
    private String pageTemplateLatin;
    private String pageTemplateCyrillic;

    private final static String simpleReplacement = "\\?";
    private final static String firstAuthorReplacement = "<firstAuthor>";
    private final static String titleReplacement = "<title>";
    private final static String authorsReplacement = "<authors>";
    private final static String journalReplacement = "<journal>";
    private final static String publisherReplacement = "<publisher>";
    private final static String yearReplacement = "<year>";
    private final static String volumeReplacement = "<vol>";
    private final static String numberReplacement = "<num>";
    private final static String otherReplacement = "<other>";
    private final static String pageReplacement = "<pages>";
    private final static String initialsRegex = "\\b[A-ZА-Я](?:(?<intermediate>\\W*)[A-ZА-Я])*\\b";
    private final static Pattern initialsPattern = Pattern.compile(initialsRegex);
    private final static String secondNameRegex = "\\b[A-ZА-Я][a-zа-я]+\\b";

    @Override
    public String generate(Bibliography bibliography) {
        if (bibliography != null && !bibliography.isEmpty()) {
            final String andString = bibliography.getLanguage().equals("latin") ? " and " : " и ";
            final String volumeTemplate = bibliography.getLanguage().equals("latin") ?
                    volumeTemplateLatin : volumeTemplateCyrillic;
            final String numberTemplate = bibliography.getLanguage().equals("latin") ?
                    numberTemplateLatin : numberTemplateCyrillic;
            final String pageTemplate = bibliography.getLanguage().equals("latin") ?
                    pageTemplateLatin : pageTemplateCyrillic;
            return bibTemplate
                    .replaceFirst(firstAuthorReplacement,
                            prepareFirstAuthor(bibliography.getAuthors(), firstAuthorTemplate))
                    .replaceFirst(titleReplacement, bibliography.getTitle().getText())
                    .replaceFirst(authorsReplacement,
                            prepareAuthors(bibliography.getAuthors(), authorTemplate, andString, andRequired))
                    .replaceFirst(journalReplacement, bibliography.getJournal().getText())
                    .replaceFirst(publisherReplacement, bibliography.getJournal().getText())
                    .replaceFirst(yearReplacement, bibliography.getYear().getText())
                    .replaceFirst(volumeReplacement,
                            prepareSingle(bibliography.getVolEntry().getValue(), volumeTemplate))
                    .replaceFirst(numberReplacement,
                            prepareSingle(bibliography.getNumEntry().getValue(), numberTemplate))
                    .replaceFirst(otherReplacement, bibliography.getOther().getText())
                    .replaceFirst(pageReplacement,
                            prepareSingle(bibliography.getPageEntry().getValue(), pageTemplate));
        }
        return "";
    }

    @Override
    public List<String> generate(List<Bibliography> bibliographies) {
        return bibliographies.parallelStream().map(this::generate).collect(Collectors.toList());
    }

    private static String prepareAuthor(Author author, String authorTemplate) {
        if (author != null && author.getSecondName() != null) {
            final Matcher matcher = initialsPattern.matcher(authorTemplate);
            String intermediate = matcher.find() ? matcher.group("intermediate") : "";
            if (intermediate == null) intermediate = "";
            StringBuilder initialsResult = new StringBuilder();
            if (author.getInitials() != null && !author.getInitials().isEmpty()) {
                initialsResult = new StringBuilder(author.getInitials().get(0));
                for (int i = 1; i < author.getInitials().size(); i++) {
                    initialsResult.append(intermediate).append(author.getInitials().get(i));
                }
            }
            return authorTemplate.replaceFirst(initialsRegex, initialsResult.toString())
                    .replaceFirst(secondNameRegex, author.getSecondName());
        }
        return "";
    }

    private static String prepareFirstAuthor(List<Author> authors, String authorTemplate) {
        if (authors != null && !authors.isEmpty()) {
            return prepareAuthor(authors.get(0), authorTemplate);
        }
        return "";
    }

    private static String prepareAuthors(List<Author> authors, String authorTemplate, String andString,
                                         boolean andRequired) {
        if (authors != null && !authors.isEmpty()) {
            StringBuilder authorsResult = new StringBuilder(prepareAuthor(authors.get(0), authorTemplate));
            for (int i = 1; i < authors.size(); i++) {
                if (andRequired && i == authors.size() - 1) {
                    authorsResult.append(andString);
                } else {
                    authorsResult.append(", ");
                }
                authorsResult.append(prepareAuthor(authors.get(i), authorTemplate));
            }
            return authorsResult.toString();
        }
        return "";
    }

    private static String prepareSingle(String value, String singleTemplate) {
        if (value != null && !value.isEmpty()) {
            return singleTemplate.replaceFirst(simpleReplacement, value);
        }
        return "";
    }
}
