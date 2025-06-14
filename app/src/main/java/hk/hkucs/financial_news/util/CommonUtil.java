package hk.hkucs.financial_news.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUtil {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String generateAlphaVantageTimestamp(int hoursAgo) {
        // Get current timestamp in Hong Kong time zone
        ZoneId hongKongZone = ZoneId.of("Asia/Hong_Kong");
        LocalDateTime now = LocalDateTime.now(hongKongZone);

        // Calculate the specified hours ago
        LocalDateTime pastTime = now.minusHours(hoursAgo);

        // Format timestamp for Alpha Vantage API (YYYYMMDDThhmm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        return pastTime.format(formatter);
    }

}