package es.studium.miavatar;

import android.widget.Button;

/**
 * Ayudas para el texto de los botones. Cada opción tiene dos métodos:
 *  - textoX(valor): traduce el valor del personaje al id de su texto en strings.xml,
 *    o -1 si todavía no hay elección.
 *  - actualizarBotonX(botón, valor): pone ese texto en el botón, o el texto inicial si es -1.
 * MainActivity también usa textoRaza y textoProfesion para escribir la ficha.
 */
public class ActualizarBotones {
    // Sin nombre (null) el botón vuelve a su texto inicial.
    public static void actualizarBotonNombre(Button btnNombre, String nombre) {
        if (nombre != null) {
            btnNombre.setText(nombre);
        } else {
            btnNombre.setText(R.string.texto_boton_nombre);
        }
    }

    // Devuelve el id del texto del sexo elegido, o -1 si no hay elección.
    public static int textoSexo(int sexo) {
        if (sexo == Personaje.CHECK_HOMBRE) {
            return R.string.dialogo_sexo_hombre;
        } else if (sexo == Personaje.CHECK_MUJER) {
            return R.string.dialogo_sexo_mujer;
        } else {
            return -1;
        }
    }

    // Si textoSexo da -1 no hay elección: texto inicial del botón.
    public static void actualizarBotonSexo(Button btnSexo, int sexo) {
        if (textoSexo(sexo) == -1) {
            btnSexo.setText(R.string.texto_boton_sexo);
        } else {
            btnSexo.setText(textoSexo(sexo));
        }
    }

    // Igual que textoSexo, para la raza (-1 si no hay elección).
    public static int textoRaza(int raza) {
        if (raza == Personaje.CHECK_ELFO) {
            return R.string.dialogo_raza_elfo;
        } else if (raza == Personaje.CHECK_ENANO) {
            return R.string.dialogo_raza_enano;
        } else if (raza == Personaje.CHECK_HOBBIT) {
            return R.string.dialogo_raza_hobbit;
        } else if (raza == Personaje.CHECK_HUMANO) {
            return R.string.dialogo_raza_humano;
        } else {
            return -1;
        }
    }

    public static void actualizarBotonRaza(Button btnRaza, int raza) {
        if (textoRaza(raza) == -1) {
            btnRaza.setText(R.string.texto_boton_raza);
        } else {
            btnRaza.setText(textoRaza(raza));
        }
    }

    // Igual que textoSexo, para la profesión (-1 si no hay elección).
    public static int textoProfesion(int profesion) {
        if (profesion == Personaje.CHECK_ARQUERO) {
            return R.string.dialogo_profesion_arquero;
        } else if (profesion == Personaje.CHECK_GUERRERO) {
            return R.string.dialogo_profesion_guerrero;
        } else if (profesion == Personaje.CHECK_MAGO) {
            return R.string.dialogo_profesion_mago;
        } else if (profesion == Personaje.CHECK_HERRERO) {
            return R.string.dialogo_profesion_herrero;
        } else if (profesion == Personaje.CHECK_MINERO) {
            return R.string.dialogo_profesion_minero;
        } else {
            return -1;
        }
    }

    public static void actualizarBotonProfesion(Button btnProfesion, int profesion) {
        if (textoProfesion(profesion) == -1) {
            btnProfesion.setText(R.string.texto_boton_profesion);
        } else {
            btnProfesion.setText(textoProfesion(profesion));
        }
    }
}
