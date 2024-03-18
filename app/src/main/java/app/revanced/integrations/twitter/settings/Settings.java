package app.revanced.integrations.twitter.settings;

import app.revanced.integrations.shared.settings.BaseSettings;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;

public class Settings extends BaseSettings {
    public static final StringSetting VID_PUBLIC_FOLDER = new StringSetting("vid_public_folder", "Movies");
    public static final StringSetting VID_SUBFOLDER = new StringSetting("vid_subfolder", "Twitter");

    public static final BooleanSetting MISC_FONT = new BooleanSetting("misc_font", false);
    public static final BooleanSetting MISC_HIDE_FAB = new BooleanSetting("misc_hide_fab", true);
    public static final BooleanSetting MISC_HIDE_RECOMMENDED_USERS = new BooleanSetting("misc_hide_recommended_users", true);
    public static final BooleanSetting MISC_HIDE_COMM_NOTES = new BooleanSetting("misc_hide_comm_notes", false);
    public static final BooleanSetting MISC_HIDE_VIEW_COUNT = new BooleanSetting("misc_hide_view_count", true);
  
    public static final BooleanSetting ADS_HIDE_PROMOTED_TRENDS = new BooleanSetting("ads_hide_promoted_trends", true);

    public static final BooleanSetting TIMELINE_HIDE_LIVETHREADS = new BooleanSetting("timeline_hide_livethreads", true);
    public static final BooleanSetting TIMELINE_HIDE_BANNER = new BooleanSetting("timeline_hide_banner", true);
    public static final BooleanSetting TIMELINE_HIDE_FORYOU = new BooleanSetting("timeline_hide_foryou", false);
}
