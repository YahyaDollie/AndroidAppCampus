package com.mosquefinder.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String SALAAH_TIMES = "SALAAH_TIMES";
    public static final String DATE = "Date";
    public static final String FAJR = "Fajr";
    public static final String THUR = "Thur";
    public static final String ASR = "Asr";
    public static final String MAGRIEB = "Magrieb";
    public static final String ISHAI = "Ishai";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "times.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + SALAAH_TIMES + " (" + DATE + " int, " + FAJR + " varchar(255), " + THUR + " varchar(255), " + ASR + " varchar(255), " + MAGRIEB + " varchar(255), " + ISHAI + " varchar(255))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addTime(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DATE, 1);
        cv.put(FAJR, "06:00");
        cv.put(THUR, "13:00");
        cv.put(ASR, "16:00");
        cv.put(MAGRIEB, "19:00");
        cv.put(ISHAI, "20:00");


        long insert = db.insert(SALAAH_TIMES, null , cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<String> getSalaahTimes(){
        String queryString = "SELECT * FROM " + SALAAH_TIMES;
        String time = null;
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> returnList = new ArrayList<String>();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                String fajr = cursor.getString(1);
                String thur = cursor.getString(2);
                String asr = cursor.getString(3);
                String magrieb = cursor.getString(4);
                String ishai = cursor.getString(5);

                returnList.add(fajr);
                returnList.add(thur);
                returnList.add(asr);
                returnList.add(magrieb);
                returnList.add(ishai);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }
}
