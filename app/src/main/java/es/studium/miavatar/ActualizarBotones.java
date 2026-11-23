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

    public static void actualizarBotonSexo(Button btnSexo, int sexo) {
        if (sexo == Personaje.CHECK_HOMBRE) {
            btnSexo.setText(R.string.dialogo_sexo_hombre);
        } else if (sexo == Personaje.CHECK_MUJER) {
            btnSexo.setText(R.string.dialogo_sexo_mujer);
        } else {
            btnSexo.setText(R.string.texto_boton_sexo);
        }
    }

    public static void actualizarBotonRaza(Button btnRaza, int raza) {
        if (raza == Personaje.CHECK_ELFO) {
            btnRaza.setText(R.string.dialogo_raza_elfo);
        } else if (raza == Personaje.CHECK_ENANO) {
            btnRaza.setText(R.string.dialogo_raza_enano);
        } else if (raza == Personaje.CHECK_HOBBIT) {
            btnRaza.setText(R.string.dialogo_raza_hobbit);
        } else if (raza == Personaje.CHECK_HUMANO) {
            btnRaza.setText(R.string.dialogo_raza_humano);
        } else {
            btnRaza.setText(R.string.texto_boton_raza);
        }
    }

    public static void actualizarBotonProfesion(Button btnProfesion, int profesion) {
        if (profesion == Personaje.CHECK_ARQUERO) {
            btnProfesion.setText(R.string.dialogo_profesion_arquero);
        } else if (profesion == Personaje.CHECK_GUERRERO) {
            btnProfesion.setText(R.string.dialogo_profesion_guerrero);
        } else if (profesion == Personaje.CHECK_MAGO) {
            btnProfesion.setText(R.string.dialogo_profesion_mago);
        } else if (profesion == Personaje.CHECK_HERRERO) {
            btnProfesion.setText(R.string.dialogo_profesion_herrero);
        } else if (profesion == Personaje.CHECK_MINERO) {
            btnProfesion.setText(R.string.dialogo_profesion_minero);
        } else {
            btnProfesion.setText(R.string.texto_boton_profesion);
        }
    }

    public static int textoRaza(int raza) {
        if (raza == Personaje.CHECK_ELFO) {
            return R.string.dialogo_raza_elfo;
        } else if (raza == Personaje.CHECK_ENANO) {
            return R.string.dialogo_raza_enano;
        } else if (raza == Personaje.CHECK_HOBBIT) {
            return R.string.dialogo_raza_hobbit;
        } else {
            return R.string.dialogo_raza_humano;
        }
    }

    public static int textoProfesion(int profesion) {
        if (profesion == Personaje.CHECK_ARQUERO) {
            return R.string.dialogo_profesion_arquero;
        } else if (profesion == Personaje.CHECK_GUERRERO) {
            return R.string.dialogo_profesion_guerrero;
        } else if (profesion == Personaje.CHECK_MAGO) {
            return R.string.dialogo_profesion_mago;
        } else if (profesion == Personaje.CHECK_HERRERO) {
            return R.string.dialogo_profesion_herrero;
        } else {
            return R.string.dialogo_profesion_minero;
        }
    }
}
