package es.studium.miavatar;

import java.util.Random;

public class GeneradorPoderes {
    private static final int MAX_VIDA = 100;
    private static final int MAX_MAGIA = 10;
    private static final int MAX_FUERZA = 20;
    private static final int MAX_VELOCIDAD = 5;

    private static final Random random = new Random();

    private static int valorAleatorio(int max){
        return random.nextInt(max +1);
    }

    public static void generarPoderes(Personaje personaje){
        personaje.setPoderes(valorAleatorio(MAX_VIDA), valorAleatorio(MAX_MAGIA),
                             valorAleatorio(MAX_FUERZA), valorAleatorio(MAX_VELOCIDAD));
    }
}
