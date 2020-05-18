package education.bert.rc.analyzer.regexelements;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseElement {

    @Getter
    private final Pattern pattern;

    public BaseElement(@NonNull String regex) {
        pattern = Pattern.compile(regex);
    }

    public List<String> find(@NonNull String text) {
        List<String> results = new ArrayList<>();
        final Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            results.add(matcher.group());
        }
        if (results.isEmpty()) {
            results.add("");
        }
        return results;
    }

    @Override
    public String toString() {
        return pattern.pattern();
    }
}
