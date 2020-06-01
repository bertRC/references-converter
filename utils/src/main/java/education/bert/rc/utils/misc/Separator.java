package education.bert.rc.utils.misc;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private Separator() {
    }

    private static final String regex = "(\r\n)|(\n\r)|[\r\n]";

    public static final String DEFAULT_LINE_SEPARATOR = "\r\n";

    public static List<String> separate(String text) {
        return Arrays.asList(text.split(regex));
    }

    public static String getLineSeparator(String text) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return DEFAULT_LINE_SEPARATOR;
    }

    public static String join(List<String> strings, String lineSeparator) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.size() - 1; i++) {
            result.append(strings.get(i).replaceAll("<br>", lineSeparator)).append(lineSeparator);
        }
        result.append(strings.get(strings.size() - 1).replaceAll("<br>", lineSeparator));
        return result.toString();
    }
}
