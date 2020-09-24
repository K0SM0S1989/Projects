import app.Application;
import commands.*;

import java.util.ArrayList;
import java.util.List;


public class CommandExecutor {
    private final Application application = new Application();


    public CommandExecutor() {
        application.init();
    }


    public void execute(String command) {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new AddShop());
        commandList.add(new AddProduct());
        commandList.add(new DisplayProduct());
        commandList.add(new ProductStatistics());

        List<String> firstCom = new ArrayList<>();
        commandList.forEach(com -> firstCom.add(com.getCommand()));

        String[] commands = command.split(" ");
        if (firstCom.contains(commands[0])){
            commandList.forEach(com -> {
                if (commands[0].equals(com.getCommand())) {
                    com.parceCommand(command, application);
                }
            });
        }else System.out.println("Неправильная команда, попробуйте снова");
    }
}
