package appewtc.masterung.pjrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/18/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Name = "Name";
    public static final String column_Address = "Address";
    public static final String column_Phone = "Phone";


    public static final String food_table = "foodTABLE";
    public static final String column_FoodSet = "FoodSet";
    public static final String column_Description = "Description";
    public static final String column_Price = "Price";
    public static final String column_URLicon = "URLicon";
    public static final String column_URLimage = "URLimage";


    public static final String order_table = "orderTABLE";
    public static final String column_Date = "Date";
    public static final String column_NameOrder = "NameOrder";
    public static final String column_Amount = "Amount";
    public static final String column_Total = "Total";

    public MyManage(Context context) {

        //Create & Connected
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addOrder(String strDate,
                         String strNameOrder,
                         String strAddress,
                         String strPhone,
                         String strFoodSet,
                         String strAmount,
                         String strTotal) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Date, strDate);
        contentValues.put(column_NameOrder, strNameOrder);
        contentValues.put(column_Address, strAddress);
        contentValues.put(column_Phone, strPhone);
        contentValues.put(column_FoodSet, strFoodSet);
        contentValues.put(column_Amount, strAmount);
        contentValues.put(column_Total, strTotal);

        return writeSqLiteDatabase.insert(order_table, null, contentValues);
    }


    public long addFood(String strFoodSet,
                        String strDescrip,
                        String strPrice,
                        String strURLicon,
                        String strURLimage) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_FoodSet, strFoodSet);
        contentValues.put(column_Description, strDescrip);
        contentValues.put(column_Price, strPrice);
        contentValues.put(column_URLicon, strURLicon);
        contentValues.put(column_URLimage, strURLimage);

        return writeSqLiteDatabase.insert(food_table, null, contentValues);
    }

    public long addUser(String strUser,
                        String strPassword,
                        String strName,
                        String strAddress,
                        String strPhone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);
        contentValues.put(column_Name, strName);
        contentValues.put(column_Address, strAddress);
        contentValues.put(column_Phone, strPhone);

        return writeSqLiteDatabase.insert(user_table, null, contentValues);
    }


}   // Main Class
