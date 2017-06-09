package edu.udel.iyannamm.penpals;

import android.support.v7.app.AppCompatActivity;

public class UserModel extends AppCompatActivity {
    static String username = "";
    static String password = "";
    static String connectconvo = "";
    static String country = "";
    static int score;
    static String signature;

    public UserModel(String username, String password, String connectconvo, String country, int score, String signature) {
        this.username = username;
        this.password = password;
        this.connectconvo = connectconvo;
        this.country = country;
        this.score = score;
        this.signature = signature;
    }

    public static void setSignature(String signature) {
        UserModel.signature = signature;
    }
}
