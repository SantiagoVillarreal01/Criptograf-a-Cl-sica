package criptografia;

public class Cifrado {

    public static String cesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                char base = 'A';
                resultado.append((char) ((c - base + desplazamiento) % 26 + base));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String atbash(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                resultado.append((char) ('Z' - (c - 'A')));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String vigenere(String texto, String clave) {
        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();
        clave = clave.toUpperCase();
        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                int desplazo = clave.charAt(j % clave.length()) - 'A';
                resultado.append((char) ((c - 'A' + desplazo) % 26 + 'A'));
                j++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String railFence(String texto) {
        texto = texto.replace(" ", "");
        StringBuilder fila1 = new StringBuilder();
        StringBuilder fila2 = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            if (i % 2 == 0) fila1.append(texto.charAt(i));
            else fila2.append(texto.charAt(i));
        }
        return fila1.toString() + fila2.toString();
    }

    public static String playfair(String texto) {
        String matriz = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        texto = texto.toUpperCase().replace("J", "I").replace(" ", "");
        if (texto.length() % 2 != 0) texto += "X";

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i += 2) {
            char a = texto.charAt(i);
            char b = texto.charAt(i+1);
            int posA = matriz.indexOf(a);
            int posB = matriz.indexOf(b);
            int filaA = posA / 5, colA = posA % 5;
            int filaB = posB / 5, colB = posB % 5;

            resultado.append(matriz.charAt(filaA * 5 + colB));
            resultado.append(matriz.charAt(filaB * 5 + colA));
        }
        return resultado.toString();
    }
}
