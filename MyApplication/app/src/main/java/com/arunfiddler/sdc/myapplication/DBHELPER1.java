package com.arunfiddler.sdc.myapplication;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHELPER1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bbit.db";
    private static final int DATABASE_VERSION = 3;
    public static final String DB_PATH = "/data/data/com.arunfiddler.sdc/databases/";
    private static final String DEPT = "DEPT";
    private static final String DSN = "DSN";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String MAIL = "MAIL";
    private static final String QFN = "QFN";
    private static final String PHNO = "PHNO";
    private static final String TABLE_NAME= "staff";
    private Context mContext;
    private SQLiteDatabase mDB;

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1;
        try{
        query1 = "CREATE TABLE IF NOT EXISTS staff (ID INTEGER, NAME TEXT,MAIL TEXT, QFN TEXT, DSN TEXT, PHNO TEXT, DEPT TEXT)";
        sqLiteDatabase.execSQL(query1);
    } catch (Exception e) {
        Log.d("creat err", "Error while trying to create table"+e.getMessage());
    } finally {
            System.out.println("creat table");
    }

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query;
        query = "DROP TABLE IF EXISTS staff";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
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
        String[] columns = new String[]{ID, NAME, MAIL, QFN, DEPT, PHNO, DSN};
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = mno;
        Cursor cursor = this.mDB.query(TABLE_NAME, columns, "ID=?", strArr, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            questions = new Staff();
            questions.id = cursor.getString(cursor.getColumnIndex(ID));
            questions.name = cursor.getString(cursor.getColumnIndex(NAME));
            questions.mail = cursor.getString(cursor.getColumnIndex(MAIL));
            questions.qfn = cursor.getString(cursor.getColumnIndex(QFN));
            questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
            questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
            questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
            cursor.close();
        }
        closeDatabase();
        return questions;
    }
    public boolean checkData( ) {
        mDB=this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }


    }


    public Staff getStaff(String id) {
        openDatabase();
        String[] columns = new String[]{ID, NAME, MAIL, QFN, DEPT, PHNO, DSN};
        String[] strArr = new String[DATABASE_VERSION];
        strArr[0] = id;
        Cursor cursor = this.mDB.query(TABLE_NAME, columns, "ID=?", strArr, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Staff questions = new Staff();
        questions.id = cursor.getString(cursor.getColumnIndex(ID));
        questions.name = cursor.getString(cursor.getColumnIndex(NAME));
        questions.mail = cursor.getString(cursor.getColumnIndex(MAIL));
        questions.qfn = cursor.getString(cursor.getColumnIndex(QFN));
        questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
        questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
        questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
        cursor.close();
        closeDatabase();
        return questions;
    }
    public void insertUser(String value) {
        SQLiteDatabase mDB = this.getWritableDatabase();
        mDB.beginTransaction();
        try {
            mDB.execSQL(value);
            System.out.println(""+value);
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ins err", "Error while trying to insert contacts table"+e.getMessage());
        } finally {
            mDB.endTransaction();
        }

    }
    public ArrayList<Staff> getUsers(String dept) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Staff> questionsArrayList = new ArrayList<Staff>();
        String selectQuery = "SELECT  * FROM staff where DEPT='"+dept+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Staff questions = new Staff();
                    questions.id = cursor.getString(cursor.getColumnIndex(ID));
                    questions.name = cursor.getString(cursor.getColumnIndex(NAME));
                    questions.mail = cursor.getString(cursor.getColumnIndex(MAIL));
                    questions.qfn = cursor.getString(cursor.getColumnIndex(QFN));
                    questions.dept = cursor.getString(cursor.getColumnIndex(DEPT));
                    questions.dsn = cursor.getString(cursor.getColumnIndex(DSN));
                    questions.phno = cursor.getString(cursor.getColumnIndex(PHNO));
                    questionsArrayList.add(questions);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("errror", "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return questionsArrayList;
    }
}

