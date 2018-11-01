package ph.hostev.paul.paginglibrarytry.util;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;

public final class MockUtil {
    public static List<Model> mock(int page) {
        List<Model> models = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            models.add(new Model(Integer.parseInt("" + page + i), "page=" + page + " item=" + i));
        }
        return models;
    }
}
