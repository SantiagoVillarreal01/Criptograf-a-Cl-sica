package criptografia;

public class CifradoCesar {

    public static String cifrar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        int desplazamientoNormalizado = (desplazamiento % 26 + 26) % 26;

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base + desplazamientoNormalizado) % 26 + base));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String descifrar(String texto, int desplazamiento) {
        return cifrar(texto, 26 - (desplazamiento % 26));
    }

    
}
