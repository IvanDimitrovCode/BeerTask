package com.example.ivandimitrov.recepiestask.retrofit.brewery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan Dimitrov on 2/17/2017.
 */

public class ExampleBrewery {

    @SerializedName("message")
    @Expose
    private String      message;
    @SerializedName("data")
    @Expose
    private BreweryData data;
    @SerializedName("status")
    @Expose
    private String      status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BreweryData getData() {
        return data;
    }

    public void setData(BreweryData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
