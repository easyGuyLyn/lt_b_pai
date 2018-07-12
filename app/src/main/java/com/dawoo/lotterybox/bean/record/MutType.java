package com.dawoo.lotterybox.bean.record;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alex on 18-4-9.
 * @author alex
 */

public class MutType implements Serializable{


    /**
     * data : {"deposit":{"company_income":["online_bank_deposit","wechatpay_fast","alipay_fast","qqwallet_fast","bdwallet_fast","jdwallet_fast","onecodepay_fast","other_fast"],"online_pay":["online_bank_pay","wechatpay_scan","alipay_scan","qqwallet_scan","bdwallet_scan","jdwallet_scan","unionpay_scan"],"system_deposit":["system_save","replenish_favorable","replenish_salary","replenish_dividen","replenish_rakeback","other_save"]},"withdraw":{"system_withdraw":["system_draw","favorable_deduct","other_deduct","system_refund"],"player_withdraw":["first_draw_withhold","draw_withhold","draw_refund"]},"game":{"game_expend":["game_bet","revocation_withhold","recalculate_withhold"],"game_income":["bet_rebate","game_payout","revoke_refund","revocation_refund","recalculate_payout"]},"favorable":{"favorable_activity":["regist_send","first_deposit","deposit_send","signed"],"system_favorable":["system_favorable"],"rakeback":["rakeback_settle"],"day_salary":["manual_favorable"],"dividen":["dividen_settle"]}}
     * dict : {"deposit":"存款","company_income":"公司入款","online_bank_deposit":"网银存款","wechatpay_fast":"微信支付","alipay_fast":"支付宝","qqwallet_fast":"QQ钱包","bdwallet_fast":"百度钱包","jdwallet_fast":"京东钱包","onecodepay_fast":"一码付","other_fast":"其他支付","online_bank_pay":"网银支付","wechatpay_scan":"微信扫码","alipay_scan":"支付宝扫码","qqwallet_scan":"QQ钱包扫码","bdwallet_scan":"百度钱包","jdwallet_scan":"京东钱包","unionpay_scan":"银联扫码","system_save":"系统存入","replenish_favorable":"补送优惠","replenish_salary":"补发工资","replenish_dividen":"补发分红","replenish_rakeback":"补发返水","other_save":"其他存入","regist_send":"注册送","first_deposit":"首存送","deposit_send":"存就送","signed":"签到","system_favorable":"系统优惠","rakeback_settle":"返水结算","manual_favorable":"工资结算","dividen_settle":"分红结算","system_draw":"系统取出","favorable_deduct":"优惠扣除","other_deduct":"其他扣除","system_refund":"系统退款","first_draw_withhold":"首取扣款","draw_withhold":"取款扣款","draw_refund":"取款退款","game_bet":"游戏投注","revocation_withhold":"撤销扣款","recalculate_withhold":"重结扣款","bet_rebate":"投注返点","game_payout":"游戏派彩","revoke_refund":"撤单退款","revocation_refund":"撤销退款","recalculate_payout":"重结派彩","online_pay":"线上支付","system_deposit":"系统存款","favorable_activity":"优惠活动","rakeback":"返水","day_salary":"日工资","dividen":"分红","system_withdraw":"系统取款","player_withdraw":"玩家取款","game_expend":"游戏支出","game_income":"游戏收入","withdraw":"取款","game":"游戏","favorable":"优惠"}
     */

