package es.studium.miavatar;

import android.widget.Button;

public class ActualizarBotones {
    public static void actualizarBotonNombre(Button btnNombre, String nombre) {
        if (nombre != null) {
            btnNombre.setText(nombre);
        } else {
            btnNombre.setText(R.string.texto_boton_nombre);
        }
    }
}
