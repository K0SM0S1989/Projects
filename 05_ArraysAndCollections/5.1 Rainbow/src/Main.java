public class Main {

    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colors = text.split(",?\\s+");
        for (int i = 0; i<colors.length; i++){
            System.out.println(colors[i]);
        }
        String temp;
        for (int i = 0; i < colors.length/2; i++) {
            temp = colors[colors.length-i-1];
            colors[colors.length-i-1] = colors[i];
            colors[i] = temp;
        }
        System.out.println("-------------");
        for (int i=0; i<colors.length; i++){
            System.out.print(colors[i]+"\n");
        }

    }
}
