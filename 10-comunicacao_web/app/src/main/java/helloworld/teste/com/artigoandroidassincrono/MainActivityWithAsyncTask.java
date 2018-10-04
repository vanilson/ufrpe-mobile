package helloworld.teste.com.artigoandroidassincrono;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivityWithAsyncTask extends AppCompatActivity {

    private ListView lView;

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
            new Tarefa().execute();
            return true;
        } else if (id == R.id.miNovo) {
            Intent intent = new Intent(this, NovoCarroActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class Tarefa extends AsyncTask<Void, Void, List<Carro>>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivityWithAsyncTask.this, "Aviso", "Aguarde, buscando dados");
        }

        @Override
        protected List<Carro> doInBackground(Void... params) {
            List<Carro> carros = new ArrayList<Carro>();
            try {
                URL url = new URL("http://10.0.2.2:3000/carros");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream is = con.getInputStream();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sBuffer = new StringBuffer();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        sBuffer.append(line);
                    }

                    JSONObject obj = new JSONObject(sBuffer.toString());
                    JSONArray array = obj.getJSONArray("carros");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objCarro = array.getJSONObject(i);
                        Carro carro = new Carro();
                        carro.nome = objCarro.getString("nome");
                        carro.ano = objCarro.getInt("ano");
                        carro.marca = objCarro.getString("marca");
                        carro.preco = (float)objCarro.getDouble("preco");
                        carros.add(carro);
                    }
                } catch (IOException e) {
                    Log.e("CARROS_APP", "erro 1: "+e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("CARROS_APP", "erro 2: "+e.getMessage());
                e.printStackTrace();
            }

            return carros;
        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            lView.setAdapter(new ArrayAdapter<Carro>(MainActivityWithAsyncTask.this, android.R.layout.simple_list_item_1, carros));
            dialog.dismiss();
        }
    }
}
