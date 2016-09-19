
package mobile.ufrpe.br.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int GAS = 1;
    public static final int ALC = 2;
    public static final int SHIT = 0;


    private TextView tvResultado;
    private EditText edtGas, edtAlc;
    private Button btnCalc;
    private int resultado;

//    View.OnClickListener listenerSayHello = new View.OnClickListener(){
//
//        @Override
//        public void onClick(View v) {
//            String msg = getString(R.string.hi) + " " + edtNome.getText() + "!!!";
//            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtGas = (EditText) findViewById(R.id.edtGasolina);
        edtAlc = (EditText) findViewById(R.id.edtAlcool);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btnCalc = (Button) findViewById(R.id.btnCalc);

        if (savedInstanceState != null) {
            resultado = savedInstanceState.getInt("resultado");
            atualizarTextoResultado();
        }

        btnCalc.setOnClickListener((v) -> {
                try{
                    double vgas = Double.parseDouble(edtGas.getText().toString());
                    double valc = Double.parseDouble(edtAlc.getText().toString());

                    if(valc/vgas <= 0.7){
                        resultado = ALC;
                    }else{
                        resultado = GAS;

                    }
                }catch (Exception e){
                    resultado = SHIT;
                }
                atualizarTextoResultado();
            }
        );
    }

    private void atualizarTextoResultado() {
        if (resultado == GAS) {
            tvResultado.setText(getString(R.string.gasolina) + " " + getString(R.string.melhor));
        } else if (resultado == ALC) {
            tvResultado.setText(getString(R.string.alcool) + " " + getString(R.string.melhor));
        } else if (resultado == SHIT) {
            tvResultado.setText(getString(R.string.error));
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("resultado", resultado);
    }
}
