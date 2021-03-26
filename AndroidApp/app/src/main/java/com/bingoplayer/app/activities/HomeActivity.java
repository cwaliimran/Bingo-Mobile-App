package com.bingoplayer.app.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bingoplayer.app.adapters.ExistindSessionsAdapter;
import com.bingoplayer.app.databinding.ActivityHomeBinding;
import com.bingoplayer.app.utils.Constants;
import com.bingoplayer.app.utils.GlobalClass;
import com.bingoplayer.app.utils.Shared;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    ActivityHomeBinding binding;
    String sessionId;
    ArrayList<String> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        Log.d(Constants.TAG, "sessions: " + Shared.getListString(Constants.EXISTING_SESSIONS));
        sessions = new ArrayList<>();
        initViews();
        setSessionAdapter();

    }

    private void setSessionAdapter() {
        ExistindSessionsAdapter adapter = new ExistindSessionsAdapter(context, Shared.getListString(Constants.EXISTING_SESSIONS));
        binding.recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        binding.actionBar.ivBack.setVisibility(View.GONE);
        binding.actionBar.tvActivityTitle.setText("Bingo Player");
    }

    public void JoinSession(View view) {
        if (checkValidation()) {
            GlobalClass.hideKeyboard(HomeActivity.this);
            binding.etSessionId.setText("");
            sessions = Shared.getListString(Constants.EXISTING_SESSIONS);
            sessions.add(sessionId);
            Shared.putListString(Constants.EXISTING_SESSIONS, sessions);
            Log.d(Constants.TAG, "sessions: " + Shared.getListString(Constants.EXISTING_SESSIONS));
            setSessionAdapter();
            gotoActivity(BoardActivity.class);
        }
    }

    private boolean checkValidation() {
        sessionId = binding.etSessionId.getText().toString().trim();
        if (sessionId.equals("")) {
            binding.etSessionId.setError("Enter session id");
            binding.etSessionId.setFocusable(true);
            return false;
        }
        return true;
    }
}