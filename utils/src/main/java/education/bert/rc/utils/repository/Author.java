package education.bert.rc.utils.repository;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(secondName, author.secondName) &&
                Objects.equals(initials, author.initials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondName, initials);
    }
}
