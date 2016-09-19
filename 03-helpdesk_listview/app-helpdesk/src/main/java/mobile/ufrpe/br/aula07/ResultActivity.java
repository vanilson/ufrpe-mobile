package mobile.ufrpe.br.aula07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView mTxtCourse;
    private TextView mTxtName;
    private TextView mTxtQuestion;
    private TextView mTxtStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // recupera dados do intent
        Intent data = getIntent();
        int courseCode = data.getIntExtra(Constants.EXTRA_COURSE, Constants.BCC);
        String courseName = getResources().getStringArray(R.array.courses)[courseCode];
        String name = data.getStringExtra(Constants.EXTRA_NAME);
        String question = data.getStringExtra(Constants.EXTRA_QUESTION);
        Boolean isStudent = data.getBooleanExtra(Constants.EXTRA_STUDENT, Boolean.FALSE);


        // recupera os elementos da tela
        mTxtCourse    = (TextView) findViewById(R.id.txtCourse);
        mTxtName      = (TextView) findViewById(R.id.txtName);
        mTxtQuestion  = (TextView) findViewById(R.id.txtQuestion);
        mTxtStudent   = (TextView) findViewById(R.id.txtStudent);

        // seta informacoes nos elementos da tela
        mTxtCourse.setText(courseName);
        mTxtName.setText(name);
        mTxtQuestion.setText(question);
        mTxtStudent.setText(isStudent ? getString(R.string.str_student) : "");

    }

    // TODO: salvar estado
}
