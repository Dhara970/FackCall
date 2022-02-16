package com.example.fake.call.Call_Screens;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.example.fake.call.Helper.DatabaseHelper;
import com.example.fake.call.Model.Note;
import com.example.fake.call.R;
import com.example.fake.call.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Ice_White extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    LinearLayout action_layout;
    LinearLayout add_call_layout;
    Animation animation;
    ImageView bluetooth_imageview;
    LinearLayout btoom_call_layout;
    public RelativeLayout caling_ui;
    ImageView call_imageview;
    ImageView cancel_call;
    ImageView cancel_call_new;
    Chronometer chronometer;
    MediaPlayer cutom_ringtone_player;
    private DatabaseHelper db;
    ImageView dial_pad;
    ImageView extra_volume_imagview;
    LinearLayout hold_layout;
    CircleImageView image_of_caller;
    LinearLayout keypad_layout;
    ImageView large_circle;
    MediaPlayer mMediaPlayer_default;
    MediaPlayer mPlayer;
    private Sensor mProximity;
    private SensorManager mSensorManager;
    public RelativeLayout main_layout;
    ImageView message_imageview;
    ImageView mute;
    LinearLayout mute_layout;
    TextView name_contact_new;
    LinearLayout name_n_numb_layout;
    TextView name_of_contact;
    LinearLayout note_layout;
    TextView number_of_contact;
    String path_image;
    ImageView pick_call;
    private PowerManager powerManager;
    SharedPreferences sharedPreferences;
    ImageView speaker;
    LinearLayout speaker_layout;
    TextView timer_textview;
    Vibrator vibrator;
    ImageView video_imageview;
    LinearLayout view_contact_layout;
    private PowerManager.WakeLock wakeLock;
    Boolean speaker_active = false;
    Boolean mute_active = false;
    Boolean extra_volume = false;
    Boolean bluetooth = false;
    Boolean add_call = false;
    Boolean end_call = false;
    Boolean dial_active = false;
    long totalSeconds = 999999999;
    public String mFileName = null;
    private List<Note> notesList = new ArrayList();
    private int field = 32;
    Boolean fake_call_picked = false;
    private final BroadcastReceiver stop_fake_call = new BroadcastReceiver() { // from class: com.softdroid.fake.call.Call_Screens.Ice_White.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Ice_White.this.stopPlaying();
            try {
                Ice_White.this.mMediaPlayer_default.stop();
            } catch ( Exception unused) {
            }
            try {
                if (Ice_White.this.sharedPreferences.getBoolean(Ice_White.this.getResources().getString(R.string.vibration_pref), true)) {
                    Ice_White.this.vibrator.cancel();
                }
            } catch ( Exception unused2) {
            }
            try {
                Ice_White.this.cutom_ringtone_player.stop();
            } catch ( Exception unused3) {
            }
            Ice_White.this.finish();
        }
    };

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onBackPressed() {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().addFlags(6815872);
        getWindow().setFlags(512, 512);
        setContentView(R.layout.activity_revolving__ice);
        if (Build.VERSION.SDK_INT >= 27) {
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(2097152);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(2310);
        }
        initilize_componenets();
        getting_prefrence();
        this.sharedPreferences.edit().putBoolean("fake_call_actvie", true).apply();
        this.mFileName = this.sharedPreferences.getString(getResources().getString(R.string.audio_path_pref), "");
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mPlayer = mediaPlayer;
        try {
            mediaPlayer.setDataSource(this.mFileName);
            this.mPlayer.setAudioStreamType(0);
            this.mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception unused) {
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        this.db = databaseHelper;
        this.notesList.addAll(databaseHelper.getAllNotes());
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.stop_fake_call, new IntentFilter(getResources().getString(R.string.stop_call_service_pref_intent)));
    }

    @SuppressLint("WrongConstant")
    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences = defaultSharedPreferences;
        String string = defaultSharedPreferences.getString("ringtone_path", "");
        if (!this.sharedPreferences.getBoolean("custom_ring_selected", false)) {
            try {
                Uri defaultUri = RingtoneManager.getDefaultUri(1);
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.mMediaPlayer_default = mediaPlayer;
                mediaPlayer.setDataSource(this, defaultUri);
                if (((AudioManager) getSystemService("audio")).getStreamVolume(2) != 0) {
                    this.mMediaPlayer_default.setAudioStreamType(2);
                    this.mMediaPlayer_default.setLooping(true);
                    this.mMediaPlayer_default.prepare();
                    this.mMediaPlayer_default.start();
                }
            } catch (Exception unused) {
            }
        } else {
            try {
                MediaPlayer create = MediaPlayer.create(this, Uri.parse(string));
                this.cutom_ringtone_player = create;
                create.setLooping(true);
                this.cutom_ringtone_player.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.name_of_contact.setText(this.sharedPreferences.getString("name_of_contact", "Girl Friend"));
        this.number_of_contact.setText(this.sharedPreferences.getString("number_of_contact", "(202) 555-0128"));
        if (!this.sharedPreferences.getBoolean("gallery_image", false)) {
            if (!this.sharedPreferences.getBoolean("custom_image", false)) {
                switch (this.sharedPreferences.getInt("position", 2)) {
                    case 1:
                        this.image_of_caller.setImageResource(R.mipmap.pizza);
                        this.path_image = "pizza";
                        break;
                    case 2:
                        this.image_of_caller.setImageResource(R.mipmap.girlfriend);
                        this.path_image = "girlfriend";
                        break;
                    case 3:
                        this.image_of_caller.setImageResource(R.mipmap.mom);
                        this.path_image = "mom";
                        break;
                    case 4:
                        this.image_of_caller.setImageResource(R.mipmap.boyfriend);
                        this.path_image = "boyfriend";
                        break;
                    case 5:
                        this.image_of_caller.setImageResource(R.mipmap.dad);
                        this.path_image = "dad";
                        break;
                    case 6:
                        this.image_of_caller.setImageResource(R.mipmap.husband);
                        this.path_image = "husband";
                        break;
                    case 7:
                        this.image_of_caller.setImageResource(R.mipmap.wife);
                        this.path_image = "wife";
                        break;
                    case 8:
                        this.image_of_caller.setImageResource(R.mipmap.boss);
                        this.path_image = "boss";
                        break;
                    case 9:
                        this.image_of_caller.setImageResource(R.mipmap.doctor);
                        this.path_image = "doctor";
                        break;
                    case 10:
                        this.image_of_caller.setImageResource(R.mipmap.lawyer);
                        this.path_image = "lawyer";
                        break;
                    case 11:
                        this.image_of_caller.setImageResource(R.mipmap.president);
                        this.path_image = "president";
                        break;
                    case 12:
                        this.image_of_caller.setImageResource(R.mipmap.ronaldo);
                        this.path_image = "ronaldo";
                        break;
                }
            } else {
                Glide.with((FragmentActivity) this).load(this.sharedPreferences.getString("custom_image_path", "")).into(this.image_of_caller);
                this.path_image = this.sharedPreferences.getString("custom_image_path", "");
            }
        } else {
            Glide.with((FragmentActivity) this).load(this.sharedPreferences.getString("imagepath", "")).into(this.image_of_caller);
            this.path_image = this.sharedPreferences.getString("imagepath", "");
        }
        this.vibrator = (Vibrator) getSystemService("vibrator");
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
            this.vibrator.vibrate(new long[]{0, 1000, 1000}, 0);
        }
    }

    private void initilize_componenets() {
        @SuppressLint("WrongConstant") SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.mSensorManager = sensorManager;
        this.mProximity = sensorManager.getDefaultSensor(8);
        @SuppressLint("WrongConstant") PowerManager powerManager = (PowerManager) getSystemService("power");
        this.powerManager = powerManager;
        this.wakeLock = powerManager.newWakeLock(this.field, getLocalClassName());
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.call_imageview = (ImageView) findViewById(R.id.call_imageview);
        this.message_imageview = (ImageView) findViewById(R.id.message_imageview);
        this.video_imageview = (ImageView) findViewById(R.id.video_imageview);
        this.pick_call = (ImageView) findViewById(R.id.pick_call);
        this.large_circle = (ImageView) findViewById(R.id.large_circle);
        this.cancel_call = (ImageView) findViewById(R.id.cancel_call);
        this.cancel_call_new = (ImageView) findViewById(R.id.cancel_call_new);
        this.image_of_caller = (CircleImageView) findViewById(R.id.image_of_caller);
        this.name_of_contact = (TextView) findViewById(R.id.name_of_contact);
        this.number_of_contact = (TextView) findViewById(R.id.number_of_contact);
        this.name_contact_new = (TextView) findViewById(R.id.name_contact_new);
        this.timer_textview = (TextView) findViewById(R.id.timer);
        this.add_call_layout = (LinearLayout) findViewById(R.id.add_call_layout);
        this.keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);
        this.caling_ui = (RelativeLayout) findViewById(R.id.caling_ui);
        this.mute_layout = (LinearLayout) findViewById(R.id.mute_layout);
        this.hold_layout = (LinearLayout) findViewById(R.id.hold_call);
        this.note_layout = (LinearLayout) findViewById(R.id.notes_add);
        this.speaker_layout = (LinearLayout) findViewById(R.id.speaker_layout);
        this.action_layout = (LinearLayout) findViewById(R.id.action_layout);
        this.name_n_numb_layout = (LinearLayout) findViewById(R.id.name_n_numb_layout);
        this.view_contact_layout = (LinearLayout) findViewById(R.id.view_contact_layout);
        this.btoom_call_layout = (LinearLayout) findViewById(R.id.btoom_call_layout);
        this.large_circle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.pick_call.setOnTouchListener(new View.OnTouchListener() { // from class: com.softdroid.fake.call.Call_Screens.Ice_White.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 2) {
                    Ice_White.this.caling_ui.setVisibility(View.GONE);
                    Ice_White.this.cancel_call.setVisibility(View.GONE);
                    Ice_White.this.large_circle.clearAnimation();
                    Ice_White.this.large_circle.setVisibility(View.GONE);
                    Ice_White.this.timer_textview.setVisibility(View.GONE);
                    Ice_White.this.btoom_call_layout.setVisibility(View.GONE);
                    Ice_White.this.cancel_call_new.setVisibility(View.VISIBLE);
                    Ice_White.this.action_layout.setVisibility(View.VISIBLE);
                    if (Ice_White.this.sharedPreferences.getBoolean(Ice_White.this.getResources().getString(R.string.vibration_pref), true)) {
                        Ice_White.this.vibrator.cancel();
                    }
                    try {
                        Ice_White.this.cutom_ringtone_player.stop();
                    } catch ( Exception unused) {
                    }
                    try {
                        Ice_White.this.mMediaPlayer_default.stop();
                    } catch ( Exception unused2) {
                    }
                    try {
                        if (new File(Ice_White.this.mFileName).exists()) {
                            Ice_White.this.startPlaying();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Ice_White.this.fake_call_picked = true;
                    Ice_White.this.chronometer.setBase(SystemClock.elapsedRealtime());
                    Ice_White.this.chronometer.setVisibility(View.VISIBLE);
                    Ice_White.this.chronometer.stop();
                    Ice_White.this.chronometer.start();
                    Ice_White.this.dial_active = true;
                }
                return true;
            }
        });
        this.cancel_call_new.setOnClickListener(this);
        this.cancel_call.setOnClickListener(this);
        this.add_call_layout.setOnClickListener(this);
        this.keypad_layout.setOnClickListener(this);
        this.caling_ui.setOnClickListener(this);
        this.speaker_layout.setOnClickListener(this);
        this.mute_layout.setOnClickListener(this);
        this.note_layout.setOnClickListener(this);
        this.hold_layout.setOnClickListener(this);
        finish_call_when_not_picked_up();
    }

    private void finish_Timer() {
        new Handler().postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Ice_White.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Ice_White.this.mSensorManager.unregisterListener(Ice_White.this);
                } catch ( Exception unused) {
                }
                Ice_White.this.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                Ice_White.this.finish();
            }
        }, 1600);
    }

    private void finish_call_when_not_picked_up() {
        new Handler().postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Call_Screens.Ice_White.4
            @Override // java.lang.Runnable
            public void run() {
                if (!Ice_White.this.fake_call_picked.booleanValue()) {
                    Ice_White.this.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                    if (Ice_White.this.sharedPreferences.getBoolean(Ice_White.this.getResources().getString(R.string.vibration_pref), true)) {
                        Ice_White.this.vibrator.cancel();
                    }
                    try {
                        Ice_White.this.cutom_ringtone_player.stop();
                    } catch ( Exception unused) {
                    }
                    try {
                        Ice_White.this.mMediaPlayer_default.stop();
                    } catch ( Exception unused2) {
                    }
                    Ice_White.this.stopPlaying();
                    Ice_White.this.sharedPreferences.edit().putBoolean("fake_call_actvie", false).apply();
                    Ice_White.this.finish();
                }
            }
        }, 50000);
    }

    public void stopPlaying() {
        try {
            MediaPlayer mediaPlayer = this.mPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                this.mPlayer.release();
                this.mPlayer = null;
            }
        } catch ( Exception unused) {
        }
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
            this.mMediaPlayer_default.stop();
        } catch ( Exception unused) {
        }
        try {
            this.cutom_ringtone_player.stop();
        } catch ( Exception unused2) {
        }
    }

    public void startPlaying() {
        try {
            this.mPlayer.start();
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        int i2 = 0;
        switch (view.getId()) {
            case R.id.add_call_layout /* 2131230796 */:
                if (!this.add_call.booleanValue()) {
                    this.add_call = true;
                    return;
                } else {
                    this.add_call = false;
                    return;
                }
            case R.id.cancel_call /* 2131230854 */:
                this.totalSeconds = 3;
                this.end_call = true;
                createNote(this.name_of_contact.getText().toString(), this.number_of_contact.getText().toString(), "00:00", this.path_image);
                this.cancel_call.setVisibility(View.GONE);
                this.large_circle.clearAnimation();
                this.caling_ui.setVisibility(View.GONE);
                this.chronometer.stop();
                if (this.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
                    this.vibrator.cancel();
                }
                this.name_contact_new.setVisibility(View.VISIBLE);
                this.name_contact_new.setText(this.name_of_contact.getText().toString());
                this.action_layout.setVisibility(View.GONE);
                this.name_n_numb_layout.setVisibility(View.GONE);
                this.view_contact_layout.setVisibility(View.VISIBLE);
                this.btoom_call_layout.setVisibility(View.GONE);
                this.large_circle.setVisibility(View.GONE);
                this.main_layout.setBackgroundColor(-1);
                this.timer_textview.setVisibility(View.VISIBLE);
                this.timer_textview.setText(" Call Ended");
                this.timer_textview.setTextColor(SupportMenu.CATEGORY_MASK);
                this.cancel_call_new.setVisibility(View.GONE);
                this.main_layout.setBackgroundColor(-1);
                this.name_of_contact.setTextColor(-16777216);
                this.number_of_contact.setTextColor(-16777216);
                this.name_contact_new.setText(this.name_of_contact.getText().toString());
                try {
                    this.cutom_ringtone_player.stop();
                } catch ( Exception unused) {
                }
                stopPlaying();
                try {
                    this.mMediaPlayer_default.stop();
                } catch ( Exception unused2) {
                }
                finish_Timer();
                return;
            case R.id.cancel_call_new /* 2131230855 */:
                this.totalSeconds = 3;
                this.end_call = true;
                try {
                    this.mSensorManager.unregisterListener(this);
                } catch ( Exception unused3) {
                }
                this.name_contact_new.setVisibility(View.VISIBLE);
                this.name_contact_new.setText(this.name_of_contact.getText().toString());
                this.action_layout.setVisibility(View.GONE);
                this.timer_textview.setVisibility(View.VISIBLE);
                this.timer_textview.setText(" Call Ended");
                this.timer_textview.setTextColor(SupportMenu.CATEGORY_MASK);
                this.large_circle.clearAnimation();
                this.name_n_numb_layout.setVisibility(View.GONE);
                this.view_contact_layout.setVisibility(View.VISIBLE);
                this.btoom_call_layout.setVisibility(View.GONE);
                this.large_circle.setVisibility(View.GONE);
                this.main_layout.setBackgroundColor(-1);
                this.cancel_call_new.setVisibility(View.GONE);
                this.main_layout.setBackgroundColor(-1);
                this.name_of_contact.setTextColor(-16777216);
                this.number_of_contact.setTextColor(-16777216);
                this.chronometer.stop();
                createNote(this.name_of_contact.getText().toString(), this.number_of_contact.getText().toString(), this.chronometer.getText().toString(), this.path_image);
                this.chronometer.setVisibility(View.GONE);
                try {
                    this.cutom_ringtone_player.stop();
                } catch ( Exception unused4) {
                }
                stopPlaying();
                try {
                    this.mMediaPlayer_default.stop();
                } catch ( Exception unused5) {
                }
                finish_Timer();
                return;
            case R.id.hold_call /* 2131230968 */:
                if (!this.extra_volume.booleanValue()) {
                    this.extra_volume = true;
                    return;
                } else {
                    this.extra_volume = false;
                    return;
                }
            case R.id.keypad_layout /* 2131230987 */:
                if (!this.dial_active.booleanValue()) {
                    this.dial_active = true;
                    return;
                } else {
                    this.dial_active = false;
                    return;
                }
            case R.id.mute_layout /* 2131231025 */:
                if (!this.mute_active.booleanValue()) {
                    this.mute_active = true;
                    return;
                } else {
                    this.mute_active = false;
                    return;
                }
            case R.id.notes_add /* 2131231041 */:
                if (!this.bluetooth.booleanValue()) {
                    this.bluetooth = true;
                    return;
                } else {
                    this.bluetooth = false;
                    return;
                }
            case R.id.pick_call /* 2131231064 */:
                this.caling_ui.setVisibility(View.GONE);
                this.cancel_call.setVisibility(View.GONE);
                this.cancel_call_new.setVisibility(View.VISIBLE);
                this.action_layout.setVisibility(View.VISIBLE);
                this.btoom_call_layout.setVisibility(View.GONE);
                this.large_circle.setVisibility(View.GONE);
                this.timer_textview.setVisibility(View.GONE);
                this.large_circle.clearAnimation();
                if (this.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
                    this.vibrator.cancel();
                }
                try {
                    this.cutom_ringtone_player.stop();
                } catch ( Exception unused6) {
                }
                try {
                    this.mMediaPlayer_default.stop();
                } catch ( Exception unused7) {
                }
                try {
                    if (new File(this.mFileName).exists()) {
                        startPlaying();
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.speaker_layout /* 2131231166 */:
                if (!this.speaker_active.booleanValue()) {
                    this.speaker_active = true;
                    try {
                        i2 = this.mPlayer.getCurrentPosition();
                    } catch ( Exception unused8) {
                    }
                    stopPlaying();
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    this.mPlayer = mediaPlayer;
                    try {
                        mediaPlayer.setDataSource(this.mFileName);
                        this.mPlayer.setAudioStreamType(3);
                        this.mPlayer.prepare();
                        this.mPlayer.seekTo(i2);
                        this.mPlayer.start();
                        return;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                } else {
                    this.speaker_active = false;
                    try {
                        i = this.mPlayer.getCurrentPosition();
                    } catch ( Exception unused9) {
                        i = 0;
                    }
                    stopPlaying();
                    MediaPlayer mediaPlayer2 = new MediaPlayer();
                    this.mPlayer = mediaPlayer2;
                    try {
                        mediaPlayer2.setDataSource(this.mFileName);
                        this.mPlayer.setAudioStreamType(0);
                        this.mPlayer.prepare();
                        this.mPlayer.seekTo(i);
                        this.mPlayer.start();
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

    private void createNote(String str, String str2, String str3, String str4) {
        Utils.path = str4;
        this.db.insertNote(str, str2, str3, str4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mSensorManager.registerListener(this, this.mProximity, 3);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!this.fake_call_picked.booleanValue()) {
            return;
        }
        if (!this.wakeLock.isHeld()) {
            this.wakeLock.acquire();
        } else if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
    }

    public void reject(View view) {
        this.totalSeconds = 3;
        this.end_call = true;
        createNote(this.name_of_contact.getText().toString(), this.number_of_contact.getText().toString(), "00:00", this.path_image);
        this.cancel_call.setVisibility(View.GONE);
        this.large_circle.clearAnimation();
        this.caling_ui.setVisibility(View.GONE);
        this.chronometer.stop();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true)) {
            this.vibrator.cancel();
        }
        this.name_contact_new.setVisibility(View.VISIBLE);
        this.name_contact_new.setText(this.name_of_contact.getText().toString());
        this.action_layout.setVisibility(View.GONE);
        this.name_n_numb_layout.setVisibility(View.GONE);
        this.view_contact_layout.setVisibility(View.VISIBLE);
        this.btoom_call_layout.setVisibility(View.GONE);
        this.large_circle.setVisibility(View.GONE);
        this.main_layout.setBackgroundColor(-1);
        this.timer_textview.setVisibility(View.VISIBLE);
        this.timer_textview.setText("End call");
        this.cancel_call_new.setVisibility(View.GONE);
        this.main_layout.setBackgroundColor(-1);
        this.name_of_contact.setTextColor(-16777216);
        this.number_of_contact.setTextColor(-16777216);
        this.name_contact_new.setText(this.name_of_contact.getText().toString());
        try {
            this.cutom_ringtone_player.stop();
        } catch ( Exception unused) {
        }
        stopPlaying();
        try {
            this.mMediaPlayer_default.stop();
        } catch ( Exception unused2) {
        }
        finish_Timer();
    }
}
