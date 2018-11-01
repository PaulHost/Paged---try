package ph.hostev.paul.paginglibrarytry.ui.adapter;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.hostev.paul.paginglibrarytry.R;
import ph.hostev.paul.paginglibrarytry.model.Model;

@SuppressLint("DefaultLocale")
public class ListAdapter extends PagedListAdapter<Model, ListAdapter.ViewHolder> {

    public ListAdapter() {
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
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Model model = getItem(i);
        if (model != null) {
            holder.name.setText(String.format("%d key=%d %s", i + 1, model.getKey(), model.getName()));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
