package com.example.ivandimitrov.recepiestask.beer;

import android.graphics.Bitmap;

/**
 * Created by Ivan Dimitrov on 2/16/2017.
 */

public class BeerNode {
    private String  mName;
    private String  mUrl;
    private Bitmap  mBitmap;
    private String  mInformation;
    private String  mAvailability;
    private String  mCategory;
    private String  mFoodPair;
    private boolean mIsOrganic;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getInformation() {
        return mInformation;
    }

    public void setInformation(String information) {
        mInformation = information;
    }

    public String getAvailability() {
        return mAvailability;
    }

    public void setAvailability(String availability) {
        mAvailability = availability;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getFoodPair() {
        return mFoodPair;
    }

    public void setFoodPair(String foodPair) {
        mFoodPair = foodPair;
    }

    public boolean isOrganic() {
        return mIsOrganic;
    }

    public void setOrganic(boolean organic) {
        mIsOrganic = organic;
    }
}
