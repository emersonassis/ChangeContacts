package br.com.changecontacts;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private TextView nomeContato;
    private ListView listViewContatos;

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
        ContentValues values = null;
        if(contact.getCount() > 0) {

            contact.moveToFirst();

            ContatoAdapter contatoAdapter = new ContatoAdapter(this, listaDeContatos);

            listViewContatos = (ListView) findViewById(R.id.listaDeContatos);
            listViewContatos.setAdapter(contatoAdapter);

            do{

                String numero = contact.getString(contact.getColumnIndex(Phone.NUMBER)).toString();
                String idContato = contact.getString(contact.getColumnIndex(Phone.CONTACT_ID));

                Contato c = new Contato();
                c.setNome(contact.getString(contact.getColumnIndex(RawContacts.DISPLAY_NAME_PRIMARY)));
                c.setNumero(numero);

                contatoAdapter.add(c);


            }while (contact.moveToNext());

        }
        contact.close();






    }

}
