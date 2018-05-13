package youngjung.test.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import youngjung.test.Model.Category;
import youngjung.test.R;


public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "huhbul";

    private static final String TABLE_ctg = "sum_ctg";      // 카테고리 랭킹
    private static final String TABLE_date = "tb_date";     // 날짜별 개수
    private static final String TABLE_money = "sum_money";  // 누적 금액
    private static final String TABLE_saving_item = "tb_saving_item";

    public static final String CREATE_TABLE_ctg = "create table "
            + TABLE_ctg + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, ctg TEXT);";

    public static final String CREATE_TABLE_date = "create table "
            + TABLE_date + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, date String);";

    public static final String CREATE_TABLE_money = "create table "
            + TABLE_money + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, money Integer);";

    public static final String CREATE_TABLE_item = "create table "
            + TABLE_saving_item + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, uid String);";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ctg);
        db.execSQL(CREATE_TABLE_date);
        db.execSQL(CREATE_TABLE_money);
        db.execSQL(CREATE_TABLE_item);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_date);
        onCreate(db);
    }

    // 잘 나오는지 확인
    public String allselect() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from sum_ctg", null);
        StringBuilder st = new StringBuilder();
        while (cursor.moveToNext()) {
            st.append(cursor.getString(1) + ", ");
        }
        cursor.close();
        db.close();
        return st.toString();
    }

    // 잘 나오는지 확인
    public ArrayList<Category> getCategoryRankingList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from sum_ctg", null);

        // 해시맵으로 해당 key에 맞는 value 값 ++
        HashMap<String, Integer> categoryHashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            String key = cursor.getString(1);
            int value;

            // hash 값에 없을 경우 0 / 아닐 시 저장된 숫자값 불러옴
            if (categoryHashMap.get(key) == null)
                value = 0;
            else
                value = categoryHashMap.get(key);

            // hash 에 +1 한 값을 등록
            categoryHashMap.put(key, value + 1);
        }

        // Iterator로 변환하여 내림차순 정렬
        Iterator it = sortByValue(categoryHashMap).iterator();

        ArrayList<Category> output = new ArrayList<>();
        while(it.hasNext()){
            String temp = (String) it.next();
            output.add(new Category(temp, categoryHashMap.get(temp), R.mipmap.ic_launcher));
        }

        cursor.close();
        db.close();
        return output;
    }

    public List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v1).compareTo(v2);
            }

        });
        Collections.reverse(list); // 주석시 오름차순

        return list;
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

    public ArrayList<String> get_date() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from tb_date", null);
        if (cursor.getCount() == 0) {
            Log.e("get_date : ", "0");
            return null;
        } else {
            ArrayList<String> arr = new ArrayList<>();
            while (cursor.moveToNext()) {
                arr.add(cursor.getString(1));
            }
            Collections.sort(arr);
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
        long rowID = db.insert(TABLE_ctg, null, values);
        Log.i("db.insert_ctg()", "rowID : " + rowID);

        db.close();
        return (int) rowID;
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
        return (int) rowID;
    }

    // 누적금액 저장
    public void insert_money(int money) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor tmp = db.rawQuery("SELECT count(*) FROM sum_money", null);
        int cnt = 0, curMoney;
        while (tmp.moveToNext()) {
            cnt = tmp.getInt(0);
        }
        if (cnt != 0) {
            // 최신값 빼고 다 지우기
            db.rawQuery("DELETE FROM sum_money WHERE _index != (SELECT max(_index) FROM sum_money)", null);

            Cursor c = db.rawQuery("SELECT * FROM sum_money", null);
            int maxMoney = 0;
            while (c.moveToNext()) {
                maxMoney = c.getInt(1);

            }
            curMoney = maxMoney + money;
            Log.e("누적금액: ", " " +curMoney);
        } else {
            curMoney = money;
            Log.e("누적금액2: ", " " +curMoney);
        }

        ContentValues cv = new ContentValues();
        cv.put("money", curMoney);

        db.update(TABLE_money, cv, null, null);
        db.close();
    }

    // 누적금액 불러오기
    public String get_money() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM sum_money WHERE _index = (SELECT max(_index) FROM sum_money)", null);
        if (cursor.getCount() == 0) {
            Log.e("sum_money : ", "0");
            return "0";
        } else {
            String lastmoney = "0";
            while (cursor.moveToNext()) {
                lastmoney = cursor.getString(1);
            }
            cursor.close();
            db.close();
            return lastmoney;
        }
    }

    // 저금할 아이템 저장
    public int insert_saving_item(String uid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        long rowID = db.insert(TABLE_saving_item, null, values);
        Log.i("db.insert_saving_item", "rowID : " + rowID);

        db.close();
        return (int) rowID;
    }

    // 저금된 아이템 불러오기
    public ArrayList<String> get_saving_item() {
        SQLiteDatabase db = getReadableDatabase();

        String CREATE_tb = "create table if not exists "
                + TABLE_saving_item + "(_index INTEGER PRIMARY KEY AUTOINCREMENT, uid String);";
        db.execSQL(CREATE_tb);

        Cursor cursor = db.rawQuery("SELECT * from tb_saving_item", null);
        if (cursor.getCount() == 0) {
            Log.e("get_item : ", "0");
            return null;
        } else {
            ArrayList<String> arr = new ArrayList<>();
            while (cursor.moveToNext()) {
                arr.add(cursor.getString(1));
            }
            cursor.close();
            db.close();
            return arr;
        }
    }


    public void delete(int _index) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM sum_ctg WHERE _index=" + _index + ";");
        db.close();
    }
}
