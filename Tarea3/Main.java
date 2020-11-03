import java.util.Scanner;



public class Main {
    public static void parsear(String[] npi) {
        Pila2 p = new Pila2();
    /*
    Cada vez que vemos un número lo apilamos, si vemos una operación se sacan los 2 últimos números si es binaria o
    solo uno si es unaria, y se reinserta en la pila un nuevo elemento correspondiente a los números operados.
    Se asume que la expresión ingresada es válida por lo que no se chequean errores.
    */

        String r="";
        for (int i = 0; i < npi.length; i++) {

            // chequeamos si es suma.
            if ("+".contains(npi[i])) {

                // se sacan los 2 últimos números.
                int x = p.pull();
                int y = p.pull();
                // se reinserta el resultado en la pila. notar que el segundo número que sale es el
                // primero en la operación.
                p.push(y + x);

                // chequeamos si es resta.
            }else if ("-".contains(npi[i])) {

                // se sacan los 2 últimos números.
                int x = p.pull();
                int y = p.pull();
                // se reinserta el resultado.
                p.push(y - x);

                // chequeamos si es multiplicacion.
            }else if ("*".contains(npi[i])) {

                // se sacan los 2 últimos números.
                int x = p.pull();
                int y = p.pull();
                // se reinserta el resultado.
                p.push(y * x);

            }else if ("/".contains(npi[i])) {

                // se sacan los 2 últimos números.
                int x = p.pull();
                int y = p.pull();
                // se reinserta el resultado.
                p.push(y / x);

            } else if ("!".contains(npi[i])) {
                // se saca el último número.
                int x = p.pull();
                // se reinserta el resultado.
                p.push(factorial(x));

            } else if ("_".contains(npi[i])) {
                // se saca el último número.
                int x = p.pull();
                // se reinserta el resultado.
                p.push(x * -1);

            } else if ("=".contains(npi[i])) {
                // se saca el último número.
                int x = p.pull();
                // se guarda en la pila.
                p.push(x);
                // se guarda tambien en el string vacío y se suma a los otros resultados
                r= r  +x+ " ";
            } else {
                // si no es operador es número y se guarda como entero en la pila.
                p.push(Integer.parseInt(npi[i]));
            }
        } System.out.println(r.substring(0, r.length() - 1));
        /*
    Cuando se termina de recorrer el arreglo se printea la variable r que almacena todos los resultados
    */

    }

    public static int factorial(int a){
        int m=1;
        for (int j=1;j<=a;j++){
            m=m*j;
        }
        return m;
    }


    public static void main(String[] args) {
        // creamos el objeto scanner que nos permite leer el input del usuario.
        Scanner in= new Scanner(System.in);
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            // creamos un arreglo de strings que guarde cada elemento entregado en el input separado por los espacios.
            String[] arreglo = linea.split(" ");
            parsear(arreglo);
        }
    }
}

// Estructura de Pila que sigue la regla FILO - first in last out
class Pila2 {

    int[] pila;
    int fin;

    public Pila2() {
        this.pila = new int[10];
        this.fin = 0;
    }

    public void push(int x) {// agrega un elemento a la pila.
        this.pila[this.fin] = x;
        this.fin += 1;

        // si la pila se llena debemos crear una nueva con más espacio.
        if (this.fin == this.pila.length) {
            this.duplicarEspacio();
        }
    }

    private void duplicarEspacio() {
        int[] nuevo = new int[this.pila.length * 2];

        for (int i = 0; i < this.pila.length; i++) {
            nuevo[i] = this.pila[i];
        }

        this.pila = nuevo;
    }

    public int pull() {// saca un elemento de la pila.
        if (this.fin == 0) {
            return 1;
        }

        this.fin -= 1;
        return this.pila[this.fin];
    }
}