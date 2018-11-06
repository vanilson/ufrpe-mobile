package mobile.ufrpe.br.aula07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class HelpdeskActivity extends AppCompatActivity {

    private static final int COURSE_REQUEST = 0;

    // armazenará o curso selecionado para salvar em caso de rotações, etc.
    private int mSelectedCourse = Constants.BCC;  // valor default


    private Button mBtnCourse;
    private Button mBtnSend;
    private CheckBox chkStudent;

    // tratador de click dos dois botões
    private TratadorEvento mEventHandler = new TratadorEvento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk);

        // inicializa os elementos que precisamos interagir (inicialmente o botao do curso)
        mBtnCourse = (Button) findViewById(R.id.btnCourse);
        mBtnCourse.setOnClickListener(mEventHandler);

        mBtnSend = (Button) findViewById(R.id.btnSend);
        mBtnSend.setOnClickListener(mEventHandler);

        chkStudent = (CheckBox) findViewById(R.id.ckbStudent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COURSE_REQUEST && resultCode == RESULT_OK) {
            mSelectedCourse = data.getIntExtra(Constants.EXTRA_COURSE, Constants.BCC);
            updateButtonCourseText();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.EXTRA_COURSE, mSelectedCourse);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mSelectedCourse = savedInstanceState.getInt(Constants.EXTRA_COURSE);
        }
        updateButtonCourseText(); // atualiza o texto do botao de acordo com o valor selecionado
    }

    /**
     * Atualiza o texto do botao do curso, de acordo com o valor do atributo mSelectedCourse
     */
    private void updateButtonCourseText() {
        String courseName = getResources().getStringArray(R.array.courses)[mSelectedCourse];
        mBtnCourse.setText(courseName);
    }

    class TratadorEvento implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btnCourse) {
                Intent it = new Intent(HelpdeskActivity.this, CourseActivity.class);
                it.putExtra(Constants.EXTRA_COURSE, mSelectedCourse);
                startActivityForResult(it, COURSE_REQUEST);

            } else if (v.getId() == R.id.btnSend) {
                Intent it = new Intent(HelpdeskActivity.this, ResultActivity.class);
                it.putExtra(Constants.EXTRA_COURSE, mSelectedCourse);
                it.putExtra(Constants.EXTRA_NAME, ((EditText)findViewById(R.id.edtName)).getText().toString());
                it.putExtra(Constants.EXTRA_QUESTION, ((EditText)findViewById(R.id.edtQuestion)).getText().toString());
                it.putExtra(Constants.EXTRA_STUDENT, chkStudent.isChecked());

                startActivity(it);
            }
        }
    }
}