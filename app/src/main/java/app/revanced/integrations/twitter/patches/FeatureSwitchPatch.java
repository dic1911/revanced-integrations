package app.revanced.integrations.twitter.patches;

import java.util.*;
import app.revanced.integrations.twitter.Pref;

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


    public static void load() {}
}