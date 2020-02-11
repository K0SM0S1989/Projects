public class Main {

    public static void main(String[] args) {

        double [] temperature = new double[30];

        for (int i = 0; i<temperature.length;i++){
            temperature[i] = Math.random()*8+32;
            temperature[i] = roundAvoid(temperature[i], 1);
            System.out.printf(" Температура %d-ого больного = %.1f",(i+1), temperature[i]);
        }

        averageTemperatures(temperature);
        healthPacients(temperature);

    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }


    public static void averageTemperatures(double[] allTemp){
        double sumTemp = 0;
        for (int i = 0; i<allTemp.length;i++){
            sumTemp += allTemp[i];
        }
        double averageTemp = sumTemp/allTemp.length;
        System.out.printf("\nСредняя температура по больнице = %.1f",averageTemp);
    }

    public static void healthPacients(double[] allTemp){
        int healthyPatientsCount = 0;
        for (int i = 0; i<allTemp.length;i++){
            if (allTemp[i]>=36.2 && allTemp[i]<=36.9){
                healthyPatientsCount += 1;
            }
        }
        System.out.println("\nКоличество здоровых пациентов = "+ healthyPatientsCount);
    }

}
