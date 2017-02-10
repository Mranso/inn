package com.inn.inn.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil {
    public static String stringValue(Cursor cursor, String columnName) {
        if (cursor.getColumnIndex(columnName) == -1 || null == cursor.getString(cursor.getColumnIndex(columnName))) {
            return "";
        }
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int intValue(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static void closeDatabaseQuietly(SQLiteDatabase database) {
        if (database == null)
            return;

        try {
            if (database.inTransaction()) {
                database.endTransaction();
            }
            database.close();
        } catch (Exception ignored) {
        }
    }

    public static void closeCursorQuietly(Cursor cursor) {
        if (cursor == null || cursor.isClosed())
            return;

        try {
            cursor.close();
        } catch (Exception ignored) {
        }
    }

    public static void closeHelperQuietly(SQLiteOpenHelper helper) {
        if (helper == null)
            return;

        try {
            helper.close();
        } catch (Exception ignored) {
        }
    }

    public static String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

    public static ContentValues getContentValues(String... params) throws Exception {
        if (params == null || params.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating names and values");
        }
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < params.length; i += 2) {
            String name = params[i];
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("column name should not be empty");
            }
            String value = params[i + 1];
            contentValues.put(name, value);
        }
        return contentValues;
    }

    public static void closeQuietly(Cursor cursor, SQLiteDatabase database, SQLiteOpenHelper helper) {
        closeCursorQuietly(cursor);
        closeDatabaseQuietly(database);
        closeHelperQuietly(helper);
    }
}
