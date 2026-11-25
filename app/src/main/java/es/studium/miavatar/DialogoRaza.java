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

/**
 * Diálogo para elegir la raza con RadioButtons. Se abre con lo ya elegido marcado
 * (argumentos) y, al aceptar, devuelve el valor a MainActivity mediante OnDialogoListener.
 */
public class DialogoRaza extends DialogFragment {
    private OnDialogoListener listener;

    // Clave del Bundle del DIÁLOGO: MainActivity la usa para enviar la elección actual.
    public static final String KEY_RAZA_BUNDLE = "raza";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogoRazaInflado = inflater.inflate(R.layout.layout_dialogo_raza, null);
        RadioGroup rgRaza = dialogoRazaInflado.findViewById(R.id.rgRaza);

        // Precarga: marca el RadioButton de la opción que el personaje ya tenía (si había).
        Bundle arguments = getArguments();
        if(arguments != null){
            int raza = arguments.getInt(KEY_RAZA_BUNDLE, Personaje.CHECK_RAZA_SIN_ELEGIR);

            if(raza == Personaje.CHECK_ELFO){
                rgRaza.check(R.id.rbElfo);
            } else if (raza == Personaje.CHECK_ENANO) {
                rgRaza.check(R.id.rbEnano);
            } else if (raza == Personaje.CHECK_HOBBIT) {
                rgRaza.check(R.id.rbHobbit);
            } else if (raza == Personaje.CHECK_HUMANO) {
                rgRaza.check(R.id.rbHumano);
            }
        }

        builder.setView(dialogoRazaInflado);

        builder.setTitle(R.string.dialogo_raza_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Traduce el RadioButton marcado (id de R.id) al valor propio de la app (Personaje.CHECK_*).
                        // Si no hay ninguno marcado se devuelve SIN_ELEGIR y MainActivity lo ignora.
                        int raza = rgRaza.getCheckedRadioButtonId();
                        int razaElegida;

                        if (raza == R.id.rbElfo) {
                            razaElegida = Personaje.CHECK_ELFO;
                        } else if (raza == R.id.rbEnano) {
                            razaElegida = Personaje.CHECK_ENANO;
                        } else if (raza == R.id.rbHobbit) {
                            razaElegida = Personaje.CHECK_HOBBIT;
                        } else if (raza == R.id.rbHumano) {
                            razaElegida = Personaje.CHECK_HUMANO;
                        } else{
                            razaElegida = Personaje.CHECK_RAZA_SIN_ELEGIR;
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
    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
