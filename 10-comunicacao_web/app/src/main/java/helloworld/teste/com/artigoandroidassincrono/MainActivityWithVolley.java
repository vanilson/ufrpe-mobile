package helloworld.teste.com.artigoandroidassincrono;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivityWithVolley extends ActionBarActivity {

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
            dialog = ProgressDialog.show(MainActivityWithVolley.this, "Aviso", "Aguarde, buscando dados");
            fazRequisicao();

            return true;
        } else if (id == R.id.miNovo) {
            Intent intent = new Intent(this, NovoCarroActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fazRequisicao(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:3000/carros";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject json) {
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
                                lView.setAdapter(new ArrayAdapter<Carro>(MainActivityWithVolley.this, android.R.layout.simple_list_item_1, carros));
                                dialog.dismiss();
                            }  catch (JSONException e) {}
                        } else {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );

        queue.add(jsonRequest);
    }
}
