package criptografia;

public class CifradoAtbash {


    public static String cifrar(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char inicioAlfabeto = Character.isUpperCase(c) ? 'A' : 'a';
                char finAlfabeto = Character.isUpperCase(c) ? 'Z' : 'z';
                
                int distanciaDesdeInicio = c - inicioAlfabeto;
                char caracterInvertido = (char) (finAlfabeto - distanciaDesdeInicio);
                
                resultado.append(caracterInvertido);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static String descifrar(String texto) {
        return cifrar(texto); 
    }

}
