package com.example.fake.call.Broadcast_Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.example.fake.call.R;
import com.example.fake.call.Utils_.Shared;

/* loaded from: classes2.dex */
public class CallReceiver extends BroadcastReceiver {
    Context mContext;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        String string = intent.getExtras().getString("state");
        if (string.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(context.getString(R.string.stop_call_service_pref_intent)));
            Shared.getInstance().saveBooleanToPreferences(context.getResources().getString(R.string.call_is_active), true, context);
        } else if (string.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Shared.getInstance().saveBooleanToPreferences(context.getResources().getString(R.string.call_is_active), false, context);
        } else if (string.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(context.getString(R.string.stop_call_service_pref_intent)));
            Shared.getInstance().saveBooleanToPreferences(context.getResources().getString(R.string.call_is_active), true, context);
        } else {
            Shared.getInstance().saveBooleanToPreferences(context.getResources().getString(R.string.call_is_active), false, context);
        }
    }
}
