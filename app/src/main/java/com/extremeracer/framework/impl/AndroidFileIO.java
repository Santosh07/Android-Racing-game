package com.extremeracer.framework.impl;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.extremeracer.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidFileIO implements FileIO {

    AssetManager assetManager;
    Context context;
    String externalStoragePath;

    public AndroidFileIO(Context context) {
        this.context = context;  //get Context (Acitivity)
        this.assetManager = context.getAssets(); //get asset From context
        externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        Log.d("ANDROIDFILEIO", externalStoragePath.toString());
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        Log.d("ANDROIDFILEIO", "wRITING TO THE FILE");
        return new FileOutputStream(externalStoragePath + fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        Log.d("ANDROIDFILEIO", "READING FROM FILE");
        return new FileInputStream(externalStoragePath + fileName);
    }
}
