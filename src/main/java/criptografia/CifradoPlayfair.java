package criptografia;

public class CifradoPlayfair {

   public static String cifrar(String texto, String clave) {
        char[][] matriz = generarMatriz(clave);
        String textoPreparado = prepararTexto(texto);
        return procesarTexto(textoPreparado, matriz, true);
    }

    public static String descifrar(String textoCifrado, String clave) {
        char[][] matriz = generarMatriz(clave);
        return procesarTexto(textoCifrado, matriz, false);
    }

    private static String prepararTexto(String texto) {
        String limpio = texto.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder(limpio);

        for (int i = 0; i < sb.length() - 1; i += 2) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X'); 
            }
        }
        if (sb.length() % 2 != 0) sb.append('X'); 
        return sb.toString();
    }

    private static char[][] generarMatriz(String clave) {
        String base = clave.toUpperCase().replace("J", "I") + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder matrizUnica = new StringBuilder();
        for (char c : base.toCharArray()) {
            if (matrizUnica.indexOf(String.valueOf(c)) == -1) matrizUnica.append(c);
        }

        char[][] matriz = new char[5][5];
        for (int i = 0; i < 25; i++) {
            matriz[i / 5][i % 5] = matrizUnica.charAt(i);
        }
        return matriz;
    }

    private static String procesarTexto(String texto, char[][] matriz, boolean cifrar) {
        StringBuilder resultado = new StringBuilder();
        int movimiento = cifrar ? 1 : 4;

        for (int i = 0; i < texto.length(); i += 2) {
            int[] posA = buscarPosicion(texto.charAt(i), matriz);
            int[] posB = buscarPosicion(texto.charAt(i + 1), matriz);

            if (posA[0] == posB[0]) {
                resultado.append(matriz[posA[0]][(posA[1] + movimiento) % 5]);
                resultado.append(matriz[posB[0]][(posB[1] + movimiento) % 5]);
            } else if (posA[1] == posB[1]) { 
                resultado.append(matriz[(posA[0] + movimiento) % 5][posA[1]]);
                resultado.append(matriz[(posB[0] + movimiento) % 5][posB[1]]);
            } else { // Rectángulo
                resultado.append(matriz[posA[0]][posB[1]]);
                resultado.append(matriz[posB[0]][posA[1]]);
            }
        }
        return resultado.toString();
    }

    private static int[] buscarPosicion(char c, char[][] matriz) {
        for (int f = 0; f < 5; f++) {
            for (int col = 0; col < 5; col++) {
                if (matriz[f][col] == c) return new int[]{f, col};
            }
        }
        return new int[]{0, 0};
    }


}
