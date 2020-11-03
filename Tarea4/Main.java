import java.util.*;



public class Main {
    public static void A23(String[] line) {
        Arbol23 AB= new Arbol23();  //Arbol vacio al que se aplicaran operaciones.
        String r="";                //Arreglo vacio en el que se guardaran los resultados de las lineas

        //Por cada elemento del arreglo, que seria una instruccion, se guarda en la variable instruc,
        // se le calcula el largo (n) y se le aplican operaciones.
        for (int i = 0; i < line.length; i++) {
            String instruc=line[i];
            int n=instruc.length();

    //Si la letra inicial es '+', tiene dos datos, el numero y el elemento, el ultimo corresponde al ultimo caracter.
            if(instruc.charAt(0)=='+'){
                char e=instruc.charAt(n-1);
                int k= Integer.parseInt(instruc.substring(1,n-1));
                if(AB.buscar(k)){                //Primero se ve si la clave esta en el arbol, si es asi, retorna error.
                    r= r+" "+"Error";
                }else{
                    AB=AB.insertar(k,e);         //Si no está, se inserta.
                }

            //Si la primera letra es '?' se compone solo de la clave, que corresponde a la cadena sin el primer caracter.
            }else if(instruc.charAt(0)=='?'){
                int k= Integer.parseInt(instruc.substring(1,n));
                if(AB.obtener(AB,k)=='0'){
                    r= r+" "+"Error";
                }else{
                    r=r+" "+AB.obtener(AB,k);
                }

            //Si el primer caracter es 'h' no se compone de nada mas y se retorna la altura.
            }else if(instruc.charAt(0)=='h'){
                r=r+" "+AB.altura();

            //Si el primer caracter es 'p' no se compone de nada mas y se retorna la notacion infix del arbol.
            }else if(instruc.charAt(0)=='p'){
            r=r+" "+AB.infix(AB);
            }
        }
        if(r==""){
            System.out.println(r);
        }else{
            System.out.println(r.substring(1)); //Se printea r sin el primer caracter, que es un espacio.
        }
    }



    public static void main(String[] args) {
        // Se crea el objeto scanner que nos permite leer el input del usuario.
        Scanner in= new Scanner(System.in);
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            // Se crea un arreglo de strings que guarde cada elemento entregado en el input separado por los espacios.
            String[] arreglo = linea.split(" ");
            A23(arreglo);
        }
    }
}


class Arbol23{
// El arbol  se compone de 2 arreglos que contendran los datos del nodo, es decir, un arreglo para las claves(int) y
// otro para los elementos(char), la clave en la posicion 'i' de su arreglo, se corresponde con el elemento de la
// posicion 'i' del arreglo de elementos.
// Ademas se compone de 5 arboles2-3, referencia al padre, y 4 hijos (los 3 que se pueden tener mas uno extra para
// el caso en que se necesite hacer split.

    ArrayList<Integer> knodo;
    ArrayList<Character> enodo;


    Arbol23 padre;
    Arbol23 izq;
    Arbol23 mid;
    Arbol23 der;
    Arbol23 extra;

    public Arbol23() { //Crea un arbol vacio.
        this.knodo = new ArrayList<>();
        this.enodo = new ArrayList<>();
        this.padre=null;
        this.izq=null;
        this.mid=null;
        this.der=null;
    }

    //Crea un arbol al que se le ingresan arbol23 padre, izq y der, y par (k,e)
    public Arbol23(int k, char e,Arbol23 p,Arbol23 i,Arbol23 m) {

        this.padre=p;
        this.knodo= new ArrayList<>();
        this.enodo= new ArrayList<>();
        this.knodo.add(k);
        this.enodo.add(e);
        this.izq=i;
        this.mid=m;

    }

