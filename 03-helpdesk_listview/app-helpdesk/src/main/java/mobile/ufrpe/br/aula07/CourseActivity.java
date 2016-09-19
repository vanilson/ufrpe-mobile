package mobile.ufrpe.br.aula07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class CourseActivity extends AppCompatActivity {

    private RadioGroup rgCourses;
    private Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // inicializa os elementos da tela
        rgCourses = (RadioGroup) findViewById(R.id.rgCourses);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(mSelectHandler);

        // recupera o curso passado como parametro no intent recebido e seleciona o radio button correspondente
        int cursoSelecionado = getIntent().getIntExtra(Constants.EXTRA_COURSE, Constants.BCC);
        checkRadioButton(cursoSelecionado);
    }

    private void checkRadioButton(int cursoSelecionado) {
        switch (cursoSelecionado) {
            case Constants.BCC:
                rgCourses.check(R.id.rbBCC);
                break;
            case Constants.BSI:
                rgCourses.check(R.id.rbBSI);
                break;
            case Constants.LC:
                rgCourses.check(R.id.rbLC);
                break;
            default:
                rgCourses.check(-1);
                break;
        }
    }

    private View.OnClickListener mSelectHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent data = new Intent();

            int checkedRadio = rgCourses.getCheckedRadioButtonId();
            switch (checkedRadio) {
                case R.id.rbBCC: data.putExtra(Constants.EXTRA_COURSE, Constants.BCC); break;
                case R.id.rbBSI: data.putExtra(Constants.EXTRA_COURSE, Constants.BSI); break;
                case R.id.rbLC: data.putExtra(Constants.EXTRA_COURSE, Constants.LC); break;
                default: data.putExtra(Constants.EXTRA_COURSE, Constants.BCC); break;

            }
            setResult(RESULT_OK, data);
            finish();
        }
    };


}
