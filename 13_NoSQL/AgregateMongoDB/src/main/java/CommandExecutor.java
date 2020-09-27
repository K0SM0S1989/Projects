import app.Application;
import commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CommandExecutor {
    private final Application application = new Application();
    private final List<Command> commandList = new ArrayList<>();

    public CommandExecutor() {
        String host = "127.0.0.1";
        int port = 27017;
        application.init(host, port);

        commandList.add(new AddShop());
        commandList.add(new AddProduct());
        commandList.add(new DisplayProduct());
        commandList.add(new ProductStatistics());
    }
    public void execute(String command) {
        String[] commands = command.split(" ");
        AtomicInteger i = new AtomicInteger();
        commandList.forEach(com -> {
            if (commands[0].equals(com.getCommand())) {
                com.parceCommand(command, application);
            } else i.getAndIncrement();
        });
        if (i.get() == commandList.size()) {
            System.out.println("Неправильная команда, попробуйте снова");
        }
    }
}
