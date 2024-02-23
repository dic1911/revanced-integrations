package app.revanced.integrations.twitter.patches.links;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Field;

public final class UnshortenUrlsPatch {
    private static final String TAG = "030-unshort";
    public static void unshort(Object entity) {
        try {
            String className = "com.twitter.model.json.core.JsonUrlEntity"; // Replace with your fully qualified class name
            Class<?> clazz = Class.forName(className);

            if (!clazz.isInstance(entity)) {
                return;
            }

            clazz.cast(entity);
            Field c = clazz.getDeclaredField("c");
            Field e = clazz.getDeclaredField("e");

            String cValue = (String) c.get(entity);
            e.set(entity, cValue);
        } catch (Exception ex) {
            Log.e(TAG, ";-;", ex);
        }
    }
}