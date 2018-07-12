package com.dawoo.lotterybox.view.activity.chart;

/**
 * 图表的实体
 * Created by benson on 18-2-23.
 */

public class ChartBean {
    private String expect;// 期号
    private String code; // 彩票代码
    private String codeNum; // 彩票号码

    public ChartBean() {
    }

    public ChartBean(String expect, String code, String codeNum) {
        this.expect = expect;
        this.code = code;
        this.codeNum = codeNum;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}
