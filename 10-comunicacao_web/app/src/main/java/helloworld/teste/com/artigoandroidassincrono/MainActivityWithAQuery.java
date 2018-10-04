package helloworld.teste.com.artigoandroidassincrono;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivityWithAQuery extends ActionBarActivity {

    private ListView lView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lView = (ListView) findViewById(R.id.list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.miRefresh) {
            dialog = ProgressDialog.show(MainActivityWithAQuery.this, "Aviso", "Aguarde, buscando dados");
            AQuery aq = new AQuery(this);
            String url = "http://10.0.2.2:3000/carros";
            aq.ajax(url, JSONObject.class, retorno);

            return true;
        } else if (id == R.id.miNovo) {
            Intent intent = new Intent(this, NovoCarroActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AjaxCallback<JSONObject> retorno = new AjaxCallback<JSONObject>(){
        @Override
        public void callback(String url, JSONObject json, AjaxStatus status) {
            if (json != null) {
                List<Carro> carros = new ArrayList<Carro>();
                try {
                    JSONArray array = json.getJSONArray("carros");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objCarro = array.getJSONObject(i);
                        Carro carro = new Carro();
                        carro.nome = objCarro.getString("nome");
                        carro.ano = objCarro.getInt("ano");
                        carro.marca = objCarro.getString("marca");
                        carro.preco = (float) objCarro.getDouble("preco");
                        carros.add(carro);
                    }
                    lView.setAdapter(new ArrayAdapter<Carro>(MainActivityWithAQuery.this, android.R.layout.simple_list_item_1,
                            carros));
                    dialog.dismiss();
                }  catch (JSONException e) {}
            } else {}
        }
    };
}
