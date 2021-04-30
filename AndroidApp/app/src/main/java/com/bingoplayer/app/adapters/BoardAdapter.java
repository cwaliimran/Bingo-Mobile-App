package com.bingoplayer.app.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bingoplayer.app.R;
import com.bingoplayer.app.activities.ResultActivity;
import com.bingoplayer.app.databinding.RowBoardBinding;
import com.bingoplayer.app.models.ModelBingoAnswer;
import com.bingoplayer.app.models.ModelBoard;
import com.bingoplayer.app.utils.APIClient;
import com.bingoplayer.app.utils.ApiInterface;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    private Context context;
    LayoutInflater inflater;
    ArrayList<ModelBoard.Child> model;
    String sessionId, gameId, playerId;
    String op;
    String ver;
    ModelBingoAnswer.Children modelBingoAnswer;
    String TAG = "response";
    List<Integer> isClicked = new ArrayList<>();

    public BoardAdapter(Context context, ArrayList<ModelBoard.Child> cells, String gameId, String sessionId, String playerId, String op, String ver) {
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
        ModelBoard.Child modelBoard = model.get(position);
        holder.binding.tvTitle.setText(String.format("%s", modelBoard.getChildren().getAnswer().getValue()));
        if (modelBoard.getChildren().getAnswered().getValue()) {
            isClicked.add(position);
            holder.binding.tvTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_edittext_colored));
            holder.binding.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.binding.tvTitle.setOnClickListener(v -> {
            if (isClicked.contains(position)) {
                Log.d(TAG, "onBindViewHolder: already checked" + position);
            } else {
                Long xVal = modelBoard.getChildren().getX().getValue();
                Long yVal = modelBoard.getChildren().getY().getValue();
                String ansVal = modelBoard.getChildren().getAnswer().getValue();

                final ProgressDialog progressDialog = ProgressDialog.show(context, context.getString(R.string.please_wait), context.getString(R.string.loading), true);
                ApiInterface apiInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("x", xVal);
                jsonParams.put("y", yVal);
                jsonParams.put("answer", ansVal);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

                Call<ModelBingoAnswer> call = apiInterface.submitAnswer(gameId, sessionId, playerId, ver, body);
                Log.d("response url", "URL==" + call.request().url());
                call.enqueue(new Callback<ModelBingoAnswer>() {
                    @Override
                    public void onResponse(@NotNull Call<ModelBingoAnswer> call, @NotNull Response<ModelBingoAnswer> response) {
                        Log.d("response main: ", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            Log.d("response Success: ", new Gson().toJson(response.body()));
                            assert response.body() != null;
                            modelBingoAnswer = response.body().getChildren();
                            Log.d(TAG, "\nonResponse: getResult" + modelBingoAnswer.getResult().getValue());
                            Log.d(TAG, "\nonResponse: getFinished" + modelBingoAnswer.getFinished().getValue());
                            Log.d(TAG, "\nonResponse: getWinner" + modelBingoAnswer.getWinner().getValue());
                            if (modelBingoAnswer != null) {
                                if (modelBingoAnswer.getResult().getValue().equals("ok")) {
                                    isClicked.add(position);
                                    holder.binding.tvTitle.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_edittext_colored));
                                    holder.binding.tvTitle.setTextColor(context.getResources().getColor(R.color.white));
                                    Toast.makeText(context, "Good answer", Toast.LENGTH_SHORT).show();
                                    if (modelBingoAnswer.getWinner().getValue()) {
                                        //winner
                                        Intent intent = new Intent(context, ResultActivity.class);
                                        intent.putExtra("result", "winner");
                                        context.startActivity(intent);
                                    } else if (modelBingoAnswer.getFinished().getValue()) {
                                        // game finished
                                        Intent intent = new Intent(context, ResultActivity.class);
                                        intent.putExtra("result", "finished");
                                        context.startActivity(intent);
                                    } else {
                                        //game continue
                                        Log.d(TAG, "onResponse: game continue");
                                    }
                                } else {
                                    Toast.makeText(context, "" + modelBingoAnswer.getResult().getValue(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            progressDialog.dismiss();
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
                    public void onFailure(@NotNull Call<ModelBingoAnswer> call, @NotNull Throwable t) {
                        // Log error here since request failed
                        Log.e("Response", "onFailure" + t.toString());
                        Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }
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

//    private void submitAnswer(Long x, Long y, String answer) {
//        final ProgressDialog progressDialog = ProgressDialog.show(context, context.getString(R.string.please_wait), context.getString(R.string.loading), true);
//        ApiInterface apiInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
//        Map<String, Object> jsonParams = new ArrayMap<>();
//        jsonParams.put("x", x);
//        jsonParams.put("y", y);
//        jsonParams.put("answer", answer);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
//
//        Call<ModelBingoAnswer> call = apiInterface.submitAnswer(gameId, sessionId, playerId, ver, body);
//        Log.d("response url", "URL==" + call.request().url());
//        call.enqueue(new Callback<ModelBingoAnswer>() {
//            @Override
//            public void onResponse(@NotNull Call<ModelBingoAnswer> call, @NotNull Response<ModelBingoAnswer> response) {
//                Log.d("response main: ", new Gson().toJson(response.body()));
//                if (response.isSuccessful()) {
//                    Log.d("response Success: ", new Gson().toJson(response.body()));
//                    assert response.body() != null;
//                    modelBingoAnswer = response.body().getChildren();
//                    Log.d(TAG, "\nonResponse: getResult" + modelBingoAnswer.getResult().getValue());
//                    Log.d(TAG, "\nonResponse: getFinished" + modelBingoAnswer.getFinished().getValue());
//                    Log.d(TAG, "\nonResponse: getWinner" + modelBingoAnswer.getWinner().getValue());
//                    if (modelBingoAnswer != null) {
//                        if (modelBingoAnswer.getResult().getValue().equals("ok")) {
//                            if (modelBingoAnswer.getWinner().getValue()) {
//                                //winner
//                                Intent intent = new Intent(context, ResultActivity.class);
//                                intent.putExtra("result", "winner");
//                                context.startActivity(intent);
//                            } else if (modelBingoAnswer.getFinished().getValue()) {
//                                // game finished
//                                Intent intent = new Intent(context, ResultActivity.class);
//                                intent.putExtra("result", "finished");
//                                context.startActivity(intent);
//                            }
//                        } else {
//                            Toast.makeText(context, "" + modelBingoAnswer.getResult().getValue(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    progressDialog.dismiss();
//                } else {
//                    progressDialog.dismiss();
//                    try {
//                        assert response.errorBody() != null;
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        String errorMsg = jObjError.getString("message");
//                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
//                    } catch (Exception e) {
//                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(@NotNull Call<ModelBingoAnswer> call, @NotNull Throwable t) {
//                // Log error here since request failed
//                Log.e("Response", "onFailure" + t.toString());
//                Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();
//            }
//        });
//    }

}