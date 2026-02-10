package com.klinker.android.send_message;

import android.content.Context;
import android.telephony.SmsManager;

public class SmsManagerFactory {

    public static SmsManager createSmsManager(Context context, Settings settings) {
        return createSmsManager(context, settings.getSubscriptionId());
    }

    public static SmsManager createSmsManager(Context context, int subscriptionId) {
        if (subscriptionId != Settings.DEFAULT_SUBSCRIPTION_ID) {
            SmsManager manager = null;

            try {
                manager = context.getSystemService(SmsManager.class).createForSubscriptionId(subscriptionId);
                return manager;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return context.getSystemService(SmsManager.class);
    }
}
