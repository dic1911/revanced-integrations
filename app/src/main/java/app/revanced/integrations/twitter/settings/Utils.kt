package app.revanced.integrations.twitter.settings

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

@SuppressLint("StaticFieldLeak")
object Utils {
    private var ctx: Context? = null

    @JvmStatic
    fun setCtx(application: Application?) {
        ctx = application
    }

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
