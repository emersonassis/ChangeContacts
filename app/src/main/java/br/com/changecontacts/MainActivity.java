package br.com.changecontacts;

import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Console;
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
                Contacts._ID,
                Contacts.DISPLAY_NAME,
                Phone.NUMBER
        };

        final CursorLoader contactCursor = new CursorLoader(
                this,
                Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        Cursor contact = contactCursor.loadInBackground();
        List<String> listaContato = new ArrayList<String>();

        if(contact.getCount() > 0) {
            contact.moveToFirst();

            do{

                String numero = contact.getString(contact.getColumnIndex(Phone.NUMBER)).toString();

                if (numero.equals("numero")) {
                    Contato c = new Contato();
                    c.setNome(contact.getString(contact.getColumnIndex(Contacts.DISPLAY_NAME)));
                    c.setNumero(numero);

                    listaContato.add(c.getNome() + " " + c.getNumero());
                }
            }while (contact.moveToNext());


        }
        contact.close();

        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaContato);
        listViewContatos = (ListView) findViewById(R.id.listaDeContatos);
        listViewContatos.setAdapter(adpter);


    }

}
