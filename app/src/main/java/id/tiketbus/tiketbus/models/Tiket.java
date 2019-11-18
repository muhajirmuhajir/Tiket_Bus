package id.tiketbus.tiketbus.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tiket implements Parcelable {
    private String asal,id_tiket,no_plat,tgl_berangkat,tujuan,harga_kursi,nama_bus,kategori;

    public Tiket() {
    }

    protected Tiket(Parcel in) {
        asal = in.readString();
        id_tiket = in.readString();
        no_plat = in.readString();
        tgl_berangkat = in.readString();
        tujuan = in.readString();
        harga_kursi = in.readString();
        nama_bus = in.readString();
        kategori = in.readString();
    }

    public static final Creator<Tiket> CREATOR = new Creator<Tiket>() {
        @Override
        public Tiket createFromParcel(Parcel in) {
            return new Tiket(in);
        }

        @Override
        public Tiket[] newArray(int size) {
            return new Tiket[size];
        }
    };

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getId_tiket() {
        return id_tiket;
    }

    public void setId_tiket(String id_tiket) {
        this.id_tiket = id_tiket;
    }

    public String getNo_plat() {
        return no_plat;
    }

    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setHarga_kursi(String harga_kursi) {
        this.harga_kursi = harga_kursi;
    }

    public String getHarga_kursi() {
        return harga_kursi;
    }

    public String getNama_bus() {

        return nama_bus;
    }

    public void setNama_bus(String nama_bus) {
        this.nama_bus = nama_bus;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKategori() {
        return kategori;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(asal);
        parcel.writeString(id_tiket);
        parcel.writeString(no_plat);
        parcel.writeString(tgl_berangkat);
        parcel.writeString(tujuan);
        parcel.writeString(harga_kursi);
        parcel.writeString(nama_bus);
        parcel.writeString(kategori);
    }
}
