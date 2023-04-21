package com.pjatk.myapplication.ui.quiz;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.pjatk.myapplication.Question;
import com.pjatk.myapplication.R;
import com.pjatk.myapplication.databinding.FragmentQuizBinding;
import java.util.List;
import java.util.Random;

public class QuizFragment extends Fragment {
    private FragmentQuizBinding binding;
    List<Question> questionList = List.of(
            new Question("czy wolno jeść na diecie pizze?", "tak", "tak", "nie", "tylko kawałek"),
            new Question("Czy banan zawiera dużo magnezu?", "tak", "tak", "nie", "nie jest to do końca wiadome"),
            new Question("Jak najszybciej schudnąć?", "jeść mniej", "jeść mniej", "chodzić do mcdonalds", "nie da się schudnąć"),
            new Question("Czy kasztan jest warzywem?", "nie", "tak", "nie", "nie jest to do końca wiadome"),
            new Question("Jaka jest najlepsza pora na napicie się wody?", "każda", "każda", "6:00 rano", "17:35 czasu IST"),
            new Question("Czy BMI to wskaźnik inkluzywny?", "nie", "tak", "nie", "ciężko powiedzieć")
    );
    Random randomGenerator = new Random();
    int index = randomGenerator.nextInt(questionList.size());

    Question question = questionList.get(index);
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView question_textview = binding.textQuizQuestion;
        CheckBox answer1_checkbox = (CheckBox) root.findViewById(R.id.text_quiz_answer1_checkbox);
        CheckBox answer2_checkbox = (CheckBox) root.findViewById(R.id.text_quiz_answer2_checkbox);
        CheckBox answer3_checkbox = (CheckBox) root.findViewById(R.id.text_quiz_answer3_checkbox);

        question_textview.setText(question.getQuestion());
        answer1_checkbox.setText(question.getAnswers().get(0));
        answer2_checkbox.setText(question.getAnswers().get(1));
        answer3_checkbox.setText(question.getAnswers().get(2));

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Boolean isCorrect = checkAnswer(root,question,buttonView.getId());
                    Log.d("Added click ", String.valueOf(isCorrect));
                    if(isCorrect){
                        Toast.makeText(getContext(), "Correct Answer!", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getContext(), "Wrong Answer!", Toast.LENGTH_LONG).show();
                    }


                    FragmentManager fragmentManager = getChildFragmentManager();
                    final Fragment current = fragmentManager.findFragmentById(R.id.layout_quiz);
                    if(current == null || !(current instanceof QuizFragment)) {
                        fragmentManager.beginTransaction()
                                .replace(R.id.layout_quiz, new QuizFragment())
                                .commitAllowingStateLoss();

                    }
                }
            }
        };
        answer1_checkbox.setOnCheckedChangeListener(listener);
        answer2_checkbox.setOnCheckedChangeListener(listener);
        answer3_checkbox.setOnCheckedChangeListener(listener);

        return root;
    }

    private Boolean checkAnswer(View root, Question question, int checkboxId) {
        CheckBox checkbox = (CheckBox) root.findViewById(checkboxId);
        return question.getRightAnswer().contentEquals(checkbox.getText());
    }
    private void refreshFragment(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
