package ph.hostev.paul.paginglibrarytry.paging;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelItemKeyedDataSource extends ItemKeyedDataSource<Integer, Model> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Model> callback) {
        callback.onResult(MockUtil.mockItemKeyed(1, params.requestedLoadSize), 1, 500);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
        callback.onResult(MockUtil.mockItemKeyedAfter(params.key, params.requestedLoadSize));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
        callback.onResult(MockUtil.mockItemKeyed(params.key, params.requestedLoadSize));
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Model item) {
        return item.getKey();
    }
}
