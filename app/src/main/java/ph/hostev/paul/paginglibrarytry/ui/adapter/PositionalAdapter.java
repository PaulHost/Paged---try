package ph.hostev.paul.paginglibrarytry.ui.adapter;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import ph.hostev.paul.paginglibrarytry.model.Model;

public class PositionalAdapter extends ListAdapter<PositionalAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(itemLayout(viewGroup));
    }

    protected class ViewHolder extends ListAdapter.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void build(Model model, int position) {
                    progressBar.setVisibility(View.GONE);
                    name.setVisibility(View.VISIBLE);
                    name.setText(String.format("%d key=%d %s", position, model.getKey(), model.getName()));
        }

        @Override
        public void placeholder() {
            progressBar.setVisibility(View.GONE);
            name.setVisibility(View.VISIBLE);
            name.setText("... ... ... ...");
        }
    }
}
