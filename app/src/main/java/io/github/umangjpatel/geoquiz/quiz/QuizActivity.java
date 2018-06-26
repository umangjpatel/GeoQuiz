package io.github.umangjpatel.geoquiz.quiz;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import io.github.umangjpatel.geoquiz.R;
import io.github.umangjpatel.geoquiz.cheat.CheatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHEAT = 1;

    private QuizViewModel mQuizViewModel;

    private AppCompatTextView mQuestionTextView;
    private AppCompatButton mTrueButton, mFalseButton, mCheatButton;


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
        mCheatButton = findViewById(R.id.cheat_button);
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
        showSnackBar(isAnswerCorrect ? R.string.correct_answer : R.string.incorrect_answer);
        updateUI();
    }

    public void cheatQuestion(View view) {
        startActivityForResult(CheatActivity.newIntent(this, mQuizViewModel.getQuestionAnswer()),
                REQUEST_CODE_CHEAT);
    }

    private void updateUI() {
        updateQuestionTextView();
        updateButtons();
    }

    private void updateButtons() {
        if (mQuizViewModel.getQuestionCheated()) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            mCheatButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(mQuizViewModel.getQuestionAnswered());
            mFalseButton.setEnabled(mQuizViewModel.getQuestionAnswered());
            mCheatButton.setEnabled(mQuizViewModel.getQuestionAnswered());
        }
    }

    private void updateQuestionTextView() {
        mQuestionTextView.setText(mQuizViewModel.getQuestion().getQuestionResId());
    }

    private void showSnackBar(int stringResource) {
        Snackbar.make(findViewById(R.id.quiz_coordinator_layout), stringResource, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (resultCode == Activity.RESULT_OK) {
                mQuizViewModel.setQuestionCheated(CheatActivity.wasAnswerShown(data));
                updateButtons();
            }
        }
    }
}
