package app.revanced.integrations.twitter.patches;

import java.util.*;
import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.Settings;

public class FeatureSwitchPatch {
    private static HashMap<String,Object> FLAGS = new HashMap<String,Object>();

    private static void addFlag(String flag, Object val){
        FLAGS.put(flag,val);
    }

    private static void fabMenu() {
        addFlag("android_compose_fab_menu_enabled", Pref.hideFABBtn());
    }

    private static void chirpFont() {
        addFlag("af_ui_chirp_enabled", Pref.isChirpFontEnabled());
    }

    private static void viewCount() {
        addFlag("view_counts_public_visibility_enabled", Pref.hideViewCount());
    }

    private static void bookmarkInTimeline() {
        addFlag("bookmarks_in_timelines_enabled", Pref.hideInlineBookmark());
    }

    private static void immersivePlayer() {
        addFlag("explore_relaunch_enable_immersive_player_across_twitter", Pref.hideImmersivePlayer());
    }

    public static Object flagInfo(String flag,Object def){
        try{
            if (FLAGS.containsKey(flag)){
                Object val = FLAGS.get(flag);
                return val;
            }
        }
        catch (Exception ex){

        }
        return def;
    }


    public static void load() {
            for (Map.Entry<String, Boolean> item : getFLAGS().entrySet()) {
                String flag = item.getKey();
                Boolean value = item.getValue();
                addFlag(flag, value);
            }
    }


    public static String[] getFlagsString() {
        String[] out = new String[]{};
        String flags = Utils.getStringPref(Settings.MISC_FEATURE_FLAGS);
        if (!flags.isEmpty()) {
            out = flags.split(",");
        }
        return out;
    }

    public static HashMap<String, Boolean> getFLAGS() {
        HashMap<String, Boolean> out = new HashMap<>();

        String flags = Utils.getStringPref(Settings.MISC_FEATURE_FLAGS);

        if (!flags.isEmpty()) {
            for (String item : flags.split(",")) {
                    String[] flag = item.split(":");
                    out.put(flag[0], Boolean.getBoolean(flag[1]));
            }
        }

        return out;
    }

}