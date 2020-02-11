import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Для определения размера квадратной матрицы \nвведите целое число:");
        Scanner scanner = new Scanner(System.in);
       try {
           int inputData = scanner.nextInt();

           String space = " ";
           String x = "x";

           String [][] text = new String[inputData][inputData];
           int k = text.length-1;
           for (int i = 0; i<text.length; i++){
               text[i][i]=x;
               text[k][i]=x;
               k--;
           }
           for (int i = 0;i<text.length;i++){
               for (int j=0;j<text[i].length;j++){
                   if (text[i][j]==null){
                       text[i][j]=space;
                   }
                   System.out.print(text[i][j]+"\t");
               }
               System.out.println();
           }
       }catch (Exception e){
           System.out.println("Введен неверный символ");
       }

    }
}
