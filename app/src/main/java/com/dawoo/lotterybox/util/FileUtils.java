package com.dawoo.lotterybox.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jack
 */
public class FileUtils {
    private static FileUtils fileUtils;
    private String SDPATH;
    private int FILESIZE = 4 * 1024;

    public String getSDPATH() {
        return SDPATH;
    }

    public static FileUtils getInstances() {
        if (fileUtils == null) {
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    public FileUtils() {
        SDPATH = getSDcardPath() + "/";
    }

    private String getSDcardPath() {
        String path = getInnerSDCardPath();
        List<String> paths = getExtSDCardPath();
        if (paths != null && paths.size() > 0) {
            for (String str : paths) {
                if (!TextUtils.isEmpty(str)) {
                    path = str;
                }
            }
        }
        return path;
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return 应该就一条记录或空
     */
    public List<String> getExtSDCardPath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

    /**
     * 在SD卡上创建文件
     *
     * @param fileName
     * @throws IOException
     */
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }


    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     * @return
     */
    public File createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        dir.mkdirs();
        return dir;
    }


    /**
     * @param dirPath
     * @param fileName
     * @return
     * @throws IOException
     */
    public File createFile(String dirPath, String fileName) throws IOException {
        File file = new File(createSDDir(dirPath), fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param file 将要删除的文件目录
     */
    public void deleteDir(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteDir(f);
                }
                file.delete();
            }
        }
    }


}