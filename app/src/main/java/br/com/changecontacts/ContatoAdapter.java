package br.com.changecontacts;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerson on 23/05/15.
 */
public class ContatoAdapter extends ArrayAdapter<Contato>{

    ListView listView = null;

    public ContatoAdapter(Context context, ArrayList<Contato> arrayAdapterContato) {
        super(context, 0, arrayAdapterContato);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        listView = (ListView) parent;
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Contato contato = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contatos_item, parent, false);
        }

        TextView txtNome = (TextView) convertView.findViewById(R.id.nome);
        TextView txtNumero = (TextView) convertView.findViewById(R.id.numero);

        txtNome.setText(contato.getNome());
        txtNumero.setText(contato.getNumero());

        convertView = this.setBackgroundColor(position, convertView, parent);

        return  convertView;
    }


    public View setBackgroundColor(int position, View current, ViewGroup parent){

        View view = (View) current;
        ListView listView = (ListView) parent;
        int backgroundColor = 0;

        if (listView.isItemChecked(position)){
            backgroundColor = Color.argb(0xFF, 0x31, 0xB6, 0xE7);
        }else
            backgroundColor = Color.TRANSPARENT;

        view.setBackgroundColor(backgroundColor);

        return view;

    }



}
