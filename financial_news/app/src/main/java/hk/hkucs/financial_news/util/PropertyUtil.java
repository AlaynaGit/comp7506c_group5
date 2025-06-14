package hk.hkucs.financial_news.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public static Properties loadProperties(Context context) {
        Properties properties = new Properties();
        try {
            properties.load(context.getAssets().open("api.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("PropertyUtil", "Failed to load properties: " + e.getMessage());
        }
        return properties;
    }
}