    private DataBean data;
    private DictBean dict;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public static class DataBean implements Serializable{
        /**
         * deposit : {"company_income":["online_bank_deposit","wechatpay_fast","alipay_fast","qqwallet_fast","bdwallet_fast","jdwallet_fast","onecodepay_fast","other_fast"],"online_pay":["online_bank_pay","wechatpay_scan","alipay_scan","qqwallet_scan","bdwallet_scan","jdwallet_scan","unionpay_scan"],"system_deposit":["system_save","replenish_favorable","replenish_salary","replenish_dividen","replenish_rakeback","other_save"]}
         * withdraw : {"system_withdraw":["system_draw","favorable_deduct","other_deduct","system_refund"],"player_withdraw":["first_draw_withhold","draw_withhold","draw_refund"]}
         * game : {"game_expend":["game_bet","revocation_withhold","recalculate_withhold"],"game_income":["bet_rebate","game_payout","revoke_refund","revocation_refund","recalculate_payout"]}
         * favorable : {"favorable_activity":["regist_send","first_deposit","deposit_send","signed"],"system_favorable":["system_favorable"],"rakeback":["rakeback_settle"],"day_salary":["manual_favorable"],"dividen":["dividen_settle"]}
         */

        private DepositBean deposit;
        private WithdrawBean withdraw;
        private GameBean game;
        private FavorableBean favorable;

        public DepositBean getDeposit() {
            return deposit;
        }

        public void setDeposit(DepositBean deposit) {
            this.deposit = deposit;
        }

        public WithdrawBean getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(WithdrawBean withdraw) {
            this.withdraw = withdraw;
        }

        public GameBean getGame() {
            return game;
        }

        public void setGame(GameBean game) {
            this.game = game;
        }

        public FavorableBean getFavorable() {
            return favorable;
        }

        public void setFavorable(FavorableBean favorable) {
            this.favorable = favorable;
        }

        public static class DepositBean implements Serializable{
            private List<String> company_income;
            private List<String> online_pay;
            private List<String> system_deposit;

            public List<String> getCompany_income() {
                return company_income;
            }

            public void setCompany_income(List<String> company_income) {
                this.company_income = company_income;
            }

            public List<String> getOnline_pay() {
                return online_pay;
            }

            public void setOnline_pay(List<String> online_pay) {
                this.online_pay = online_pay;
            }

            public List<String> getSystem_deposit() {
                return system_deposit;
            }

            public void setSystem_deposit(List<String> system_deposit) {
                this.system_deposit = system_deposit;
            }
        }

        public static class WithdrawBean implements Serializable{
            private List<String> system_withdraw;
            private List<String> player_withdraw;

            public List<String> getSystem_withdraw() {
                return system_withdraw;
            }

            public void setSystem_withdraw(List<String> system_withdraw) {
                this.system_withdraw = system_withdraw;
            }

            public List<String> getPlayer_withdraw() {
                return player_withdraw;
            }

            public void setPlayer_withdraw(List<String> player_withdraw) {
                this.player_withdraw = player_withdraw;
            }
        }

        public static class GameBean implements Serializable{
            private List<String> game_expend;
            private List<String> game_income;

            public List<String> getGame_expend() {
                return game_expend;
            }

            public void setGame_expend(List<String> game_expend) {
                this.game_expend = game_expend;
            }

            public List<String> getGame_income() {
                return game_income;
            }

            public void setGame_income(List<String> game_income) {
                this.game_income = game_income;
            }
        }

        public static class FavorableBean implements Serializable{
            private List<String> favorable_activity;
            private List<String> system_favorable;
            private List<String> rakeback;
            private List<String> day_salary;
            private List<String> dividen;

            public List<String> getFavorable_activity() {
                return favorable_activity;
            }

            public void setFavorable_activity(List<String> favorable_activity) {
                this.favorable_activity = favorable_activity;
            }

            public List<String> getSystem_favorable() {
                return system_favorable;
            }

            public void setSystem_favorable(List<String> system_favorable) {
                this.system_favorable = system_favorable;
            }

            public List<String> getRakeback() {
                return rakeback;
            }

            public void setRakeback(List<String> rakeback) {
                this.rakeback = rakeback;
            }

            public List<String> getDay_salary() {
                return day_salary;
            }

            public void setDay_salary(List<String> day_salary) {
                this.day_salary = day_salary;
            }

            public List<String> getDividen() {
                return dividen;
            }

            public void setDividen(List<String> dividen) {
                this.dividen = dividen;
            }
        }
    }

