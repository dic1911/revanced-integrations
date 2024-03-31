package app.revanced.integrations.twitter.patches;

import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.SettingsStatus;
import android.util.Log;
import java.lang.reflect.Field;


public class TimelineEntry {
    private static boolean hideAds,hideGAds,hideWTF,hideCTS,hideCTJ,hideDetailedPosts,hideRBMK,hidePinnedPosts;
    static {
        hideAds = Pref.hideAds();
        hideGAds = Pref.hideGoogleAds();
        hideWTF = Pref.hideWTF();
        hideCTS = Pref.hideCTS();
        hideCTJ = Pref.hideCTJ();
        hideDetailedPosts = Pref.hideDetailedPosts();
        hideRBMK = Pref.hideRBMK();
        hidePinnedPosts = Pref.hideRPinnedPosts();
    }



    private static boolean isEntryIdRemove(String entryId) {
        String[] split = entryId.split("-");
        String entryId2 = split[0];
        if (!entryId2.equals("cursor") && !entryId2.equals("Guide") && !entryId2.startsWith("semantic_core")) {
            if ((entryId2.equals("promoted") || ((entryId2.equals("conversationthread") && split.length == 3) || entryId2.equals("superhero"))) && hideAds) {
                return true;
            }
            if (entryId2.equals("rtb") && hideGAds) {
                return true;
            }

            if (entryId2.equals("tweetdetailrelatedtweets") && hideDetailedPosts) {
                return true;
            }
            if (entryId2.equals("bookmarked") && hideRBMK) {
                return true;
            }
            if (entryId.startsWith("community-to-join") && hideCTJ) {
                return true;
            }
            if (entryId.startsWith("who-to-follow") && hideWTF) {
                return true;
            }
            if (entryId.startsWith("who-to-subscribe") && hideCTS) {
                return true;
            }
            if (entryId.startsWith("pinned-tweets") && hidePinnedPosts) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkEntry(Object jsonTimelineEntry) {
        try {
            String className = "com.twitter.model.json.timeline.urt.JsonTimelineEntry"; // Replace with your fully qualified class name
            Class<?> clazz = Class.forName(className);

            if (!clazz.isInstance(jsonTimelineEntry)) {
                return false;
            }

            clazz.cast(jsonTimelineEntry);
            Field a = clazz.getDeclaredField("a");
            String entryId = (String) a.get(jsonTimelineEntry);
            return isEntryIdRemove(entryId);
        } catch (Exception unused) {

        }
        return false;
    }

//end
}