package commands;

import app.Application;

public interface Command {

    String getCommand();

    void parceCommand(String command, Application application);
}