    public static class DictBean implements Serializable{
        /**
         * deposit : 存款
         * company_income : 公司入款
         * online_bank_deposit : 网银存款
         * wechatpay_fast : 微信支付
         * alipay_fast : 支付宝
         * qqwallet_fast : QQ钱包
         * bdwallet_fast : 百度钱包
         * jdwallet_fast : 京东钱包
         * onecodepay_fast : 一码付
         * other_fast : 其他支付
         * online_bank_pay : 网银支付
         * wechatpay_scan : 微信扫码
         * alipay_scan : 支付宝扫码
         * qqwallet_scan : QQ钱包扫码
         * bdwallet_scan : 百度钱包
         * jdwallet_scan : 京东钱包
         * unionpay_scan : 银联扫码
         * system_save : 系统存入
         * replenish_favorable : 补送优惠
         * replenish_salary : 补发工资
         * replenish_dividen : 补发分红
         * replenish_rakeback : 补发返水
         * other_save : 其他存入
         * regist_send : 注册送
         * first_deposit : 首存送
         * deposit_send : 存就送
         * signed : 签到
         * system_favorable : 系统优惠
         * rakeback_settle : 返水结算
         * manual_favorable : 工资结算
         * dividen_settle : 分红结算
         * system_draw : 系统取出
         * favorable_deduct : 优惠扣除
         * other_deduct : 其他扣除
         * system_refund : 系统退款
         * first_draw_withhold : 首取扣款
         * draw_withhold : 取款扣款
         * draw_refund : 取款退款
         * game_bet : 游戏投注
         * revocation_withhold : 撤销扣款
         * recalculate_withhold : 重结扣款
         * bet_rebate : 投注返点
         * game_payout : 游戏派彩
         * revoke_refund : 撤单退款
         * revocation_refund : 撤销退款
         * recalculate_payout : 重结派彩
         * online_pay : 线上支付
         * system_deposit : 系统存款
         * favorable_activity : 优惠活动
         * rakeback : 返水
         * day_salary : 日工资
         * dividen : 分红
         * system_withdraw : 系统取款
         * player_withdraw : 玩家取款
         * game_expend : 游戏支出
         * game_income : 游戏收入
         * withdraw : 取款
         * game : 游戏
         * favorable : 优惠
         */

        private String deposit;
        private String company_income;
        private String online_bank_deposit;
        private String wechatpay_fast;
        private String alipay_fast;
        private String qqwallet_fast;
        private String bdwallet_fast;
        private String jdwallet_fast;
        private String onecodepay_fast;
        private String other_fast;
        private String online_bank_pay;
        private String wechatpay_scan;
        private String alipay_scan;
        private String qqwallet_scan;
        private String bdwallet_scan;
        private String jdwallet_scan;
        private String unionpay_scan;
        private String system_save;
        private String replenish_favorable;
        private String replenish_salary;
        private String replenish_dividen;
        private String replenish_rakeback;
        private String other_save;
        private String regist_send;
        private String first_deposit;
        private String deposit_send;
        private String signed;
        private String system_favorable;
        private String rakeback_settle;
        private String manual_favorable;
        private String dividen_settle;
        private String system_draw;
        private String favorable_deduct;
        private String other_deduct;
        private String system_refund;
        private String first_draw_withhold;
        private String draw_withhold;
        private String draw_refund;
        private String game_bet;
        private String revocation_withhold;
        private String recalculate_withhold;
        private String bet_rebate;
        private String game_payout;
        private String revoke_refund;
        private String revocation_refund;
        private String recalculate_payout;
        private String online_pay;
        private String system_deposit;
        private String favorable_activity;
        private String rakeback;
        private String day_salary;
        private String dividen;
        private String system_withdraw;
        private String player_withdraw;
        private String game_expend;
        private String game_income;
        private String withdraw;
        private String game;
        private String favorable;

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getCompany_income() {
            return company_income;
        }

        public void setCompany_income(String company_income) {
            this.company_income = company_income;
        }

        public String getOnline_bank_deposit() {
            return online_bank_deposit;
        }

        public void setOnline_bank_deposit(String online_bank_deposit) {
            this.online_bank_deposit = online_bank_deposit;
        }

        public String getWechatpay_fast() {
            return wechatpay_fast;
        }

        public void setWechatpay_fast(String wechatpay_fast) {
            this.wechatpay_fast = wechatpay_fast;
        }

        public String getAlipay_fast() {
            return alipay_fast;
        }

        public void setAlipay_fast(String alipay_fast) {
            this.alipay_fast = alipay_fast;
        }

        public String getQqwallet_fast() {
            return qqwallet_fast;
        }

        public void setQqwallet_fast(String qqwallet_fast) {
            this.qqwallet_fast = qqwallet_fast;
        }

        public String getBdwallet_fast() {
            return bdwallet_fast;
        }

        public void setBdwallet_fast(String bdwallet_fast) {
            this.bdwallet_fast = bdwallet_fast;
        }

