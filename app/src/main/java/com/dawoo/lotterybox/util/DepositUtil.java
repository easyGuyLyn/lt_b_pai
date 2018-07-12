package com.dawoo.lotterybox.util;

import android.content.Context;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.Deposit.BankType;
import com.dawoo.lotterybox.bean.Deposit.PayAccountAccountType;
import com.dawoo.lotterybox.bean.Deposit.PayAccountCompanyThirdType;
import com.dawoo.lotterybox.bean.record.recordnum.BillItemEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by rain on 18-5-1.
 */

public class DepositUtil {
    final static String NOTEFILEPATH = "type_note";
    final static String DEFAULT = "default";
    static Map<String, String[]> notes = new HashMap<>();

    /**
     * 读取assets下的csv文件
     */
    public static LinkedList<String> readAssetsCsv(Context context, String fileName) {
        LinkedList<String> lineList = new LinkedList<>();
        try {
            InputStream is = context.getAssets().open(fileName + ".csv");
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String strLine = null;
            while ((strLine = br.readLine()) != null) {
                //   Log.e("lyn_readLine", strLine);
                lineList.add(strLine);
            }
            is.close();
            isr.close();
            br.close();
            return lineList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取公司入库name
     * @param code
     * @return
     */
    public static  String getCompanyNameByCode(String code){
        String name="其他";
        for(PayAccountCompanyThirdType thirdType:PayAccountCompanyThirdType.values()){
            if(thirdType.getCode().equalsIgnoreCase(code)){
                name = thirdType.getTrans();
                break;
            }
        }
        return name;
    }
    /**
     * 获取公司入款bg
     * @param code
     * @return
     */
    public static  int getCompanyDrawableByCode(String code){
        int drawable= R.mipmap.icon_other;
        for(PayAccountCompanyThirdType thirdType:PayAccountCompanyThirdType.values()){
            if(thirdType.getCode().equalsIgnoreCase(code)){
                drawable = thirdType.getDrawable();
                break;
            }
        }
        return drawable;
    }

    /**
     * 获取在线支付name
     * @param code
     * @return
     */
    public static  String getOnlineNameByCode(String code){
        String name="其他";
        for(PayAccountAccountType thirdType:PayAccountAccountType.values()){
            if(thirdType.getCode().equalsIgnoreCase(code)){
                name = thirdType.getTrans();
                break;
            }
        }
        return name;
    }
    /**
     * 获取在线支付bg
     * @param code
     * @return
     */
    public static  int getOnlineDrawableByCode(String code){
        int drawable= R.mipmap.icon_other;
        for(PayAccountAccountType thirdType:PayAccountAccountType.values()){
            if(thirdType.getCode().equalsIgnoreCase(code)){
                drawable = thirdType.getDrawable();
                break;
            }
        }
        return drawable;
    }

    /**
     * 获取bankName
     * @param code
     * @return
     */
    public static  String getBankNamenyCode(String code){
        String bankName="未知";
        for(BankType bankType:BankType.values()){
            if(bankType.getCode().equalsIgnoreCase(code)){
                bankName = bankType.getTrans();
                break;
            }
        }
        return bankName;
    }

    public static String getNoteByCode(String code, boolean isRandom, String type, boolean isfirstView) {
        String note = "";
        if (notes.isEmpty()) {
            List<String> lineList = readAssetsCsv(BoxApplication.getContext(), NOTEFILEPATH);
            for (String item : lineList) {
                if (!item.isEmpty()) {
                    String[] items = item.split(",");
                    if (items.length == 3) {
                        notes.put(items[0], new String[]{items[1], items[2]});
                    }
                }
            }
        }
        String[] getNotes=null;
        for (String key:notes.keySet()){
            if(code.contains(key)){
                getNotes = notes.get(key);
                break;
            }
        }
        if(getNotes==null){
            getNotes = notes.get("default");
        }
        if (isRandom) {
            note = "*为了提高对账速度及成功率，当前支付方式已开随机额度，请输入整数存款金额，将随机增加0.01~0.99元";
        } else {
            note = getNotes[0];
        }

        if (type.equalsIgnoreCase("2")) {
            note += "\n" + getNotes[1];
            note = note.replace("*请先搜索账号或扫描二维码添加好友，<br>", "");
            return note;
        } else {
            if (isfirstView) {
                return note;
            } else {
                note = getNotes[1];
                return note;
            }
        }
    }

    /**
     * 获取公司入款type
     * @param bankCode
     * @return
     */
    public static String getCompanyTypeByCode(String bankCode){
        String type="other_fast";
        if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.WECHAT.getCode())) {
          type= BillItemEnum.WECHATPAY_FAST.getCode();
        } else if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.ALIPAY.getCode())) {
            type= BillItemEnum.ALIPAY_FAST.getCode();
        } else if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.ONE_CODE_PAY.getCode())) {
            type= BillItemEnum.ONECODEPAY_FAST.getCode();
        }else if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.BD_WWALLET.getCode())) {
            type= BillItemEnum.BDWALLET_FAST.getCode();
        }else if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.UNIOP_PAY.getCode())) {
            type= BillItemEnum.QQWALLET_FAST.getCode();
        }else if (bankCode.equalsIgnoreCase(PayAccountCompanyThirdType.JD_WALLET.getCode())) {
            type= BillItemEnum.JDWALLET_FAST.getCode();
        }
        return type;
    }

    /**
     * 公司入款网银转账
     * @return
     */
    public static String getCompanyType(){
        return BillItemEnum.ONLINE_BANK_DEPOSIT.getCode();
    }

    /**
     * 获取线上支付type
     * @param accountType
     * @return
     */
    public static String getOnlinePayType(String accountType){
        String type="";
        if(accountType.equalsIgnoreCase(PayAccountAccountType.WECHAT.getCode())){
            type = BillItemEnum.WECHATPAY_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.ALIPAY.getCode())){
            type = BillItemEnum.ALIPAY_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.QQWALLET.getCode())){
            type = BillItemEnum.QQWALLET_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.DIGICCY.getCode())){
            type = "other";
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.JD_PAY.getCode())){
            type = BillItemEnum.JDWALLET_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.BAIFU_PAY.getCode())){
            type = BillItemEnum.BDWALLET_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.UNION_PAY.getCode())){
            type = BillItemEnum.UNIONPAY_SCAN.getCode();
        }else if(accountType.equalsIgnoreCase(PayAccountAccountType.ONLINE_BANK_PAY.getCode())){
            type = BillItemEnum.ONLINE_BANK_PAY.getCode();
        }
        return type;
    }
}
