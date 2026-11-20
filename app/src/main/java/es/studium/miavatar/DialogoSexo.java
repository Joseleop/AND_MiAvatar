package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

public class DialogoSexo extends DialogFragment {
    private OnDialogoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoSexoInflado = inflater.inflate(R.layout.layout_dialogo_sexo, null);
        RadioGroup rgSexo = dialogoSexoInflado.findViewById(R.id.rgSexo);

        builder.setView(dialogoSexoInflado);

        builder.setTitle(R.string.dialogo_sexo_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoSexoGuardarListener(rgSexo.getCheckedRadioButtonId());
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoSexoCancelarListener();
                    }
                });
        return builder.create();
    }
}
