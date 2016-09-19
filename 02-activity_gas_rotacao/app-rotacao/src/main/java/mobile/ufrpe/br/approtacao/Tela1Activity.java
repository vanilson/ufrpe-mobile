package mobile.ufrpe.br.approtacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tela1Activity extends AppCompatActivity {

    EditText edtNome;
    TextView txtNome;
    Button btnOk;
    Button btnTela2;
    TextView txtConfirmacao;
    Button btnConfirmar;

    String nomeDigitado;

    private View.OnClickListener tratadorEvento  = (v) -> {
        switch (v.getId()) {
            case R.id.btnOk:
//                txtNome.setText(edtNome.getText());  // TODO: por que na 1a vez que giramos a tela o texto é mantido?
                nomeDigitado =  edtNome.getText().toString();
                atualizaNome();
                edtNome.getText().clear();

                break;

            case R.id.btnTela2:
                Intent it = new Intent(Tela1Activity.this, Tela2Activity.class); // recebe um contexto (application ou activity), classe da tela a ser aberta

                // passa valor no nomeDigitado para a tela 2
                it.putExtra("nomeDigitado", edtNome.getText().toString());
                startActivity(it);
                break;
        }

    };

    private void atualizaNome() {
        if (nomeDigitado != null) {
            txtNome.setText(nomeDigitado);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        Log.i("Tela1", "onCreate");

        // inicializa atributos
        edtNome = (EditText) findViewById(R.id.edtNome);
        txtNome = (TextView) findViewById(R.id.txtNomeDigitado);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnTela2 = (Button) findViewById(R.id.btnTela2);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        txtConfirmacao = (TextView) findViewById(R.id.txtConfirmacao);

        // nos caso de rotacao, por exemplo
        if (savedInstanceState != null) {
            nomeDigitado = savedInstanceState.getString("nomeDigitado");
            atualizaNome();
        }

        btnOk.setOnClickListener(tratadorEvento);
        btnTela2.setOnClickListener(tratadorEvento);

        // abre tela para confirmar nomeDigitado
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela1Activity.this, Tela2Activity.class);
                it.putExtra("nomeDigitado", edtNome.getText().toString());

                // abra activity que vou esperar o result (intent, requeste code) e tratá-lo no onActivityResult
                startActivityForResult(it, 0);  // ao inves de startActivity
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // chamado de volta sempre
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) { // poderia ser RESULT_CANCELLED
            String nomeConfirmado = data.getBooleanExtra("confirmado", false) ? "Nome confirmado!" : "Nome não confirmado!" ;
            txtConfirmacao.setText(nomeConfirmado);
        }
    }

    // TODO: salvar estado da activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nomeDigitado", nomeDigitado);
        Log.i("Tela1", "onSaveInstanceState");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Tela1", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Tela1", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Tela1", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Tela1", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Tela1", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Tela1", "onDetroy");
    }
}
