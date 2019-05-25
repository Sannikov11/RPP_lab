package com.example.rpp_lab2.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Techno implements Serializable, Parcelable {
    @SerializedName("flags")
    @Expose
    private String flags;
    @SerializedName("graphic")
    @Expose
    private String graphic;
    @SerializedName("graphic_alt")
    @Expose
    private String graphicAlt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("req1")
    @Expose
    private String req1;
    @SerializedName("req2")
    @Expose
    private String req2;
    @SerializedName("helptext")
    @Expose
    private String helptext;
    @SerializedName("bonus_message")
    @Expose
    private String bonusMessage;

    public Techno(Parcel in) {
        name = in.readString();
        helptext = in.readString();
        graphic = in.readString();
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getGraphic() {
        return "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public String getGraphicAlt() {
        return graphicAlt;
    }

    public void setGraphicAlt(String graphicAlt) {
        this.graphicAlt = graphicAlt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReq1() {
        return req1;
    }

    public void setReq1(String req1) {
        this.req1 = req1;
    }

    public String getReq2() {
        return req2;
    }

    public void setReq2(String req2) {
        this.req2 = req2;
    }

    public String getHelptext() {
        return helptext;
    }

    public void setHelptext(String helptext) {
        this.helptext = helptext;
    }

    public String getBonusMessage() {
        return bonusMessage;
    }

    public void setBonusMessage(String bonusMessage) {
        this.bonusMessage = bonusMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(helptext);
        dest.writeString(graphic);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Techno createFromParcel(Parcel in) {
            return new Techno(in);
        }
        public Techno[] newArray(int size) {
            return new Techno[size];
        }
    };
}
