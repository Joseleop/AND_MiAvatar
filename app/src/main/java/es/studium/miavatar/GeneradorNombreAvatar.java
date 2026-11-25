package es.studium.miavatar;

/**
 * Construye el nombre del drawable del avatar con el formato sexo_raza_profesion
 * (por ejemplo hom_elf_arq). MainActivity busca ese nombre en res/drawable.
 */
public class GeneradorNombreAvatar {

    // Une las tres abreviaturas con "_". Si falta alguna elige "desc" y no habrá imagen con ese nombre.
    public static String nombreImagen(Personaje personaje) {
        return abreviaturaSexo(personaje) + "_" + abreviaturaRaza(personaje) + "_" + abreviaturaProfesion(personaje);
    }

    // Abreviatura de cada opción tal y como se llaman los archivos de res/drawable.
    private static String abreviaturaSexo(Personaje personaje) {
        if (personaje.getSexo() == Personaje.CHECK_HOMBRE) {
            return "hom";
        } else if (personaje.getSexo() == Personaje.CHECK_MUJER) {
            return "muj";
        } else {
            return "desc";
        }
    }

    private static String abreviaturaRaza(Personaje personaje) {
        if (personaje.getRaza() == Personaje.CHECK_ELFO) {
            return "elf";
        } else if (personaje.getRaza() == Personaje.CHECK_ENANO) {
            return "ena";
        } else if (personaje.getRaza() == Personaje.CHECK_HOBBIT) {
            return "hob";
        } else if (personaje.getRaza() == Personaje.CHECK_HUMANO) {
            return "hum";
        } else {
            return "desc";
        }
    }
    private static String abreviaturaProfesion(Personaje personaje) {
        if (personaje.getProfesion() == Personaje.CHECK_ARQUERO) {
            return "arq";
        } else if (personaje.getProfesion() == Personaje.CHECK_GUERRERO) {
            return "gue";
        } else if (personaje.getProfesion() == Personaje.CHECK_MAGO) {
            return "mag";
        } else if (personaje.getProfesion() == Personaje.CHECK_HERRERO) {
            return "her";
        } else if (personaje.getProfesion() == Personaje.CHECK_MINERO) {
            return "min";
        } else {
            return "desc";
        }
    }

}
