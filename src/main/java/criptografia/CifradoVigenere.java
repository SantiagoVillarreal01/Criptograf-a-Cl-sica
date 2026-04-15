package criptografia;

public class CifradoVigenere {

    public static String cifrar(String texto, String clave) {
        if (clave == null || clave.isEmpty()) return texto;
        
        StringBuilder resultado = new StringBuilder();
        int indiceClave = 0;
        
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char inicioAlfabetoTexto = Character.isUpperCase(c) ? 'A' : 'a';
                char caracterClaveActual = clave.toUpperCase().charAt(indiceClave % clave.length());
                int valorDesplazamientoClave = caracterClaveActual - 'A';
                
                int posicionOriginal = c - inicioAlfabetoTexto;
                int nuevaPosicion = (posicionOriginal + valorDesplazamientoClave) % 26;
                
                resultado.append((char) (inicioAlfabetoTexto + nuevaPosicion));
                indiceClave++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String descifrar(String texto, String clave) {
        if (clave == null || clave.isEmpty()) return texto;
        
        StringBuilder resultado = new StringBuilder();
        int indiceClave = 0;
        
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char inicioAlfabetoTexto = Character.isUpperCase(c) ? 'A' : 'a';
                
                char caracterClaveActual = clave.toUpperCase().charAt(indiceClave % clave.length());
                int valorDesplazamientoClave = caracterClaveActual - 'A';
                
                int posicionOriginal = c - inicioAlfabetoTexto;
                int nuevaPosicion = (posicionOriginal - valorDesplazamientoClave + 26) % 26;
                
                resultado.append((char) (inicioAlfabetoTexto + nuevaPosicion));
                indiceClave++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

}
