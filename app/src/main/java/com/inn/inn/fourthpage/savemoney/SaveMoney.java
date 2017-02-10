package com.inn.inn.fourthpage.savemoney;

import android.content.ContentValues;
import android.database.Cursor;

import com.inn.inn.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaveMoney {
    private String id;
    private int money;
    private int usedStatus;
    private String usedTime;

    public int getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(int usedStatus) {
        this.usedStatus = usedStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public static List<SaveMoney> initSaveMoneyData() {
        List<SaveMoney> saveMoneys = new ArrayList<>();
        for (int i = 1; i <= 365; i++) {
            saveMoneys.add(createSaveMoney(i));
        }
        return saveMoneys;
    }

    private static SaveMoney createSaveMoney(int money){
        SaveMoney saveMoney = new SaveMoney();
        saveMoney.setId(UUID.randomUUID().toString());
        saveMoney.setMoney(money);
        saveMoney.setUsedStatus(0);
        saveMoney.setUsedTime("");
        return saveMoney;
    }

    public static ArrayList<SaveMoney> toList(Cursor cursor) {
        ArrayList<SaveMoney> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(SaveMoney.from(cursor));
        }
        return result;
    }

    private static SaveMoney from(Cursor cursor) {
        SaveMoney saveMoney = new SaveMoney();
        saveMoney.setId(DatabaseUtil.stringValue(cursor, "Id"));
        saveMoney.setMoney(DatabaseUtil.intValue(cursor, "Money"));
        saveMoney.setUsedStatus(DatabaseUtil.intValue(cursor, "UsedStatus"));
        saveMoney.setUsedTime(DatabaseUtil.stringValue(cursor, "UsedTime"));
        return saveMoney;
    }

    public ContentValues asContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("Money", money);
        contentValues.put("UsedStatus", usedStatus);
        contentValues.put("UsedTime", usedTime);
        return contentValues;
    }
}
