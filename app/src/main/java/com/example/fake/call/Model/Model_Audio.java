package com.example.fake.call.Model;

import android.graphics.Bitmap;

/* loaded from: classes2.dex */
public class Model_Audio {
    String audio_name;
    String audio_video_icon_path;
    long audio_video_size;
    Bitmap bitmap;
    boolean boolean_selected;
    String get_path;
    boolean isAudioSelected;
    boolean is_checked;
    long size;
    String str_path;
    String str_thumb;

    public boolean getIsAudioSelected() {
        return this.isAudioSelected;
    }

    public void setIsAudioSelected(boolean z) {
        this.isAudioSelected = z;
    }

    public String getAudio_video_name() {
        return this.audio_name;
    }

    public void setAudio_video_name(String str) {
        this.audio_name = str;
    }

    public long getAudio_video_size() {
        return this.audio_video_size;
    }

    public void setAudio_video_size(long j) {
        this.audio_video_size = j;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public long getSize() {
        return this.size;
    }

    public String getAudio_video_icon_path() {
        return this.audio_video_icon_path;
    }

    public void setAudio_video_icon_path(String str) {
        this.audio_video_icon_path = str;
    }

    public String getStr_path() {
        return this.str_path;
    }

    public void setStr_path(String str) {
        this.str_path = str;
    }

    public String getStr_thumb() {
        return this.str_thumb;
    }

    public void setStr_thumb(String str) {
        this.str_thumb = str;
    }

    public boolean is_playing() {
        return this.boolean_selected;
    }

    public void set_playing(boolean z) {
        this.boolean_selected = z;
    }

    public boolean is_checked() {
        return this.is_checked;
    }

    public void set_checked(boolean z) {
        this.is_checked = z;
    }
}
