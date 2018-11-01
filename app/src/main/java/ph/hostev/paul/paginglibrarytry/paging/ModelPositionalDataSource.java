package ph.hostev.paul.paginglibrarytry.paging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelPositionalDataSource extends PositionalDataSource<Model> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Model> callback) {
        callback.onResult(MockUtil.mock(1), 1);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Model> callback) {
        callback.onResult(MockUtil.mock(params.startPosition));
    }
}
