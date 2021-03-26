package com.bingoplayer.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bingoplayer.app.databinding.RowExistindSessionsBinding;

import java.util.ArrayList;

public class ExistindSessionsAdapter extends RecyclerView.Adapter<ExistindSessionsAdapter.MyViewHolder> {
    private Context context;
    LayoutInflater inflater;
    ArrayList<String> model;

    public ExistindSessionsAdapter(Context context, ArrayList<String> listString) {
        this.model = listString;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowExistindSessionsBinding.inflate(inflater, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //show data from session model
        holder.binding.tvSessionId.setText(model.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RowExistindSessionsBinding binding;

        public MyViewHolder(@NonNull RowExistindSessionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}