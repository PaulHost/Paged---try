package ph.hostev.paul.paginglibrarytry.ui;

import android.annotation.SuppressLint;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ph.hostev.paul.paginglibrarytry.R;
import ph.hostev.paul.paginglibrarytry.model.Model;
import ph.hostev.paul.paginglibrarytry.paging.ModelDataSourceFactory;
import ph.hostev.paul.paginglibrarytry.paging.ModelPageKeyedDataSource;
import ph.hostev.paul.paginglibrarytry.ui.adapter.ListAdapter;

@SuppressLint("CheckResult")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    @Nullable
    Unbinder unbinder;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @Nullable
    ListAdapter adapter;
    @NonNull
    PagedList.Config config;
    @NonNull
    Flowable<PagedList<Model>> listFlowable;
    @Nullable
    private CompositeDisposable composite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        if (recyclerView != null) {
            adapter = new ListAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }

        config = new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(25)
                .setPrefetchDistance(50)
                .setPageSize(100)
                .build();

        listFlowable = pagedListFlowable(ModelPageKeyedDataSource.class);
        button.setText(ModelPageKeyedDataSource.class.getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            addForDispose(listFlowable.subscribe(adapter::submitList));
        }
    }

    @NonNull
    Flowable<PagedList<Model>> pagedListFlowable(Class aClass) {
        return new RxPagedListBuilder<>(new ModelDataSourceFactory(aClass), config)
                .buildFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @OnClick(R.id.button)
    public void onButtonClick(View view) {
        PopupMenu popup = new PopupMenu(MainActivity.this, button);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            try {
                String name = ModelDataSourceFactory.class.getPackage().getName() + ".Model" + String.valueOf(item.getTitle());
                recyclerView.setAdapter(null);
                adapter = new ListAdapter();
                recyclerView.setAdapter(adapter);
                listFlowable = pagedListFlowable(Class.forName(name));
                button.setText(item.getTitle());
            } catch (ClassNotFoundException e) {
                Toast.makeText(MainActivity.this, item.getTitle() + "can't be shown", Toast.LENGTH_SHORT).show();
                Log.e(TAG, e.getMessage() + '\n' + e.getException());
            } finally {
                if (adapter != null) {
                    addForDispose(listFlowable.subscribe(adapter::submitList));
                }
            }
            return true;
        });
        popup.show();
    }

    protected final void addForDispose(@NonNull Disposable disposable) {
        if (composite == null) {
            composite = new CompositeDisposable();
        }
        composite.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (composite != null) {
            composite.dispose();
        }
    }
}
