package com.bingoplayer.app.models;

import java.util.ArrayList;
import java.util.List;

public class ModelBoardSession {
    List sessionId;

    public ModelBoardSession(List sessionId) {
        this.sessionId = sessionId;
    }

    public List getSessionId() {
        return sessionId;
    }

    public void setSessionId(List sessionId) {
        this.sessionId = sessionId;
    }
}
