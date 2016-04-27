package appewtc.masterung.pjrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 4/27/16 AD.
 */
public class OrderAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] foodStrings, priceStrings, iconStrings;

    public OrderAdapter(Context context,
                        String[] foodStrings,
                        String[] priceStrings,
                        String[] iconStrings) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
        this.iconStrings = iconStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.order_listview, viewGroup, false);

        TextView foodTextView = (TextView) view1.findViewById(R.id.textView2);
        foodTextView.setText(foodStrings[i]);

        TextView priceTextView = (TextView) view1.findViewById(R.id.textView3);
        priceTextView.setText(priceStrings[i]);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView);
        Picasso.with(context).load(iconStrings[i]).resize(100,100).into(iconImageView);


        return view1;
    }
}   // Main Class
