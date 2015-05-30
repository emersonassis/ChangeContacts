package br.com.changecontacts;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerson on 23/05/15.
 */

public class MainActivity extends Activity{

    private ListView listViewContatos;
    private ContatoAdapter contatoAdapter;

    private Button bntChangeContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntChangeContact = (Button) findViewById(R.id.bntChangeContact);

        final String[] projection = new String[] {
                RawContacts._ID,
                RawContacts.DISPLAY_NAME_PRIMARY,
                Contacts.DISPLAY_NAME,
                Phone.NUMBER,
                Phone.CONTACT_ID
        };

        Cursor contact = getContentResolver().query(
                Phone.CONTENT_URI, projection,
                Phone.CONTACT_ID,
                null,
                Phone.CONTACT_ID + " ASC"
        );

        ArrayList<Contato> listaDeContatos = new ArrayList<Contato>();

        if(contact.getCount() > 0) {

            contact.moveToFirst();

            contatoAdapter = new ContatoAdapter(this, listaDeContatos);

            listViewContatos = (ListView) findViewById(R.id.listaDeContatos);
            listViewContatos.setAdapter(contatoAdapter);

            do{

                String nome = contact.getString(contact.getColumnIndex(RawContacts.DISPLAY_NAME_PRIMARY));
                String numero = contact.getString(contact.getColumnIndex(Phone.NUMBER));

                Contato c = new Contato();
                c.setNome(nome);
                c.setNumero(numero);

                contatoAdapter.add(c);

            }while (contact.moveToNext());

        }
        contact.close();

        final ArrayList<Contato> contatosSelecionados = new ArrayList<Contato>();
        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contato itemSelecionado = (Contato) listViewContatos.getItemAtPosition(position);

                contatosSelecionados.add(itemSelecionado);
                contatoAdapter.getView(position, view, parent);


            }
        });

        bntChangeContact.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.
            }
        });

    }

}
