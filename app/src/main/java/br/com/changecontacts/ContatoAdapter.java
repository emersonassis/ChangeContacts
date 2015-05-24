package br.com.changecontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 23/05/15.
 */
public class ContatoAdapter extends ArrayAdapter<Contato>{


    public ContatoAdapter(Context context, ArrayList<Contato> arrayAdapterContato) {
        super(context, 0, arrayAdapterContato);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Contato contato = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contatos_item, parent, false);
        }

        TextView txtNome = (TextView) convertView.findViewById(R.id.nome);
        TextView txtNumero = (TextView) convertView.findViewById(R.id.numero);

        txtNome.setText(contato.getNome());
        txtNumero.setText(contato.getNumero());

        return  convertView;
    }

}
