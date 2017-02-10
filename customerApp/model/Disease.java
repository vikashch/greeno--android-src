package com.green0.customerApp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ironman on 15/12/16.
 */

public class Disease {
    @SerializedName("_id")
    private String _id;

    @SerializedName("info")
    private Info info;

    @SerializedName("name")
    private String name;

    @SerializedName("__v")
    private int __v;

    public Disease(String _id, Info info, String name, int __v) {
        this._id = _id;
        this.info = info;
        this.name = name;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

class Info {
    @SerializedName("chemical")
    private Chemical chemical;
    @SerializedName("chemCtrl")
    private String chemCtrl;
    @SerializedName("bioCtrl")
    private String bioCtrl;
    @SerializedName("symptoms")
    private String symptoms;
    @SerializedName("_id")
    private String _id;

    public Info(Chemical chemical, String chemCtrl, String bioCtrl, String symptoms, String _id) {
        this.chemical = chemical;
        this.chemCtrl = chemCtrl;
        this.bioCtrl = bioCtrl;
        this.symptoms = symptoms;
        this._id = _id;
    }

    public Chemical getChemical() {
        return chemical;
    }

    public void setChemical(Chemical chemical) {
        this.chemical = chemical;
    }

    public String getChemCtrl() {
        return chemCtrl;
    }

    public void setChemCtrl(String chemCtrl) {
        this.chemCtrl = chemCtrl;
    }

    public String getBioCtrl() {
        return bioCtrl;
    }

    public void setBioCtrl(String bioCtrl) {
        this.bioCtrl = bioCtrl;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
class Chemical {
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("chemId")
    private String chemId;
    @SerializedName("_id")
    private String _id;

    public Chemical(int quantity, String chemId, String _id) {
        this.quantity = quantity;
        this.chemId = chemId;
        this._id = _id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getChemId() {
        return chemId;
    }

    public void setChemId(String chemId) {
        this.chemId = chemId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
