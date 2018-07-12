package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-25.
 */

public class TeamMemberBean {
    /**
     * id : 10238
     * playerName : alexa3
     * playerType : agent
     * balance : 0.0
     * createTime : 1524626880718
     */

    private int id;
    private String playerName;
    private String playerType;
    private double balance;
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
