package io.github.umangjpatel.geoquiz.cheat;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import io.github.umangjpatel.geoquiz.R;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_TRUE =
            CheatActivity.class.getPackage().getName() + ".extra_answer_true",
            EXTRA_ANSWER_SHOWN = CheatActivity.class.getPackage().getName() + ".extra_answer_shown";

    private CheatViewModel mCheatViewModel;

    private AppCompatTextView mAnswerTextView;

    public static Intent newIntent(Context packageContext, boolean isAnswerTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_TRUE, isAnswerTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        wireUpWidgets();
        mCheatViewModel = ViewModelProviders.of(this).get(CheatViewModel.class);
        mCheatViewModel.setAnswerTrue(getIntent().getBooleanExtra(EXTRA_ANSWER_TRUE, false));
    }

    private void wireUpWidgets() {
        mAnswerTextView = findViewById(R.id.answer_text_view);
    }

    private void updateAnswerTextView() {
        mAnswerTextView.setText(mCheatViewModel.getAnswerTrue());
    }

    public void showAnswer(View view) {
        updateAnswerTextView();
        setAnswerShownResult();
    }

    private void setAnswerShownResult() {
        setResult(Activity.RESULT_OK, setData());
    }

    private Intent setData() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, true);
        return data;
    }
}
