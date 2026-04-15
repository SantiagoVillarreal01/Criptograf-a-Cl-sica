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

        int longitudTexto = textoCifrado.length();
        char[][] matrizZigZag = new char[numeroRails][longitudTexto];
        
        //Llenar la matriz con un valor nulo para identificar el camino
        for (char[] fila : matrizZigZag) Arrays.fill(fila, '\n');

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
                    matrizZigZag[i][j] = textoCifrado.charAt(indiceTextoCifrado++);
                }
            }
        }

        //Reconstruir el mensaje
        StringBuilder mensajeOriginal = new StringBuilder();
        railActual = 0;
        moviéndoseHaciaAbajo = false;
        for (int i = 0; i < longitudTexto; i++) {
            mensajeOriginal.append(matrizZigZag[railActual][i]);
            if (railActual == 0 || railActual == numeroRails - 1) {
                moviéndoseHaciaAbajo = !moviéndoseHaciaAbajo;
            }
            railActual += moviéndoseHaciaAbajo ? 1 : -1;
        }

        return mensajeOriginal.toString();
    }

}
