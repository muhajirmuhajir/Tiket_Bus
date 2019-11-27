package id.tiketbus.tiketbus.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.tiketbus.tiketbus.R;
import id.tiketbus.tiketbus.models.Place;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private ArrayList<Place> places;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekomendasi, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.kota.setText(places.get(position).getKota());
        holder.provinsi.setText(places.get(position).getProvinsi());

        String url = places.get(position).getFoto();
        if (!url.equals("default")) {
            Picasso.get().load(url).into(holder.imgFoto);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(places.get(holder.getAdapterPosition()));
            }
        });

    }

    public interface OnItemClickCallback{
        void onItemClicked(Place place);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView kota, provinsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.img_photo);
            kota = itemView.findViewById(R.id.tv_kota);
            provinsi = itemView.findViewById(R.id.tv_provinsi);
        }
    }
}
