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
        if(sexo == DialogoSexo.CHECK_HOMBRE){
            btnSexo.setText(R.string.dialogo_sexo_hombre);
        } else if (sexo == DialogoSexo.CHECK_MUJER) {
            btnSexo.setText(R.string.dialogo_sexo_mujer);
        } else {
            btnSexo.setText(R.string.texto_boton_sexo);
        }
    }
}
