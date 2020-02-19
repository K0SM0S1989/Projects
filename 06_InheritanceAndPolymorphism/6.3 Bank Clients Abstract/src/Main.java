import bank.Entity;
import bank.Individual;
import bank.IndividualEntrepreneur;

public class Main {

    public static void main(String[] args) {
        Individual individual = new Individual(5000);
        Entity entity = new Entity(7000);
        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur(6000);

        individual.replenishment(300);
        individual.withdrawal(500);
        individual.withdrawal(5500);

        entity.withdrawal(1000);
        entity.withdrawal(11000);
        entity.replenishment(15000);


        individualEntrepreneur.replenishment(1500);
        individualEntrepreneur.replenishment(150);
        individualEntrepreneur.withdrawal(5000);


        System.out.println(entity.getPaymentAccount());
        System.out.println(individualEntrepreneur.getPaymentAccount());
        System.out.println(individual.getPaymentAccount());


    }
}
