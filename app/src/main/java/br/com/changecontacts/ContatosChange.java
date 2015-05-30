package br.com.changecontacts;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by emerson on 30/05/15.
 */
public class ContatosChange {


    public static void showContatosSelecionados(ArrayList<Contato> contatosSelecionados){

        ArrayList<Contato> auxContatosSelecionados = contatosSelecionados;
        Log.e("PULA LINHA", "PULA LINHA ");
        for(Contato c: auxContatosSelecionados){
            Log.e("SELECIONADO", c.getNome());
        }

    }


}
