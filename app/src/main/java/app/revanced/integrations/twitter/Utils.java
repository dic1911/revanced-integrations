package app.revanced.integrations.twitter;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import java.util.Arrays;

import app.revanced.integrations.shared.settings.Setting;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.twitter.settings.SettingsActivity;

public class Utils {
    private static final Context ctx = app.revanced.integrations.shared.Utils.getContext();

    public static void startActivity() {
        Intent intent = new Intent(ctx, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    @SuppressWarnings("deprecation")
    private static String getStringPref(Setting<String> setting) {
        String value = PreferenceManager.getDefaultSharedPreferences(ctx).getString(setting.key, setting.defaultValue);
        if (value.isBlank()) {
            return setting.defaultValue;
        }
        return setting.key;
    }

    public static String getVideoFolder(String filename) {
        return getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }

    public static String[] addPref(String[] prefs, String pref) {
        String[] bigger = Arrays.copyOf(prefs, prefs.length+1);
        bigger[prefs.length] = pref;
        return bigger;
    }
}
