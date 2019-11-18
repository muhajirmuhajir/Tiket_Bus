package id.tiketbus.tiketbus.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.tiketbus.tiketbus.R;
import id.tiketbus.tiketbus.models.Tiket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private ArrayList<Tiket> tikets;

    public void setTikets(ArrayList<Tiket> tikets) {
        this.tikets = tikets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(tikets.get(position).getNama_bus());
        holder.harga.setText("Rp."+tikets.get(position).getHarga_kursi());
        holder.type.setText(tikets.get(position).getKategori());

    }

    @Override
    public int getItemCount() {
        return tikets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, type, harga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_bus_name);
            type = itemView.findViewById(R.id.tv_type);
            harga = itemView.findViewById(R.id.price);

        }
    }
}
