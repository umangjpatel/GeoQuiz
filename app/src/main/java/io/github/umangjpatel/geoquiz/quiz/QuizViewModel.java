package io.github.umangjpatel.geoquiz.quiz;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.github.umangjpatel.geoquiz.utils.Question;
import io.github.umangjpatel.geoquiz.utils.QuestionRepository;

public class QuizViewModel extends ViewModel {

    private int mQuestionIndex = 0;
    private List<Question> mQuestions;

    public QuizViewModel() {
        QuestionRepository repository = new QuestionRepository();
        mQuestions = repository.getQuestions().getValue();
    }

    public void getNextQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % mQuestions.size();
    }

    public void getPreviousQuestion() {
        mQuestionIndex = (mQuestionIndex + mQuestions.size() - 1) % mQuestions.size();
    }

    public Question getQuestion() {
        return mQuestions.get(mQuestionIndex);
    }

    private boolean getAnswer() {
        return mQuestions.get(mQuestionIndex).isAnswerTrue();
    }

    public boolean checkAnswer(boolean userPressedTrue) {
        setQuestionAnswered();
        return getAnswer() == userPressedTrue;
    }

    public boolean getQuestionAnswered() {
        return !mQuestions.get(mQuestionIndex).isAnswered();
    }

    private void setQuestionAnswered() {
        mQuestions.get(mQuestionIndex).setAnswered();
    }
}
