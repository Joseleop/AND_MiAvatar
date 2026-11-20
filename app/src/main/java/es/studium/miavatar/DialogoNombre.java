package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;


public class DialogoNombre extends DialogFragment {

    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoNombreInflado = inflater.inflate(R.layout.layout_dialogo_nombre, null);
        EditText etNombre = dialogoNombreInflado.findViewById(R.id.editTextNombre);

        if (getArguments() != null) {
            etNombre.setText(getArguments().getString("nombre"));
        }

        builder.setView(dialogoNombreInflado);

        builder.setTitle(R.string.dialogo_nombre_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(getActivity(), "Botón aceptar pulsado", Toast.LENGTH_SHORT).show();
                        listener.onDialogoNombreGuardarListener(etNombre.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(getActivity(), "Botón calcelar pulsado", Toast.LENGTH_SHORT).show();
                        listener.onDialogoNombreCancelarListener();
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
