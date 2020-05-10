package education.bert.rc.analyzer.repository;

import java.util.List;

public class Author {
    private final String secondName;
    private final List<String> initials;

    public Author(String secondName, List<String> initials) {
        this.secondName = secondName;
        this.initials = initials;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<String> getInitials() {
        return initials;
    }

    @Override
    public String toString() {
        return "Author{" +
                "secondName='" + secondName + '\'' +
                ", initials=" + initials +
                '}';
    }
}
