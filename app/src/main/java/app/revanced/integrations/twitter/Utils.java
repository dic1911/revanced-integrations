package app.revanced.integrations.twitter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import app.revanced.integrations.shared.settings.Setting;
import app.revanced.integrations.shared.settings.preference.SharedPrefCategory;
import app.revanced.integrations.twitter.settings.SettingsActivity;
import app.revanced.integrations.twitter.settings.SettingsStatus;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static final Context ctx = app.revanced.integrations.shared.Utils.getContext();
    private static SharedPrefCategory sp = new SharedPrefCategory("com.twitter.android_preferences");

    static {
        SettingsStatus.load();
    }


    private static void startActivity(Class cls) {
        Intent intent = new Intent(ctx, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    private static void startActivityFromClassName(String className){
        try {
            Class<?> clazz = Class.forName(className);
            startActivity(clazz);
        }catch (Exception unused) {

        }
    }


    public static void startSettingsActivity(){
        startActivity(SettingsActivity.class);
    }

    public static void startUndoPostActivity(){
        String className = "com.twitter.feature.subscriptions.settings.undotweet.UndoTweetSettingsActivity";
        startActivityFromClassName(className);
    }

    public static void startAppIconNNavIconActivity(){
        String className = "com.twitter.feature.subscriptions.settings.extras.ExtrasSettingsActivity";
        startActivityFromClassName(className);
    }

    public static String getStringPref(Setting<String> setting) {
        String value = sp.getString(setting.key, setting.defaultValue);
        if (value.isBlank()) {
            return setting.defaultValue;
        }
        return value;
    }

    public static Boolean getBooleanPerf(Setting<Boolean> setting) {
        return sp.getBoolean(setting.key, setting.defaultValue);
    }

    public static String[] addPref(String[] prefs, String pref) {
        String[] bigger = Arrays.copyOf(prefs, prefs.length+1);
        bigger[prefs.length] = pref;
        return bigger;
    }

}
