package ph.hostev.paul.paginglibrarytry.paging;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelItemKeyedDataSource extends ItemKeyedDataSource<Integer, Model> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Model> callback) {
        List<Model> models = MockUtil.mockItemKeyedAfter(1, params.requestedLoadSize);
        callback.onResult(models, 1, 500);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
        int nextKey = params.key + 1;
        List<Model> models = MockUtil.mockItemKeyedAfter(nextKey, params.requestedLoadSize);
        callback.onResult(models);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Model> callback) {
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
