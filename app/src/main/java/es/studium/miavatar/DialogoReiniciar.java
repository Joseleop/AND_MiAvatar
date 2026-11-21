package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class DialogoReiniciar extends DialogFragment {
    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialogo_reiniciar_titulo)
                .setMessage(R.string.dialogo_reiniciar_mensaje)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoReiniciarAceptarListener();
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoReiniciarCancelarListener();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}