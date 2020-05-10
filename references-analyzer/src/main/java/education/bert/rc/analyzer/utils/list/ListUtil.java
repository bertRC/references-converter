package education.bert.rc.analyzer.utils.list;

import java.util.List;

public class ListUtil {
    public static <E> E getFirst(List<E> list, E emptyElement) {
        if (list == null || list.isEmpty()) {
            return emptyElement;
        }
        return list.get(0);
    }
}
