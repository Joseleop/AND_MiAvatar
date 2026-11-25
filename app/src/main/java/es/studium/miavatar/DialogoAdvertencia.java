package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class DialogoAdvertencia extends DialogFragment {

    public static final String KEY_TIPO_BUNDLE = "tipo";
    public static final String KEY_VALOR_BUNDLE = "valor";
    public static final int CAMBIO_RAZA = 1;
    public static final int CAMBIO_PROFESION = 2;

    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int tipo = getArguments().getInt(KEY_TIPO_BUNDLE);
        int valor = getArguments().getInt(KEY_VALOR_BUNDLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);
        View vista = getActivity().getLayoutInflater().inflate(R.layout.layout_dialogo_advertencia, null);

        builder.setView(vista)
                .setTitle(R.string.dialogo_advertencia_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, (dialog, which) ->
                        listener.onDialogoAdvertenciaAceptarListener(tipo, valor))
                .setNegativeButton(R.string.dialogo_cancelar, (dialog, which) ->
                        listener.onDialogoAdvertenciaCancelarListener());
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}