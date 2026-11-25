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
 * Diálogo para elegir el sexo con RadioButtons. Se abre con lo ya elegido marcado
 * (argumentos) y, al aceptar, devuelve el valor a MainActivity mediante OnDialogoListener.
 */
public class DialogoSexo extends DialogFragment {
    private OnDialogoListener listener;

    // Clave del Bundle del DIÁLOGO: MainActivity la usa para enviar la elección actual.
    public static final String KEY_SEXO_BUNDLE = "sexo";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogoPergamino);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogoSexoInflado = inflater.inflate(R.layout.layout_dialogo_sexo, null);
        RadioGroup rgSexo = dialogoSexoInflado.findViewById(R.id.rgSexo);

        // Precarga: marca el RadioButton de la opción que el personaje ya tenía (si había).
        Bundle arguments = getArguments();
        if(arguments != null){
            int sexo = arguments.getInt(KEY_SEXO_BUNDLE, Personaje.CHECK_SEXO_SIN_ELEGIR);
            if(sexo == Personaje.CHECK_HOMBRE){
                rgSexo.check(R.id.rbHombre);
            } else if (sexo == Personaje.CHECK_MUJER) {
                rgSexo.check(R.id.rbMujer);
            }
        }
        builder.setView(dialogoSexoInflado);

        builder.setTitle(R.string.dialogo_sexo_titulo)
                .setPositiveButton(R.string.dialogo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Traduce el RadioButton marcado (id de R.id) al valor propio de la app (Personaje.CHECK_*).
                        // Si no hay ninguno marcado se devuelve SIN_ELEGIR y MainActivity lo ignora.
                        int sexo = rgSexo.getCheckedRadioButtonId();
                        int sexoElegido;
                        if(sexo == R.id.rbHombre){
                            sexoElegido = Personaje.CHECK_HOMBRE;
                        } else if (sexo == R.id.rbMujer) {
                            sexoElegido = Personaje.CHECK_MUJER;
                        }
                        else {
                            sexoElegido = Personaje.CHECK_SEXO_SIN_ELEGIR;
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
    // Aquí se obtiene a MainActivity como listener (implementa OnDialogoListener).
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (OnDialogoListener) context;
    }
}
