package es.studium.miavatar;

/**
 * Contrato entre los diálogos y la Activity. Cada diálogo avisa por aquí (Guardar/Aceptar o
 * Cancelar) y MainActivity decide qué hacer. Así los diálogos no conocen a MainActivity,
 * solo este interface (por eso se obtiene en onAttach con un cast).
 */
public interface OnDialogoListener {
    // Nombre: devuelve el texto escrito (puede venir vacío).
    void onDialogoNombreGuardarListener(String nombre);
    void onDialogoNombreCancelarListener();

    // Sexo, raza y profesion: devuelven el valor Personaje.CHECK_* elegido (o SIN_ELEGIR).
    void onDialogoSexoGuardarListener(int sexoElegido);
    void onDialogoSexoCancelarListener();

    void onDialogoRazaGuardarListener(int razaElegida);
    void onDialogoRazaCancelarListener();

    void onDialogoProfesionGuardarListener(int profesionElegida);
    void onDialogoProfesionCancelarListener();

    // Reiniciar: confirmación de borrar el personaje.
    void onDialogoReiniciarAceptarListener();

    void onDialogoReiniciarCancelarListener();

    // Advertencia: devuelve el tipo de acción que se confirmó y el valor elegido.
    void onDialogoAdvertenciaAceptarListener(int tipo, int valor);
    void onDialogoAdvertenciaCancelarListener();
}
