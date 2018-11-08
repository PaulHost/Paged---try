package ph.hostev.paul.paginglibrarytry.paging;

import androidx.paging.PageKeyedDataSource;
import androidx.annotation.NonNull;
import android.util.Log;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelPageKeyedDataSource extends PageKeyedDataSource<Integer, Model> {
    private static final String TAG = "PageKeyed_Mock";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Model> callback) {
        Log.d(TAG, "loadInitial requestedLoadSize=" +
                params.requestedLoadSize +
                " placeholdersEnabled=" +
                params.placeholdersEnabled);
        callback.onResult(MockUtil.mockPageKeyed(0), null, 1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Model> callback) {
        Log.d(TAG, "loadBefore");
        callback.onResult(MockUtil.mockPageKeyed(params.key), params.key-1);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Model> callback) {
        Log.d(TAG, "loadAfter");
        callback.onResult(MockUtil.mockPageKeyed(params.key), params.key+1);
    }

}
