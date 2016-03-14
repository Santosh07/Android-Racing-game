package com.extremeracer.mrnom;

import com.extremeracer.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {
    public static boolean soundEnabled;
    public static int highScore[] = {100, 80, 50, 30 , 10};

    public static void save(FileIO file) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter((file.writeFile(".mrnom"))));
            out.write(Boolean.toString(soundEnabled) + "\n");
            for (int i = 0; i < 5; i++) {
                out.write(Integer.toString(highScore[i]) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void load(FileIO file) {
        BufferedReader in = null;
        String str = null;
        try {
            in = new BufferedReader(new InputStreamReader(file.readFile((".mrnom"))));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i++) {
                if ((str = in.readLine()) == null) {
                    continue;
                }
                highScore[i] = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (highScore[i] < score) {
                for (int j = 4; j > i; j--)
                    highScore[j] = highScore[j - 1];
                highScore[i] = score;
                break;
            }
        }
    }
}
