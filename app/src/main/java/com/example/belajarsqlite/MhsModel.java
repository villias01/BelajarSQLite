package com.example.belajarsqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class MhsModel implements Parcelable {

    int id;
    String nama;
    String nim;
    String nomerhp;

    public MhsModel(int id, String nama, String nim, String nomerhp) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.nomerhp = nomerhp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNomerhp() {
        return nomerhp;
    }

    public void setNomerhp(String nomerhp) {
        this.nomerhp = nomerhp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.nim);
        dest.writeString(this.nomerhp);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.nim = source.readString();
        this.nomerhp = source.readString();
    }

    public MhsModel() {
    }

    protected MhsModel(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.nim = in.readString();
        this.nomerhp = in.readString();
    }

    public static final Creator<MhsModel> CREATOR = new Creator<MhsModel>() {
        @Override
        public MhsModel createFromParcel(Parcel source) {
            return new MhsModel(source);
        }

        @Override
        public MhsModel[] newArray(int size) {
            return new MhsModel[size];
        }
    };
}
