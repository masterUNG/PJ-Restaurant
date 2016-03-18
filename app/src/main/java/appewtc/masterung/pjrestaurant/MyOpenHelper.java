package appewtc.masterung.pjrestaurant;

import android.content.Context;

/**
 * Created by masterUNG on 3/18/16 AD.
 */
public class MyOpenHelper {

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
            "URLicon text" +
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
    }   // Constructor

}   // Main Class
