package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;


/**
 * Diálogo para escribir el nombre. Recibe el nombre actual por getArguments() (precarga)
 * y devuelve el texto escrito a MainActivity mediante OnDialogoListener.
 */
public class DialogoNombre extends DialogFragment {

    private OnDialogoListener listener;

    // Clave del Bundle del DIÁLOGO: MainActivity la usa para enviar el nombre actual.
    public static final String KEY_NOMBRE_BUNDLE = "nombre";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoNombreInflado = inflater.inflate(R.layout.layout_dialogo_nombre, null);
        EditText etNombre = dialogoNombreInflado.findViewById(R.id.editTextNombre);

        // Precarga: si el personaje ya tiene nombre, aparece escrito en el cuadro.
        if (getArguments() != null) {
            etNombre.setText(getArguments().getString(KEY_NOMBRE_BUNDLE));
        }

        builder.setView(dialogoNombreInflado);

        // Aceptar devuelve el texto sin espacios sobrantes (trim); Cancelar no cambia nada.
        builder.setTitle(R.string.dialogo_nombre_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogoNombreGuardarListener(etNombre.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogoNombreCancelarListener();
                    }
                });
        return builder.create();
    }

    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
