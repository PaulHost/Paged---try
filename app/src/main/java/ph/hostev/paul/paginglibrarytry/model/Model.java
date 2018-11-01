package ph.hostev.paul.paginglibrarytry.model;

import android.support.annotation.NonNull;

public class Model {
    @NonNull
    private final String name;

    @NonNull
    private final Integer key;

    public Model(@NonNull Integer key, @NonNull String name) {
        this.key = key;
        this.name = name;
    }

    @NonNull
    public Integer getKey() {
        return key;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
