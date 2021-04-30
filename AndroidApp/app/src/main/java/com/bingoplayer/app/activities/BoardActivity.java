package com.bingoplayer.app.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bingoplayer.app.R;
import com.bingoplayer.app.adapters.BoardAdapter;
import com.bingoplayer.app.databinding.ActivityBoardBinding;
import com.bingoplayer.app.models.ModelBoard;
import com.bingoplayer.app.utils.APIClient;
import com.bingoplayer.app.utils.ApiInterface;
import com.bingoplayer.app.utils.Constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardActivity extends BaseActivity {
    ModelBoard.Children model;
    ArrayList<ModelBoard.Child> cells = new ArrayList<>();
    Long boardWidth;
    ActivityBoardBinding binding;
    String sessionId, gameId, playerId;
    String op = "JOIN";
    String ver = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        initViews();
        if (getIntent().getExtras() != null) {
            playerId = getIntent().getStringExtra("player_id");
            gameId = getIntent().getStringExtra("game_id");
            sessionId = getIntent().getStringExtra("session_id");
            Log.d(Constants.TAG, playerId + " " + gameId + " " + sessionId);
        }

        //get boards
        getBoards(BoardActivity.this);
    }

    private void initViews() {
        binding.actionBar.tvActivityTitle.setText("Boards");
        binding.actionBar.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void getBoards(final Activity context) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, getString(R.string.please_wait), getString(R.string.loading), true);
        ApiInterface apiInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ModelBoard> call = apiInterface.getBoards(gameId, sessionId, playerId, op, ver);
        Log.d("response url", "URL==" + call.request().url());
        call.enqueue(new Callback<ModelBoard>() {
            @Override
            public void onResponse(@NotNull Call<ModelBoard> call, @NotNull Response<ModelBoard> response) {
                if (response.isSuccessful()) {
                    Log.d("response Success: ", new Gson().toJson(response.body()));
                    assert response.body() != null;

                    if (response.body().getChildren().getResult().getValue().equals("ok")) {
                        model = response.body().getChildren();
                        cells.addAll(response.body().getChildren().getBoard().getChildren().getCells().getChildren());
                        boardWidth = response.body().getChildren().getBoard().getChildren().getWidth().getValue();
                        String width = boardWidth.toString();
                        int finalCells = Integer.parseInt(width);
                        setBoardAdapter(finalCells, gameId, sessionId, playerId, op, ver);
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(context, "" + response.body().getChildren().getResult(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        finish();
                    }

                } else {
                    progressDialog.dismiss();
                    try {
                        assert response.errorBody() != null;
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String errorMsg = jObjError.getString("message");
                        Log.d(TAG, "onResponse: try" +errorMsg);
                        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            }


            @Override
            public void onFailure(@NotNull Call<ModelBoard> call, @NotNull Throwable t) {
                // Log error here since request failed
                Log.d("Response", "onFailure" + t.toString());
                Toast.makeText(context, "" + t.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                finish();
            }
        });
    }

    private void setBoardAdapter(int finalCells, String gameId, String sessionId, String playerId, String op, String ver) {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, finalCells, GridLayoutManager.VERTICAL, false));
        BoardAdapter adapter = new BoardAdapter(context, cells, gameId, sessionId, playerId, op, ver);
        binding.recyclerView.setAdapter(adapter);
    }


}