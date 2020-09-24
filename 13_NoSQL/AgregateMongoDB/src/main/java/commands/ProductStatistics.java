package commands;


import app.Application;

public class ProductStatistics implements Command {

    @Override
    public String getCommand() {
        return "СТАТИСТИКА_ТОВАРОВ";
    }

    @Override
    public void parceCommand(String command, Application application) {
        System.out.println(application.productStatistics());
    }
}
