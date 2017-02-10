package com.inn.inn.fourthpage.savemoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inn.inn.common.InnApplication;
import com.inn.inn.common.Joiner;
import com.inn.inn.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class SaveMoneyDao extends SQLiteOpenHelper {

    private static final String SAVE_MONEY_DB_NAME = "save_money.db";
    private static final String SAVE_MONEY_TABLE = "save_money";
    private static final Object object = new Object();
    private static final int DB_VERSION = 1;
    private static SaveMoneyDao instance;

    private SaveMoneyDao(Context context) {
        super(context, SAVE_MONEY_DB_NAME, null, DB_VERSION);
    }

    public static SaveMoneyDao getInstance() {
        if (instance == null) {
            instance = new SaveMoneyDao(InnApplication.getInstance());
        }
        return instance;
    }

    private List<String> columns(String... columns) {
        return asList(columns);
    }

    private String createTable(String tableName, List<String> columns) {
        String createTable = "CREATE TABLE IF NOT EXISTS %s (%s)";
        return String.format(createTable, tableName, Joiner.on(", ").join(columns));
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.beginTransaction();
        try {
            database.execSQL(createTable(SAVE_MONEY_TABLE, columns("Id TEXT NOT NULL", "Money INT", "UsedStatus INT", "UsedTime TEXT")));
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<SaveMoney> loadAllSaveMoneys() {
        synchronized (object) {
            SQLiteDatabase database = null;
            Cursor cursor = null;
            try {
                database = getReadableDatabase();
                cursor = database.query(SAVE_MONEY_TABLE, new String[]{"Id", "Money", "UsedStatus", "UsedTime"}, "", null, null, null, null);
                return SaveMoney.toList(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeCursorQuietly(cursor);
                DatabaseUtil.closeDatabaseQuietly(database);
            }
            return new ArrayList<>();
        }
    }

    public List<SaveMoney> loadUnusedSaveMoneys() {
        synchronized (object) {
            SQLiteDatabase database = null;
            Cursor cursor = null;
            try {
                database = getReadableDatabase();
                cursor = database.query(SAVE_MONEY_TABLE, new String[]{"Id", "Money", "UsedStatus", "UsedTime"}, "UsedStatus = ?", new String[]{"0"}, null, null, null);
                return SaveMoney.toList(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeCursorQuietly(cursor);
                DatabaseUtil.closeDatabaseQuietly(database);
            }
            return new ArrayList<>();
        }
    }

    public List<SaveMoney> loadUsedSaveMoneys() {
        synchronized (object) {
            SQLiteDatabase database = null;
            Cursor cursor = null;
            try {
                database = getReadableDatabase();
                cursor = database.query(SAVE_MONEY_TABLE, new String[]{"Id", "Money", "UsedStatus", "UsedTime"}, "UsedStatus = ?", new String[]{"1"}, null, null, null);
                return SaveMoney.toList(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeCursorQuietly(cursor);
                DatabaseUtil.closeDatabaseQuietly(database);
            }
            return new ArrayList<>();
        }
    }

    public List<SaveMoney> loadTodaySaveMoneys(String time) {
        synchronized (object) {
            SQLiteDatabase database = null;
            Cursor cursor = null;
            try {
                database = getReadableDatabase();
                cursor = database.query(SAVE_MONEY_TABLE, new String[]{"Id", "Money", "UsedStatus", "UsedTime"}, "UsedTime = ?", new String[]{time}, null, null, null);
                return SaveMoney.toList(cursor);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeCursorQuietly(cursor);
                DatabaseUtil.closeDatabaseQuietly(database);
            }
            return new ArrayList<>();
        }
    }

    public boolean checkDataIsEmpty() {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = getReadableDatabase();
            cursor = database.query(SAVE_MONEY_TABLE, new String[]{"Id", "Money", "UsedStatus", "UsedTime"}, "", null, null, null, null);
            return cursor.getCount() <= 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeCursorQuietly(cursor);
            DatabaseUtil.closeDatabaseQuietly(database);
        }
        return false;
    }

    public void insertPatients(List<SaveMoney> saveMoneys) {
        synchronized (object) {
            SQLiteDatabase database = null;
            try {
                database = getWritableDatabase();
                database.beginTransaction();
                for (SaveMoney saveMoney : saveMoneys) {
                    database.insert(SAVE_MONEY_TABLE, null, saveMoney.asContentValues());
                }
                database.setTransactionSuccessful();
                database.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeDatabaseQuietly(database);
            }
        }
    }

    public void updateSaveMoney(SaveMoney saveMoney) {
        synchronized (object) {
            SQLiteDatabase database = null;
            try {
                database = getWritableDatabase();
                ContentValues patientTableValues = new ContentValues();
                patientTableValues.put("Id", saveMoney.getId());
                patientTableValues.put("Money", saveMoney.getMoney());
                patientTableValues.put("UsedStatus", saveMoney.getUsedStatus());
                patientTableValues.put("UsedTime", saveMoney.getUsedTime());
                database.update(SAVE_MONEY_TABLE, patientTableValues, "Id=?", new String[]{saveMoney.getId()});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeDatabaseQuietly(database);
            }
        }
    }

    public void deleteSaveMoneyById(String id) {
        synchronized (object) {
            SQLiteDatabase database = null;
            try {
                database = getWritableDatabase();
                database.beginTransaction();
                database.delete(SAVE_MONEY_TABLE, "Id=?", new String[]{id});
                database.setTransactionSuccessful();
                database.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseUtil.closeDatabaseQuietly(database);
            }
        }
    }
}
