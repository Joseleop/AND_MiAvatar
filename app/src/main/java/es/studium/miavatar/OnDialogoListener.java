package es.studium.miavatar;

public interface OnDialogoListener {
    void onDialogoNombreGuardarListener(String nombre);
    void onDialogoNombreCancelarListener();

    void onDialogoSexoGuardarListener(int sexoElegido);
    void onDialogoSexoCancelarListener();

    void onDialogoRazaGuardarListener(int razaElegida);
    void onDialogoRazaCancelarListener();
}
