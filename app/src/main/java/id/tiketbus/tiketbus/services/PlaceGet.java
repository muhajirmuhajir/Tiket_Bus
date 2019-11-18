package id.tiketbus.tiketbus.services;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.tiketbus.tiketbus.models.Place;

public class PlaceGet {
    public interface OnPlaceGet {
        void OnGet(ArrayList<Place> data);
    }

    public void getPlace(final OnPlaceGet placeGet) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Wisata");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Place> places = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Place detailPlace = dataSnapshot1.getValue(Place.class);

                        Place place = new Place();
                        place.setFoto(detailPlace.getFoto());
                        place.setId(detailPlace.getId());
                        place.setKota(detailPlace.getKota());
                        place.setProvinsi(detailPlace.getProvinsi());

                        places.add(place);
                    }
                    placeGet.OnGet(places);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
