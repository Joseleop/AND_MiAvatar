package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

/**
 * Confirmación antes de borrar el personaje. Solo muestra un mensaje: Aceptar avisa a
 * MainActivity para que reinicie; Cancelar no hace nada.
 */
public class DialogoReiniciar extends DialogFragment {
    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.layout_dialogo_reiniciar, null);

        builder.setView(vista)
                .setTitle(R.string.dialogo_reiniciar_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, (dialog, which) ->
                        listener.onDialogoReiniciarAceptarListener())
                .setNegativeButton(R.string.dialogo_cancelar, (dialog, which) ->
                        listener.onDialogoReiniciarCancelarListener());
        return builder.create();
    }

    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}