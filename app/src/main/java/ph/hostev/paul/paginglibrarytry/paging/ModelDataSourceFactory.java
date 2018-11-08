package ph.hostev.paul.paginglibrarytry.paging;

import androidx.paging.DataSource;
import androidx.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ph.hostev.paul.paginglibrarytry.model.Model;

public class ModelDataSourceFactory<Key> extends DataSource.Factory<Key, Model> {

    @NonNull
    private Class<? extends DataSource> dataSource;

    public ModelDataSourceFactory(@NonNull Class<? extends DataSource> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource<Key, Model> create() {
        try {
            Constructor<? extends DataSource> constructor = dataSource.getConstructor();
            constructor.setAccessible(true);
            return (DataSource<Key, Model>) constructor.newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(ModelDataSourceFactory.class.getCanonicalName(), e.getMessage());
        }

        return null;
    }
}
