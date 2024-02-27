package app.revanced.integrations.twitter.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@SuppressWarnings("deprecation")
public class SettingsActivity extends Activity {
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        FrameLayout layout = new FrameLayout(getApplicationContext());
        layout.setLayoutParams(layoutParams);
        layout.setId(999999);

        setContentView(layout);

        getFragmentManager().beginTransaction().add(layout.getId(), new Screen()).commit();
    }

    public static class Screen extends PreferenceFragment {
        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Context context = getContext();

            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            EditTextPreference downloadPath = new EditTextPreference(context);
            downloadPath.setTitle("Dwnload Path");
            downloadPath.setSummary("Path to download videos to");
            downloadPath.setKey("download_path");
            downloadPath.setDialogTitle("Path");
            downloadPath.setDefaultValue("Twitter");
            downloadPath.setText("Twitter");
            screen.addPreference(downloadPath);

            EditTextPreference shareHost = new EditTextPreference(context);
            shareHost.setTitle("Share Link Host");
            shareHost.setSummary("Host to use when sharing links");
            shareHost.setKey("share_host");
            screen.addPreference(shareHost);

            setPreferenceScreen(screen);
        }
    }
}
