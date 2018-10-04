package helloworld.teste.com.artigoandroidassincrono;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class MainActivityWithRetrofit extends AppCompatActivity {

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
            dialog = ProgressDialog.show(MainActivityWithRetrofit.this, "Aviso", "Aguarde, buscando dados");
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
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.0.2.2:3000")
                .build();
        CarrosService service = restAdapter.create(CarrosService.class);
        service.listCarros(new Callback<CarrosContainer>(){
            @Override
            public void failure(RetrofitError error) {
                Log.e("App-RetroFit", error.getMessage());
                Toast.makeText(getApplicationContext(), "Erro ao obter dados: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void success(CarrosContainer c, retrofit.client.Response response) {
                lView.setAdapter(new ArrayAdapter<Carro>(MainActivityWithRetrofit.this, android.R.layout.simple_list_item_1,c.carros));
                dialog.dismiss();
            }
        });

    }
}
