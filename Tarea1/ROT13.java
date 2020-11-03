import java.util.*;

class Main {


    public static String rot13(String pal) {
        String palabrarot=""; /*Palabra vacia para ir agregando las letras de la palabra con rot13*/
        for(int i=0; i < pal.length() ;++i) {
            char letra = pal.charAt(i);/*Se extrae la letra i de la palabra(pal) ingresada y luego se cambia a la
                                            correspondiente con el rot13 aplicado.*/
            if (letra == 'a') {
                letra = 'n';
            } else if (letra == 'b') {
                letra = 'o';
            } else if (letra == 'c') {
                letra = 'p';
            } else if (letra == 'd') {
                letra = 'q';
            } else if (letra == 'e') {
                letra = 'r';
            } else if (letra == 'f') {
                letra = 's';
            } else if (letra == 'g') {
                letra = 't';
            } else if (letra == 'h') {
                letra = 'u';
            } else if (letra == 'i') {
                letra = 'v';
            } else if (letra == 'j') {
                letra = 'w';
            } else if (letra == 'k') {
                letra = 'x';
            } else if (letra == 'l') {
                letra = 'y';
            } else if (letra == 'm') {
                letra = 'z';
            } else if (letra == 'n') {
                letra = 'a';
            } else if (letra == 'o') {
                letra = 'b';
            } else if (letra == 'p') {
                letra = 'c';
            } else if (letra == 'q') {
                letra = 'd';
            } else if (letra == 'r') {
                letra = 'e';
            } else if (letra == 's') {
                letra = 'f';
            } else if (letra == 't') {
                letra = 'g';
            } else if (letra == 'u') {
                letra = 'h';
            } else if (letra == 'v') {
                letra = 'i';
            } else if (letra == 'w') {
                letra = 'j';
            } else if (letra == 'x') {
                letra = 'k';
            } else if (letra == 'y') {
                letra = 'l';
            } else {
                letra = 'm';
            }

            palabrarot += letra; /*Se agrega cada letra a la palabra que en un principio estaba vacia.*/
        }
        return palabrarot;
    }





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String respuesta = rot13(line);
            System.out.println(respuesta);
        }
    }

}



