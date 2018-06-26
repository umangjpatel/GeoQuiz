package io.github.umangjpatel.geoquiz.utils;

public class Question {

    private int mQuestionResId;
    private boolean mAnswerTrue, mIsAnswered, mIsCheated;

    Question(int questionResId, boolean answerTrue) {
        mQuestionResId = questionResId;
        mAnswerTrue = answerTrue;
        mIsAnswered = false;
    }

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean isAnswered() {
        return mIsAnswered;
    }

    public void setAnswered() {
        mIsAnswered = true;
    }

    public boolean isCheated() {
        return mIsCheated;
    }

    public void setCheated(boolean cheated) {
        mIsCheated = cheated;
    }
}
