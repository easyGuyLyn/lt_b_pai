package com.dawoo.lotterybox.net;

import android.content.Context;

import com.blankj.utilcode.util.ActivityUtils;
import com.dawoo.coretool.util.activity.ActivityStackManager;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.LoginActivity;
import com.dawoo.lotterybox.view.activity.MainActivity;
import com.hwangjr.rxbus.RxBus;


/**
 *200	请求成功
 *201	新资源被创建
 *202	已接受处理请求但尚未完成（异步处理）
 *204	资源为空
 *301	资源的URI被更新
 *400	坏请求
 *401	禁止访问（token失效或无权限等）
 *404	资源不存在
 *406	服务端不支持所需表示
 *409	通用冲突
 *412	前置条件失败
 *415	接受到的表示不受支持
 *500	服务器错误
 *503	服务当前无法处理请求
 *701	请求的token为空或已失效
 * Created by benson on 18-1-8.
 */

public class CustomHttpException extends RuntimeException {

    public CustomHttpException(int code) {
        String errorMessage = getCustomHttpException(code);
        throw new RuntimeException(errorMessage);
    }

    private String getCustomHttpException(int code) {
        Context context = BoxApplication.getContext();
        switch (code) {
            case 201:
                return context.getString(R.string.http_code_201);
            case 202:
                return context.getString(R.string.http_code_202);
            case 204:
                return context.getString(R.string.http_code_204);
            case 301:
                return context.getString(R.string.http_code_301);
            case 400:
                return context.getString(R.string.http_code_400);
            case 401:
                ActivityUtils.startActivity(LoginActivity.class);
                RxBus.get().post(ConstantValue.EVENT_TYPE_NETWORK_RETURN, context.getString(R.string.http_code_401));
                return context.getString(R.string.http_code_401);
            case 404:
                return context.getString(R.string.http_code_404);
            case 406:
                return context.getString(R.string.http_code_406);
            case 409:
                return context.getString(R.string.http_code_409);
            case 412:
                return context.getString(R.string.http_code_412);
            case 415:
                return context.getString(R.string.http_code_415);
            case 500:
                return context.getString(R.string.http_code_500);
            case 503:
                return context.getString(R.string.http_code_503);
            case 701:
                ActivityUtils.startActivity(LoginActivity.class);
                ActivityStackManager.getInstance().finishToActivity(MainActivity.class,true);
             //   RxBus.get().post(ConstantValue.EVENT_TYPE_NETWORK_RETURN, "登录超时");
                return context.getString(R.string.http_code_701);
            default:
        }
        return context.getString(R.string.http_code_default, code);
    }


}
