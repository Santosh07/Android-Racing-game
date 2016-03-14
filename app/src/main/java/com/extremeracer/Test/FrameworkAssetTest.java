package com.extremeracer.Test;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import com.extremeracer.framework.impl.AndroidFileIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 6/26/15.
 */
public class FrameworkAssetTest extends Activity {
    TextView textView;
    AndroidFileIO fileIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileIO = new AndroidFileIO(this);
        textView = new TextView(this);
        setContentView(textView);


        AssetManager assetManager = getAssets();
        InputStream in = null;
        String str = null;

        try {
            in = fileIO.readAsset("dbtools");
            str = loadTextFile(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(str);
    }

    private String loadTextFile(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;
        while ((len = in.read(bytes)) > 0) {
            out.write(bytes, 0, len);
        }
        return new String(out.toByteArray(), "UTF-8");
    }
}
