import java.util.*;

public class Main {

    //Crea un arreglo con los runs de un arreglo
    public static ArrayList<ArrayList<Integer>> runs(ArrayList<Integer> arr){
        ArrayList<ArrayList<Integer>> runs=new ArrayList<>();

        int fin=1;
        int i=0;
        int inicio=0;
        //Se recorre el arreglo hasta el penúltimo elemento comparando el elemento actual con el siguiente.
        while(i<arr.size()-1){
            //Si el actual es mayor se hace el run correspondiente.
            if (arr.get(i)>arr.get(i+1)){
                runs.add(new ArrayList<>(arr.subList(inicio,fin)));
                i+=1;
                inicio=fin;
                fin+=1;
            //Sino, se sigue iterando
            }else{
                i+=1;
                fin+=1;
            }
        }//Se añade el run que falta (recordar que el arreglo no se recorrió completo)
        runs.add(new ArrayList<>(arr.subList(inicio,fin)));
        return runs;
    }


    public static int merge(ArrayList<Integer> A,ArrayList<Integer> B,ArrayList<Integer> R){
    //recibe dos arreglos que serán mergeados:A y B. Y uno que corresponde al arreglo que se utiliza dentro de los
    //métodos para no ordenar el arreglo real, este es al que se le van anadiendo los elementos.
        int i = 0;
        int j = 0;
        int count=0;


        while (i < A.size() && j < B.size()) {
            if (A.get(i) <= B.get(j)) {
                R.add(A.get(i));
                i += 1;
                count += 1;
            } else {
                R.add(B.get(j));
                j += 1;
                count += 1;
            }
        }
        while (i < A.size()) {
            R.add(A.get(i));
            i += 1;
        }
        while (j < B.size()) {
            R.add(B.get(j));
            j += 1;
        }

        return count;
    }

    public static int AdaptiveMS(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> A = runs(a);
        int count = 0;

        //Mientras el arreglo de runs tenga mas de un elemento, se mergean los dos primeros runs y se anade el
        //resultado al final. Se borran los que fueron mergeados.
        while (A.size() > 1) {
            ArrayList<Integer> Ai = new ArrayList<>();
            count+=merge(A.get(0),A.get(1),Ai);
            A.remove(0);
            A.remove(0);
            A.add(Ai);
        }return count;
    }


    public static int mergeSort(ArrayList<Integer> arr){
        ArrayList<Integer> A = new ArrayList<>();
        //Se traspasan todos los elementos a uno nuevo para no ordenar el ingresado.
        for (int i=0;i<arr.size();i++){
            A.add(arr.get(i));
        }

        int n=A.size();
        int size;
        int ini;
        int c=0;

        //Se van haciendo subarreglos desde tamano uno. El tamaño se va duplicando.
        for(size = 1; size < n; size = size*2){
            //Se van mergeando los subarreglos de a dos.
            for(ini = 0; ini < n-1; ini += 2*size){

                int fin_primero = ini + size-1;
                int fin_segundo = Math.min(n-1, fin_primero+size);

                //No se utiliza el mismo merge de antes, pues aca no se hacen nuevos arreglos, se gurdan los punteros
                c+=merge(A, ini, fin_primero, fin_segundo);
            }
        }return c;
    }

    //Este metodo es similar al merge anterior solo que en vez de recibir arreglos recibe punteros. El arreglo A es
    //donde se insertaran los runs mergeados.
    public static int merge(ArrayList<Integer> A, int ini, int fin_primero, int fin_segundo){

        int n1 = fin_primero - ini + 1;
        int n2 = fin_segundo - fin_primero;

        ArrayList<Integer> aux1 = new ArrayList<>();
        ArrayList<Integer> aux2 = new ArrayList<>();


        //Se traspasan todos los elementos a unos nuevos arreglos para poder mergearlos.
        for (int i=0; i < n1; i++ ) {
            aux1.add(A.get(i+ini));
        }
        for (int i=0; i < n2; i++ ) {
            aux2.add(A.get(i+fin_primero+1));
        }

        int i=0, j=0;
        int k=ini;
        int c=0;
        while(i < n1 && j < n2){

            if(aux1.get(i)<=aux2.get(j)){
                A.remove(k);
                A.add(k,aux1.get(i));
                i++;

            }
            else{
                A.remove(k);
                A.add(k,aux2.get(j));
                j++;
            }
            c+=1;
            k++;
        }
        while(i < n1){
            A.remove(k);
            A.add(k,aux1.get(i));
            i++;
            k++;
        }

        while(j < n2){
            A.remove(k);
            A.add(k,aux2.get(j));
            j++;
            k++;
        }

        return c;
    }




