package main.controller;

import main.dto.TodoDTO;
import main.mappers.TodoMapper;
import main.models.Todo;
import main.serv.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class DefaultController {

    final TaskService taskService;

    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public String webMethod(Model model) {
        Iterable<Todo> todoIterable = taskService.getListOfItems();
        model.addAttribute("Todo", todoIterable);
        return "index";
    }

    @PostMapping("/")
    public String add(@RequestParam @Valid String todo, Model model) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTodoString(todo);
        taskService.addTodo(TodoMapper.convert(todoDTO));
        return "index";
    }

}
