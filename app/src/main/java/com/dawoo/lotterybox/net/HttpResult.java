package com.dawoo.lotterybox.net;

import java.util.Map;

/**
 * Created by benson on 17-12-20.
 */

public class HttpResult<T> extends BaseHttpResult {
//    {
//        "error": 0,
//            "code": null,
//            "message": null,
//            "data": null,
//            "extend": null
//    }
//    error	int	响应状态，0表示成功，1表示失败
//    code	String	状态代码（见错误码说明）
//    message	String	状态信息（见错误码说明）
//    data	Object	主体业务数据
//    extend	Map	扩展业务数据

    private T data;
    private Map extend;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map getExtend() {
        return extend;
    }

    public void setExtend(Map extend) {
        this.extend = extend;
    }
}