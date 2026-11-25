package es.studium.miavatar;

/**
 * Modelo del personaje: guarda lo elegido (nombre, sexo, raza, profesión) y los 4 poderes.
 * No usa nada de Android (ni vistas ni textos): solo datos y comprobaciones sencillas.
 */
public class Personaje {

    // Valores PROPIOS de la app para cada opción. No son ids de R.id: cada diálogo traduce
    // el RadioButton marcado a estos valores. -1 significa "todavía sin elegir".
    public static final int CHECK_SEXO_SIN_ELEGIR = -1;
    public static final int CHECK_HOMBRE = 1;
    public static final int CHECK_MUJER = 2;

    public static final int CHECK_PROFESION_SIN_ELEGIR = -1;
    public static final int CHECK_ARQUERO = 1;
    public static final int CHECK_GUERRERO = 2;
    public static final int CHECK_MAGO = 3;
    public static final int CHECK_HERRERO = 4;
    public static final int CHECK_MINERO = 5;

    public static final int CHECK_RAZA_SIN_ELEGIR = -1;
    public static final int CHECK_ELFO = 1;
    public static final int CHECK_ENANO = 2;
    public static final int CHECK_HOBBIT = 3;
    public static final int CHECK_HUMANO = 4;

    // -1 = el poder todavía no se ha generado.
    public static final int STAT_SIN_GENERAR = -1;

    // Elecciones del usuario (null / -1 mientras no se haya elegido).
    private String nombre;
    private int sexo;
    private int raza;
    private int profesion;

    // Poderes, generados por GeneradorPoderes (-1 mientras no se hayan generado).
    private int vida;
    private int magia;
    private int fuerza;
    private int velocidad;

    public Personaje() {
        reiniciar();
    }

    // Deja el personaje como al empezar: sin elecciones y sin poderes.
    public void reiniciar() {
        nombre = null;
        sexo = CHECK_SEXO_SIN_ELEGIR;
        raza = CHECK_RAZA_SIN_ELEGIR;
        profesion = CHECK_PROFESION_SIN_ELEGIR;
        borrarPoderes();
    }

    // Borra solo los poderes (al cambiar raza o profesión hay que generar otros).
    public void borrarPoderes(){
        vida = STAT_SIN_GENERAR;
        magia = STAT_SIN_GENERAR;
        fuerza = STAT_SIN_GENERAR;
        velocidad = STAT_SIN_GENERAR;
    }

    // true si ya tiene nombre, sexo, raza y profesión: solo entonces se muestra la ficha.
    public boolean estaCompleto() {
        if (nombre == null) {
            return false;
        }
        if (sexo == CHECK_SEXO_SIN_ELEGIR) {
            return false;
        }
        if (raza == CHECK_RAZA_SIN_ELEGIR) {
            return false;
        }
        if (profesion == CHECK_PROFESION_SIN_ELEGIR) {
            return false;
        }
        return true;
    }

    // true si los 4 poderes ya están generados (ninguno vale -1).
    public boolean tienePoderes() {
        if (vida == STAT_SIN_GENERAR) {
            return false;
        }
        if (magia == STAT_SIN_GENERAR) {
            return false;
        }
        if (fuerza == STAT_SIN_GENERAR) {
            return false;
        }
        if (velocidad == STAT_SIN_GENERAR) {
            return false;
        }
        return true;
    }

    // ----- Datos elegidos (getters y setters) -----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getRaza() {
        return raza;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    public int getProfesion() {
        return profesion;
    }

    public void setProfesion(int profesion) {
        this.profesion = profesion;
    }

    // ----- Poderes -----

    public void setPoderes(int vida, int magia, int fuerza, int velocidad) {
        this.vida = vida;
        this.magia = magia;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
    }

    public int getVida() {
        return vida;
    }

    public int getMagia() {
        return magia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
