package es.studium.miavatar;

public interface OnDialogoListener {
    void onDialogoNombreGuardarListener(String nombre);
    void onDialogoNombreCancelarListener();

    void onDialogoSexoGuardarListener(int sexoElegido);
    void onDialogoSexoCancelarListener();

    void onDialogoRazaGuardarListener(int razaElegida);
    void onDialogoRazaCancelarListener();

    void onDialogoProfesionGuardarListener(int profesionElegida);
    void onDialogoProfesionCancelarListener();

    void onDialogoReiniciarAceptarListener();

    void onDialogoReiniciarCancelarListener();
}
