package main.controller;

import main.models.Todo;
import main.serv.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {

    final TaskService taskService;

    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public String webMethod(Model model) {
        Iterable<Todo> todoIterable = taskService.getListOfItems();
        model.addAttribute("Todo",todoIterable);
        return "index";
    }

}
