package app.revanced.integrations.twitter.patches;

import java.util.*;
import app.revanced.integrations.twitter.Pref;

public class FeatureSwitchPatch {
    private static HashMap<String,Object> FLAGS = new HashMap<String,Object>();

    public static Object flagInfo(String flag,Object def){
        try{
            if (FLAGS.containsKey(flag)){
                Object val = FLAGS.get(flag);
                return val;
            }
        }
        catch (Exception ex){

        }
        return def;
    }


    public static void load() {}
}