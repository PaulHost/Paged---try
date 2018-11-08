package ph.hostev.paul.paginglibrarytry.ui.adapter;

import android.annotation.SuppressLint;
import androidx.paging.PagedListAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.hostev.paul.paginglibrarytry.R;
import ph.hostev.paul.paginglibrarytry.model.Model;

@SuppressLint("DefaultLocale")
public abstract class ListAdapter<Holder extends ListAdapter.ViewHolder> extends PagedListAdapter<Model, Holder> {
    protected static final String TAG = ListAdapter.class.getCanonicalName();

    public static ListAdapter getInstance(@NonNull String dataSource) {
        ListAdapter adapter = null;
        switch (dataSource) {
            case "PageKeyedDataSource":
                adapter = new PageKeyedAdapter();
                break;
            case "PositionalDataSource":
                adapter = new PositionalAdapter();
                break;
            case "ItemKeyedDataSource":
                adapter = new ItemKeyedAdapter();
                break;
        }
        return adapter;
    }

    ListAdapter() {
        super(new DiffUtil.ItemCallback<Model>() {
            @Override
            public boolean areItemsTheSame(@NonNull Model model, @NonNull Model t1) {
                return model == t1;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Model model, @NonNull Model t1) {
                return model.equals(t1);
            }
        });
    }

    @NonNull
    protected View itemLayout(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Model model = null;
        try {
            model = getItem(i);
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (model != null) {
                holder.build(model, i);
            } else {
                holder.placeholder();
            }
        }
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_progress)
        ProgressBar progressBar;
        @BindView(R.id.item_name)
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void build(Model model, int position);

        public abstract void placeholder();
    }
}
