package com.example.belajarsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsVH> {

    private ArrayList<MhsModel> mhsList ;
    private final OnItemClickListener listener;

    public MhsAdapter(ArrayList<MhsModel> mhsList, OnItemClickListener listener) {
        this.mhsList = mhsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MhsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_listmhs, parent, false);

        return new MhsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsVH holder, int position) {
        holder.tvnamaval.setText(mhsList.get(position).getNama());
        holder.tvnimval.setText(mhsList.get(position).getNim());
        holder.tvnomerhpval.setText(mhsList.get(position).getNomerhp());

        holder.bind(mhsList, position, listener);
    }

    public interface OnItemClickListener{
        void OnItemClick(ArrayList<MhsModel> mhslist, int position);
    }

    public void removeItem(int position){
        this.mhsList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mhsList.size();
    }

    public class MhsVH extends RecyclerView.ViewHolder {

        private TextView tvnamaval, tvnimval, tvnomerhpval;
        private CardView cvitem;

        public MhsVH(@NonNull View itemView) {
            super(itemView);

            tvnamaval = itemView.findViewById(R.id.tvnamaval);
            tvnimval = itemView.findViewById(R.id.tvnimval);
            tvnomerhpval = itemView.findViewById(R.id.tvnomerhpval);

            cvitem = itemView.findViewById(R.id.cvitem);


        }

        public void bind(ArrayList<MhsModel> mhsList, int position, OnItemClickListener listener){
            cvitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(mhsList, position);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
