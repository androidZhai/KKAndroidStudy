package com.kkandroidstudy.network.bean;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by shiyan on 2016/10/9.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class PersonInfo2 {
    private String success;

    private Result result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PersonInfo2{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
