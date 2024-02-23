package app.revanced.integrations.twitter.patches.links;

import android.content.Context;
import android.content.Intent;

public final class OpenLinksWithAppChooserPatch {
    public static void openWithChooser(final Context context, final Intent originalIntent) {
        final Intent intent = new Intent("android.intent.action.VIEW", originalIntent.getData());
        context.startActivity(Intent.createChooser(intent, null));
    }
}
