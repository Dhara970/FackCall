package com.example.fake.call.Utils_;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.example.fake.call.R;

/* loaded from: classes2.dex */
public class Shared {
    public static final String UNSEEN_LOCATION = "/UnSeen";
    private static Shared ourInstance = new Shared();
    public Drawable drawableG1;
    public Drawable drawableG2;
    public Drawable drawableG3;
    public String gridPackage1;
    public String gridPackage2;
    public String gridPackage3;
    SharedPreferences.Editor shareEditor;
    SharedPreferences sharedPreferences;
    public String title1;
    public String title2;
    public String title3;
    public final String[] SMS = {"android.permission.CAMERA", "android.permission.READ_PHONE_STATE"};
    public final String[] PHONE = {"android.permission.CAMERA"};
    public final String[] CAMERA = {"android.permission.CAMERA"};

    public static Shared getInstance() {
        return ourInstance;
    }

    private Shared() {
    }

    public int getIntValueFromPreference(String str, int i, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        return this.sharedPreferences.getInt(str, i);
    }

    public void saveIntToPreferences(String str, int i, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        this.shareEditor = edit;
        edit.putInt(str, i);
        this.shareEditor.commit();
    }

    public String getStringValueFromPreference(String str, String str2, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        return this.sharedPreferences.getString(str, str2);
    }

    public void saveStringToPreferences(String str, String str2, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        this.shareEditor = edit;
        edit.putString(str, str2);
        this.shareEditor.commit();
    }

    public void saveBooleanToPreferences(String str, Boolean bool, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        this.shareEditor = edit;
        edit.putBoolean(str, bool.booleanValue());
        this.shareEditor.commit();
    }

    public Boolean getBooleanValueFromPreference(String str, boolean z, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        return Boolean.valueOf(this.sharedPreferences.getBoolean(str, z));
    }

    public void saveLongToPreferences(String str, long j, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        this.shareEditor = edit;
        edit.putLong(str, j);
        this.shareEditor.commit();
    }

    public long getLongValueFromPreference(String str, long j, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        return this.sharedPreferences.getLong(str, j);
    }

    public boolean containsKey(String str, Context context) {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
        }
        return this.sharedPreferences.contains(str);
    }

    public void initPref(Context context) {
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_pref_name), 0);
    }

    public void startInstalledAppDetailsActivity(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            intent.addFlags(8388608);
            activity.startActivityForResult(intent, 200);
        }
    }

    public void shareApplication(Activity activity) {
        Intent intent = new Intent();
        intent.addFlags(1);
        intent.addFlags(2);
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", "Please download this Flash on Call and SMS from \nhttps://play.google.com/store/apps/details?id=" + getPackageName(activity));
        intent.setType("text/plain");
        activity.startActivity(Intent.createChooser(intent, "Share Link via"));
    }

    public void like(Activity activity) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName(activity))));
        } catch (ActivityNotFoundException unused) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName(activity))));
        }
    }

    public String getPackageName(Activity activity) {
        return activity.getPackageName().toString();
    }

    public ObjectAnimator setAnim(ImageView imageView) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat("scaleX", 1.1f), PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        ofPropertyValuesHolder.setDuration(750L);
        ofPropertyValuesHolder.setRepeatCount(-1);
        ofPropertyValuesHolder.setRepeatMode(2);
        return ofPropertyValuesHolder;
    }
}
