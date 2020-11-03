import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main{

     public static String diff(String[] TA, String[] TB){
        int m = TA.length;
        int n = TB.length;

        int[][] dp = new int[m][n];
        String[][] cp = new String[m][n];

         for (int i=0; i < m ; i++) {
             for (int j=0; j< n ; j++) {
                 if(i==0 && j==0 && ! TA[i].equals(TB[j])){
                     dp[i][j] = 1;
                     cp[i][j]=i+1 +",c,"+TB[j]+'\n';
                 }
                 // no hay nada en el primer string, por lo que los unicos cambios
                 // que nos faltan son los que no hemos hecho en T, es decir j
                 else if(i==0 && j==0 && TA[i].equals(TB[j])){
                     dp[i][j] = 0;
                     cp[i][j]="";
                 }
                  else if(i == 0){
                     dp[i][j] = j;
                     cp[i][j] = cp[i][j - 1];
                     cp[i][j] += j + ",i," + TB[j] + '\n';

                 }
                 // no hay nada en el segundo string, por lo que los unicos cambios
                 // que nos faltan son los que no hemos hecho en S, es decir i
                 else if(j == 0){
                     dp[i][j] = i;
                     cp[i][j] = cp[i-1][j];
                     cp[i][j] += i+",d"+'\n';

                 }
                 // si son los mismo caracteres no debemos hacer ningun cambio
                 else if(TA[i].equals(TB[j])){
                     dp[i][j] = dp[i-1][j-1];
                     cp[i][j]=cp[i-1][j-1];
                     // no debemos hacer cambios, pero puede ser que ya hayamos
                     // hecho cambios antes de llegar a este punto,
                     //por lo que debenos considerar las operaciones anteriores
                 }

                 // cualquier otro caso, debemos si o si hacer una operacion
                 else{
                     dp[i][j] = 1 +  Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1]);
                     // debemos encontrar el minimo de las posibilidades que tenmos
                     if (Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1])==dp[i][j-1]){
                          cp[i][j]=cp[i][j-1]+j+",i,"+TB[j]+'\n';
                     }
                     else if (Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1])==dp[i-1][j]){
                         cp[i][j]=cp[i-1][j]+i+",d\n";
                     }
                     else{
                         cp[i][j]=cp[i-1][j-1]+i+",c,"+TB[j]+'\n';
                     }
                 }
             }

         }

         return cp[m-1][n-1];
     }

    public static void main(String[] args)  throws FileNotFoundException, IOException {

        Scanner scanner = new Scanner(System.in);

        String filename1 = scanner.nextLine();
        String filename2 = scanner.nextLine();

        File file1 = new File(filename1); //filename es el nombre del archivo
        File file2 = new File(filename2); //filename es el nombre del archivo

            Scanner A = new Scanner(file1);
            Scanner B = new Scanner(file2);

            int countB = 0;
            String lineasb= "";
            while (B.hasNextLine()) {
                lineasb +="-"+B.nextLine();
                countB+=1;
            }


            int countA = 0;
            String lineasa= "";
            while (A.hasNextLine()) {
                lineasa +="-"+A.nextLine();
                countA+=1;
            }

            String[] TA = lineasa.split("-");

            String[] TB = lineasb.split("-");

            System.out.println(diff(TA,TB));


    }

    /*
        String largos[] = scanner.nextLine().split(" ");
        int largoA = Integer.parseInt(largos[0]);
        int largoB = Integer.parseInt(largos[1]);

        System.out.println("largoA = " + largoA + " largoB = " + largoB);

        String[] TA = new String[largoA];
        String[] TB = new String[largoB];

        for (int i = 0; i < largoA; ++i) {
            TA[i] = scanner.nextLine();
        }

        for (int i = 0; i < largoB; ++i) {
            TB[i] = scanner.nextLine();
        }

        System.out.println(diff(TA, TB));


    }*/
}


