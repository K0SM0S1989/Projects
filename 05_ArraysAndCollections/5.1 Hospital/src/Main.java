public class Main {

    public static void main(String[] args) {

        double [] temperature = new double[30];
        double sumTemp = 0;
        int healthyPatientsCount = 0;
        for (int i = 0; i<temperature.length;i++){
            temperature[i] = Math.random()*8+32;
            temperature[i] = roundAvoid(temperature[i], 1);
            if (temperature[i]>=36.2 && temperature[i]<=36.9){
                healthyPatientsCount += 1;
            }
            System.out.printf(" Температура %d-ого больного = %.1f",(i+1), temperature[i]);
            sumTemp += temperature[i];
        }
        double averageTemp = sumTemp/temperature.length;
        System.out.printf("\nСредняя температура по больнице = %.1f",averageTemp);
        System.out.println("\nКоличество здоровых пациентов = "+ healthyPatientsCount);
    }
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
