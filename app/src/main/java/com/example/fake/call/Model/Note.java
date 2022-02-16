package com.example.fake.call.Model;

/* loaded from: classes2.dex */
public class Note {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_PATH = "img_path";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String CREATE_TABLE = "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT,note TEXT,number TEXT,time TEXT,img_path TEXT,timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
    public static final String TABLE_NAME = "notes";
    private int id;
    private String note;
    private String number;
    private String path;
    private String time;
    private String timestamp;

    public Note() {
    }

    public Note(int i, String str, String str2, String str3, String str4, String str5) {
        this.id = i;
        this.note = str;
        this.timestamp = str5;
        this.number = str2;
        this.time = str3;
        this.path = str4;
    }

    public int getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }
}
