package edu.udel.iyannamm.penpals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizHighScore extends AppCompatActivity {
    private TextView score;
    private TextView highScore;
    private int scoreValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_high_score);

        score = (TextView)findViewById(R.id.Score);
        highScore = (TextView)findViewById(R.id.Highscore);

        // upon creation update the score and highscore so that it is shown on the screen after the quiz is completed
        // by the user.
        updateScore();
        updateHighScore();

    }

    // Compares the score that the user just got to the current highest score and determines if it is a new highest score
    // if so, update the highest score. If not, display the previous highest score across all users.
    private void updateHighScore(){
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        int HighScore = pref.getInt("Highest Score", 0);
        if(HighScore >= scoreValue){
            highScore.setText("Highest Score Across All Users: "+ HighScore+"/"+QuizQuestionBank.questions.length);
        }
        else{
            highScore.setText("New Highest Score Across All Users: " + scoreValue +"/"+QuizQuestionBank.questions.length);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("Highest Score", scoreValue);
            edit.commit();
        }
    }

    private void updateScore(){
        scoreValue = getIntent().getIntExtra("score", 0);
        score.setText("Score: " + scoreValue +"/"+QuizQuestionBank.questions.length);
    }

}
