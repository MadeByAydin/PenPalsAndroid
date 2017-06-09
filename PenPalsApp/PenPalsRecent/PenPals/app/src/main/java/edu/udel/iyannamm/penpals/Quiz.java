package edu.udel.iyannamm.penpals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
    private QuizQuestionBank bank = new QuizQuestionBank();
    private TextView scoreView;
    private TextView Question;
    private Button Answer1;
    private Button Answer2;
    private Button Answer3;

    private String answer;
    static int score = 0;
    private int questionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        scoreView = (TextView)findViewById(R.id.score);
        Question = (TextView)findViewById(R.id.question);
        Answer1 = (Button)findViewById(R.id.answer1);
        Answer2 = (Button)findViewById(R.id.answer2);
        Answer3 = (Button)findViewById(R.id.answer3);

        // updates the question the first time the quiz launches
        updateQuestion();

        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Answer1.getText() == answer && questionNumber != bank.questions.length){
                   score += 1;
                   updateScore();
                   updateQuestion();
               }
               else if(bank.questions.length == questionNumber){
                   Intent intent = new Intent(Quiz.this, QuizHighScore.class);
                   intent.putExtra("score", score);
                   startActivity(intent);
                   resetScore();
               }
               else{
                   updateQuestion();
                   Toast.makeText(Quiz.this, "Incorrect", Toast.LENGTH_SHORT).show();
               }
            }
        });

        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Answer2.getText() == answer && questionNumber != bank.questions.length){
                    score += 1;
                    updateScore();
                    updateQuestion();
                }
                else if(bank.questions.length == questionNumber && Answer2.getText() == answer){
                    score +=1;
                    Intent intent = new Intent(Quiz.this, QuizHighScore.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    resetScore();
                }
                else
                    {
                    updateQuestion();
                    Toast.makeText(Quiz.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Answer3.getText() == answer && questionNumber != bank.questions.length){
                    score += 1;
                    updateScore();
                    updateQuestion();
                }
                else if(bank.questions.length == questionNumber){
                    score += 1;
                    Intent intent = new Intent(Quiz.this, QuizHighScore.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    resetScore();
                }
                else{
                    updateQuestion();
                    Toast.makeText(Quiz.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // Update the score view at the top of the quiz
    private void updateScore(){
        Question.setText(QuizQuestionBank.getQuestion(questionNumber));
        scoreView.setText("" + score);
        Toast.makeText(Quiz.this, "Correct ",
                Toast.LENGTH_LONG).show();
    }

    // resets score so that once the quiz is closed, the users score will reset.
    private void resetScore(){
        score = 0;
    }

    // updates the question by setting the TextView's and Buttons with the following question number
    private void updateQuestion(){
            Question.setText(bank.getQuestion(questionNumber));
            Answer1.setText(bank.getAnswer1(questionNumber));
            Answer2.setText(bank.getAnswer2(questionNumber));
            Answer3.setText(bank.getAnswer3(questionNumber));
            answer = bank.getCorrectAnswer(questionNumber);
            questionNumber++;
    }


}
