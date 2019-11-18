package id.tiketbus.tiketbus.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.tiketbus.tiketbus.R;
import id.tiketbus.tiketbus.adapters.PlaceAdapter;
import id.tiketbus.tiketbus.models.Place;
import id.tiketbus.tiketbus.services.PlaceGet;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rvRekomendasi = view.findViewById(R.id.rv_rekomendasi);
        final TextView tvName = view.findViewById(R.id.hello);

        PlaceGet placeGet = new PlaceGet();
        placeGet.getPlace(new PlaceGet.OnPlaceGet() {
            @Override
            public void OnGet(ArrayList<Place> data) {
                rvRekomendasi.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                PlaceAdapter adapter = new PlaceAdapter();
                adapter.setPlaces(data);
                rvRekomendasi.setAdapter(adapter);

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Penumpang").child(FirebaseAuth.getInstance().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    tvName.setText("Halo " + dataSnapshot.child("nama").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
