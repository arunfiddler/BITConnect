package com.example.arunfiddler.myapplication;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;

public class DBHELPER1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "staff.sql";
    private static final int DATABASE_VERSION = 1;
    public static final String DB_PATH = "/data/data/com.example.arunfiddler/databases/";
    private static final String DEPT = "DEPT";
    private static final String DSN = "DSN";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String PHNO = "PHNO";
    private static final String TABLE_NAME= "staff";
    private Context mContext;
    private SQLiteDatabase mDB;

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DBHELPER1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void openDatabase() {
        String dbPath = this.mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (this.mDB == null || !this.mDB.isOpen()) {
            this.mDB = SQLiteDatabase.openDatabase(dbPath, null, 0);
        }
    }

    public void closeDatabase() {
        if (this.mDB != null) {
            this.mDB.close();
        }
    }

    public Staff login(String mno) {
        Staff questions = null;
        openDatabase();
        String[] columns = new String[]{ID, NAME, DEPT, PHNO, DSN};
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = mno;
        Cursor cursor = this.mDB.query(TABLE_NAME, columns, "ID=?", strArr, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            questions = new Staff();
            questions.id = cursor.getString(cursor.getColumnIndex(ID));
            questions.name = cursor.getString(cursor.getColumnIndex(NAME));
            questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
            questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
            questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
            cursor.close();
        }
        closeDatabase();
        return questions;
    }

    public Staff getStaff(String id) {
        openDatabase();
        String[] columns = new String[]{ID, NAME, DEPT, PHNO, DSN};
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = id;
        Cursor cursor = this.mDB.query(TABLE_NAME, columns, "ID=?", strArr, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Staff questions = new Staff();
        questions.id = cursor.getString(cursor.getColumnIndex(ID));
        questions.name = cursor.getString(cursor.getColumnIndex(NAME));
        questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
        questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
        questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
        cursor.close();
        closeDatabase();
        return questions;
    }

    public ArrayList<Staff> getUsers(String dept) {
        openDatabase();
        String[] columns = new String[]{ID, NAME, DEPT, PHNO, DSN};
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = dept;
        Cursor cursor = this.mDB.query(TABLE_NAME, columns, "DEPT=?", strArr, null, null, null);
        ArrayList<Staff> questionsArrayList = new ArrayList();
        while (cursor.moveToNext()) {
            Staff questions = new Staff();
            questions.id = cursor.getString(cursor.getColumnIndex(ID));
            questions.name = cursor.getString(cursor.getColumnIndex(NAME));
            questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
            questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
            questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
            questionsArrayList.add(questions);
        }
        cursor.close();
        closeDatabase();
        return questionsArrayList;
    }
}

