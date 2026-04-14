import criptografia.Cifrado;
import criptografia.Descifrado;

public class AppCifrado {

    public static void main() {

        String palabra = "CIFRANDO";
        System.out.println("Original: " + palabra);
        System.out.println("Cifrado César: " + Cifrado.cesar(palabra, 3));
        System.out.println("Cifrado Atbash:     " + Cifrado.atbash(palabra));
        System.out.println("Cifrado Vigenere:   " + Cifrado.vigenere(palabra, "VIGENERE"));
        System.out.println("Cifrado Rail Fence: " + Cifrado.railFence(palabra));
        System.out.println("Cifrado Playfair:   " + Cifrado.playfair(palabra));


        System.out.println("Descifrado César: " + Descifrado.cesar("FLIUDQGR", 3));
        System.out.println("Descifrado Atbash:     " + Descifrado.atbash("XRUIZMWL"));
        System.out.println("Descifrado Vigenere:   " + Descifrado.vigenere("XQLVNRUS", "VIGENERE"));
        System.out.println("Descifrado Rail Fence: " + Descifrado.railFence("CFADIRNO"));
        System.out.println("Descifrado Playfair:   " + Descifrado.playfair("DHGQCLDO"));

    }


}
