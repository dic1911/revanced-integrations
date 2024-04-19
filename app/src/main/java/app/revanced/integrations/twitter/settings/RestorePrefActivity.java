package app.revanced.integrations.twitter.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.Settings;

public class RestorePrefActivity extends Activity {
    private static final int READ_REQUEST_CODE = 42;
    private boolean featureFlag = false;

    private static String readFileContent(Uri uri){
        try {
            Context context = app.revanced.integrations.shared.Utils.getContext();
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(openInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                return stringBuilder.toString();
            }
        }catch (Exception e){

        }
        return "";
    }

    public static void receiveFileForRestore(Uri uri,boolean flags) {
        String jsonString = readFileContent(uri);
        boolean sts = false;
        if(flags){
            sts = Utils.setStringPref(Settings.MISC_FEATURE_FLAGS.key,jsonString);
        }else{
            sts = Utils.setAll(jsonString);

        }
        if(sts){
            toast("piko_pref_import_saved");

        }
        else{
            toast("piko_pref_import_failed");
        }

        return;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == READ_REQUEST_CODE && i2 == -1) {
            Uri uri = null;
            if (intent != null) {
                uri = intent.getData();
            }
            if (uri != null) {
                receiveFileForRestore(uri,this.featureFlag);
            }
            else{
                toast("piko_pref_import_no_uri");
            }

        }
        finish();
        return;
    }

    private static void toast(String tag){
        Utils.toast(app.revanced.integrations.shared.Utils.getResourceString(tag));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.featureFlag = getIntent().getBooleanExtra("featureFlag", false);
        requestFileForRestore();
    }

    public void requestFileForRestore() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }
}