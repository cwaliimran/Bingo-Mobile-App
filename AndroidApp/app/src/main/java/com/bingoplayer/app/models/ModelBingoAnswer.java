package com.bingoplayer.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBingoAnswer {

    @SerializedName("_children")
    @Expose
    private Children children;
    @SerializedName("_nodeFactory")
    @Expose
    private NodeFactory nodeFactory;

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public NodeFactory getNodeFactory() {
        return nodeFactory;
    }

    public void setNodeFactory(NodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public class Children {

        @SerializedName("result")
        @Expose
        private Result result;
        @SerializedName("finished")
        @Expose
        private Finished finished;
        @SerializedName("winner")
        @Expose
        private Winner winner;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Finished getFinished() {
            return finished;
        }

        public void setFinished(Finished finished) {
            this.finished = finished;
        }

        public Winner getWinner() {
            return winner;
        }

        public void setWinner(Winner winner) {
            this.winner = winner;
        }

    }

    public class Finished {

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

    public class Winner {

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
}