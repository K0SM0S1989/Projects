package commands;


import app.Application;

public class AddProduct implements Command {

    @Override
    public String getCommand() {
        return "ДОБАВИТЬ_ТОВАР";
    }

    @Override
    public void parceCommand(String command, Application application) {
       application.addProduct(command);
    }
}
