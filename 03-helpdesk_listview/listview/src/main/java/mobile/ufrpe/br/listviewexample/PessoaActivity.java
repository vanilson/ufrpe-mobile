package mobile.ufrpe.br.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PessoaActivity extends AppCompatActivity {
    private ArrayList<Pessoa>  mPessoas;
    private ArrayAdapter<Pessoa> mAdapter;
    private ListView mLvPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        loadPeople();


        // posso passar o id de um layout meu, desde que seja um TextView
//        mAdapter = new ArrayAdapter<Pessoa>(this,
//                R.layout.item_pessoa_simples, mPessoas);
//
//        mAdapter = new ArrayAdapter<Pessoa>(this, R.layout.item_pessoa_simples, mPessoas);
        mAdapter = new PessoaAdapter(this, mPessoas);

        // atacha o adapter a list view
        mLvPessoa = (ListView) findViewById(R.id.lvPessoa);
        mLvPessoa.setAdapter(mAdapter);

    }

    private void loadPeople() {
        mPessoas = new ArrayList<>();
        for (int i=0; i<200; i++) {
            mPessoas.add(new Pessoa("Vanilson", "Burégio", 18, "Brasil"));
            mPessoas.add(new Pessoa("José", "Silva", 56, "Brasil"));
            mPessoas.add(new Pessoa("Maria", "José", 23, "Colômbia"));
            mPessoas.add(new Pessoa("Banilson", "Vurégio", 45, "Croácia"));
        }
    }
}
