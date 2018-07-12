package com.dawoo.lotterybox.view.activity.setting;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.BitmapUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.guoqi.actionsheet.ActionSheet;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.util.BitmapUtil.getLoacalBitmap;

/**
 * @author jack
 * @date 18-2-9
 * 上传头像
 */

public class UpLoadAvatarActivity extends BaseActivity implements ActionSheet.OnActionSheetSelected {
    @BindView(R.id.upload_avatar_gv)
    GridView uploadAvatarGv;
    @BindView(R.id.bt_submit)
    Button btSubmit;

    @BindView(R.id.load_image)
    ImageView loadImage;

    private Context context;
    private final int PIC_FROM_CAMERA = 1;
    private final int PIC_FROM＿LOCALPHOTO = 0;

    private String picPath = "";
    private Dialog mCameraDialog;

    @Override

    protected void createLayoutView() {
        setContentView(R.layout.activity_uploadavatar);
        context = this;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        UploadImage();
    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked() {
        ActionSheet.showSheet(this, this, null);
    }

    private void CamePressmission() {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            }
        }

    }

    private void Pressmission() {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
        }

    }

    private void doHandlerPhoto(int type) {
        try {
            if (type == PIC_FROM＿LOCALPHOTO) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PIC_FROM_CAMERA:
                try {
                    picPath = BitmapUtil.saveImage2Local(data);
                    UploadImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO:
                if (data == null) {
                    return;
                }
                try {
                    Uri uri = data.getData();
                    System.out.println(uri.getPath());
                    ContentResolver cr = this.getContentResolver();
                    Bitmap myBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    picPath = BitmapUtil.saveMyBitmap(myBitmap, "444");
                    UploadImage();
                } catch (Exception e) {
                }
            default:
        }
    }

    private void UploadImage() {
        Bitmap bitmap = getLoacalBitmap(picPath);
        loadImage.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case ActionSheet.CHOOSE_PICTURE:
                Pressmission();
                doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                break;
            case ActionSheet.TAKE_PICTURE:
                CamePressmission();
                doHandlerPhoto(PIC_FROM_CAMERA);
                break;
            case ActionSheet.CANCEL:
                //取消
                break;
            default:
        }
    }


}
