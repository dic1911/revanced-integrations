package app.revanced.integrations.twitter.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.*;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;
import app.revanced.integrations.twitter.settings.featureflags.FeatureFlag;
import app.revanced.integrations.twitter.settings.featureflags.FeatureFlagsFragment;
import com.twitter.ui.widget.LegacyTwitterPreferenceCategory;
@SuppressWarnings("deprecation")
public class SettingsActivity extends Activity {
    public static Toolbar toolbar;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(Utils.getResourceIdentifier("preference_fragment_activity", "layout"));
        toolbar = findViewById(Utils.getResourceIdentifier("toolbar", "id"));
        toolbar.setNavigationIcon(Utils.getResourceIdentifier("ic_vector_arrow_left", "drawable"));
        toolbar.setTitle(Utils.getResourceString("piko_title_settings"));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getFragmentManager().beginTransaction().replace(Utils.getResourceIdentifier("fragment_container", "id"), new Screen()).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount()>0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public static class Screen extends PreferenceFragment implements Preference.OnPreferenceClickListener {
        private Context context;

        @Override
        public void onResume() {
            super.onResume();
            SettingsActivity.toolbar.setTitle(Utils.getResourceString("piko_settings_title"));
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getContext();

            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            screen.addPreference(
                    buttonPreference(
                            "Feature flags",
                            "",
                            Settings.MISC_FEATURE_FLAGS.key
                    )
            );

            if (SettingsStatus.enablePremiumSection()) {
                LegacyTwitterPreferenceCategory premiumPrefs = preferenceCategory(Utils.getResourceString("piko_title_premium"), screen);
                if (SettingsStatus.enableReaderMode) {
                    premiumPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_reader_mode"),
                                    Utils.getResourceString("piko_pref_reader_mode_desc"),
                                    Settings.PREMIUM_READER_MODE
                            )
                    );
                }
                if (SettingsStatus.enableUndoPosts) {
                    premiumPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_undo_posts"),
                                    Utils.getResourceString("piko_pref_undo_posts_desc"),
                                    Settings.PREMIUM_UNDO_POSTS
                            )
                    );

