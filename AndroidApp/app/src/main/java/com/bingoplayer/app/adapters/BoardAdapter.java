package com.bingoplayer.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bingoplayer.app.R;
import com.bingoplayer.app.databinding.RowBoardBinding;
import com.bingoplayer.app.models.ModelBoard;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    private Context context;
    LayoutInflater inflater;
    ArrayList<ModelBoard.Cell> model;

    public BoardAdapter(Context context, ArrayList<ModelBoard.Cell> cells) {
        this.model = cells;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowBoardBinding.inflate(inflater, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //show data from session model
        ModelBoard.Cell modelBoard = model.get(position);
        holder.binding.tvTitle.setText(String.format("%s", modelBoard.getAnswer()));
        holder.binding.tvTitle.setOnClickListener(v -> {
            holder.binding.tvTitle.setBackground(context.getDrawable(R.drawable.rounded_edittext_colored));
            holder.binding.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
            //call request to check if answer was correct or not
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RowBoardBinding binding;

        public MyViewHolder(@NonNull RowBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}