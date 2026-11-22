package es.studium.miavatar;

public class GeneradorNombreAvatar {

    public static String nombreImagen(Personaje personaje){
        String sexoAcortado;
        String razaAcortada;
        String profesionAcortada;

        if(personaje.getSexo() == Personaje.CHECK_HOMBRE){
            sexoAcortado = "hom";
        } else if (personaje.getSexo() == Personaje.CHECK_MUJER){
            sexoAcortado = "muj";
        } else {
            sexoAcortado = "desc";
        }

        if(personaje.getRaza() == Personaje.CHECK_ELFO){
            razaAcortada = "elf";
        } else if (personaje.getRaza() == Personaje.CHECK_ENANO) {
            razaAcortada = "ena";
        } else if (personaje.getRaza() == Personaje.CHECK_HOBBIT) {
            razaAcortada = "hob";
        } else if (personaje.getRaza() == Personaje.CHECK_HUMANO) {
            razaAcortada = "hum";
        } else{
            razaAcortada = "desc";
        }

        if(personaje.getProfesion() == Personaje.CHECK_ARQUERO){
            profesionAcortada = "arq";
        } else if (personaje.getProfesion() == Personaje.CHECK_GUERRERO) {
            profesionAcortada = "gue";
        } else if (personaje.getProfesion() == Personaje.CHECK_MAGO) {
            profesionAcortada = "mag";
        } else if (personaje.getProfesion() == Personaje.CHECK_HERRERO) {
            profesionAcortada = "her";
        } else if (personaje.getProfesion() == Personaje.CHECK_MINERO) {
            profesionAcortada = "min";
        } else{
            profesionAcortada = "desc";
        }

        return sexoAcortado + "_" + razaAcortada + "_" + profesionAcortada;
    }
}
