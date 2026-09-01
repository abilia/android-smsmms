/*
 * Copyright 2013 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.send_message;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold all relevant message information to send
 *
 * @author Jake Klinker
 */
public class Message {

    public static final class Part {
        private byte[] media;
        private String contentType;
        private String name;
        public Part(byte[] media, String contentType, String name) {
            this.media = media;
            this.contentType = contentType;
            this.name = name;
        }

        public byte[] getMedia() {
            return media;
        }

        public String getContentType() {
            return contentType;
        }

        public String getName() {
            return name;
        }
    }
    private String text;
    private String subject;
    private String fromAddress;
    private String[] addresses;
    private Bitmap[] images;
    private String[] imageNames;
    private List<Part> parts = new ArrayList<Part>();
    private boolean save;
    private int delay;
    private Uri messageUri;

    /**
     * Constructor
     *
     * @param text    is the message to send
     * @param address is the phone number to send to
     */
    public Message(String text, String address) {
        this(text, address.trim().split(" "));
    }

    /**
     * Constructor
     *
     * @param text      is the message to send
     * @param addresses is an array of phone numbers to send to
     */
    public Message(String text, String[] addresses) {
        this.text = text;
        this.addresses = addresses;
        this.images = new Bitmap[0];
        this.subject = null;
        this.save = true;
        this.delay = 0;
    }

    /**
     * Sets image
     *
     * @param image is the single image to send to recipient
     */
    public void setImage(Bitmap image) {
        this.images = new Bitmap[1];
        this.images[0] = image;
    }

    /**
     * Gets the text of the message to send
     *
     * @return the string of the message to send
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the from address of the message
     * @return the string of the from address
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Gets the addresses of the message
     *
     * @return an array of strings with all of the addresses
     */
    public String[] getAddresses() {
        return this.addresses;
    }

    /**
     * Gets the images in the message
     *
     * @return an array of bitmaps with all of the images
     */
    public Bitmap[] getImages() {
        return this.images;
    }

    /**
     * Gets image names for the message
     *
     * @return
     */
    public String[] getImageNames() {
        return this.imageNames;
    }

    /**
     * Gets the audio sample in the message
     *
     * @return an array of bytes with audio information for the message
     */
    public List<Part> getParts() {
        return this.parts;
    }

    /**
     * Gets the subject of the mms message
     *
     * @return a string with the subject of the message
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Gets whether or not to save the message to the database
     *
     * @return a boolean of whether or not to save
     */
    public boolean getSave() {
        return this.save;
    }

    /**
     * Gets the time to delay before sending the message
     *
     * @return the delay time in milliseconds
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Static method to convert a bitmap into a byte array to easily send it over http
     *
     * @param image is the image to convert
     * @return a byte array of the image data
     */
    public static byte[] bitmapToByteArray(Bitmap image) {
		byte[] output = new byte[0];
        if (image == null) {
            Log.v("Message", "image is null, returning byte array of size 0");
            return output;
        }
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			image.compress(Bitmap.CompressFormat.JPEG, 90, stream);
			output = stream.toByteArray();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {}
		}
		return output;
    }

    /**
     * Gets existing message uri
     *
     * @return existing message uri
     */
    public Uri getMessageUri() {
        return messageUri;
    }
}
