package es.studium.miavatar;

import java.util.Random;

/**
 * Calcula los 4 poderes del personaje. Cada poder = un número al azar entre 0 y su máximo,
 * más un plus por la raza y otro por la profesión, recortado para no salirse de 0..máximo.
 * Así la raza y la profesión influyen en los poderes, no solo en la imagen.
 */
public class GeneradorPoderes {

    // Maximo de cada poder (el minimo siempre es 0).
    private static final int MAX_VIDA = 100;
    private static final int MAX_MAGIA = 10;
    private static final int MAX_FUERZA = 20;
    private static final int MAX_VELOCIDAD = 5;

    // Un único Random compartido por toda la clase.
    private static final Random random = new Random();

    // Posiciones de cada poder dentro de los arrays de pluses
    private static final int VIDA = 0;
    private static final int MAGIA = 1;
    private static final int FUERZA = 2;
    private static final int VELOCIDAD = 3;

    // Orden de cada array: {vida, magia, fuerza, velocidad}
    // Pluses por raza: se suman al valor aleatorio (los negativos restan).
    private static final int[] PLUS_ELFO = {-10, 2, -3, 2};
    private static final int[] PLUS_ENANO = {20, -2, 4, -2};
    private static final int[] PLUS_HOBBIT = {15, 0, -4, -1};
    private static final int[] PLUS_HUMANO = {5, 0, 0, 1};

    // Pluses por profesión.
    private static final int[] PLUS_ARQUERO = {-5, 0, -2, 2};
    private static final int[] PLUS_GUERRERO = {10, -3, 6, 0};
    private static final int[] PLUS_MAGO = {-15, 5, -5, 0};
    private static final int[] PLUS_HERRERO = {5, -2, 5, -1};
    private static final int[] PLUS_MINERO = {15, -2, 3, -2};

    // Para cuando no hay raza o profesión elegida: no suma ni resta nada.
    private static final int[] SIN_PLUS = {0, 0, 0, 0};

    // Genera los 4 poderes del personaje (que ya debe tener raza y profesión) y se los guarda.
    public static void generarPoderes(Personaje personaje) {
        // 1. Valor base aleatorio
        int vida = valorAleatorio(MAX_VIDA);
        int magia = valorAleatorio(MAX_MAGIA);
        int fuerza = valorAleatorio(MAX_FUERZA);
        int velocidad = valorAleatorio(MAX_VELOCIDAD);

        // 2. Pluses de raza y de profesión
        int[] plusRaza = plusPorRaza(personaje.getRaza());
        int[] plusProfesion = plusPorProfesion(personaje.getProfesion());

        // 3. Suma y recorte entre 0 y el máximo
        vida = limitar(vida + plusRaza[VIDA] + plusProfesion[VIDA], MAX_VIDA);
        magia = limitar(magia + plusRaza[MAGIA] + plusProfesion[MAGIA], MAX_MAGIA);
        fuerza = limitar(fuerza + plusRaza[FUERZA] + plusProfesion[FUERZA], MAX_FUERZA);
        velocidad = limitar(velocidad + plusRaza[VELOCIDAD] + plusProfesion[VELOCIDAD], MAX_VELOCIDAD);

        personaje.setPoderes(vida, magia, fuerza, velocidad);
    }

    // Número al azar entre 0 y max, ambos incluidos (nextInt excluye el límite, por eso max + 1).
    private static int valorAleatorio(int max) {
        return random.nextInt(max + 1);
    }

    // Deja el valor entre 0 y max: un plus negativo no puede dar un poder negativo.
    private static int limitar(int valor, int max) {
        if (valor < 0) {
            return 0;
        }
        if (valor > max) {
            return max;
        }
        return valor;
    }

    // Traduce la raza del personaje a su array de pluses (SIN_PLUS si no hay raza).
    private static int[] plusPorRaza(int raza) {
        if (raza == Personaje.CHECK_ELFO) {
            return PLUS_ELFO;
        } else if (raza == Personaje.CHECK_ENANO) {
            return PLUS_ENANO;
        } else if (raza == Personaje.CHECK_HOBBIT) {
            return PLUS_HOBBIT;
        } else if (raza == Personaje.CHECK_HUMANO) {
            return PLUS_HUMANO;
        } else {
            return SIN_PLUS;
        }
    }

    // Igual que plusPorRaza, para la profesión.
    private static int[] plusPorProfesion(int profesion) {
        if (profesion == Personaje.CHECK_ARQUERO) {
            return PLUS_ARQUERO;
        } else if (profesion == Personaje.CHECK_GUERRERO) {
            return PLUS_GUERRERO;
        } else if (profesion == Personaje.CHECK_MAGO) {
            return PLUS_MAGO;
        } else if (profesion == Personaje.CHECK_HERRERO) {
            return PLUS_HERRERO;
        } else if (profesion == Personaje.CHECK_MINERO) {
            return PLUS_MINERO;
        } else {
            return SIN_PLUS;
        }
    }
}