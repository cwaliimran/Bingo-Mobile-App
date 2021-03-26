package com.bingoplayer.app.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBoard {

    @SerializedName("board")
    @Expose
    private Board board;
    @SerializedName("result")
    @Expose
    private String result;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public class Board {

        @SerializedName("width")
        @Expose
        private Long width;
        @SerializedName("height")
        @Expose
        private Long height;
        @SerializedName("cells")
        @Expose
        private List<Cell> cells = null;

        public Long getWidth() {
            return width;
        }

        public void setWidth(Long width) {
            this.width = width;
        }

        public Long getHeight() {
            return height;
        }

        public void setHeight(Long height) {
            this.height = height;
        }

        public List<Cell> getCells() {
            return cells;
        }

        public void setCells(List<Cell> cells) {
            this.cells = cells;
        }

    }

    public class Cell {

        @SerializedName("x")
        @Expose
        private Long x;
        @SerializedName("y")
        @Expose
        private Long y;
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("answered")
        @Expose
        private Boolean answered;
        @SerializedName("statistics")
        @Expose
        private Statistics statistics;

        public Long getX() {
            return x;
        }

        public void setX(Long x) {
            this.x = x;
        }

        public Long getY() {
            return y;
        }

        public void setY(Long y) {
            this.y = y;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public Boolean getAnswered() {
            return answered;
        }

        public void setAnswered(Boolean answered) {
            this.answered = answered;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }

    }

    public class Statistics {


    }
}