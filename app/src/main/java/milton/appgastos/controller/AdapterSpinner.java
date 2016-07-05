package milton.appgastos.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import milton.appgastos.R;
import milton.appgastos.model.Conta;

/**
 * Created by Aluno on 14/06/2016.
 */
public class AdapterSpinner extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<ItemSpinner> itens;

    public AdapterSpinner(Context context, ArrayList<ItemSpinner> itens) {

        //Itens que preenchem o listview
        this.itens = itens;
        //resposavel por pegar o layout do item
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        ItemSpinner item = itens.get(position);

        //Resgatar o layout a ser preenchido
        view = inflater.inflate(R.layout.item_spinner, null);

        //Resgatar o Textview e o imageView para inserção do conteudo
        TextView tvNomeConta = (TextView) view.findViewById(R.id.textViewNomeConta);
        TextView tvValorConta = (TextView) view.findViewById(R.id.textViewValorConta);

        //Mandando os dados para o listview
        tvNomeConta.setText(item.getTexto());
        tvValorConta.setText(String.valueOf(item.getValorDisponivel()));

        return view;
    }
}
