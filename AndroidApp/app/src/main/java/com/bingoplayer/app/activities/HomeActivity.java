package com.bingoplayer.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bingoplayer.app.R;
import com.bingoplayer.app.adapters.ExistindSessionsAdapter;
import com.bingoplayer.app.databinding.ActivityHomeBinding;
import com.bingoplayer.app.utils.Constants;
import com.bingoplayer.app.utils.GlobalClass;
import com.bingoplayer.app.utils.Shared;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    ActivityHomeBinding binding;
    String sessionId, gameId, playerId;
    ArrayList<String> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        //Log.d(Constants.TAG, "sessions: " + Shared.getListString(Constants.EXISTING_SESSIONS));
       // sessions = new ArrayList<>();
        initViews();
        setSessionAdapter();

    }

    private void setSessionAdapter() {
        ExistindSessionsAdapter adapter = new ExistindSessionsAdapter(context, Shared.getListString(Constants.EXISTING_SESSIONS));
        binding.recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        binding.actionBar.ivBack.setVisibility(View.GONE);
        binding.actionBar.tvActivityTitle.setText(getResources().getString(R.string.app_name));
    }

    public void JoinSession(View view) {
        if (checkValidation()) {
            GlobalClass.hideKeyboard(HomeActivity.this);
//            binding.etPlayerId.setText("");
//            binding.etGameId.setText("");
//            binding.etSessionId.setText("");
//            sessions = Shared.getListString(Constants.EXISTING_SESSIONS);
//            sessions.add(sessionId);
//            Shared.putListString(Constants.EXISTING_SESSIONS, sessions);
//            Log.d(Constants.TAG, "sessions: " + Shared.getListString(Constants.EXISTING_SESSIONS));
//            setSessionAdapter();
//            gotoActivity(BoardActivity.class);

            Intent intent = new Intent(this, BoardActivity.class);
            intent.putExtra("player_id", playerId);
            intent.putExtra("game_id", gameId);
            intent.putExtra("session_id", sessionId);
            startActivity(intent);

        }
    }

    private boolean checkValidation() {
        playerId = binding.etPlayerId.getText().toString().trim();
        gameId = binding.etGameId.getText().toString().trim();
        sessionId = binding.etSessionId.getText().toString().trim();
        if (playerId.equals("")) {
            binding.etPlayerId.setError("Enter player id");
            return false;
        }
        if (gameId.equals("")) {
            binding.etGameId.setError("Enter game id");
            return false;
        }
        if (sessionId.equals("")) {
            binding.etSessionId.setError("Enter session id");
            return false;
        }


        return true;
    }
}