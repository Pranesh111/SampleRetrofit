package atrue.pranesh.sampleretrofit2.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Users implements Parcelable {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("address")
    public Address address;
    @SerializedName("phone")
    public String phone;
    @SerializedName("website")
    public String website;
    @SerializedName("company")
    public Company company;

    public static class Geo {
        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;
    }

    public static class Address {
        @SerializedName("street")
        public String street;
        @SerializedName("suite")
        public String suite;
        @SerializedName("city")
        public String city;
        @SerializedName("zipcode")
        public String zipcode;
        @SerializedName("geo")
        public Geo geo;
    }

    public static class Company {
        @SerializedName("name")
        public String name;
        @SerializedName("catchPhrase")
        public String catchPhrase;
        @SerializedName("bs")
        public String bs;
    }

    protected Users(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        phone = in.readString();
        website = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(website);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
