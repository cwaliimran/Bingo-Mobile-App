package com.bingoplayer.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBingoAnswer {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("finished")
    @Expose
    private String finished;
    @SerializedName("winner")
    @Expose
    private String winner;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}