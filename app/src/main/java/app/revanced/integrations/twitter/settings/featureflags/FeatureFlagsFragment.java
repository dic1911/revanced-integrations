package app.revanced.integrations.twitter.settings.featureflags;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.twitter.settings.SettingsActivity;

import java.util.ArrayList;

public class FeatureFlagsFragment extends Fragment {
    ArrayList<FeatureFlag> flags;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flags = new ArrayList<>();
        String flagsPref = app.revanced.integrations.twitter.Utils.getStringPref(Settings.MISC_FEATURE_FLAGS);
        if (!flagsPref.isEmpty()) {
            for (String flag : flagsPref.split(",")) {
                String[] item = flag.split(":");
                flags.add(new FeatureFlag(item[0], Boolean.valueOf(item[1])));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SettingsActivity.toolbar.setTitle("Feature Flags");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(Utils.getResourceIdentifier("feature_flags_view", "layout"), container, false);

        RecyclerView rc = view.findViewById(Utils.getResourceIdentifier("list", "id"));
        rc.setLayoutManager(new LinearLayoutManager(1));

        CustomAdapter adapter = new CustomAdapter(flags);

        adapter.setItemClickListener(position -> {
        });

        adapter.setItemCheckedChangeListener((checked, position) -> {
            FeatureFlag flag = flags.get(position);
            flags.set(position, new FeatureFlag(flag.getName(), checked));
        });

        rc.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        app.revanced.integrations.twitter.Utils.setStringPref(Settings.MISC_FEATURE_FLAGS, FeatureFlag.toStringPref(flags));
    }
}
