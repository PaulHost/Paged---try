package ph.hostev.paul.paginglibrarytry.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelPageKeyedDataSource extends PageKeyedDataSource<Integer, Model> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Model> callback) {
        callback.onResult(MockUtil.mock(0), null, 1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Model> callback) {
        callback.onResult(MockUtil.mock(params.key), params.key-1);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Model> callback) {
        callback.onResult(MockUtil.mock(params.key), params.key+1);
    }

}
