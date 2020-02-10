public class Main {

    public static void main(String[] args) {
        String [][] text = {{"x     x"},{" x   x "},{"  x x  "}, {"   x   "},{"  x x  "},{" x   x "},{"x     x"}};
        for (int i = 0;i<text.length;i++){
            for (int j=0;j<text[i].length;j++){
                System.out.println(text[i][j]);
            }
        }
    }
}