    //Este metodo se aplica cuando el nodo tiene una variable y se le agrega otra.
    public void Arbol2(Arbol23 AB, Arbol23 p,Arbol23 i, Arbol23 m,Arbol23 d, int k, char e) {

        AB.izq=i;
        AB.mid=m;
        AB.der=d;
        AB.padre=p;
        if(AB.knodo.get(0) < k){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else {
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }
    }

    //Este metodo se aplica para agregar un par (k,e) a arbol vacio, sin hijos y con padre.
    public void Arbol1(Arbol23 p,int k, char e) {
        this.padre=p;
        this.knodo= new ArrayList<>();
        this.enodo= new ArrayList<>();
        this.knodo.add(k);
        this.enodo.add(e);
        this.izq=null;
        this.mid=null;
    }

    //Este metodo se aplica cuando el nodo tiene una variable y se le agrega otra.
    public void Arbol2(Arbol23 AB,Arbol23 p ,int k, char e) {
        AB.der=null;
        AB.padre=p;
        if(AB.knodo.get(0) < k){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else {
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }
    }

    public void Arbol2(Arbol23 AB,Arbol23 p ,Arbol23 m,Arbol23 d, int k, char e) {
        //Este metodo se aplica cuando el nodo tiene una variable y se le agrega otra.
        AB.mid=m;
        AB.der=d;
        AB.padre=p;
        if(AB.knodo.get(0) < k){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else {
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }
    }


    //Este metodo se aplica cuando el nodo tiene dos variables y se le agrega otra
    public void Arbol3(Arbol23 AB,Arbol23 p ,int k, char e) {
        AB.extra=null;
        AB.padre=p;
        if(AB.knodo.get(0) > k){
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }else if (k>AB.knodo.get(1)){
            AB.knodo.add(2,k);
            AB.enodo.add(2,e);
        }else{
            AB.knodo.add(1,k);
            AB.enodo.add(1,e);
        }
    }

    public void Arbol3(Arbol23 AB,Arbol23 p,Arbol23 i, Arbol23 m,Arbol23 d,Arbol23 ex,int k, char e) {
        AB.extra=ex;
        AB.der=d;
        AB.izq=i;
        AB.mid=m;
        AB.padre=p;
        if(AB.knodo.get(0) > k){
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }else if (k>AB.knodo.get(1)){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else{
            AB.knodo.add(1,k);
            AB.enodo.add(1,e);
        }
    }

    public void Arbol3(Arbol23 AB,Arbol23 p,Arbol23 m, Arbol23 d,Arbol23 ex,int k, char e) {
        AB.extra=ex;
        AB.mid=m;
        AB.der=d;
        AB.padre=p;
        if(AB.knodo.get(0) > k){
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }else if (k>AB.knodo.get(1)){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else{
            AB.knodo.add(1,k);
            AB.enodo.add(1,e);
        }
    }

    public void Arbol3(Arbol23 AB, Arbol23 p,Arbol23 d,Arbol23 ex,int k, char e) {
        AB.extra=ex;
        AB.der=d;
        AB.padre=p;
        if(AB.knodo.get(0) > k){
            AB.knodo.add(0,k);
            AB.enodo.add(0,e);
        }else if (k>AB.knodo.get(1)){
            AB.knodo.add(k);
            AB.enodo.add(e);
        }else{
            AB.knodo.add(1,k);
            AB.enodo.add(1,e);
        }
    }

    //Sirve para hacer split. Retorna el arbol que se genera de la parte izquierda al separar el nodo.
    public Arbol23 Arbolsplitizq(Arbol23 AB) {
        int k=AB.knodo.get(0);
        char e=AB.enodo.get(0);
        return new Arbol23(k, e,AB.padre,AB.izq,AB.mid);
    }

    //Sirve para hacer split. Retorna el arbol que se genera de la parte derecha al separar el nodo.
    public Arbol23 Arbolsplitder(Arbol23 AB) {
        int k = AB.knodo.get(2);
        char e = AB.enodo.get(2);
        return new Arbol23(k, e, AB.padre, AB.der, AB.extra);
    }

    public boolean buscar(int k) {// busca la clave x en el arbol AB.
        Arbol23 a= this;
        while(a!=null){
            if (a.esVacio()) {
                return false;
            }else if(a.knodo.size()==1){
                if(a.knodo.get(0)==k){
                    return true;
                }else if(a.knodo.get(0)<k){
                    a=a.mid;
                }else {
                    a=a.izq;
                }
            }else{
                if(a.knodo.get(1)==k){
                    return true;
                }else if(a.knodo.get(0)==k){
                    return true;
                }else if(a.knodo.get(0)>k){
                    a=a.izq;

                }else if(a.knodo.get(1)>k){
                    a=a.mid;
                }else{
                    a=a.der;
                }
            }
        }return false;
    }

    //inserta par (k,e) al arbol23
    public Arbol23 insertar(int k,char e) {
        Arbol23 a = this;
        Arbol23 p=null;
        //Con el while se recorre el arbol hasta que se llega hasta un nodo externo. Se actualiza el padre en cada caso.
        while (a.izq != null) {
            if (a.knodo.size() == 1) {
                if (k < a.knodo.get(0)) {
                    p=a;
                    a = a.izq;

                } else if (a.knodo.get(0) < k) {
                    p=a;
                    a = a.mid;
                }
            } else {
                if (k < a.knodo.get(0)) {
                    p=a;
                    a = a.izq;
                } else if (k < a.knodo.get(1)) {
                    p=a;
                    a = a.mid;
                } else {
                    p=a;
                    a = a.der;
                }
            }
        }//cuando se llega a nodo externo, se inserta dependiendo de la cantidad de elementos que tenga.
        if (a.esVacio()) {
            a.Arbol1(p,k, e);
        } else if (a.knodo.size() == 1) {
            Arbol2(a,p ,k, e);
        } else { //(a.knodo.size()==2)
            Arbol3(a, p,k, e);
            a = split(a);  //Si el nodo tenia dos elementos, al agregar queda con 3 y hay que hacer split.
        }
        //Se devuelve hasta la raiz y se retorna ese arbol
        while(a.padre!=null){
            a=a.padre;
        }
        return a;
    }



   public Arbol23 split(Arbol23 AB) {
        int a=AB.knodo.get(1);  //Se extrae el la clave y el elemento del medio del nodo.
        char b=AB.enodo.get(1);
        //Dependiendo de la posicion del nodo, se crea o se modifica el arbol que sea necesario. Se actualiza el
        //padre en cada caso y se hace split recursivamente cuando sea necesario.
        if (AB.padre==null){
            AB= new Arbol23(AB.knodo.get(1),AB.enodo.get(1),AB.padre,AB.Arbolsplitizq(AB),AB.Arbolsplitder(AB));
        }else if(AB==AB.padre.izq) {
            if (AB.padre.knodo.size() == 1) {
                Arbol2(AB.padre, AB.padre.padre,Arbolsplitizq(AB), Arbolsplitder(AB), AB.padre.mid, a, b);

            }
            else if (AB.padre.knodo.size() == 2) {  //Si el largo es dos, tiene 3 hijos.
                Arbol3(AB.padre,AB.padre.padre,Arbolsplitizq(AB), Arbolsplitder(AB),AB.padre.mid,AB.padre.der,a,b);
                split(AB.padre);

            }
        }else if (AB==AB.padre.der) { //Si el nodo que se analiza es un hijo derecho, el padre tiene 2 elementos.
            Arbol3(AB.padre, AB.padre.padre,Arbolsplitizq(AB),Arbolsplitder(AB),a, b);
            split(AB.padre);

        }else {
            if (AB.padre.knodo.size() == 1) {
                Arbol2(AB.padre, AB.padre.padre,Arbolsplitizq(AB),Arbolsplitder(AB), a, b);

            }else if (AB.padre.knodo.size() == 2) {  //Si el largo es dos, tiene 3 hijos.
                Arbol3(AB.padre,AB.padre.padre,Arbolsplitizq(AB), Arbolsplitder(AB),AB.padre.der,a,b);
                split(AB.padre);
            }
        }while(AB.padre!=null){
           AB=AB.padre;
       }return AB;
    }

    public int altura() {// para determinar la altura del arbol solo se recorrerán las ramas izquierdas,
                                    //pues no habra subarboles con mas altura que los izq, pero si mas cortos.
        Arbol23 a= this;
        int count = 0;
        if (a.esVacio()) {
            return count;
        }while (a != null){
            a=a.izq;
            count+=1;
        }


        return count;
    }

    //Se le da un arbol y se obtiene el elemento asociado a la clave.
    public char obtener(Arbol23 AB,int k){
        Arbol23 a=AB;
        while(a!=null){
            if (a.esVacio()) {
                return '0';
            }else if(a.knodo.size()==1){
                if(a.knodo.get(0)==k){
                    return a.enodo.get(0);
                }else if(a.knodo.get(0)<k){
                    a=a.mid;
                }else {
                    a=a.izq;
                }
            }else{
                if(a.knodo.get(1)==k){
                    return a.enodo.get(1);
                }else if(a.knodo.get(0)==k){
                    return a.enodo.get(0);
                }else if(a.knodo.get(0)>k){
                    a=a.izq;

                }else if(a.knodo.get(1)>k){
                    a=a.mid;
                }else{
                    a=a.der;
                }
            }
        }return '0';
    }

    //aplica la notacion infix.
    public String infix(Arbol23 AB){
        if(AB == null || AB.esVacio()){
            return "[]";
        }else if(AB.knodo.size()==1){
            return "(" + infix(AB.izq) + AB.knodo.get(0) + infix(AB.mid) + ")";
        }else if (AB.knodo.size()==2){
            return "(" + infix(AB.izq) + AB.knodo.get(0)  + infix(AB.mid) + AB.knodo.get(1) + infix(AB.der) + ")";
        }else{
            return "(" + infix(AB.izq) + AB.knodo.get(0)  + infix(AB.mid) + AB.knodo.get(1) + infix(AB.der) + AB.knodo.get(2) + infix(AB.extra) + ")";
        }
    }

    //Detecta si un arbol es vacio
    public boolean esVacio(){
        return (this.knodo.size()==0);
    }
}

