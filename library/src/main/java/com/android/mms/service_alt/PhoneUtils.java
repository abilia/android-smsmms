/*
 * Copyright (C) 2015 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mms.service_alt;

import android.telephony.TelephonyManager;

/**
 * Utility to handle phone numbers.
 */
public class PhoneUtils {

    /**
     * Get a canonical national format phone number. If parsing fails, just return the
     * original number.
     *
     * <p>This used to delegate to {@code com.android.i18n.phonenumbers.PhoneNumberUtil}, a
     * vendored libphonenumber stand-in that was never actually implemented:
     * {@code PhoneNumberUtil.getInstance()} unconditionally returned null, so every call here
     * threw a NullPointerException instead of the {@code NumberParseException} the code was
     * built to catch. It didn't crash the app (callers up the stack catch {@code Exception}),
     * but it did silently fail MMS send/download for any carrier whose mms_config.xml resolves
     * the "##LINE1NOCOUNTRYCODE##" http param macro, since that's what routed here. Real
     * national-format parsing was never wired up, so this now does directly what the broken
     * path was documented to fall back to anyway: return the number unchanged.
     *
     * @param telephonyManager unused; kept for call-site compatibility
     * @param subId unused; kept for call-site compatibility
     * @param phoneText The input phone number text
     * @return phoneText, unchanged
     */
    public static String getNationalNumber(TelephonyManager telephonyManager, int subId,
            String phoneText) {
        return phoneText;
    }
}
