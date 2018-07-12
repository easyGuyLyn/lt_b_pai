package com.dawoo.lotterybox.view.view.sortcar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by archar on 18-3-7.
 */

public class SortCarSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final long REFRESH_INTERVAL_TIME = 50l;//
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private volatile boolean isSurfaceDestoryed = true; //默认未创建，相当于Destory
    private Thread mThread;  //动画刷新线程

    private volatile ArrayList<SortCar> listSortCar = new ArrayList<>();//当前排序数据源
    private Bitmap moveTail = getScale(R.drawable.move_tail, 0.6f);//尾气向左
    private Bitmap moveTail1 = getScale(R.drawable.move_tail_1, 0.6f);//尾气向右边
    private Bitmap imgFengdan = getScale(R.drawable.racingb_icon_fengdan, 0.6f);//封单显示的图片
    private int perLen = 0;
    private int starLen = 0;
    private float f5954y = 0;//bitmap y轴的值
    private boolean isPlay;//是否播放
    private boolean isPlayRand;//是否播放随机动画
    private boolean isFenDan;//当前是否是封单状态
    private boolean isResult;//有结果的状态


    public SortCarSurfaceView(Context context) {
        super(context);
        init();
    }

    public SortCarSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SortCarSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    private void init() {
        Log.e("car", "init()");
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setZOrderOnTop(true);//设置画布背景透明
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mThread = new Thread(this);
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        for (int i = 0; i < 10; i++) {
            SortCar sortCar = new SortCar();
            sortCar.setNum(i + 1);
            this.listSortCar.add(sortCar);
        }
        ((SortCar) this.listSortCar.get(0)).setBitmap(getScale(R.mipmap.car_1, 0.6f));
        ((SortCar) this.listSortCar.get(1)).setBitmap(getScale(R.mipmap.car_2, 0.6f));
        ((SortCar) this.listSortCar.get(2)).setBitmap(getScale(R.mipmap.car_3, 0.6f));
        ((SortCar) this.listSortCar.get(3)).setBitmap(getScale(R.mipmap.car_4, 0.6f));
        ((SortCar) this.listSortCar.get(4)).setBitmap(getScale(R.mipmap.car_5, 0.6f));
        ((SortCar) this.listSortCar.get(5)).setBitmap(getScale(R.mipmap.car_6, 0.6f));
        ((SortCar) this.listSortCar.get(6)).setBitmap(getScale(R.mipmap.car_7, 0.6f));
        ((SortCar) this.listSortCar.get(7)).setBitmap(getScale(R.mipmap.car_8, 0.6f));
        ((SortCar) this.listSortCar.get(8)).setBitmap(getScale(R.mipmap.car_9, 0.6f));
        ((SortCar) this.listSortCar.get(9)).setBitmap(getScale(R.mipmap.car_10, 0.6f));

        int speed = DensityUtil.dp2px(getContext(), 2.0f);
        perLen = ((SortCar) this.listSortCar.get(0)).getBitmap().getWidth() + speed;

        starLen = ((getWidth() - (((SortCar) listSortCar.get(0)).getBitmap().getWidth() * 10)) - (speed * 9)) / 2;
        for (int i = 0; i < 10; i++) {
            ((SortCar) this.listSortCar.get(i)).setX((float) getX(i));
        }

        f5954y = (float) ((getHeight() - ((SortCar) this.listSortCar.get(0)).getBitmap().getHeight()) / 2);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("car", "surfaceCreated()");
        isSurfaceDestoryed = false;
        if (f5954y == 0) {
            initData();
        }
        if (isFenDan) {//可能surfaceView会自己销毁，所以重新create的时候接着上次的逻辑
            setFenDanUI();
        } else if (isResult) {
            setResultNoPlay(null);
        } else if (isPlayRand) {
            setRandPlay();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("car", "surfaceDestroyed()");
        isSurfaceDestoryed = true;
    }

    //销毁要放到外部页面中，因为surfaceView可能会 ： 如果直接按Home键回到桌面，这时候SurfaceView已经被销毁
    public void destroyAll() {
        isPlay = false;
        isPlayRand = false;
        if (null != mThread && mThread.isAlive()) {
            mThread.interrupt();
            mThread = null;
        }
        if (!imgFengdan.isRecycled()) {
            imgFengdan.recycle();
        }
        if (!moveTail.isRecycled()) {
            moveTail.recycle();
        }
        if (!moveTail1.isRecycled()) {
            moveTail1.recycle();
        }
        for (int i = 0; i < listSortCar.size(); i++) {
            ((SortCar) listSortCar.get(i)).getBitmap().recycle();
        }
        listSortCar.clear();
    }

    /**
     * 开始排序动画
     */
    private void startAnimation() {
        if (mThread.getState() == Thread.State.NEW) {
            mThread.start();
        } else if (mThread.getState() == Thread.State.TERMINATED) {
            mThread = new Thread(this);
            mThread.start();
        }
    }

    /**
     * 设置当前为封单状态
     */
    public void setFenDanUI() {
        stopAllPlay();
        isFenDan = true;
        isResult = false;
        if (isSurfaceDestoryed) return;
        mCanvas = mSurfaceHolder.lockCanvas();
        mCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
        for (int i = 0; i < this.listSortCar.size(); i++) {
            Matrix matrix = new Matrix();
            matrix.setTranslate((float) getX(i), f5954y);
            mCanvas.drawBitmap(imgFengdan, matrix, null);
        }
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    /**
     * 设置当前为某个固定的结果,动画的形式
     */
    public void setResultAndPlay(ArrayList<Integer> arrayList) {
        isFenDan = false;
        isPlay = true;
        if (isPlayRand) {
            isPlayRand = false;
            initPlayData(arrayList);
        } else {
            isPlayRand = false;
            initPlayData(arrayList);
            startAnimation();
        }
        isResult = true;
    }

    /**
     * 设置当前为某个固定的结果,无动画的形式
     */
    public void setResultNoPlay(ArrayList<Integer> arrayList) {
        isFenDan = false;
        isPlay = false;
        isPlayRand = false;
        if (arrayList != null) {
            initPlayData(arrayList);
        }
        if (!isSurfaceDestoryed) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
            for (int i = 0; i < this.listSortCar.size(); i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate((float) getX(i), f5954y);
                mCanvas.drawBitmap(listSortCar.get(i).getBitmap(), matrix, null);
            }
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
        isResult = true;
    }


    /**
     * 设置此时应该要开始随机排序动画
     */
    public void setRandPlay() {
        isPlay = true;
        isPlayRand = true;
        isFenDan = false;
        isResult = false;
        startAnimation();
    }


    private void stopAllPlay() {
        isPlayRand = false;
        isPlay = false;
    }

    @Override
    public void run() {
        Log.e("car", "thread    run()");
        executeAnimation();
    }

    //执行动画
    private void executeAnimation() {
        draw();
    }

    private void draw() {
        while (isPlay && !isSurfaceDestoryed) {
            try {
                if (isPlayRand) {
                    initPlayRandData();
                    boolean isHaveRun = true;
                    while (isHaveRun) {
                        mCanvas = mSurfaceHolder.lockCanvas();
                        mCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
                        for (int i = 0; i < listSortCar.size(); i++) {
                            if (listSortCar.get(i).cheakMove()) {
                                Matrix matrix = new Matrix();
                                matrix.setTranslate(listSortCar.get(i).move(), f5954y);
                                mCanvas.drawBitmap(listSortCar.get(i).getBitmap(), matrix, null);
                                if (listSortCar.get(i).getSpeed() > 0.0f) {
                                    Matrix matrix2 = new Matrix();
                                    matrix2.postTranslate(listSortCar.get(i).move() - moveTail1.getWidth() - 5, f5954y + 5);
                                    mCanvas.drawBitmap(moveTail1, matrix2, null);
                                } else if (listSortCar.get(i).getSpeed() < 0.0f) {
                                    Matrix matrix2 = new Matrix();
                                    matrix2.postTranslate(listSortCar.get(i).move() + moveTail.getWidth() + 10, f5954y + 5);
                                    mCanvas.drawBitmap(moveTail, matrix2, null);
                                }
                                listSortCar.get(i).move();
                            } else {
                                Matrix matrix = new Matrix();
                                matrix.setTranslate((float) getX(i), f5954y);
                                mCanvas.drawBitmap(listSortCar.get(i).getBitmap(), matrix, null);
                            }
                        }
                        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                        int runSize = 0;
                        for (int y = 0; y < listSortCar.size(); y++) {
                            if (listSortCar.get(y).cheakMove()) {
                                runSize++;
                            }
                        }
                        if (runSize > 0) {
                            isHaveRun = true;
                        } else {
                            isHaveRun = false;
                        }
                        Thread.sleep(REFRESH_INTERVAL_TIME);
                    }
                } else {
                    mCanvas = mSurfaceHolder.lockCanvas();
                    mCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
                    for (int i = 0; i < this.listSortCar.size(); i++) {
                        Matrix matrix = new Matrix();
                        matrix.setTranslate((float) getX(i), f5954y);
                        mCanvas.drawBitmap(listSortCar.get(i).getBitmap(), matrix, null);
                    }
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    isPlay = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    //随机
    private void initPlayRandData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < this.listSortCar.size() + 1; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        randInsert(arrayList);
        initPlayData(arrayList);
    }

    //排序到指定的 list集合顺序
    private void initPlayData(ArrayList<Integer> arrayList) {
        Log.e("car rand", arrayList.toString());
        int i = 0;
        if (arrayList.size() == this.listSortCar.size()) {
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                for (int i3 = 0; i3 < this.listSortCar.size(); i3++) {
                    if (((Integer) arrayList.get(i2)).intValue() == ((SortCar) this.listSortCar.get(i3)).getNum()) {
                        arrayList2.add(this.listSortCar.get(i3));
                        break;
                    }
                }
            }
            this.listSortCar = arrayList2;
            while (i < this.listSortCar.size()) {
                ((SortCar) this.listSortCar.get(i)).setIndex(i);
                ((SortCar) this.listSortCar.get(i)).setTargeX((float) getX(i));
                i++;
            }
        }
    }

    //获取随机数
    private static int getRandInt(int i) {
        return new Random().nextInt(i);
    }


    //随机插入
    private void randInsert(ArrayList<Integer> arrayList) {
        if (arrayList.size() >= 10) {
            int randInt = getRandInt(arrayList.size());
            int intValue = ((Integer) arrayList.get(randInt)).intValue();
            arrayList.remove(randInt);
            arrayList.add(getRandInt(arrayList.size()), Integer.valueOf(intValue));
        }
    }


    //获得x轴的偏移量
    private int getX(int i) {
        return this.starLen + (this.perLen * i);
    }

    //缩放
    private Bitmap getScale(int resId, float f) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), resId);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        float f2 = ((float) width) * f;
        f2 = ((float) height) * f;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
        decodeResource.recycle();
        return createBitmap;
    }
}
