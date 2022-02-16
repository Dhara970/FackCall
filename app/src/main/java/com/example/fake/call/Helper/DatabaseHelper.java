package com.example.fake.call.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fake.call.Model.Note;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(Note.CREATE_TABLE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(sQLiteDatabase);
    }

    public long insertNote(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.COLUMN_NOTE, str);
        contentValues.put(Note.COLUMN_NUMBER, str2);
        contentValues.put("time", str3);
        contentValues.put(Note.COLUMN_IMAGE_PATH, str4);
        long insert = writableDatabase.insert(Note.TABLE_NAME, null, contentValues);
        writableDatabase.close();
        return insert;
    }

    public Note getNote(long j) {
        Cursor query = getReadableDatabase().query(Note.TABLE_NAME, new String[]{"id", Note.COLUMN_NOTE, Note.COLUMN_NUMBER, "time", Note.COLUMN_IMAGE_PATH, "timestamp"}, "id=?", new String[]{String.valueOf(j)}, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        @SuppressLint("Range") Note note = new Note(query.getInt(query.getColumnIndex("id")), query.getString(query.getColumnIndex(Note.COLUMN_NOTE)), query.getString(query.getColumnIndex(Note.COLUMN_NUMBER)), query.getString(query.getColumnIndex("time")), query.getString(query.getColumnIndex(Note.COLUMN_IMAGE_PATH)), query.getString(query.getColumnIndex("timestamp")));
        query.close();
        return note;
    }

    @SuppressLint("Range")
    public List<Note> getAllNotes() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM notes ORDER BY timestamp DESC", null);
        if (rawQuery.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(rawQuery.getInt(rawQuery.getColumnIndex("id")));
                note.setNote(rawQuery.getString(rawQuery.getColumnIndex(Note.COLUMN_NOTE)));
                note.setNumber(rawQuery.getString(rawQuery.getColumnIndex(Note.COLUMN_NUMBER)));
                note.setTime(rawQuery.getString(rawQuery.getColumnIndex("time")));
                note.setPath(rawQuery.getString(rawQuery.getColumnIndex(Note.COLUMN_IMAGE_PATH)));
                note.setTimestamp(rawQuery.getString(rawQuery.getColumnIndex("timestamp")));
                arrayList.add(note);
            } while (rawQuery.moveToNext());
            writableDatabase.close();
            return arrayList;
        }
        writableDatabase.close();
        return arrayList;
    }

    public int getNotesCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM notes", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }

    public int updateNote(Note note) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Note.COLUMN_NOTE, note.getNote());
        return writableDatabase.update(Note.TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Note.TABLE_NAME, "id = ?", new String[]{String.valueOf(note.getId())});
        writableDatabase.close();
    }

    public void deleteallNotes() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Note.TABLE_NAME, null, null);
        writableDatabase.close();
    }
}
