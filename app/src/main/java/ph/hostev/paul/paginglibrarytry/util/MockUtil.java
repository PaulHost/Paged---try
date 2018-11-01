package ph.hostev.paul.paginglibrarytry.util;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;

public final class MockUtil {
    public static List<Model> mockPageKeyed(int page) {
        List<Model> models = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            models.add(new Model(Integer.parseInt("" + page + i), "page=" + page + " item=" + i));
        }
        return models;
    }

    public static List<Model> mockItemKeyed(Integer key, int requestedLoadSize) {
        List<Model> models = new ArrayList<>();
        key++;
        for (; key < requestedLoadSize + key && models.size() < requestedLoadSize && key < 501; key++) {
            models.add(new Model(key, " item=" + key));
        }
        return models;
    }

    public static List<Model> mockItemKeyedAfter(Integer key, int requestedLoadSize) {
        List<Model> models = new ArrayList<>();
        key--;
        for (; key > 0 && models.size() < requestedLoadSize + 1; key--) {
            models.add(new Model(key, " item=" + key));
        }
        return models;
    }
}
