package app.revanced.integrations.twitter.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.shared.settings.StringSetting;

@SuppressWarnings("deprecation")
public class SettingsActivity extends Activity {
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(Utils.getResourceIdentifier("preference_fragment_activity", "layout"));
        Toolbar toolbar = findViewById(Utils.getResourceIdentifier("toolbar", "id"));
        toolbar.setNavigationIcon(Utils.getResourceIdentifier("ic_vector_arrow_left", "drawable"));
        toolbar.setTitle("Mod Settings");
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getFragmentManager().beginTransaction().add(Utils.getResourceIdentifier("fragment_container", "id"), new Screen()).commit();
    }

    public static class Screen extends PreferenceFragment {
        private Context context;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getContext();
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            screen.addPreference(editTextPreference(
                    "Download Subfolder",
                    "The subfolder to download videos to (Movies/[Subfolder])",
                    Settings.VID_SUBFOLDER
            ));

            setPreferenceScreen(screen);
        }

        private Preference editTextPreference(String title, String summary, StringSetting setting) {
            EditTextPreference preference = new EditTextPreference(context);
            preference.setTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);

            return preference;
        }
    }
}
