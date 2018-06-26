package io.github.umangjpatel.geoquiz.cheat;

import android.arch.lifecycle.ViewModel;

import io.github.umangjpatel.geoquiz.R;

public class CheatViewModel extends ViewModel {

    private boolean mAnswerTrue;

    public int getAnswerTrue() {
        return (mAnswerTrue ? R.string.true_button_label : R.string.false_button_label);
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

}
