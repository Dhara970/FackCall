package com.example.fake.call.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fake.call.R;
import com.example.fake.call.Utils_.Shared;

/* loaded from: classes2.dex */
public class Promotion_act extends AppCompatActivity {

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (NullPointerException unused) {
        }
        setContentView(R.layout.activity_promotion_act);
        String stringExtra = getIntent().getStringExtra("promo_package_name");
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + stringExtra)));
        } catch (ActivityNotFoundException unused2) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + stringExtra)));
        }
        Shared.getInstance().saveStringToPreferences(getResources().getString(R.string.promotion_package_name_pref), null, getApplicationContext());
        finish();
    }
}
