package com.example.ivandimitrov.recepiestask.brewery;

import android.graphics.Bitmap;

/**
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class BreweryNode {
    private String  mName;
    private String  mUrl;
    private Bitmap  mBitmap;
    private String  mInformation;
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


    public boolean isOrganic() {
        return mIsOrganic;
    }

    public void setOrganic(boolean organic) {
        mIsOrganic = organic;
    }
}