    public static int HuffmanSort(ArrayList<Integer> a){
        ArrayList<ArrayList<Integer>> A = runs(a);
        MinHeap H= new MinHeap();

        //Cada run se agrega al minHeap. El heap se ordena segun los tamanos de los runs
        for(int i=0;i<A.size();i++){
            H.insert(A.get(i));
        }
        int count=0;

        while(H.tamano()>1){
            //El arreglo R es el que contendrá el resultado del merge.
            ArrayList<Integer> R = new ArrayList<>();

            //Se extraen los dos runs mas cortos del heap.
            ArrayList<Integer> firtsmin = H.extractMin();
            ArrayList<Integer> scndmin = H.extractMin();

            count+=merge(firtsmin,scndmin,R);

            //Se anade al heap.
            H.insert(R);

        }return count;
    }


    public static void main(String[] args) {
        // Se crea el objeto scanner que nos permite leer el input del usuario.
        Scanner in= new Scanner(System.in);
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            // Se crea un arreglo de strings que guarde cada elemento entregado en el input separado por los espacios.
            String[] a = linea.split(" ");
            ArrayList<Integer> arreglo= new ArrayList<>();
            for (int i=0; i<a.length; i++){
                arreglo.add(Integer.parseInt(a[i]));
            }
            System.out.println(runs(arreglo).size()+" "+Integer.toString(mergeSort(arreglo))+" "+Integer.toString(AdaptiveMS(arreglo)+arreglo.size()-1)+" "+ Integer.toString(HuffmanSort(arreglo)+arreglo.size()-1));
            //System.out.println(AdaptiveMSH(runs(arreglo)).size());
        }
    }
}

class MinHeap {
    //El heap sera un ArrayList que contendrá otros Arrays con un run en la segunda posicion y su tamano en la primera.
    //por ejemplo [[[3] [1 2 3]] [[4] [1 1 3 4]]]
    private ArrayList<ArrayList<Integer >> list;

    public MinHeap() {

        this.list = new ArrayList<>();
    }

    public void insert(ArrayList<Integer> item) {

        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        //Para insertar se comparan los tamanos de los runs.
        while (parent != i && list.get(i).size() < list.get(parent).size()) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    //retorna el elemento minimo y lo borra.
    public ArrayList<Integer>  extractMin() {

        if (list.size() == 0) {

            throw new IllegalStateException("MinHeap is EMPTY");

         //Si tiene un elemento solo se debe extraer y borrarlo
        } else if (list.size() == 1) {
            ArrayList<Integer> min = list.remove(0);
            return min;
        }

        // Se saca el elemento de la raiz (minimo), se remueve el ultimo elemento y se inserta en la raíz.
        ArrayList<Integer>  min = list.get(0);
        ArrayList<Integer> lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // Se hace bubble down hasta que llegue a la posición correcta. Manteniendo las propiedades de un Heap.
        minHeapify(0);

        // Se retorna el minimo
        return min;
    }


    private void minHeapify(int i) {

        int left = left(i);
        int right = right(i);
        int smallest;

        //Se encuentra el run de tamaño mas pequeño entre el nodo y sus hijos
        if (left <= list.size() - 1 && list.get(left).size() <= list.get(i).size()) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right).size() <= list.get(smallest).size()) {
            smallest = right;
        }

        //Si la llave mas pequeña no es la llave actual, se hace bubble-down
        if (smallest != i) {

            swap(i, smallest);
            minHeapify(smallest);
        }

    }

    //intercambia los nodos i y padre de posición
    private void swap(int i, int parent) {

        ArrayList<Integer> temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

    public int tamano() {

        return list.size();
    }

    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }

    private int parent(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
    }
}