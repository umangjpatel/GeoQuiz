package io.github.umangjpatel.geoquiz.utils;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.geoquiz.R;

public class QuestionRepository {

    private MutableLiveData<List<Question>> mQuestions;

    public QuestionRepository() {
        mQuestions = new MutableLiveData<>();
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(R.string.question_australia, true));
        questions.add(new Question(R.string.question_oceans, true));
        questions.add(new Question(R.string.question_mideast, false));
        questions.add(new Question(R.string.question_africa, false));
        questions.add(new Question(R.string.question_americas, true));
        questions.add(new Question(R.string.question_asia, true));
        mQuestions.setValue(questions);
    }

    public MutableLiveData<List<Question>> getQuestions() {
        return mQuestions;
    }
}
