package com.example.ivandimitrov.recepiestask.beer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivandimitrov.recepiestask.R;

import java.util.List;

/**
 * Created by Ivan Dimitrov on 2/16/2017.
 */

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.MyViewHolder> {
    public static final String ITEM_NAME         = "ITEM_NAME";
    public static final String ITEM_PICTURE      = "ITEM_PICTURE";
    public static final String ITEM_INFORMATION  = "ITEM_INFORMATION";
    public static final String ITEM_AVAILABILITY = "ITEM_AVAILABILITY";
    public static final String ITEM_CATEGORY     = "ITEM_CATEGORY";
    public static final String ITEM_FOOD_PAIR    = "ITEM_FOOD_PAIR";
    public static final String ITEM_IS_ORGANIC   = "ITEM_IS_ORGANIC";

    private List<BeerNode> mMenuItems;
    private Activity       mActivity;

    public BeerListAdapter(Activity activity, List<BeerNode> menuItems) {
        mMenuItems = menuItems;
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BeerNode menuItem = mMenuItems.get(position);
        holder.mItemImage.setImageBitmap(menuItem.getBitmap());
        holder.mName.setText(menuItem.getName());
        holder.mName.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, BeerActivity.class);
            intent.putExtra(ITEM_NAME, menuItem.getName());
            intent.putExtra(ITEM_PICTURE, menuItem.getUrl());
            intent.putExtra(ITEM_INFORMATION, menuItem.getInformation());
            intent.putExtra(ITEM_AVAILABILITY, menuItem.getAvailability());
            intent.putExtra(ITEM_CATEGORY, menuItem.getCategory());
            intent.putExtra(ITEM_FOOD_PAIR, menuItem.getFoodPair());
            intent.putExtra(ITEM_IS_ORGANIC, menuItem.isOrganic());
            mActivity.startActivity(intent);
        });
    }

    public void clearResources() {
//        mActivity = null;
    }

    @Override
    public int getItemCount() {
        return mMenuItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImage;
        private TextView  mName;

        MyViewHolder(View view) {
            super(view);
            mItemImage = (ImageView) view.findViewById(R.id.item_icon);
            mName = (TextView) view.findViewById(R.id.item_label);
        }
    }
}
