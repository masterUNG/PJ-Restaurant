package appewtc.masterung.pjrestaurant;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by masterUNG on 3/18/16 AD.
 */
public class MyAlert {

    public void myDialog(Context context, String strMessage) {
        Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
    }

}   // Main Class
