package ph.hostev.paul.paginglibrarytry.paging;

import androidx.paging.ItemKeyedDataSource;
import androidx.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelItemKeyedDataSource extends ItemKeyedDataSource<Integer, Model> {
    private static final String TAG = "ItemKeyed_Mock";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Model> callback) {
        Log.d(TAG, "loadInitial: requestedInitialKey=" + params.requestedInitialKey + " " + "requestedLoadSize = " + params.requestedLoadSize + " placeholdersEnabled=" + params.placeholdersEnabled);
        int initialKey = params.requestedInitialKey == null ? 0 : params.requestedInitialKey;
        List<Model> models = MockUtil.mockItemKeyedAfter(initialKey, params.requestedLoadSize);
        callback.onResult(models, initialKey, 500);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
        Log.d(TAG, "loadAfter");
        int nextKey = params.key + 1;
        List<Model> models = MockUtil.mockItemKeyedAfter(nextKey, params.requestedLoadSize);
        callback.onResult(models);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
        Log.d(TAG, "loadBefore");
        int prevKey = params.key - 1;
        List<Model> models = MockUtil.mockItemKeyedBefore(prevKey, params.requestedLoadSize);
        callback.onResult(models);
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Model item) {
        return item.getKey();
    }
}
