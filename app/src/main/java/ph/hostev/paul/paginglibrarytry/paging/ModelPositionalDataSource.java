package ph.hostev.paul.paginglibrarytry.paging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.util.MockUtil;

public class ModelPositionalDataSource extends PositionalDataSource<Model> {
    @NonNull
    private int totalCount = 500;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Model> callback) {
        int position = 0;
        List<Model> models = MockUtil.mockPositional(position, params.pageSize, totalCount);
        callback.onResult(models, position, totalCount);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Model> callback) {
        callback.onResult(MockUtil.mockPositional(params.startPosition, params.loadSize, totalCount));
    }
}
