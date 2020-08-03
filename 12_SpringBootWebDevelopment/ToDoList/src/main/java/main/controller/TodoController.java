package main.controller;

import main.EntityNotFoundException;
import main.dto.TodoDTO;
import main.mappers.TodoMapper;
import main.models.Todo;
import main.serv.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/api")
public class TodoController {

    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/todo/")
    public List<TodoDTO> list() {
        List<TodoDTO> collect = taskService.getListOfItems().stream().map(TodoMapper::map).collect(toList());
        return collect;
    }


    @PostMapping("/todo/")
    public long add(@Valid TodoDTO todo) {
        return taskService.addTodo(TodoMapper.convert(todo));
    }

    @GetMapping("/todo/{id}")
    public TodoDTO get(@PathVariable long id) throws EntityNotFoundException {
        TodoDTO todoDTO = TodoMapper.map(taskService.getTodo(id));
        return todoDTO;
    }

    @DeleteMapping("/todo/{id}")
    public String delete(@PathVariable long id) throws EntityNotFoundException {
        return taskService.deleteTodo(id);
    }

    @PutMapping("/todo/{id}")
    public String update(@PathVariable long id, TodoDTO todo) throws EntityNotFoundException {
        Todo todoConvert = TodoMapper.convert(todo);
        return taskService.updateTodo(id, todoConvert);
    }

    @DeleteMapping("/todo/")
    public String deleteAll() {
        return taskService.deleteAllTodo();
    }
}
