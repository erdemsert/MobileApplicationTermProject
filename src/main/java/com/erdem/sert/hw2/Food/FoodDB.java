package com.erdem.sert.hw2.Food;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class FoodDB {
    public static String TABLE_NAME="food";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_IMAGE = "image";
    public static String FIELD_TYPE="type";
    public static String FIELD_PRICE = "price";
    public static String FIELD_CALORIE="calorie";


    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+"( "+FIELD_ID+" INTEGER, "+FIELD_NAME+" TEXT, "+FIELD_IMAGE+" BLOB, "+FIELD_TYPE+" INTEGER, "+FIELD_PRICE+" NUMERIC, "+FIELD_CALORIE+" NUMERIC, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Food> getAllFoods(DatabaseHelper dbhelper){
        Cursor cursor= dbhelper.getAllRecords(TABLE_NAME,null);
        Food anItem;
        ArrayList<Food> data = new ArrayList<>();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image= cursor.getBlob(2);
            int ty=cursor.getInt(3);
            double price=cursor.getDouble(4);
            double calorie=cursor.getDouble(5);
            anItem = new Food(id, name,image,ty,price,calorie);
            data.add(anItem);
        }
        return data;
    }
    public static ArrayList<Food> getFoodsByType(DatabaseHelper dbhelper, int type){
        String[] cols = {"id","name","image","type","price","calorie"};
        String where=FIELD_TYPE+"="+type;
        Cursor cursor= dbhelper.getSomeRecords(TABLE_NAME,cols,where);
        Food anItem;
        ArrayList<Food> data = new ArrayList<Food>();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image= cursor.getBlob(2);
            int ty=cursor.getInt(3);
            double price=cursor.getDouble(4);
            double calorie=cursor.getDouble(5);
            anItem = new Food(id, name,image,ty,price,calorie);
            data.add(anItem);
        }

        return data;
    }
    public static boolean insert(DatabaseHelper dbHelper, String id, String name, String image,String type,double price,int calorie) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_IMAGE, image);
        contentValues.put(FIELD_TYPE,type);
        contentValues.put(FIELD_PRICE,price);
        contentValues.put(FIELD_CALORIE,calorie);


        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, String id, String name, String image,String type,double price,int calorie) {
        //ContentValues  allows to define key value pairs.
        //The key represents the table column identifier and the value represents the content for the table record in this column.
        //ContentVales can be used for insert and update operations over table

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_IMAGE, image);
        contentValues.put(FIELD_TYPE,type);
        contentValues.put(FIELD_PRICE,price);
        contentValues.put(FIELD_CALORIE,calorie);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, String id){
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
