package com.bingoplayer.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelBoard {

    @SerializedName("_children")
    @Expose
    private Children children;
    @SerializedName("_nodeFactory")
    @Expose
    private NodeFactory__4 nodeFactory;

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public NodeFactory__4 getNodeFactory() {
        return nodeFactory;
    }

    public void setNodeFactory(NodeFactory__4 nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public class Answer {

        @SerializedName("_value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public class Answered {

        @SerializedName("_value")
        @Expose
        private Boolean value;

        public Boolean getValue() {
            return value;
        }

        public void setValue(Boolean value) {
            this.value = value;
        }

    }

    public class Board {

        @SerializedName("_children")
        @Expose
        private Children__1 children;
        @SerializedName("_nodeFactory")
        @Expose
        private NodeFactory__3 nodeFactory;

        public Children__1 getChildren() {
            return children;
        }

        public void setChildren(Children__1 children) {
            this.children = children;
        }

        public NodeFactory__3 getNodeFactory() {
            return nodeFactory;
        }

        public void setNodeFactory(NodeFactory__3 nodeFactory) {
            this.nodeFactory = nodeFactory;
        }

    }

    public class Cells {

        @SerializedName("_children")
        @Expose
        private List<Child> children = null;
        @SerializedName("_nodeFactory")
        @Expose
        private NodeFactory__2 nodeFactory;

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public NodeFactory__2 getNodeFactory() {
            return nodeFactory;
        }

        public void setNodeFactory(NodeFactory__2 nodeFactory) {
            this.nodeFactory = nodeFactory;
        }

    }

    public class Child {

        @SerializedName("_children")
        @Expose
        private Children__2 children;
        @SerializedName("_nodeFactory")
        @Expose
        private NodeFactory__1 nodeFactory;

        public Children__2 getChildren() {
            return children;
        }

        public void setChildren(Children__2 children) {
            this.children = children;
        }

        public NodeFactory__1 getNodeFactory() {
            return nodeFactory;
        }

        public void setNodeFactory(NodeFactory__1 nodeFactory) {
            this.nodeFactory = nodeFactory;
        }

    }

    public class Children {

        @SerializedName("board")
        @Expose
        private Board board;
        @SerializedName("result")
        @Expose
        private Result result;

        public Board getBoard() {
            return board;
        }

        public void setBoard(Board board) {
            this.board = board;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

    }

    public class Children__1 {

        @SerializedName("width")
        @Expose
        private Width width;
        @SerializedName("height")
        @Expose
        private Height height;
        @SerializedName("cells")
        @Expose
        private Cells cells;

        public Width getWidth() {
            return width;
        }

        public void setWidth(Width width) {
            this.width = width;
        }

        public Height getHeight() {
            return height;
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Cells getCells() {
            return cells;
        }

        public void setCells(Cells cells) {
            this.cells = cells;
        }

    }

    public class Children__2 {

        @SerializedName("x")
        @Expose
        private X x;
        @SerializedName("y")
        @Expose
        private Y y;
        @SerializedName("answer")
        @Expose
        private Answer answer;
        @SerializedName("answered")
        @Expose
        private Answered answered;
        @SerializedName("statistics")
        @Expose
        private Statistics statistics;

        public X getX() {
            return x;
        }

        public void setX(X x) {
            this.x = x;
        }

        public Y getY() {
            return y;
        }

        public void setY(Y y) {
            this.y = y;
        }

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Answered getAnswered() {
            return answered;
        }

        public void setAnswered(Answered answered) {
            this.answered = answered;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }

    }

    public class Children__3 {


    }

    public class Height {

        @SerializedName("_value")
        @Expose
        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }

    public class NodeFactory {

        @SerializedName("_cfgBigDecimalExact")
        @Expose
        private Boolean cfgBigDecimalExact;

        public Boolean getCfgBigDecimalExact() {
            return cfgBigDecimalExact;
        }

        public void setCfgBigDecimalExact(Boolean cfgBigDecimalExact) {
            this.cfgBigDecimalExact = cfgBigDecimalExact;
        }

    }

    public class NodeFactory__1 {

        @SerializedName("_cfgBigDecimalExact")
        @Expose
        private Boolean cfgBigDecimalExact;

        public Boolean getCfgBigDecimalExact() {
            return cfgBigDecimalExact;
        }

        public void setCfgBigDecimalExact(Boolean cfgBigDecimalExact) {
            this.cfgBigDecimalExact = cfgBigDecimalExact;
        }

    }

    public class NodeFactory__2 {

        @SerializedName("_cfgBigDecimalExact")
        @Expose
        private Boolean cfgBigDecimalExact;

        public Boolean getCfgBigDecimalExact() {
            return cfgBigDecimalExact;
        }

        public void setCfgBigDecimalExact(Boolean cfgBigDecimalExact) {
            this.cfgBigDecimalExact = cfgBigDecimalExact;
        }

    }

    public class NodeFactory__3 {

        @SerializedName("_cfgBigDecimalExact")
        @Expose
        private Boolean cfgBigDecimalExact;

        public Boolean getCfgBigDecimalExact() {
            return cfgBigDecimalExact;
        }

        public void setCfgBigDecimalExact(Boolean cfgBigDecimalExact) {
            this.cfgBigDecimalExact = cfgBigDecimalExact;
        }

    }

    public class NodeFactory__4 {

        @SerializedName("_cfgBigDecimalExact")
        @Expose
        private Boolean cfgBigDecimalExact;

        public Boolean getCfgBigDecimalExact() {
            return cfgBigDecimalExact;
        }

        public void setCfgBigDecimalExact(Boolean cfgBigDecimalExact) {
            this.cfgBigDecimalExact = cfgBigDecimalExact;
        }

    }

    public class Result {

        @SerializedName("_value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public class Statistics {

        @SerializedName("_children")
        @Expose
        private Children__3 children;
        @SerializedName("_nodeFactory")
        @Expose
        private NodeFactory nodeFactory;

        public Children__3 getChildren() {
            return children;
        }

        public void setChildren(Children__3 children) {
            this.children = children;
        }

        public NodeFactory getNodeFactory() {
            return nodeFactory;
        }

        public void setNodeFactory(NodeFactory nodeFactory) {
            this.nodeFactory = nodeFactory;
        }

    }

    public class Width {

        @SerializedName("_value")
        @Expose
        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }

    public class X {

        @SerializedName("_value")
        @Expose
        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }

    public class Y {

        @SerializedName("_value")
        @Expose
        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }
}