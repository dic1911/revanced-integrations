package app.revanced.integrations.twitter.settings.featureflags;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.patches.FeatureSwitchPatch;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.e<CustomAdapter.ViewHolder> {
    TextView flagTextView;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch enabled;

    ArrayList<FeatureFlag> flags;
    OnItemClickListener itemClickListener;
    OnItemCheckedChangeListener itemCheckedChangeListener;

    public CustomAdapter(ArrayList<FeatureFlag> flags) {
        this.flags = flags;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemCheckedChangeListener(OnItemCheckedChangeListener itemCheckedChangeListener) {
        this.itemCheckedChangeListener = itemCheckedChangeListener;
    }

    public final class ViewHolder extends RecyclerView.c0 {
        // a0() = getAdapterPosition()
        public ViewHolder(CustomAdapter adapter, View view) {
            super(view);
            flagTextView = view.findViewById(Utils.getResourceIdentifier("textView", "id"));

            view.setOnClickListener(view1 -> {
                if (itemClickListener!=null) itemClickListener.onClick(a0());
            });

            enabled = view.findViewById(Utils.getResourceIdentifier("enabled", "id"));
            enabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (itemCheckedChangeListener!=null) itemCheckedChangeListener.onCheck(b, a0());
                }
            });
        }
    }

    @Override
    public RecyclerView.c0 I(int i, RecyclerView recyclerView){
        View view = LayoutInflater.from(recyclerView.getContext())
                .inflate(
                        Utils.getResourceIdentifier("item_row", "layout"),
                        recyclerView, false
                );
        return new ViewHolder(this, view);
    }

    @Override
    public void F(ViewHolder viewHolder, int i) {
        flagTextView.setText(flags.get(i).getName());
        enabled.setChecked(flags.get(i).getEnabled());
    }

    @Override
    public int k() {
        return flags.size();
    }
}

interface OnItemCheckedChangeListener {
    void onCheck(boolean checked, int position);
}

interface OnItemClickListener {
    void onClick(int position);
}