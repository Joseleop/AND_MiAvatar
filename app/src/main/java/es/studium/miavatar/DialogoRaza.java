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

public class DialogoRaza extends DialogFragment {
    private OnDialogoListener listener;

    public static final String KEY_RAZA_BUNDLE = "raza";

    public static final int CHECK_RAZA_SIN_ELEGIR = -1;
    public static final int CHECK_ELFO = 1;
    public static final int CHECK_ENANO = 2;
    public static final int CHECK_HOBBIT = 3;
    public static final int CHECK_HUMANO = 4;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogoRazaInflado = inflater.inflate(R.layout.layout_dialogo_raza, null);
        RadioGroup rgRaza = dialogoRazaInflado.findViewById(R.id.rgRaza);

        Bundle arguments = getArguments();
        if(arguments != null){
            int raza = arguments.getInt(KEY_RAZA_BUNDLE, CHECK_RAZA_SIN_ELEGIR);

            if(raza == CHECK_ELFO){
                rgRaza.check(R.id.rbElfo);
            } else if (raza == CHECK_ENANO) {
                rgRaza.check(R.id.rbEnano);
            } else if (raza == CHECK_HOBBIT) {
                rgRaza.check(R.id.rbHobbit);
            } else if (raza == CHECK_HUMANO) {
                rgRaza.check(R.id.rbHumano);
            }
        }

        builder.setView(dialogoRazaInflado);

        builder.setTitle(R.string.dialogo_raza_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int raza = rgRaza.getCheckedRadioButtonId();
                        int razaElegida;

                        if (raza == R.id.rbElfo) {
                            razaElegida = CHECK_ELFO;
                        } else if (raza == R.id.rbEnano) {
                            razaElegida = CHECK_ENANO;
                        } else if (raza == R.id.rbHobbit) {
                            razaElegida = CHECK_HOBBIT;
                        } else if (raza == R.id.rbHumano) {
                            razaElegida = CHECK_HUMANO;
                        } else{
                            razaElegida = CHECK_RAZA_SIN_ELEGIR;
                        }
                        listener.onDialogoRazaGuardarListener(razaElegida);
                    }
                })
                .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogoRazaCancelarListener();
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
