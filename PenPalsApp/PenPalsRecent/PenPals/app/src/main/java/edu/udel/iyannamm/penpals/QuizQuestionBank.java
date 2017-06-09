package edu.udel.iyannamm.penpals;

/**
 * Created by Muhammet on 5/7/2017.
 */

public class QuizQuestionBank {
    // Bank of questions
    static String questions[] = {
            "Which city is the only city in the world situated on two continents?",
            "What European country was in an official state of emergency from 1933 until 1945?",
            "What country used weather-born balloons to drop more than a hundred bombs on North America during World War II?",
            "Which country has a capital city whose name translates as Capital City in the native tongue?",
            "What organization elects the 15 judges of the World Court?",
            "What famous Swiss citizen said of nuclear bombs: If I had known, I would have become a watchmaker?",
            "Festivals in the land of the Thunder Dragon take place in what country?",
            "In what country is the thumbs-up gesture an insult?",
            "What landforms is northern India known for?",





    };

    // Bank of choices
    private String choices[][] = {
            {"Istanbul", "Frankfurt", "New York City"},
            {"Germany", "Poland", "Japan",},
            {"Germany", "Japan", "Austria"},
            {"Seoul, South Korea", "Canberra, Australia", "Bishkek, Kyrgyzstan"},
            {"The United Nations", "World Health Organization", "World Court Committee"},
            {"Roger Schawinski", "Jean Tinguely", "Albert Einstein"},
            {"China", "Japan", "Bhutan"},
            {"Syria", "Iraq", "Saudi Arabia"},
            {"Plains", "Mountains", "Valleys"},



    };

    // Bank of correct answers
    private String correctAnswers[] = {"Istanbul", "Germany", "Japan", "Seoul, South Korea", "The United Nations",
            "Albert Einstein", "Bhutan", "Iraq", "Mountains"};

    // Get the question for the corresponding question number
    public static String getQuestion(int qN) {
        String question = questions[qN];
        return question;
    }

    // Get the first choice for the first button for corresponding question number
    public String getAnswer1(int qN) {
        String choice1 = choices[qN][0];
        return choice1;
    }

    // Get the second choice for the second button for corresponding question number
    public String getAnswer2(int qN) {
        String choice1 = choices[qN][1];
        return choice1;
    }

    // Get the third choice for the third button for corresponding question number
    public String getAnswer3(int qN) {
        String choice1 = choices[qN][2];
        return choice1;
    }

    // Get the first choice for the first button for corresponding question number
    public String getCorrectAnswer(int qN) {
        String answer = correctAnswers[qN];
        return answer;
    }

}
