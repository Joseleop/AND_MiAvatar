package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;

/**
 * Aviso genérico "se perderán los poderes actuales". Se usa en tres casos (cambiar raza,
 * cambiar profesión y generar poderes nuevos). El diálogo no sabe qué acción es: la recibe
 * como argumento y la devuelve tal cual al aceptar, para que MainActivity la ejecute.
 */
public class DialogoAdvertencia extends DialogFragment {

    // Argumentos del DIÁLOGO: tipo = qué acción pide confirmación, valor = lo elegido
    // (la raza o profesión nueva; en GENERAR_PODERES no se usa).
    public static final String KEY_TIPO_BUNDLE = "tipo";
    public static final String KEY_VALOR_BUNDLE = "valor";
    // Valores posibles de "tipo".
    public static final int CAMBIO_RAZA = 1;
    public static final int CAMBIO_PROFESION = 2;
    public static final int GENERAR_PODERES = 3;

    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Se leen aquí, fuera del lambda, para devolverlos tal cual al pulsar Aceptar.
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

    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}