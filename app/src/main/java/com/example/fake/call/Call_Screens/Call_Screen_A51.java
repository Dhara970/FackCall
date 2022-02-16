package com.example.fake.call.Call_Screens;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.example.fake.call.Helper.DatabaseHelper;
import com.example.fake.call.R;
import com.example.fake.call.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class Call_Screen_A51 extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    Holder holder;
    ShapeDrawable mDrawable;
    private final BroadcastReceiver stop_fake_call = new BroadcastReceiver() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Call_Screen_A51.this.stopPlaying();
            try {
                Call_Screen_A51.this.holder.r.stop();
            } catch ( Exception unused) {
            }
            try {
                if (Call_Screen_A51.this.holder.sharedPreferences.getBoolean(Call_Screen_A51.this.getResources().getString(R.string.vibration_pref), true)) {
                    Call_Screen_A51.this.holder.vibrator.cancel();
                }
            } catch ( Exception unused2) {
            }
            try {
                Call_Screen_A51.this.holder.cutom_ringtone_player.stop();
            } catch ( Exception unused3) {
            }
            Call_Screen_A51.this.finish();
        }
    };

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onBackPressed() {
    }


    public class Holder {
        static final int INTERVAL = 1000;
        LinearLayout action_layout;
        ImageView add_call_imagview;
        LinearLayout add_call_layout;
        ImageView bluetooth_imageview;
        ImageView call_imageview;
        ImageView cancel_call;
        ImageView cancel_call_new;
        Chronometer chronometer;
        MediaPlayer cutom_ringtone_player;
        private DatabaseHelper db;
        ImageView dial_pad;
        ImageView extra_volume_imagview;
        LinearLayout hold_call;
        CircleImageView image_of_caller;
        LinearLayout keypad_layout;
        MediaPlayer mPlayer;
        private Sensor mProximity;
        private SensorManager mSensorManager;
        public RelativeLayout main_layout;
        ImageView message_imageview;
        ImageView mute;
        LinearLayout mute_layout;
        LinearLayout name_n_numb_layout;
        TextView name_of_contact;
        LinearLayout notes_add;
        TextView number_of_contact;
        String path_image;
        ImageView pick_call;
        LinearLayout pick_call_layout;
        private PowerManager powerManager;
        Ringtone r;
        LinearLayout reject_call_layout;
        LinearLayout reject_with_message;
        SharedPreferences sharedPreferences;
        ImageView speaker;
        LinearLayout speaker_layout;
        TextView timer_textview;
        Vibrator vibrator;
        ImageView video_imageview;
        private PowerManager.WakeLock wakeLock;
        private final View myView = null;
        boolean whichColor = true;
        Boolean speaker_active = false;
        Boolean mute_active = false;
        Boolean extra_volume = false;
        Boolean bluetooth = false;
        Boolean hold = false;
        Boolean notes = false;
        Boolean add_call = false;
        Boolean end_call = false;
        Boolean dial_active = false;
        long totalSeconds = 999999999;
        long intervalSeconds = 1;
        public String mFileName = null;
        private final int field = 32;
        Boolean fake_call_picked = false;

        public Holder() {

            //Call_Screen_A51.this = r4;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(6815872);
        getWindow().setFlags(512, 512);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(2310);
        }
        setContentView(R.layout.activity_call__now__s7);
        if (Build.VERSION.SDK_INT >= 27) {
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(2097152);
        }
        this.holder = new Holder();
        this.mDrawable = new ShapeDrawable(new RectShape());
        initilize_componenets();
        getting_prefrence();
        this.holder.sharedPreferences.edit().putBoolean("fake_call_actvie", true).apply();
        Holder holder = this.holder;
        holder.mFileName = holder.sharedPreferences.getString(getResources().getString(R.string.audio_path_pref), "");
        this.holder.mPlayer = new MediaPlayer();
        try {
            this.holder.mPlayer.setDataSource(this.holder.mFileName);
            this.holder.mPlayer.setAudioStreamType(0);
            this.holder.mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception unused) {
        }
        this.holder.db = new DatabaseHelper(this);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.stop_fake_call, new IntentFilter(getResources().getString(R.string.stop_call_service_pref_intent)));
    }

    @SuppressLint("WrongConstant")
    private void getting_prefrence() {
        this.holder.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = this.holder.sharedPreferences.getString(getResources().getString(R.string.ringtone_path), "");
        if (!this.holder.sharedPreferences.getBoolean(getResources().getString(R.string.custom_ring_selected), false)) {
            try {
                Uri defaultUri = RingtoneManager.getDefaultUri(1);
                this.holder.r = RingtoneManager.getRingtone(getApplicationContext(), defaultUri);
                this.holder.r.play();
            } catch (NullPointerException unused) {
                Toast.makeText(this, getResources().getString(R.string.ringotne_not_found), Toast.LENGTH_SHORT).show();
            } catch (Exception unused2) {
            }
        } else {
            try {
                Uri parse = Uri.parse(string);
                this.holder.cutom_ringtone_player = MediaPlayer.create(this, parse);
                this.holder.cutom_ringtone_player.setLooping(true);
                this.holder.cutom_ringtone_player.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.holder.name_of_contact.setText(this.holder.sharedPreferences.getString("name_of_contact", "Girl Friend"));
        this.holder.number_of_contact.setText(this.holder.sharedPreferences.getString("number_of_contact", "(202) 555-0128"));
        if (!this.holder.sharedPreferences.getBoolean("gallery_image", false)) {
            if (!this.holder.sharedPreferences.getBoolean("custom_image", false)) {
                switch (this.holder.sharedPreferences.getInt("position", 2)) {
                    case 1:
                        this.holder.image_of_caller.setImageResource(R.mipmap.pizza);
                        this.holder.path_image = "pizza";
                        break;
                    case 2:
                        this.holder.image_of_caller.setImageResource(R.mipmap.girlfriend);
                        this.holder.path_image = "girlfriend";
                        break;
                    case 3:
                        this.holder.image_of_caller.setImageResource(R.mipmap.mom);
                        this.holder.path_image = "mom";
                        break;
                    case 4:
                        this.holder.image_of_caller.setImageResource(R.mipmap.boyfriend);
                        this.holder.path_image = "boyfriend";
                        break;
                    case 5:
                        this.holder.image_of_caller.setImageResource(R.mipmap.dad);
                        this.holder.path_image = "dad";
                        break;
                    case 6:
                        this.holder.image_of_caller.setImageResource(R.mipmap.husband);
                        this.holder.path_image = "husband";
                        break;
                    case 7:
                        this.holder.image_of_caller.setImageResource(R.mipmap.wife);
                        this.holder.path_image = "wife";
                        break;
                    case 8:
                        this.holder.image_of_caller.setImageResource(R.mipmap.boss);
                        this.holder.path_image = "boss";
                        break;
                    case 9:
                        this.holder.image_of_caller.setImageResource(R.mipmap.doctor);
                        this.holder.path_image = "doctor";
                        break;
                    case 10:
                        this.holder.image_of_caller.setImageResource(R.mipmap.lawyer);
                        this.holder.path_image = "lawyer";
                        break;
                    case 11:
                        this.holder.image_of_caller.setImageResource(R.mipmap.president);
                        this.holder.path_image = "president";
                        break;
                    case 12:
                        this.holder.image_of_caller.setImageResource(R.mipmap.ronaldo);
                        this.holder.path_image = "ronaldo";
                        break;
                }
            } else {
                Glide.with((FragmentActivity) this).load(this.holder.sharedPreferences.getString("custom_image_path", "")).into(this.holder.image_of_caller);
                Holder holder = this.holder;
                holder.path_image = holder.sharedPreferences.getString("custom_image_path", "");
            }
        } else {
            Glide.with((FragmentActivity) this).load(this.holder.sharedPreferences.getString("imagepath", "")).into(this.holder.image_of_caller);
            Holder holder2 = this.holder;
            holder2.path_image = holder2.sharedPreferences.getString("imagepath", "");
        }
        this.holder.vibrator = (Vibrator) getSystemService("vibrator");
        if (this.holder.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
            this.holder.vibrator.vibrate(new long[]{0, 1000, 1000}, 0);
        }
        finish_call_when_not_picked_up();
    }

    @SuppressLint("WrongConstant")
    private void initilize_componenets() {
        this.holder.mSensorManager = (SensorManager) getSystemService("sensor");
        Holder holder = this.holder;
        holder.mProximity = holder.mSensorManager.getDefaultSensor(8);
        this.holder.powerManager = (PowerManager) getSystemService("power");
        Holder holder2 = this.holder;
        holder2.wakeLock = holder2.powerManager.newWakeLock(32, getLocalClassName());
        this.holder.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.holder.call_imageview = (ImageView) findViewById(R.id.call_imageview);
        this.holder.message_imageview = (ImageView) findViewById(R.id.message_imageview);
        this.holder.video_imageview = (ImageView) findViewById(R.id.video_imageview);
        this.holder.reject_with_message = (LinearLayout) findViewById(R.id.reject_with_messa);
        this.holder.hold_call = (LinearLayout) findViewById(R.id.hold_call);
        this.holder.notes_add = (LinearLayout) findViewById(R.id.notes_add);
        this.holder.pick_call = (ImageView) findViewById(R.id.pick_call);
        this.holder.cancel_call = (ImageView) findViewById(R.id.cancel_call);
        this.holder.cancel_call_new = (ImageView) findViewById(R.id.cancel_call_new);
        this.holder.image_of_caller = (CircleImageView) findViewById(R.id.image_of_caller);
        this.holder.name_of_contact = (TextView) findViewById(R.id.name_of_contact);
        this.holder.number_of_contact = (TextView) findViewById(R.id.number_of_contact);
        this.holder.timer_textview = (TextView) findViewById(R.id.timer);
        this.holder.add_call_layout = (LinearLayout) findViewById(R.id.add_call_layout);
        this.holder.keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);
        this.holder.mute_layout = (LinearLayout) findViewById(R.id.mute_layout);
        this.holder.speaker_layout = (LinearLayout) findViewById(R.id.speaker_layout);
        this.holder.action_layout = (LinearLayout) findViewById(R.id.action_layout);
        this.holder.name_n_numb_layout = (LinearLayout) findViewById(R.id.name_n_numb_layout);
        this.holder.pick_call_layout = (LinearLayout) findViewById(R.id.pick_call_layout);
        this.holder.reject_call_layout = (LinearLayout) findViewById(R.id.reject_call_layout);
        this.holder.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.holder.pick_call.setOnTouchListener(new View.OnTouchListener() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 2) {
                    Call_Screen_A51.this.holder.pick_call.setVisibility(View.GONE);
                    Call_Screen_A51.this.holder.cancel_call.setVisibility(View.GONE);
                    Call_Screen_A51.this.holder.cancel_call_new.setVisibility(View.VISIBLE);
                    Call_Screen_A51.this.holder.action_layout.setVisibility(View.VISIBLE);
                    Call_Screen_A51.this.holder.reject_with_message.setVisibility(View.GONE);
                    Call_Screen_A51.this.holder.pick_call_layout.setVisibility(View.GONE);
                    Call_Screen_A51.this.holder.reject_call_layout.setVisibility(View.GONE);
                    if (Call_Screen_A51.this.holder.sharedPreferences.getBoolean(Call_Screen_A51.this.getResources().getString(R.string.vibration_pref), true)) {
                        Call_Screen_A51.this.holder.vibrator.cancel();
                    }
                    try {
                        Call_Screen_A51.this.holder.cutom_ringtone_player.stop();
                    } catch ( Exception unused) {
                    }
                    Call_Screen_A51.this.holder.chronometer.setBase(SystemClock.elapsedRealtime());
                    Call_Screen_A51.this.holder.chronometer.setVisibility(View.VISIBLE);
                    Call_Screen_A51.this.holder.timer_textview.setVisibility(View.GONE);
                    Call_Screen_A51.this.holder.chronometer.stop();
                    Call_Screen_A51.this.holder.chronometer.start();
                    try {
                        Call_Screen_A51.this.holder.r.stop();
                    } catch (NullPointerException unused2) {
                    }
                    try {
                        if (new File(Call_Screen_A51.this.holder.mFileName).exists()) {
                            Call_Screen_A51.this.startPlaying();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Call_Screen_A51.this.holder.fake_call_picked = true;
                    Call_Screen_A51.this.holder.dial_active = true;
                }
                return true;
            }
        });
        this.holder.cancel_call_new.setOnClickListener(this);
        this.holder.cancel_call.setOnClickListener(this);
        this.holder.add_call_layout.setOnClickListener(this);
        this.holder.notes_add.setOnClickListener(this);
        this.holder.hold_call.setOnClickListener(this);
        this.holder.reject_with_message.setOnClickListener(this);
        this.holder.keypad_layout.setOnClickListener(this);
        this.holder.mute_layout.setOnClickListener(this);
        this.holder.speaker_layout.setOnClickListener(this);
        change_main_color(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        int i2 = 0;
        switch (view.getId()) {
            case R.id.add_call_layout /* 2131230796 */:
                Holder holder = this.holder;
                holder.add_call = Boolean.valueOf(!holder.add_call.booleanValue());
                return;
            case R.id.cancel_call /* 2131230854 */:
                this.holder.totalSeconds = 3;
                this.holder.end_call = true;
                createNote(this.holder.name_of_contact.getText().toString(), this.holder.number_of_contact.getText().toString(), "00:00", this.holder.path_image);
                this.holder.cancel_call.setVisibility(View.GONE);
                this.holder.pick_call.setVisibility(View.GONE);
                this.holder.pick_call_layout.setVisibility(View.GONE);
                this.holder.reject_call_layout.setVisibility(View.GONE);
                this.holder.reject_with_message.setVisibility(View.GONE);
                if (this.holder.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
                    this.holder.vibrator.cancel();
                }
                this.holder.chronometer.setVisibility(View.GONE);
                this.holder.timer_textview.setVisibility(View.VISIBLE);
                this.holder.chronometer.stop();
                this.holder.timer_textview.setText(" Call Ended");
                this.holder.timer_textview.setTextColor(SupportMenu.CATEGORY_MASK);
                this.holder.action_layout.setVisibility(View.GONE);
                change_main_color(false);
                this.holder.cancel_call_new.setVisibility(View.GONE);
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused) {
                }
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused2) {
                }
                stopPlaying();
                try {
                    this.holder.r.stop();
                } catch (NullPointerException unused3) {
                }
                finish_Timer();
                return;
            case R.id.cancel_call_new /* 2131230855 */:
                this.holder.totalSeconds = 3;
                this.holder.end_call = true;
                try {
                    this.holder.mSensorManager.unregisterListener(this);
                } catch ( Exception unused4) {
                }
                this.holder.reject_with_message.setVisibility(View.GONE);
                this.holder.action_layout.setVisibility(View.GONE);
                change_main_color(false);
                this.holder.cancel_call_new.setVisibility(View.GONE);
                createNote(this.holder.name_of_contact.getText().toString(), this.holder.number_of_contact.getText().toString(), this.holder.chronometer.getText().toString(), this.holder.path_image);
                this.holder.chronometer.setVisibility(View.GONE);
                this.holder.timer_textview.setVisibility(View.VISIBLE);
                this.holder.chronometer.stop();
                this.holder.timer_textview.setText(" Call Ended");
                this.holder.timer_textview.setTextColor(SupportMenu.CATEGORY_MASK);
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused5) {
                }
                stopPlaying();
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused6) {
                }
                try {
                    this.holder.r.stop();
                } catch (NullPointerException unused7) {
                }
                finish_Timer();
                return;
            case R.id.hold_call /* 2131230968 */:
                Holder holder2 = this.holder;
                holder2.hold = Boolean.valueOf(!holder2.hold.booleanValue());
                return;
            case R.id.keypad_layout /* 2131230987 */:
                Holder holder3 = this.holder;
                holder3.dial_active = Boolean.valueOf(!holder3.dial_active.booleanValue());
                return;
            case R.id.mute_layout /* 2131231025 */:
                Holder holder4 = this.holder;
                holder4.mute_active = Boolean.valueOf(!holder4.mute_active.booleanValue());
                return;
            case R.id.notes_add /* 2131231041 */:
                Holder holder5 = this.holder;
                holder5.notes = Boolean.valueOf(!holder5.notes.booleanValue());
                return;
            case R.id.pick_call /* 2131231064 */:
                this.holder.pick_call.setVisibility(View.GONE);
                this.holder.cancel_call.setVisibility(View.GONE);
                this.holder.cancel_call_new.setVisibility(View.VISIBLE);
                this.holder.action_layout.setVisibility(View.VISIBLE);
                this.holder.action_layout.setVisibility(View.VISIBLE);
                this.holder.pick_call_layout.setVisibility(View.GONE);
                this.holder.reject_call_layout.setVisibility(View.GONE);
                this.holder.reject_with_message.setVisibility(View.GONE);
                if (this.holder.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
                    this.holder.vibrator.cancel();
                }
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused8) {
                }
                try {
                    this.holder.cutom_ringtone_player.stop();
                } catch ( Exception unused9) {
                }
                try {
                    this.holder.r.stop();
                } catch (NullPointerException unused10) {
                }
                try {
                    if (new File(this.holder.mFileName).exists()) {
                        startPlaying();
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.reject_with_message /* 2131231100 */:
                Holder holder6 = this.holder;
                holder6.hold = Boolean.valueOf(!holder6.hold.booleanValue());
                return;
            case R.id.speaker_layout /* 2131231166 */:
                if (!this.holder.speaker_active.booleanValue()) {
                    this.holder.speaker_active = true;
                    try {
                        i2 = this.holder.mPlayer.getCurrentPosition();
                    } catch ( Exception unused11) {
                    }
                    stopPlaying();
                    this.holder.mPlayer = new MediaPlayer();
                    try {
                        this.holder.mPlayer.setDataSource(this.holder.mFileName);
                        this.holder.mPlayer.setAudioStreamType(3);
                        this.holder.mPlayer.prepare();
                        this.holder.mPlayer.seekTo(i2);
                        this.holder.mPlayer.start();
                        return;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                } else {
                    this.holder.speaker_active = false;
                    try {
                        i = this.holder.mPlayer.getCurrentPosition();
                    } catch ( Exception unused12) {
                        i = 0;
                    }
                    stopPlaying();
                    this.holder.mPlayer = new MediaPlayer();
                    try {
                        this.holder.mPlayer.setDataSource(this.holder.mFileName);
                        this.holder.mPlayer.setAudioStreamType(0);
                        this.holder.mPlayer.prepare();
                        this.holder.mPlayer.seekTo(i);
                        this.holder.mPlayer.start();
                        return;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return;
                    }
                }
            default:
                return;
        }
    }

    private void change_main_color(boolean z) {
        if (z) {
            new Thread(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.3
                @Override // java.lang.Runnable
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Call_Screen_A51.this.updateColor();
                        Call_Screen_A51.this.holder.whichColor = !Call_Screen_A51.this.holder.whichColor;
                    }
                }
            }).start();
            return;
        }
        this.mDrawable.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) this.holder.main_layout.getHeight(), getResources().getColor(R.color.s7_green), getResources().getColor(R.color.s7_perpul), Shader.TileMode.REPEAT));
        this.holder.main_layout.setBackgroundDrawable(this.mDrawable);
    }

    public void updateColor() {
        try {
            runOnUiThread(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.4
                @Override // java.lang.Runnable
                public void run() {
                    if (Call_Screen_A51.this.holder.whichColor) {
                        Call_Screen_A51.this.mDrawable.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) Call_Screen_A51.this.holder.main_layout.getHeight(), Call_Screen_A51.this.getResources().getColor(R.color.s7_green), Call_Screen_A51.this.getResources().getColor(R.color.s7_perpul), Shader.TileMode.REPEAT));
                        Call_Screen_A51.this.holder.main_layout.setBackgroundDrawable(Call_Screen_A51.this.mDrawable);
                        return;
                    }
                    Call_Screen_A51.this.mDrawable.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) Call_Screen_A51.this.holder.main_layout.getHeight(), Call_Screen_A51.this.getResources().getColor(R.color.s7_green), Call_Screen_A51.this.getResources().getColor(R.color.s7_perpul), Shader.TileMode.REPEAT));
                    Call_Screen_A51.this.holder.main_layout.setBackgroundDrawable(Call_Screen_A51.this.mDrawable);
                }
            });
        } catch (Exception | OutOfMemoryError unused) {
        }
    }

    private void finish_Timer() {
        new Handler().postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Call_Screen_A51.this.holder.mSensorManager.unregisterListener(Call_Screen_A51.this);
                } catch ( Exception unused) {
                }
                Call_Screen_A51.this.holder.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                Call_Screen_A51.this.finish();
            }
        }, 1600);
    }

    private void finish_call_when_not_picked_up() {
        new Handler().postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Call_Screen_A51.6
            @Override // java.lang.Runnable
            public void run() {
                if (!Call_Screen_A51.this.holder.fake_call_picked.booleanValue()) {
                    Call_Screen_A51.this.holder.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                    if (Call_Screen_A51.this.holder.sharedPreferences.getBoolean(Call_Screen_A51.this.getResources().getString(R.string.vibration_pref), true)) {
                        Call_Screen_A51.this.holder.vibrator.cancel();
                    }
                    try {
                        Call_Screen_A51.this.holder.cutom_ringtone_player.stop();
                    } catch ( Exception unused) {
                    }
                    Call_Screen_A51.this.holder.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                    Call_Screen_A51.this.stopPlaying();
                    try {
                        Call_Screen_A51.this.holder.r.stop();
                    } catch (NullPointerException unused2) {
                    }
                    Call_Screen_A51.this.finish();
                }
            }
        }, 50000);
    }

    public void stopPlaying() {
        try {
            if (this.holder.mPlayer != null) {
                this.holder.mPlayer.stop();
                this.holder.mPlayer.release();
                this.holder.mPlayer = null;
            }
        } catch ( Exception unused) {
        }
    }

    public void startPlaying() {
        try {
            this.holder.mPlayer.start();
        } catch (Exception unused) {
        }
    }

    private void createNote(String str, String str2, String str3, String str4) {
        Utils.path = str4;
        this.holder.db.insertNote(str, str2, str3, str4);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Utils.fake_call_actvie = false;
        try {
            unregisterReceiver(this.stop_fake_call);
        } catch ( Exception unused) {
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        stopPlaying();
        try {
            this.holder.r.stop();
        } catch (NullPointerException unused) {
        }
        try {
            this.holder.cutom_ringtone_player.stop();
        } catch ( Exception unused2) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.holder.mSensorManager.registerListener(this, this.holder.mProximity, 3);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            this.holder.mSensorManager.unregisterListener(this);
        } catch ( Exception unused) {
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!this.holder.fake_call_picked.booleanValue()) {
            return;
        }
        if (!this.holder.wakeLock.isHeld()) {
            this.holder.wakeLock.acquire();
        } else if (this.holder.wakeLock.isHeld()) {
            this.holder.wakeLock.release();
        }
    }
}
