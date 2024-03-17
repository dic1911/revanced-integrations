package app.revanced.integrations.twitter;

import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.Settings;
import java.util.ArrayList;
import java.util.Arrays;

public class Pref {

    public static boolean isChirpFontEnabled() {return Utils.getBooleanPerf(Settings.MISC_FONT);}

    public static String getPublicFolder() { return Utils.getStringPref(Settings.VID_PUBLIC_FOLDER);}

    public static String getVideoFolder(String filename) {
        return Utils.getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }

    public static ArrayList liveThread(ArrayList fleets) {
        if (Utils.getBooleanPerf(Settings.TIMELINE_HIDE_LIVETHREADS)) { return null; }
        return fleets;
    }

    public static boolean hideBanner() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_BANNER);
    }


}