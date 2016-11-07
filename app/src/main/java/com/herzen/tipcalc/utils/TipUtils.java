package com.herzen.tipcalc.utils;
import java.text.SimpleDateFormat;
import com.herzen.tipcalc.entity.TipRecord;
/**
 * Created by herzen on 7/11/16.
 */

public class TipUtils {
    public static double getTip(TipRecord tipRecord) {
        return tipRecord.getBill() * (tipRecord.getTipPercentage()/100d);
    }

    public static String getDateFormated(TipRecord tipRecord) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm");
        return simpleDateFormat.format(tipRecord.getTimestamp());
    }
}
