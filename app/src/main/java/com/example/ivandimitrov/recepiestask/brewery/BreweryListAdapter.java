package com.example.ivandimitrov.recepiestask.brewery;

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
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.MyViewHolder> {
    public static final String ITEM_NAME        = "ITEM_NAME";
    public static final String ITEM_PICTURE     = "ITEM_PICTURE";
    public static final String ITEM_INFORMATION = "ITEM_INFORMATION";
    public static final String ITEM_IS_ORGANIC  = "ITEM_IS_ORGANIC";

    private List<BreweryNode> mMenuItems;
    private Activity          mActivity;

    public BreweryListAdapter(Activity activity, List<BreweryNode> menuItems) {
        mMenuItems = menuItems;
        mActivity = activity;
    }

    @Override
    public BreweryListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_layout, parent, false);
        return new BreweryListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BreweryListAdapter.MyViewHolder holder, int position) {
        final BreweryNode menuItem = mMenuItems.get(position);
        holder.mItemImage.setImageBitmap(menuItem.getBitmap());
        holder.mName.setText(menuItem.getName());
        holder.mName.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, BreweryActivity.class);
            intent.putExtra(ITEM_NAME, menuItem.getName());
            intent.putExtra(ITEM_PICTURE, menuItem.getUrl());
            intent.putExtra(ITEM_INFORMATION, menuItem.getInformation());
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