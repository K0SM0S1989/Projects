package commands;

import app.Application;

public class AddShop implements Command{

    @Override
    public String getCommand() {
        return "ДОБАВИТЬ_МАГАЗИН";
    }

    @Override
    public void parceCommand(String command, Application application) {
     application.addShop(command);
    }
}