        public String getJdwallet_fast() {
            return jdwallet_fast;
        }

        public void setJdwallet_fast(String jdwallet_fast) {
            this.jdwallet_fast = jdwallet_fast;
        }

        public String getOnecodepay_fast() {
            return onecodepay_fast;
        }

        public void setOnecodepay_fast(String onecodepay_fast) {
            this.onecodepay_fast = onecodepay_fast;
        }

        public String getOther_fast() {
            return other_fast;
        }

        public void setOther_fast(String other_fast) {
            this.other_fast = other_fast;
        }

        public String getOnline_bank_pay() {
            return online_bank_pay;
        }

        public void setOnline_bank_pay(String online_bank_pay) {
            this.online_bank_pay = online_bank_pay;
        }

        public String getWechatpay_scan() {
            return wechatpay_scan;
        }

        public void setWechatpay_scan(String wechatpay_scan) {
            this.wechatpay_scan = wechatpay_scan;
        }

        public String getAlipay_scan() {
            return alipay_scan;
        }

        public void setAlipay_scan(String alipay_scan) {
            this.alipay_scan = alipay_scan;
        }

        public String getQqwallet_scan() {
            return qqwallet_scan;
        }

        public void setQqwallet_scan(String qqwallet_scan) {
            this.qqwallet_scan = qqwallet_scan;
        }

        public String getBdwallet_scan() {
            return bdwallet_scan;
        }

        public void setBdwallet_scan(String bdwallet_scan) {
            this.bdwallet_scan = bdwallet_scan;
        }

        public String getJdwallet_scan() {
            return jdwallet_scan;
        }

        public void setJdwallet_scan(String jdwallet_scan) {
            this.jdwallet_scan = jdwallet_scan;
        }

        public String getUnionpay_scan() {
            return unionpay_scan;
        }

        public void setUnionpay_scan(String unionpay_scan) {
            this.unionpay_scan = unionpay_scan;
        }

        public String getSystem_save() {
            return system_save;
        }

        public void setSystem_save(String system_save) {
            this.system_save = system_save;
        }

        public String getReplenish_favorable() {
            return replenish_favorable;
        }

        public void setReplenish_favorable(String replenish_favorable) {
            this.replenish_favorable = replenish_favorable;
        }

        public String getReplenish_salary() {
            return replenish_salary;
        }

        public void setReplenish_salary(String replenish_salary) {
            this.replenish_salary = replenish_salary;
        }

        public String getReplenish_dividen() {
            return replenish_dividen;
        }

        public void setReplenish_dividen(String replenish_dividen) {
            this.replenish_dividen = replenish_dividen;
        }

        public String getReplenish_rakeback() {
            return replenish_rakeback;
        }

        public void setReplenish_rakeback(String replenish_rakeback) {
            this.replenish_rakeback = replenish_rakeback;
        }

        public String getOther_save() {
            return other_save;
        }

        public void setOther_save(String other_save) {
            this.other_save = other_save;
        }

        public String getRegist_send() {
            return regist_send;
        }

        public void setRegist_send(String regist_send) {
            this.regist_send = regist_send;
        }

        public String getFirst_deposit() {
            return first_deposit;
        }

        public void setFirst_deposit(String first_deposit) {
            this.first_deposit = first_deposit;
        }

        public String getDeposit_send() {
            return deposit_send;
        }

        public void setDeposit_send(String deposit_send) {
            this.deposit_send = deposit_send;
        }

        public String getSigned() {
            return signed;
        }

        public void setSigned(String signed) {
            this.signed = signed;
        }

        public String getSystem_favorable() {
            return system_favorable;
        }

        public void setSystem_favorable(String system_favorable) {
            this.system_favorable = system_favorable;
        }

        public String getRakeback_settle() {
            return rakeback_settle;
        }

        public void setRakeback_settle(String rakeback_settle) {
            this.rakeback_settle = rakeback_settle;
        }

        public String getManual_favorable() {
            return manual_favorable;
        }

        public void setManual_favorable(String manual_favorable) {
            this.manual_favorable = manual_favorable;
        }

        public String getDividen_settle() {
            return dividen_settle;
        }

        public void setDividen_settle(String dividen_settle) {
            this.dividen_settle = dividen_settle;
        }

        public String getSystem_draw() {
            return system_draw;
        }

