package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

public class DialogoProfesion extends DialogFragment {
    private OnDialogoListener listener;

    public static final String KEY_PROFESION_BUNDLE = "profesion";

    public static final int CHECK_PROFESION_SIN_ELEGIR = -1;
    public static final int CHECK_ARQUERO = 1;
    public static final int CHECK_GUERRERO = 2;
    public static final int CHECK_MAGO = 3;
    public static final int CHECK_HERRERO = 4;
    public static final int CHECK_MINERO = 5;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoProfesionInflado = inflater.inflate(R.layout.layout_dialogo_profesion, null);
        RadioGroup rgProfesion = dialogoProfesionInflado.findViewById(R.id.rgProfesion);

        Bundle arguments = getArguments();
        if(arguments != null){
            int profesion = arguments.getInt(KEY_PROFESION_BUNDLE, CHECK_PROFESION_SIN_ELEGIR);

            if(profesion == CHECK_ARQUERO){
                rgProfesion.check(R.id.rbArquero);
            } else if (profesion == CHECK_GUERRERO) {
                rgProfesion.check(R.id.rbGuerrero);
            } else if (profesion == CHECK_MAGO) {
                rgProfesion.check(R.id.rbMago);
            } else if (profesion == CHECK_HERRERO) {
                rgProfesion.check(R.id.rbHerrero);
            } else if (profesion == CHECK_MINERO) {
                rgProfesion.check(R.id.rbMinero);
            }
        }

        builder.setView(dialogoProfesionInflado);
        builder.setTitle(R.string.dialogo_profesion_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int profesion = rgProfesion.getCheckedRadioButtonId();
                        int profesionElegida;

                        if(profesion == R.id.rbArquero){
                            profesionElegida = CHECK_ARQUERO;
                        } else if (profesion == R.id.rbGuerrero) {
                            profesionElegida = CHECK_GUERRERO;
                        } else if (profesion == R.id.rbMago) {
                            profesionElegida = CHECK_MAGO;
                        } else if (profesion == R.id.rbHerrero) {
                            profesionElegida = CHECK_HERRERO;
                        } else if (profesion == R.id.rbMinero) {
                            profesionElegida = CHECK_MINERO;
                        } else {
                            profesionElegida = CHECK_PROFESION_SIN_ELEGIR;
                        }
                        listener.onDialogoProfesionGuardarListener(profesionElegida);
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoProfesionCancelarListener();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
