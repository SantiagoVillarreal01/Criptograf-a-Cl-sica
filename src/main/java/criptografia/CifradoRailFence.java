package criptografia;

import java.util.Arrays;

public class CifradoRailFence {

    public static String cifrar(String texto, int numeroRails) {
        if (numeroRails <= 1) return texto;

        StringBuilder[] rails = new StringBuilder[numeroRails];
        for (int i = 0; i < numeroRails; i++) {
            rails[i] = new StringBuilder();
        }

        int railActual = 0;
        boolean moviéndoseHaciaAbajo = false;

        for (char c : texto.toCharArray()) {
            rails[railActual].append(c);
            if (railActual == 0 || railActual == numeroRails - 1) {
                moviéndoseHaciaAbajo = !moviéndoseHaciaAbajo;
            }
            railActual += moviéndoseHaciaAbajo ? 1 : -1;
        }

        StringBuilder resultadoFinal = new StringBuilder();
        for (StringBuilder contenidoRail : rails) {
            resultadoFinal.append(contenidoRail);
        }
        return resultadoFinal.toString();
    }

    public static String descifrar(String textoCifrado, int numeroRails) {
        if (numeroRails <= 1) return textoCifrado;

        //Eliminar espacios y normalizar antes de procesar
        String textoLimpio = textoCifrado.replaceAll("\\s", "").toUpperCase();

        //Calculo de la longitud del texto y creacion de la matriz para el zigzag
        int longitudTexto = textoLimpio.length();
        char[][] matrizZigZag = new char[numeroRails][longitudTexto];
        
        //Llenar la matriz con un valor nulo para identificar el camino
        for (int i = 0; i < numeroRails; i++) Arrays.fill(matrizZigZag[i], '\n');

        //Marcar el camino del zigzag
        int railActual = 0;
        boolean moviéndoseHaciaAbajo = false;
        for (int i = 0; i < longitudTexto; i++) {
            matrizZigZag[railActual][i] = '*';
            if (railActual == 0 || railActual == numeroRails - 1) {
                moviéndoseHaciaAbajo = !moviéndoseHaciaAbajo;
            }
            railActual += moviéndoseHaciaAbajo ? 1 : -1;
        }

        //Colocar las letras del texto cifrado
        int indiceTextoCifrado = 0;
        for (int i = 0; i < numeroRails; i++) {
            for (int j = 0; j < longitudTexto; j++) {
                if (matrizZigZag[i][j] == '*' && indiceTextoCifrado < longitudTexto) {
                    matrizZigZag[i][j] = textoLimpio.charAt(indiceTextoCifrado++);
                }
            }
        }

        //Reconstruir el mensaje
        StringBuilder resultado = new StringBuilder();
        railActual = 0;
        moviéndoseHaciaAbajo = false;
        for (int i = 0; i < longitudTexto; i++) {
            resultado.append(matrizZigZag[railActual][i]);
            if (railActual == 0 || railActual == numeroRails - 1) {
                moviéndoseHaciaAbajo = !moviéndoseHaciaAbajo;
            }
            railActual += moviéndoseHaciaAbajo ? 1 : -1;
        }

        return resultado.toString();
    }

}