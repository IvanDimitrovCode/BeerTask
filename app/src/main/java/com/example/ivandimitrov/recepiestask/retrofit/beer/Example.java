package com.example.ivandimitrov.recepiestask.retrofit.beer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan Dimitrov on 2/16/2017.
 */

public class Example {
    @SerializedName("message")
    @Expose
    private String   message;
    @SerializedName("data")
    @Expose
    private BeerData data;
    @SerializedName("status")
    @Expose
    private String   status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeerData getData() {
        return data;
    }

    public void setData(BeerData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
