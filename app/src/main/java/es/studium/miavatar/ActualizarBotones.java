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

    public static void actualizarBotonRaza(Button btnRaza, int raza){
        if(raza == DialogoRaza.CHECK_ELFO){
            btnRaza.setText(R.string.dialogo_raza_elfo);
        } else if (raza == DialogoRaza.CHECK_ENANO) {
            btnRaza.setText(R.string.dialogo_raza_enano);
        } else if (raza == DialogoRaza.CHECK_HOBBIT) {
            btnRaza.setText(R.string.dialogo_raza_hobbit);
        } else if (raza == DialogoRaza.CHECK_HUMANO) {
            btnRaza.setText(R.string.dialogo_raza_humano);
        } else {
            btnRaza.setText(R.string.texto_boton_raza);
        }
    }
}
