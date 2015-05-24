package br.com.changecontacts;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by emerson on 23/05/15.
 */

public class MainActivity extends Activity{

    private ListView listViewContatos;
    private ContatoAdapter contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

}