                    premiumPrefs.addPreference(
                            buttonPreference(
                                    Utils.getResourceString("piko_pref_undo_posts_btn"),
                                    "",
                                    Settings.PREMIUM_UNDO_POSTS.key
                            )
                    );
                }
                if (SettingsStatus.enableAppIconNNavIcon) {
                    premiumPrefs.addPreference(
                            buttonPreference(
                                    Utils.getResourceString("piko_pref_icon_n_navbar_btn"),
                                    "",
                                    Settings.PREMIUM_ICONS
                            )
                    );
                }
            }

            if (SettingsStatus.changeDownloadEnabled) {
                LegacyTwitterPreferenceCategory downloadPrefs = preferenceCategory(Utils.getResourceString("piko_title_download"), screen);
                downloadPrefs.addPreference(listPreference(
                        Utils.getResourceString("piko_pref_download_path"),
                        Utils.getResourceString("piko_pref_download_path_desc"),
                        Settings.VID_PUBLIC_FOLDER
                ));
                downloadPrefs.addPreference(editTextPreference(
                        Utils.getResourceString("piko_pref_download_folder"),
                        Utils.getResourceString("piko_pref_download_folder_desc"),
                        Settings.VID_SUBFOLDER
                ));
            }

            if (SettingsStatus.enableAdsSection()) {
                LegacyTwitterPreferenceCategory adsPrefs = preferenceCategory(Utils.getResourceString("piko_title_ads"), screen);

                if (SettingsStatus.hideAds) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_promoted_posts"),
                                    "",
                                    Settings.ADS_HIDE_PROMOTED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hideGAds) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_g_ads"),
                                    "",
                                    Settings.ADS_HIDE_GOOGLE_ADS
                            )
                    );
                }
                if (SettingsStatus.hideWTF) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_wtf_section"),
                                    "",
                                    Settings.ADS_HIDE_WHO_TO_FOLLOW
                            )
                    );
                }

                if (SettingsStatus.hideCTS) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_cts_section"),
                                    "",
                                    Settings.ADS_HIDE_CREATORS_TO_SUB
                            )
                    );
                }

                if (SettingsStatus.hideCTJ) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_ctj_section"),
                                    "",
                                    Settings.ADS_HIDE_COMM_TO_JOIN
                            )
                    );
                }

                if (SettingsStatus.hideRBMK) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_ryb_section"),
                                    "",
                                    Settings.ADS_HIDE_REVISIT_BMK
                            )
                    );
                }

                if (SettingsStatus.hideRPinnedPosts) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_pinned_posts_section"),
                                    "",
                                    Settings.ADS_HIDE_REVISIT_PINNED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hideDetailedPosts) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_detailed_posts"),
                                    "",
                                    Settings.ADS_HIDE_DETAILED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hidePromotedTrend) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_trends"),
                                    "",
                                    Settings.ADS_HIDE_PROMOTED_TRENDS
                            )
                    );
                }


            }

            if (SettingsStatus.enableMiscSection()) {
                LegacyTwitterPreferenceCategory miscPrefs = preferenceCategory(Utils.getResourceString("piko_title_misc"), screen);
                if (SettingsStatus.enableFontMod) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_chirp_font"),
                                    "",
                                    Settings.MISC_FONT
                            )
                    );
                }
                if (SettingsStatus.hideFAB) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_fab"),
                                    "",
                                    Settings.MISC_HIDE_FAB
                            )
                    );
                }
                if (SettingsStatus.hideFABBtns) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_fab_menu"),
                                    "",
                                    Settings.MISC_HIDE_FAB_BTN
                            )
                    );
                }

                if (SettingsStatus.hideRecommendedUsers) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_rec_users"),
                                    "",
                                    Settings.MISC_HIDE_RECOMMENDED_USERS
                            )
                    );
                }

                if (SettingsStatus.hideCommunityNote) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_comm_notes"),
                                    "",
                                    Settings.MISC_HIDE_COMM_NOTES
                            )
                    );
                }

                if (SettingsStatus.hideViewCount) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_view_count"),
                                    "",
                                    Settings.MISC_HIDE_VIEW_COUNT
                            )
                    );
                }

                if (SettingsStatus.customSharingDomainEnabled) {
                    miscPrefs.addPreference(
                            editTextPreference(
                                    Utils.getResourceString("piko_pref_custom_share_domain"),
                                    Utils.getResourceString("piko_pref_custom_share_domain_desc"),
                                    Settings.CUSTOM_SHARING_DOMAIN
                            )
                    );
                }
            }

            if (SettingsStatus.enableTimelineSection()) {
                LegacyTwitterPreferenceCategory timelinePrefs = preferenceCategory(Utils.getResourceString("piko_title_timeline"), screen);
                if (SettingsStatus.hideForyou) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_for_you"),
                                    "",
                                    Settings.TIMELINE_HIDE_FORYOU
                            )
                    );
                }
                if (SettingsStatus.hideLiveThreads) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_live_threads"),
                                    Utils.getResourceString("piko_pref_hide_live_threads_desc"),
                                    Settings.TIMELINE_HIDE_LIVETHREADS
                            )
                    );
                }
                if (SettingsStatus.hideBanner) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_banner"),
                                    Utils.getResourceString("piko_pref_hide_banner_desc"),
                                    Settings.TIMELINE_HIDE_BANNER
                            )
                    );
                }
                if (SettingsStatus.hideInlineBmk) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_bmk_timeline"),
                                    "",
                                    Settings.TIMELINE_HIDE_BMK_ICON
                            )
                    );
                }

                if (SettingsStatus.showPollResultsEnabled) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_show_poll_result"),
                                    Utils.getResourceString("piko_pref_show_poll_result_desc"),
                                    Settings.TIMELINE_SHOW_POLL_RESULTS
                            )
                    );
                }

                if (SettingsStatus.hideImmersivePlayer) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    Utils.getResourceString("piko_pref_hide_immersive_player"),
                                    Utils.getResourceString("piko_pref_hide_immersive_player_desc"),
                                    Settings.TIMELINE_HIDE_IMMERSIVE_PLAYER
                            )
                    );
                }

            }

            setPreferenceScreen(screen);
        }

        private Preference editTextPreference(String title, String summary, StringSetting setting) {
            EditTextPreference preference = new EditTextPreference(context);
            preference.setTitle(title);
            preference.setDialogTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);
            setOnPreferenceChangeListener(preference);
            return preference;
        }

        private Preference switchPreference(String title, String summary, BooleanSetting setting) {
            SwitchPreference preference = new SwitchPreference(context);
            preference.setTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);
            setOnPreferenceChangeListener(preference);
            return preference;
        }

        private Preference buttonPreference(String title, String summary, String key) {
            Preference preference = new Preference(context);
            preference.setKey(key);
            preference.setTitle(title);
            preference.setSummary(summary);
            preference.setOnPreferenceClickListener(this);
            return preference;
        }

        private Preference listPreference(String title, String summary, StringSetting setting) {
            ListPreference preference = new ListPreference(context);
            preference.setTitle(title);
            preference.setDialogTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);
            preference.setEntries(new CharSequence[]{"Movies", "DCIM", "Pictures", "Download"});
            preference.setEntryValues(new CharSequence[]{"Movies", "DCIM", "Pictures", "Download"});
            setOnPreferenceChangeListener(preference);
            return preference;
        }

        private LegacyTwitterPreferenceCategory preferenceCategory(String title, PreferenceScreen screen) {
            LegacyTwitterPreferenceCategory preferenceCategory = new LegacyTwitterPreferenceCategory(context);
            preferenceCategory.setTitle(title);
            screen.addPreference(preferenceCategory);
            return preferenceCategory;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();
            if (key.equals(Settings.PREMIUM_UNDO_POSTS.key.toString())) {
                app.revanced.integrations.twitter.Utils.startUndoPostActivity();
            } else if (key.equals(Settings.PREMIUM_ICONS)) {
                app.revanced.integrations.twitter.Utils.startAppIconNNavIconActivity();
            } else if (key.equals(Settings.MISC_FEATURE_FLAGS.key)) {
                getFragmentManager().beginTransaction().replace(Utils.getResourceIdentifier("fragment_container", "id"), new FeatureFlagsFragment()).addToBackStack(null).commit();
            }
            return true;
        }

        private void setOnPreferenceChangeListener(Preference preference) {
            String key = preference.getKey();
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    try{
                        if (newValue!=null){
                            if(newValue.getClass() == Boolean.class){
                                app.revanced.integrations.twitter.Utils.putBooleanPerf(key,(Boolean)newValue);
                            }
                            else if(newValue.getClass() == String.class){
                                app.revanced.integrations.twitter.Utils.putStringPerf(key,(String)newValue);
                            }
                        }

                    }catch (Exception ex){
                        Utils.showToastShort(ex.toString());
                    }
                    return true;
                }
            });
        }

        //end
    }


}
