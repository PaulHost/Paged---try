package ph.hostev.paul.paginglibrarytry.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Model {
    public static final int MODEL_TYPE = 0;
    public static final int PROGRESS_TYPE = 1;

    @NonNull
    private final Integer type;

    @NonNull
    private final String name;

    @NonNull
    private final Integer key;

    public Model(@NonNull Integer type) {
        this(type, 0, "");
    }

    public Model(@NonNull Integer key, @NonNull String name) {
        this(Model.MODEL_TYPE, key, name);
    }

    public Model(@NonNull Integer type, @NonNull Integer key, @NonNull String name) {
        this.type = type;
        this.key = key;
        this.name = name;
    }

    @NonNull
    public Integer getType() {
        return type;
    }

    @NonNull
    public Integer getKey() {
        return key;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(getType(), model.getType()) &&
                Objects.equals(getName(), model.getName()) &&
                Objects.equals(getKey(), model.getKey());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getType(), getName(), getKey());
    }
}
