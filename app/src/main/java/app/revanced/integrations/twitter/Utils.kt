package app.revanced.integrations.twitter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import app.revanced.integrations.shared.Utils
import app.revanced.integrations.twitter.settings.SettingsActivity

@SuppressLint("StaticFieldLeak")
object Utils {
    private var ctx: Context = Utils.getContext()

    @JvmStatic
    fun startActivity() {
        val intent = Intent(ctx, SettingsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx!!.startActivity(intent)
    }

    @Suppress("DEPRECATION")
    private fun getPref(key: String, defaultValue: String): String {
        val value = PreferenceManager.getDefaultSharedPreferences(ctx).getString(key, defaultValue)
        return if (value!!.isBlank()) {
            defaultValue
        } else value
    }

    @JvmStatic
    fun getVideoFolder(filename: String) =
        "${getPref("download_path", "Twitter")}/$filename"
}
