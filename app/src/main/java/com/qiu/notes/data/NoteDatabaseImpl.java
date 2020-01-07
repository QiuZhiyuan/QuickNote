package com.qiu.notes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteDatabaseImpl {

    private static NoteDatabaseImpl sInstance;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBEntry.TABLE_NAME
                    + " ("
                    + DBEntry.ID + " INTEGER PRIMARY KEY,"
                    + DBEntry.CREATE_TIME + " INTEGER,"
                    + DBEntry.UPDATE_TIME + " INTEGER,"
                    + DBEntry.CONTENT + " TEXT"
                    + ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME;

    public static NoteDatabaseImpl i() {
        if (sInstance == null) {
            synchronized (NoteDatabaseImpl.class) {
                if (sInstance == null) {
                    sInstance = new NoteDatabaseImpl();
                }
            }
        }
        return sInstance;
    }

    private static class DBEntry {
        static final String DATABASE_NAME = "Quick_Note_DB.db";
        static final int VERSION = 1;
        static final String TABLE_NAME = "NoteTable";
        static final String ID = "id";
        static final String CREATE_TIME = "create_time";
        static final String UPDATE_TIME = "update_time";
        static final String CONTENT = "content";

        private DBEntry() {
        }

        static String[] getColumns() {
            return new String[]{
                    ID, CREATE_TIME, UPDATE_TIME, CONTENT
            };
        }
    }

    private static class NoteSQLiteOpenHelper extends SQLiteOpenHelper {

        public NoteSQLiteOpenHelper(Context context, String name,
                SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    @Nullable
    private NoteSQLiteOpenHelper mNoteSQLiteOpenHelper;

    private NoteDatabaseImpl() {
    }

    public void init(@NonNull Context context) {
        mNoteSQLiteOpenHelper =
                new NoteSQLiteOpenHelper(context, DBEntry.DATABASE_NAME, null, DBEntry.VERSION);
    }

    public void insert(int id, long createTime, long updateTime, @NonNull String content) {
        if (mNoteSQLiteOpenHelper == null) {
            return;
        }
        final SQLiteDatabase db = mNoteSQLiteOpenHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(DBEntry.ID, id);
        values.put(DBEntry.CREATE_TIME, createTime);
        values.put(DBEntry.UPDATE_TIME, updateTime);
        values.put(DBEntry.CONTENT, content);

        long newRowId = db.insert(DBEntry.TABLE_NAME, null, values);
    }

    @NonNull
    public List<TextContentEntry> queryAll() {
        final List<TextContentEntry> entryList = new ArrayList<>();
        if (mNoteSQLiteOpenHelper == null) {
            return entryList;
        }
        final SQLiteDatabase db = mNoteSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(DBEntry.TABLE_NAME, DBEntry.getColumns(), null, null, null, null,
                DBEntry.CREATE_TIME + " DESC");
        while (cursor.moveToNext()) {
            TextContentEntry entry = new TextContentEntry();
            entry.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBEntry.ID)));
            entry.setCreatedTime(cursor.getLong(cursor.getColumnIndexOrThrow(DBEntry.CREATE_TIME)));
            entry.setUpdateTime(cursor.getLong(cursor.getColumnIndexOrThrow(DBEntry.UPDATE_TIME)));
            entry.setNote(cursor.getString(cursor.getColumnIndexOrThrow(DBEntry.CONTENT)));
            entryList.add(entry);
        }
        cursor.close();
        return entryList;
    }
}

/*
    SQLiteDatabase db = dbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
        BaseColumns._ID,
        FeedEntry.COLUMN_NAME_TITLE,
        FeedEntry.COLUMN_NAME_SUBTITLE
        };

    // Filter results WHERE "title" = 'My Title'
    String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
    String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

    Cursor cursor = db.query(
        FeedEntry.TABLE_NAME,   // The table to query
        projection,             // The array of columns to return (pass null to get all)
        selection,              // The columns for the WHERE clause
        selectionArgs,          // The values for the WHERE clause
        null,                   // don't group the rows
        null,                   // don't filter by row groups
        sortOrder               // The sort order
        );
 * */