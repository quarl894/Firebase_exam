package youngjung.test.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class MyDBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "huhbul";

    private static final String TABLE_ctg = "sum_ctg";
    private static final String TABLE_date = "tb_date";
    private static final String TABLE_money = "sum_money";
    public static final String CREATE_TABLE_ctg = "create table "
            + TABLE_ctg + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, ctg TEXT);";

    public static final String CREATE_TABLE_date = "create table "
            + TABLE_date + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, date String);";

    public static final String CREATE_TABLE_money = "create table "
            + TABLE_money + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, money Integer);";

    public MyDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ctg);
        db.execSQL(CREATE_TABLE_date);
        db.execSQL(CREATE_TABLE_money);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_date);
        onCreate(db);
    }

    // 잘 나오는지 확인
    public String allselect(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from sum_ctg",null);
        StringBuilder st = new StringBuilder();
        while(cursor.moveToNext()){
            st.append(cursor.getString(1) + ", ");
        }
        cursor.close();
        db.close();
        return st.toString();
    }

    //table 존재여부 확인
//    public boolean test(){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT name from sqlite_master WHERE type='table' AND name='tb_date'",null);
//        cursor.moveToFirst();
//        if(cursor.getCount()>0){
//            return true;
//        }else return false;
//    }

    public ArrayList<String> get_date(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from tb_date", null);
        if(cursor.getCount()==0){
            Log.e("get_date : ", "0");
            return null;
        }else{
            ArrayList<String> arr = new ArrayList<>();
            while(cursor.moveToNext()){
                arr.add(cursor.getString(1));
            }
            cursor.close();
            db.close();
            return arr;
        }
    }

    //카테고리 저장
    public int insert_ctg(String ctg) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ctg", ctg);
        long rowID = db.insert(TABLE_ctg, null,values);
        Log.i("db.insert_ctg()", "rowID : " + rowID);

        db.close();
        return (int)rowID;
    }
    //날짜 저장
    public int insert_date(String date) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        long rowID = db.insert(TABLE_date, null, values);
        Log.i("db.insert_date()", "rowID : " + rowID);

        db.close();
        return (int)rowID;
    }

    //누적금액 저장
    public int insert_money(int money) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money", money);
        long rowID = db.insert(TABLE_money, null, values);
        Log.i("db.insert()", "rowID : " + rowID);

        db.close();
        return (int)rowID;
    }



    public void delete(int _index) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM sum_ctg WHERE _index=" + _index+";");
        db.close();
    }
}
