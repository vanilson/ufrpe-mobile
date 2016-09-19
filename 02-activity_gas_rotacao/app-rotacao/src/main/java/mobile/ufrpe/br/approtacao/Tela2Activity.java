package mobile.ufrpe.br.approtacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Tela2Activity extends AppCompatActivity {
    TextView txtNome;
    CheckBox chkConfirmado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Log.i("Tela2", "onCreate");


        // recebe como parâmetro no intent
        String nome = getIntent().getStringExtra("nomeDigitado");

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtNome.setText(nome);

        chkConfirmado = (CheckBox) findViewById(R.id.chkConfirmado);
        findViewById(R.id.btnConfirmar).setOnClickListener( (v) -> {
            Intent it = new Intent(); // para devolver resultado, nao precisa informar qual a activity. Ela será a tela que me chamou
            it.putExtra("confirmado", chkConfirmado.isChecked());
            setResult(RESULT_OK, it);
            finish();

        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tela2", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tela2", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tela2", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Tela2", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tela2", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tela2", "onDetroy");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Tela2", "onSaveInstanceState");
    }

}