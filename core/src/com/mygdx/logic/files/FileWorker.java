package com.mygdx.logic.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileWorker {

    public static String SPLIT = "#";
    private static String RESULTS_FILE_PATH = "results.txt";

    public static void saveScore(String userName, String score, Date date) {
        FileHandle fileHandle = Gdx.files.local(RESULTS_FILE_PATH);
        fileHandle.writeString(userName + SPLIT + score + SPLIT + date, false);
    }

    public static void saveScore(long score) {
        FileHandle fileHandle = Gdx.files.local(RESULTS_FILE_PATH);
        fileHandle.writeString("Alex" + SPLIT + score + SPLIT + new Date(), false);
    }

//    public static String getUserResults() {
//        if (Gdx.files.local(RESULTS_FILE_PATH).exists()) {
//            return (Gdx.files.local(RESULTS_FILE_PATH).readString());
//        } else {
//            Gdx.files.local(RESULTS_FILE_PATH).writeString("", false);
//        }
//        return "0";
//    }

    public static String getUserScore() {
        if (Gdx.files.local(RESULTS_FILE_PATH).exists()) {
            if (!Gdx.files.local(RESULTS_FILE_PATH).readString().equals("")) {
                return (Gdx.files.local(RESULTS_FILE_PATH).readString().split(SPLIT)[1]);
            }
        } else {
            Gdx.files.local(RESULTS_FILE_PATH).writeString("", false);
        }
        return "0";
    }

    public static void clearResults() {
        Gdx.files.local(RESULTS_FILE_PATH).writeString("", false);
    }
}
