package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDataQuery {

    // Thêm mới một người dùng
    public static long insert(Context context, User user) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_USER_NAME, user.getName());
        values.put(Utils.COLUMN_USER_AVATAR, user.getAvatar());
        long rs = sqLiteDatabase.insert(Utils.TABLE_USER, null, values);
        return rs;
    }

    // Lấy danh sách người dùng
    public static ArrayList<User> getAll(Context context) {
        ArrayList<User> lstUser = new ArrayList<>();
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + Utils.TABLE_USER, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String avatar = cs.getString(2);
            lstUser.add(new User(id, name, avatar));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }
    //xoa item
    public static boolean delete (Context context, int id)
    {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(Utils.TABLE_USER,Utils.COLUMN_USER_ID+"=?", new String[]{String.valueOf(id)});
        return (rs >0);
    }

    // Cập nhật thông tin người dùng
    public static int update(Context context, User user) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_USER_NAME, user.getName());
        values.put(Utils.COLUMN_USER_AVATAR, user.getAvatar());
        int rs = sqLiteDatabase.update(Utils.TABLE_USER, values, Utils.COLUMN_USER_ID + "=?", new String[]{String.valueOf(user.getId())});
        sqLiteDatabase.close();
        return (rs);
    }
}
