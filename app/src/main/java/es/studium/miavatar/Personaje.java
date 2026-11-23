package es.studium.miavatar;

public class Personaje {

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

    private String nombre;
    private int sexo;
    private int raza;
    private int profesion;

    private int vida;
    private int magia;
    private int fuerza;
    private int velocidad;
    public static final int STAT_SIN_GENERAR = -1;

    public Personaje() {
        reiniciar();
    }

    public void reiniciar() {
        nombre = null;
        sexo = CHECK_SEXO_SIN_ELEGIR;
        raza = CHECK_RAZA_SIN_ELEGIR;
        profesion = CHECK_PROFESION_SIN_ELEGIR;
        borrarPoderes();
    }

    public void borrarPoderes(){
        vida = STAT_SIN_GENERAR;
        magia = STAT_SIN_GENERAR;
        fuerza = STAT_SIN_GENERAR;
        velocidad = STAT_SIN_GENERAR;
    }

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
}
