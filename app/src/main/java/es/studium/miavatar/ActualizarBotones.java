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

    public static void actualizarBotonSexo(Button btnSexo, int sexo){
        if(sexo == 1){
            btnSexo.setText(R.string.dialogo_sexo_hombre);
        } else if (sexo == 2) {
            btnSexo.setText(R.string.dialogo_sexo_mujer);
        } else {
            btnSexo.setText(R.string.texto_boton_sexo);
        }
    }
}
