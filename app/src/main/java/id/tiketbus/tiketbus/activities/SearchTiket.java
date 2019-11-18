package id.tiketbus.tiketbus.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.tiketbus.tiketbus.R;
import id.tiketbus.tiketbus.adapters.TicketAdapter;
import id.tiketbus.tiketbus.models.Tiket;

public class SearchTiket extends AppCompatActivity {
    ArrayList<Tiket> tikets;
    RecyclerView containerTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tiket);

        containerTicket = findViewById(R.id.rv_ticket);


        loadData();

    }

    private void loadData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Tiket");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tikets = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    final Tiket tiket = new Tiket();
                    tiket.setId_tiket(dataSnapshot1.child("id_tiket").getValue().toString());
                    tiket.setNo_plat(dataSnapshot1.child("no_plat").getValue().toString());

                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference ref = mDatabase.getReference().child("Bus").child(tiket.getNo_plat());
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String nama_bus = dataSnapshot.child("nama_bus").getValue().toString();
                                String kategori = dataSnapshot.child("kategori").getValue().toString();
                                String harga = dataSnapshot.child("harga_kursi").getValue().toString();
                                tiket.setNama_bus(nama_bus);
                                tiket.setHarga_kursi(harga);
                                tiket.setKategori(kategori);

                                tikets.add(tiket);

                                containerTicket.setLayoutManager(new LinearLayoutManager(SearchTiket.this));
                                containerTicket.setHasFixedSize(true);

                                TicketAdapter adapter = new TicketAdapter();
                                adapter.setTikets(tikets);
                                containerTicket.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
