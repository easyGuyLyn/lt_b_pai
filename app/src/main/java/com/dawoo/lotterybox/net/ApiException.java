package com.dawoo.lotterybox.net;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.hwangjr.rxbus.RxBus;

/**
 * Created by benson on 17-12-21.
 */

public class ApiException extends RuntimeException {

    public static final String NO_DATA = "ED001";//查无数据

    public ApiException(String resultCode, String msg) {
        this(getApiExceptionMessage(resultCode, msg));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(String code, String msg) {
        //返回错误
        if ("已存在未审核取款订单".equals(msg)) {

        } else if ("今日取款已达上限".equals(msg)) {

        } else {
            ToastUtils.showShort(msg);
        }
        RxBus.get().post(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION, msg);
        return msg;
    }

}
