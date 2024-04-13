package app.revanced.integrations.twitter.settings.featureflags;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.settings.SettingsActivity;

public class FeatureFlagsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        rc.setAdapter(new CustomAdapter());

        return view;
    }
}
