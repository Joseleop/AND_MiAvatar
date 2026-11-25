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
 * Diálogo para elegir la profesión con RadioButtons. Se abre con lo ya elegido marcado
 * (argumentos) y, al aceptar, devuelve el valor a MainActivity mediante OnDialogoListener.
 */
public class DialogoProfesion extends DialogFragment {
    private OnDialogoListener listener;

    // Clave del Bundle del DIÁLOGO: MainActivity la usa para enviar la elección actual.
    public static final String KEY_PROFESION_BUNDLE = "profesion";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoProfesionInflado = inflater.inflate(R.layout.layout_dialogo_profesion, null);
        RadioGroup rgProfesion = dialogoProfesionInflado.findViewById(R.id.rgProfesion);

        // Precarga: marca el RadioButton de la opción que el personaje ya tenía (si había).
        Bundle argumentos = getArguments();
        if(argumentos != null){
            int profesion = argumentos.getInt(KEY_PROFESION_BUNDLE, Personaje.CHECK_PROFESION_SIN_ELEGIR);

            if(profesion == Personaje.CHECK_ARQUERO){
                rgProfesion.check(R.id.rbArquero);
            } else if (profesion == Personaje.CHECK_GUERRERO) {
                rgProfesion.check(R.id.rbGuerrero);
            } else if (profesion == Personaje.CHECK_MAGO) {
                rgProfesion.check(R.id.rbMago);
            } else if (profesion == Personaje.CHECK_HERRERO) {
                rgProfesion.check(R.id.rbHerrero);
            } else if (profesion == Personaje.CHECK_MINERO) {
                rgProfesion.check(R.id.rbMinero);
            }
        }

        builder.setView(dialogoProfesionInflado);
        builder.setTitle(R.string.dialogo_profesion_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Traduce el RadioButton marcado (id de R.id) al valor propio de la app (Personaje.CHECK_*).
                        // Si no hay ninguno marcado se devuelve SIN_ELEGIR y MainActivity lo ignora.
                        int profesion = rgProfesion.getCheckedRadioButtonId();
                        int profesionElegida;

                        if(profesion == R.id.rbArquero){
                            profesionElegida = Personaje.CHECK_ARQUERO;
                        } else if (profesion == R.id.rbGuerrero) {
                            profesionElegida = Personaje.CHECK_GUERRERO;
                        } else if (profesion == R.id.rbMago) {
                            profesionElegida = Personaje.CHECK_MAGO;
                        } else if (profesion == R.id.rbHerrero) {
                            profesionElegida = Personaje.CHECK_HERRERO;
                        } else if (profesion == R.id.rbMinero) {
                            profesionElegida = Personaje.CHECK_MINERO;
                        } else {
                            profesionElegida = Personaje.CHECK_PROFESION_SIN_ELEGIR;
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

    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
