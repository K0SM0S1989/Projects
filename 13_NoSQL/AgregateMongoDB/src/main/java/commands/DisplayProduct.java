package commands;


import app.Application;

public class DisplayProduct implements Command {

    @Override
    public String getCommand() {
        return "ВЫСТАВИТЬ_ТОВАР";
    }

    @Override
    public void parceCommand(String command, Application application) {
        application.displayProduct(command);
    }
}