        public void setSystem_draw(String system_draw) {
            this.system_draw = system_draw;
        }

        public String getFavorable_deduct() {
            return favorable_deduct;
        }

        public void setFavorable_deduct(String favorable_deduct) {
            this.favorable_deduct = favorable_deduct;
        }

        public String getOther_deduct() {
            return other_deduct;
        }

        public void setOther_deduct(String other_deduct) {
            this.other_deduct = other_deduct;
        }

        public String getSystem_refund() {
            return system_refund;
        }

        public void setSystem_refund(String system_refund) {
            this.system_refund = system_refund;
        }

        public String getFirst_draw_withhold() {
            return first_draw_withhold;
        }

        public void setFirst_draw_withhold(String first_draw_withhold) {
            this.first_draw_withhold = first_draw_withhold;
        }

        public String getDraw_withhold() {
            return draw_withhold;
        }

        public void setDraw_withhold(String draw_withhold) {
            this.draw_withhold = draw_withhold;
        }

        public String getDraw_refund() {
            return draw_refund;
        }

        public void setDraw_refund(String draw_refund) {
            this.draw_refund = draw_refund;
        }

        public String getGame_bet() {
            return game_bet;
        }

        public void setGame_bet(String game_bet) {
            this.game_bet = game_bet;
        }

        public String getRevocation_withhold() {
            return revocation_withhold;
        }

        public void setRevocation_withhold(String revocation_withhold) {
            this.revocation_withhold = revocation_withhold;
        }

        public String getRecalculate_withhold() {
            return recalculate_withhold;
        }

        public void setRecalculate_withhold(String recalculate_withhold) {
            this.recalculate_withhold = recalculate_withhold;
        }

        public String getBet_rebate() {
            return bet_rebate;
        }

        public void setBet_rebate(String bet_rebate) {
            this.bet_rebate = bet_rebate;
        }

        public String getGame_payout() {
            return game_payout;
        }

        public void setGame_payout(String game_payout) {
            this.game_payout = game_payout;
        }

        public String getRevoke_refund() {
            return revoke_refund;
        }

        public void setRevoke_refund(String revoke_refund) {
            this.revoke_refund = revoke_refund;
        }

        public String getRevocation_refund() {
            return revocation_refund;
        }

        public void setRevocation_refund(String revocation_refund) {
            this.revocation_refund = revocation_refund;
        }

        public String getRecalculate_payout() {
            return recalculate_payout;
        }

        public void setRecalculate_payout(String recalculate_payout) {
            this.recalculate_payout = recalculate_payout;
        }

        public String getOnline_pay() {
            return online_pay;
        }

        public void setOnline_pay(String online_pay) {
            this.online_pay = online_pay;
        }

        public String getSystem_deposit() {
            return system_deposit;
        }

        public void setSystem_deposit(String system_deposit) {
            this.system_deposit = system_deposit;
        }

        public String getFavorable_activity() {
            return favorable_activity;
        }

        public void setFavorable_activity(String favorable_activity) {
            this.favorable_activity = favorable_activity;
        }

        public String getRakeback() {
            return rakeback;
        }

        public void setRakeback(String rakeback) {
            this.rakeback = rakeback;
        }

        public String getDay_salary() {
            return day_salary;
        }

        public void setDay_salary(String day_salary) {
            this.day_salary = day_salary;
        }

        public String getDividen() {
            return dividen;
        }

        public void setDividen(String dividen) {
            this.dividen = dividen;
        }

        public String getSystem_withdraw() {
            return system_withdraw;
        }

        public void setSystem_withdraw(String system_withdraw) {
            this.system_withdraw = system_withdraw;
        }

        public String getPlayer_withdraw() {
            return player_withdraw;
        }

        public void setPlayer_withdraw(String player_withdraw) {
            this.player_withdraw = player_withdraw;
        }

        public String getGame_expend() {
            return game_expend;
        }

        public void setGame_expend(String game_expend) {
            this.game_expend = game_expend;
        }

        public String getGame_income() {
            return game_income;
        }

        public void setGame_income(String game_income) {
            this.game_income = game_income;
        }

        public String getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(String withdraw) {
            this.withdraw = withdraw;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getFavorable() {
            return favorable;
        }

        public void setFavorable(String favorable) {
            this.favorable = favorable;
        }
    }
}
