package app.revanced.integrations.twitter;

import app.revanced.integrations.twitter.settings.Settings;

import java.util.ArrayList;

public class Pref {

    public static boolean isChirpFontEnabled() {return Utils.getBooleanPerf(Settings.MISC_FONT);}

    public static String getPublicFolder() { return Utils.getStringPref(Settings.VID_PUBLIC_FOLDER);}

    public static String getVideoFolder(String filename) {
        return Utils.getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }

    public  static ArrayList hideRecommendedUsers(ArrayList users) {
        if (Utils.getBooleanPerf(Settings.MISC_HIDE_RECOMMENDED_USERS)) { return null; }
        return users;
    }

    public static ArrayList liveThread(ArrayList fleets) {
        if (Utils.getBooleanPerf(Settings.TIMELINE_HIDE_LIVETHREADS)) { return null; }
        return fleets;
    }

    public static boolean hideBanner() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_BANNER);
    }

    public static int hideForYou() {
        return Utils.getBooleanPerf(Settings.TIMELINE_HIDE_FORYOU)?34:17;
    }

    public static boolean hideFAB() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_FAB);
    }

    public static boolean hidePromotedTrend(Object data) {
         if(data!=null && Utils.getBooleanPerf(Settings.ADS_HIDE_PROMOTED_TRENDS)){
            return true;
        }
         return false;
    }
    public static boolean hideCommNotes() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_COMM_NOTES);
    }

    public static boolean hideViewCount() {
        return !Utils.getBooleanPerf(Settings.MISC_HIDE_VIEW_COUNT);
    }
}