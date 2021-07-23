package com.example.appsale28042021.shared;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AppCache {
    public static boolean createFile(String data, Context context) {
        if (data.equals("[]")) {
            return false;
        } else {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("cart.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write(data);
                outputStreamWriter.close();
                return true;
            } catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
                return false;
            }
        }
    }

    public static boolean deleteFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("cart.txt", Context.MODE_PRIVATE);
            File f = new File(System.getProperty("user.dir"), "cart.txt");
            f.delete();
            fos.close();
            return true;
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
    }

    public static String readFile(Context context) {
        String result = "";
        try {
            InputStream inputStream = context.openFileInput("cart.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            }else{
                return result;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return result;

    }
}