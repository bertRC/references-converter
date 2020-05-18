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

    @NonNull private String bibTemplate;
    @NonNull private String authorTemplate;
    @NonNull private String firstAuthorTemplate;
    @NonNull private boolean andRequired;
    @NonNull private String volumeTemplateLatin;
    @NonNull private String volumeTemplateCyrillic;
    @NonNull private String numberTemplateLatin;
    @NonNull private String numberTemplateCyrillic;
    @NonNull private String otherTemplate;
    @NonNull private String pageTemplateLatin;
    @NonNull private String pageTemplateCyrillic;

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
    public String generate(@NonNull Bibliography bibliography) {
        if (!bibliography.isEmpty()) {
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
    public List<String> generate(@NonNull List<Bibliography> bibliographies) {
        return bibliographies.parallelStream().map(this::generate).collect(Collectors.toList());
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
        return authorTemplate.replaceFirst(initialsRegex, initialsResult.toString())
                .replaceFirst(secondNameRegex, author.getSecondName());
    }

    private static String prepareFirstAuthor(@NonNull List<Author> authors, @NonNull String authorTemplate) {
        if (!authors.isEmpty()) {
            return prepareAuthor(authors.get(0), authorTemplate);
        }
        return "";
    }

    private static String prepareAuthors(@NonNull List<Author> authors, @NonNull String authorTemplate,
                                         @NonNull String andString, boolean andRequired) {
        if (!authors.isEmpty()) {
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

    private static String prepareSingle(@NonNull String value, @NonNull String singleTemplate) {
        if (!value.isEmpty()) {
            return singleTemplate.replaceFirst(simpleReplacement, value);
        }
        return "";
    }
}
