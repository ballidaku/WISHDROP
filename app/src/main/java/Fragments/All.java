package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameba.sharan.wishdrop.R;

import java.util.ArrayList;

import Adapters.WishListCategory_Adapter;
import HelperClasses.StaggeredGridView;

/**
 A simple {@link Fragment} subclass. */
public class All extends Fragment
{
    Context con;

    StaggeredGridView staggeredGridView;

    public All()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        con = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);



        setUpIDS(view);
        setUpViews();

        return view;
    }


    public void setUpIDS(View view)
    {


       /* Typeface tf = Typeface.createFromAsset(getAssets(),"Lobster.otf");
        TextView tv = (TextView) findViewById(R.id.txtv_header);
        tv.setTypeface(tf);*/


        staggeredGridView = (StaggeredGridView)view. findViewById(R.id.gridView_search);

        staggeredGridView.setOnScrollListener(scrollListener);



       /* adapter=new Search_Initial_Adapter(con);
        gridView_search.setAdapter(adapter);*/
    }

    public void setUpViews()
    {

        ArrayList<Integer> product_image_list = new ArrayList<>();

        product_image_list.add(R.mipmap.profile_pic);
        product_image_list.add(R.mipmap.img_profilebg);
        product_image_list.add(R.mipmap.profile_pic);
        product_image_list.add(R.mipmap.img_profilebg);
        product_image_list.add(R.mipmap.profile_pic);
        product_image_list.add(R.mipmap.img_profilebg);
        product_image_list.add(R.mipmap.profile_pic);
        product_image_list.add(R.mipmap.img_profilebg);
        product_image_list.add(R.mipmap.profile_pic);
        product_image_list.add(R.mipmap.img_profilebg);

        WishListCategory_Adapter item;
        for (int i = 0; i < product_image_list.size(); i++)
        {
            item = new WishListCategory_Adapter(con, product_image_list.get(i));
            staggeredGridView.addItem(item);
        }
    }


    private StaggeredGridView.OnScrollListener scrollListener = new StaggeredGridView.OnScrollListener()
    {
        public void onTop()
        {
        }

        public void onScroll()
        {

        }

        public void onBottom()
        {
            //            loadMoreData();
        }
    };

}
