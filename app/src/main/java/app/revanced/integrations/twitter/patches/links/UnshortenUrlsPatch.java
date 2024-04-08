package app.revanced.integrations.twitter.patches.links;

import android.util.Log;

import com.twitter.model.json.core.JsonUrlEntity;

public final class UnshortenUrlsPatch {
    private static final String TAG = "030-unshort";
    public static JsonUrlEntity unshort(JsonUrlEntity entity) {
        try {
            entity.e = entity.c;
        } catch (Exception ex) {
            Log.e(TAG, ";-;", ex);
        }
        return entity;
    }
}