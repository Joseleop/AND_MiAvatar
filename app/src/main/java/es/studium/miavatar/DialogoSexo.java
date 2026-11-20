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

public class DialogoSexo extends DialogFragment {
    private OnDialogoListener listener;

    public static final String KEY_SEXO_BUNDLE = "sexo";

    public static final int CHECK_SEXO_SIN_ELEGIR = -1;
    public static final int CHECK_HOMBRE = 1;
    public static final int CHECK_MUJER = 2;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoSexoInflado = inflater.inflate(R.layout.layout_dialogo_sexo, null);
        RadioGroup rgSexo = dialogoSexoInflado.findViewById(R.id.rgSexo);

        Bundle arguments = getArguments();
        if(arguments != null){
            int sexo = arguments.getInt(KEY_SEXO_BUNDLE, CHECK_SEXO_SIN_ELEGIR);
            if(sexo == CHECK_HOMBRE){
                rgSexo.check(R.id.rbHombre);
            } else if (sexo == CHECK_MUJER) {
                rgSexo.check(R.id.rbMujer);
            }
        }
        builder.setView(dialogoSexoInflado);

        builder.setTitle(R.string.dialogo_sexo_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int sexo = rgSexo.getCheckedRadioButtonId();
                        int sexoElegido;
                        if(sexo == R.id.rbHombre){
                            sexoElegido = CHECK_HOMBRE;
                        } else if (sexo == R.id.rbMujer) {
                            sexoElegido = CHECK_MUJER;
                        }
                        else {
                            sexoElegido = CHECK_SEXO_SIN_ELEGIR;
                        }
                        listener.onDialogoSexoGuardarListener(sexoElegido);
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
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
