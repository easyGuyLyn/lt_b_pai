package com.dawoo.lotterybox.bean;

/**
 * Created by rain on 18-3-14.
 */

public class KeyMap {
    private String key;
    private PlayExplainBean value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PlayExplainBean getValue() {
        return value;
    }

    public void setValue(PlayExplainBean value) {
        this.value = value;
    }

    public class PlayExplainBean {
        private String explain;//玩法
        private String scheme;//投注方案
        private String simple;//中奖示例

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getSimple() {
            return simple;
        }

        public void setSimple(String simple) {
            this.simple = simple;
        }
    }
}