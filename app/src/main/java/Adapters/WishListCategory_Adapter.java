package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sharan.wishdrop.R;
import com.example.sharan.wishdrop.WishDetails;

import HelperClasses.ImageViewSmoothScroll;
import HelperClasses.StaggeredGridViewItem;

/**
 Created by sharan on 31/5/16. */
public class WishListCategory_Adapter extends StaggeredGridViewItem
{
    Context con;

    View mView;

    int mHeight;

    int image;

    public WishListCategory_Adapter(Context con, int image/*, ArrayList<HashMap<String, String>> list*/)
    {
        this.con = con;
        this.image = image;

        //        this.list = list;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent)
    {
        //        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



            mView = inflater.inflate(R.layout.custom_wishlist_item, parent, false);

            ImageViewSmoothScroll imgv_search_profile = (ImageViewSmoothScroll) mView.findViewById(R.id.imgv_search_profile);

            imgv_search_profile.setImageResource(image);

            mView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                Intent i=new Intent(con, WishDetails.class);
                i.putExtra("from_where","Search_Initial");
                con.startActivity(i);
                }
            });


        return mView;
    }

    @Override
    public int getViewHeight(LayoutInflater inflater, ViewGroup parent)
    {
        LinearLayout item_containerFrameLayout = (LinearLayout) mView.findViewById(R.id.container);
        item_containerFrameLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mHeight = item_containerFrameLayout.getMeasuredHeight();
        return mHeight;
    }



}
