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

package com.google.android.mms.util_alt;

import android.content.Context;
import android.drm.DrmManagerClient;
import android.util.Log;

public class DownloadDrmHelper {
    private static final String TAG = "DownloadDrmHelper";

    /** The MIME type of special DRM files */
    public static final String MIMETYPE_DRM_MESSAGE = "application/vnd.oma.drm.message";






    /**
     * Checks if the Media Type needs to be DRM converted
     *
     * @param mimetype Media type of the content
     * @return True if convert is needed else false
     */
    public static boolean isDrmConvertNeeded(String mimetype) {
        return MIMETYPE_DRM_MESSAGE.equals(mimetype);
    }



    /**
     * Gets the original mime type of DRM protected content.
     *
     * @param context The context
     * @param path Path to the file
     * @param containingMime The current mime type of of the file i.e. the
     *            containing mime type
     * @return The original mime type of the file if DRM protected else the
     *         currentMime
     */
    public static String getOriginalMimeType(Context context, String path, String containingMime) {
        String result = containingMime;
        DrmManagerClient drmClient = new DrmManagerClient(context);
        try {
            if (drmClient.canHandle(path, null)) {
                result = drmClient.getOriginalMimeType(path);
            }
        } catch (IllegalArgumentException ex) {
            Log.w(TAG,
                    "Can't get original mime type since path is null or empty string.");
        } catch (IllegalStateException ex) {
            Log.w(TAG, "DrmManagerClient didn't initialize properly.");
        }
        return result;
    }
}
