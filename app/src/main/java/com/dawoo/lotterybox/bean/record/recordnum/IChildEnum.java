package com.dawoo.lotterybox.bean.record.recordnum;

import com.dawoo.lotterybox.bean.lottery.lotteryenum.ICodeEnum;

/**
 * Created by rain on 18-4-19.
 */

public interface IChildEnum extends ICodeEnum{
    ICodeEnum getParent();
}
