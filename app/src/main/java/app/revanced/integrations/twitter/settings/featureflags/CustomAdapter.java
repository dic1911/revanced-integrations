package app.revanced.integrations.twitter.settings.featureflags;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.patches.FeatureSwitchPatch;

import java.util.HashMap;

public class CustomAdapter extends RecyclerView.e<CustomAdapter.ViewHolder> {
    TextView flagTextView;
    String[] flags = FeatureSwitchPatch.getFlagsString();

    public final class ViewHolder extends RecyclerView.c0 {
        public ViewHolder(CustomAdapter adapter, View view) {
            super(view);
            flagTextView = view.findViewById(Utils.getResourceIdentifier("textView", "id"));
        }
    }

    @Override
    public RecyclerView.c0 I(int i, RecyclerView recyclerView) {
        View view = LayoutInflater.from(recyclerView.getContext())
                .inflate(
                        Utils.getResourceIdentifier("item_row", "layout"),
                        recyclerView, false
                );
        return new ViewHolder(this, view);
    }

    @Override
    public void F(ViewHolder viewHolder, int i) {
        flagTextView.setText(flags[i]);
    }

    @Override
    public int k() {
        return flags.length;
    }
}
