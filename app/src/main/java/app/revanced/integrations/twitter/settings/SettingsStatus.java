package app.revanced.integrations.twitter.settings;

public class SettingsStatus {
    public static boolean changeDownloadEnabled = false;


    public static void enableDownload() {
        changeDownloadEnabled = true;
    }

    public static void load() {}
}
