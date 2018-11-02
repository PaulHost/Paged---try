package ph.hostev.paul.paginglibrarytry.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import ph.hostev.paul.paginglibrarytry.model.Model;

public class PageKeyedAdapter extends ListAdapter<PageKeyedAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(itemLayout(viewGroup));
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if (count != 0) {
            count++;
        }
        return count;
    }

    protected class ViewHolder extends ListAdapter.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void build(Model model, int position) {
            switch (model.getType()) {
                case Model.MODEL_TYPE:
                    progressBar.setVisibility(View.GONE);
                    name.setVisibility(View.VISIBLE);
                    name.setText(String.format("%d key=%d %s", position, model.getKey(), model.getName()));
                    break;
                case Model.PROGRESS_TYPE:
                    name.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void placeholder() {
            progressBar.setVisibility(View.VISIBLE);
            name.setVisibility(View.GONE);
        }
    }
}
