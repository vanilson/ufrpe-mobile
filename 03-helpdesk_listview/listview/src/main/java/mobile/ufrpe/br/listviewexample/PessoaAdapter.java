package mobile.ufrpe.br.listviewexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PessoaAdapter extends ArrayAdapter<Pessoa> {

    public PessoaAdapter(Context context, ArrayList<Pessoa> Pessoas) {
        super(context, 0, Pessoas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1 - Obtem o item de dado para esta posicao
        Pessoa Pessoa = getItem(position);

        // TODO  [performance] Verificar se a view existente está sendo reusada, caso contrario infla a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pessoa, parent, false);
      }

        // 2 - inflate view
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_pessoa, null);

        // 3 - busca views do layout
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvLasname);

        // 4 - preenche os dados
        tvName.setText(Pessoa.nome);
        tvHome.setText(Pessoa.sobrenome);

        // 5 - retorna view renderizada
        return convertView;
    }
}
