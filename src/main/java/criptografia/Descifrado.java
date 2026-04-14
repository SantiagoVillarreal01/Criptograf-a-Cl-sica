package criptografia;

public class Descifrado {

    public static String cesar(String texto, int desplazamiento) {
        Cifrado motor = new Cifrado();
        return motor.cesar(texto, 26 - (desplazamiento % 26));
    }

    public static String atbash(String texto) {
        Cifrado motor = new Cifrado();
        return motor.atbash(texto);
    }

    public static String vigenere(String texto, String clave) {
        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();
        clave = clave.toUpperCase();

        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                int desplazo = clave.charAt(j % clave.length()) - 'A';
                char descifrado = (char) ((c - 'A' - desplazo + 26) % 26 + 'A');
                resultado.append(descifrado);
                j++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String railFence(String texto) {
        int n = texto.length();
        int mitad = (n + 1) / 2; // Punto donde termina la fila 1 y empieza la 2
        String fila1 = texto.substring(0, mitad);
        String fila2 = texto.substring(mitad);

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < mitad; i++) {
            resultado.append(fila1.charAt(i));
            if (i < fila2.length()) {
                resultado.append(fila2.charAt(i));
            }
        }
        return resultado.toString();
    }

    public static String playfair(String texto) {
        return Cifrado.playfair(texto);
    }
}
