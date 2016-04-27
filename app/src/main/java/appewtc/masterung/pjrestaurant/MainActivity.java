package appewtc.masterung.pjrestaurant;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Request SQLite
        myManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete SQLite
        deleteSQLite();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // Main Method

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(MainActivity.this, "กรุณากรอกให้ครบ ทุกช่อง คะ");
        } else {
            checkUser();
        }

    }   // clickSign

    private void checkUser() {

        try {

            String[] resultStrings = myManage.searchUser(userString);

            if (passwordString.equals(resultStrings[2])) {
                //Password True
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("Result", resultStrings);
                startActivity(intent);
                finish();

            } else {
                //Password False
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(MainActivity.this, "ลองพิมพ์ใหม่ Password ผิด");
            }

        } catch (Exception e) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(MainActivity.this, "ไม่มี " + userString + " ในฐานข้อมูลของเรา");
        }


    }   // checkUser

    private void synJSONtoSQLite() {

        //Connected Http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTimes = 0;
        while (intTimes <=1) {

            //1 Create InputStream
            InputStream inputStream = null;
            String[] urlStrings = {"http://swiftcodingthai.com/pj/php_get_user_master.php",
                    "http://swiftcodingthai.com/pj/php_get_food_master.php"};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlStrings[intTimes]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            } catch (Exception e) {
                Log.d("pj", "InputStream ==> " + e.toString());
            }

            //2 Create JSON String
            String strJSON = null;

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLine);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();

            } catch (Exception e) {
                Log.d("pj", "JSON String ==> " + e.toString());
            }

            //3 Update to SQLite
            try {

                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 0:

                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);
                            String strAddress = jsonObject.getString(MyManage.column_Address);
                            String strPhone = jsonObject.getString(MyManage.column_Phone);

                            myManage.addUser(strUser, strPassword, strName, strAddress, strPhone);

                            break;
                        case 1:

                            String strFoodSet = jsonObject.getString(MyManage.column_FoodSet);
                            String strDescrip = jsonObject.getString(MyManage.column_Description);
                            String strPrice = jsonObject.getString(MyManage.column_Price);
                            String strURLicon = jsonObject.getString(MyManage.column_URLicon);
                            String strURLimage = jsonObject.getString(MyManage.column_URLimage);

                            myManage.addFood(strFoodSet, strDescrip, strPrice, strURLicon, strURLimage);

                            break;
                    }   // switch

                }   // for

            } catch (Exception e) {
                Log.d("pj", "Update SQLite ==> " + e.toString());
            }



            intTimes += 1;
        }   // while

    }   // synJSON

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.food_table, null, null);
        sqLiteDatabase.delete(MyManage.order_table, null, null);
    }

    private void testAddValue() {
        myManage.addUser("user", "pass", "name", "address", "phone");
        myManage.addFood("food", "descrip", "price", "icon", "image");
        myManage.addOrder("date", "name", "address", "phone", "food", "amount", "total");
    }

}   // Main Class
