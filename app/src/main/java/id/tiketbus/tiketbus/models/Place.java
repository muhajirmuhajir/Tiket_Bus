package id.tiketbus.tiketbus.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String foto, id, kota, provinsi;

    public Place() {
    }

    protected Place(Parcel in) {
        foto = in.readString();
        id = in.readString();
        kota = in.readString();
        provinsi = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foto);
        parcel.writeString(id);
        parcel.writeString(kota);
        parcel.writeString(provinsi);
    }
}
