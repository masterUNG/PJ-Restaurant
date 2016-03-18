package appewtc.masterung.pjrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 3/18/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "PJ.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Name text, " +
            "Address text, " +
            "Phone text);";

    private static final String create_food_table = "create table foodTABLE (" +
            "_id integer primary key, " +
            "FoodSet text, " +
            "Description text, " +
            "Price text, " +
            "URLicon text, " +
            "URLimage text);";

    private static final String create_order_table = "create table orderTABLE (" +
            "_id integer primary key, " +
            "Date text, " +
            "NameOrder text, " +
            "Address text, " +
            "Phone text, " +
            "FoodSet text, " +
            "Amount text, " +
            "Total text);";


    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_food_table);
        sqLiteDatabase.execSQL(create_order_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
