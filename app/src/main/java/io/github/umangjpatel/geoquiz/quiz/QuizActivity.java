package io.github.umangjpatel.geoquiz.quiz;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import io.github.umangjpatel.geoquiz.R;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel mQuizViewModel;

    private AppCompatTextView mQuestionTextView;
    private AppCompatButton mTrueButton, mFalseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        wireUpWidgets();
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        updateUI();
    }

    private void wireUpWidgets() {
        mQuestionTextView = findViewById(R.id.question_text_view);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
    }

    public void nextQuestion(View view) {
        mQuizViewModel.getNextQuestion();
        updateUI();
    }

    public void previousQuestion(View view) {
        mQuizViewModel.getPreviousQuestion();
        updateUI();
    }

    public void checkAnswer(View view) {
        checkCorrectAnswer(view.getId() == R.id.true_button);
    }

    private void checkCorrectAnswer(boolean userPressedTrue) {
        boolean isAnswerCorrect = mQuizViewModel.checkAnswer(userPressedTrue);
        Snackbar.make(findViewById(R.id.quiz_coordinator_layout), isAnswerCorrect ?
                R.string.true_button_label :
                R.string.false_button_label, Snackbar.LENGTH_SHORT).show();
        updateUI();
    }

    private void updateUI() {
        updateQuestionTextView();
        updateAnswerButtons();
    }

    private void updateAnswerButtons() {
        mTrueButton.setEnabled(mQuizViewModel.getQuestionAnswered());
        mFalseButton.setEnabled(mQuizViewModel.getQuestionAnswered());
    }

    private void updateQuestionTextView() {
        mQuestionTextView.setText(mQuizViewModel.getQuestion().getQuestionResId());
    }


}
