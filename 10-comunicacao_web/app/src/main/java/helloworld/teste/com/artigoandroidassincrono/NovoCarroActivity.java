package helloworld.teste.com.artigoandroidassincrono;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NovoCarroActivity extends ActionBarActivity {

    private EditText edtNome;
    private EditText edtMarca;
    private EditText edtAno;
    private EditText edtPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_carro);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtMarca = (EditText) findViewById(R.id.edtMarca);
        edtAno = (EditText) findViewById(R.id.edtAno);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
    }

    public void salvar (View v){

    }
}
