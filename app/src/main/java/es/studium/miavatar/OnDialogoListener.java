package es.studium.miavatar;

public interface OnDialogoListener {
    public void onDialogoNombreGuardarListener(String nombre);
    public void onDialogoNombreCancelarListener();
    public void onDialogoSexoGuardarListener(int sexoElegido);
    public void onDialogoSexoCancelarListener();
}
