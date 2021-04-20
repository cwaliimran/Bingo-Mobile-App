package com.bingoplayer.app.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bingoplayer.app.R;
import com.bingoplayer.app.databinding.RowBoardBinding;
import com.bingoplayer.app.models.ModelBoard;
import com.bingoplayer.app.utils.APIClient;
import com.bingoplayer.app.utils.ApiInterface;
import com.bingoplayer.app.utils.Constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    private Context context;
    LayoutInflater inflater;
    ArrayList<ModelBoard.Cell> model;
    String sessionId, gameId, playerId;
    String op;
    String ver;

    public BoardAdapter(Context context, ArrayList<ModelBoard.Cell> cells, String gameId, String sessionId, String playerId, String op, String ver) {
        this.model = cells;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.gameId = gameId;
        this.sessionId = sessionId;
        this.playerId = playerId;
        this.op = op;
        this.ver = ver;

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
        if (modelBoard.getAnswered()) {
            holder.binding.tvTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_edittext_colored));
            holder.binding.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.binding.tvTitle.setOnClickListener(v -> {
            if (!modelBoard.getAnswered()) {
                holder.binding.tvTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_edittext_colored));
                holder.binding.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
             //   submitAnswer(modelBoard.getX(), modelBoard.getY(), modelBoard.getAnswer());
            }
            //call request to check if answer was correct or not
            //  submitAnswer(modelBoard.getX(), modelBoard.getY(), modelBoard.getAnswer());
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

    private void submitAnswer(Long x, Long y, String answer) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, context.getString(R.string.please_wait), context.getString(R.string.loading), true);
        ApiInterface apiInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("x", x);
        jsonParams.put("y", y);
        jsonParams.put("answer", answer);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<JSONObject> call = apiInterface.submitAnswer(gameId, sessionId, playerId, op, body);
//        Call<JSONObject> call = apiInterface.submitAnswer(gameId, sessionId, playerId, ver, body);
        Log.d("response url", "URL==" + call.request().url());
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(@NotNull Call<JSONObject> call, @NotNull Response<JSONObject> response) {
                Log.d("response main: ", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    Log.d("response Success: ", new Gson().toJson(response.body()));
                    assert response.body() != null;
                    JSONObject jsonObject = response.body();
                    try {
                        String result = jsonObject.getString("result");
                        Log.d(Constants.TAG, "submitAnswer result: " + result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    if (response.body().getResult().equals("ok")) {
//
//                        Log.d(Constants.TAG, response.body().getResult());
//                        cells.addAll(response.body().getBoard().getCells());
//                        boardWidth = response.body().getBoard().getWidth();
//                        String width = boardWidth.toString();
//                        int finalCells = Integer.parseInt(width);
//                        Log.d(Constants.TAG, "boardWidth:" + finalCells);
//                        //create board
//                        setBoardAdapter(finalCells);
                    progressDialog.dismiss();
//                    } else {
//                        Toast.makeText(context, "API result is not equal to - OK", Toast.LENGTH_LONG).show();
//                        progressDialog.dismiss();
//                    }

                } else {
                    progressDialog.dismiss();
                    try {
                        assert response.errorBody() != null;
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String errorMsg = jObjError.getString("message");
                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(@NotNull Call<JSONObject> call, @NotNull Throwable t) {
                // Log error here since request failed
                Log.e("Response", "onFailure" + t.toString());
                Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}