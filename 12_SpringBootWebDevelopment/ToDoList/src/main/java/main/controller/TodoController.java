package main.controller;

import main.EntityNotFoundException;
import main.models.Todo;
import main.serv.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TodoController {

    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

//
//    final TodoRepository todoRepository;
//
//    public TodoController(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }
//
//
//
//    @GetMapping("/todo/")
//    public Iterable<Todo> list() {
//
//        Iterable<Todo> todoIterable = todoRepository.findAll();
//        return todoIterable;
//    }
//
//    @PostMapping("/todo/")
//    public long add(Todo todo) {
//        Todo newTodo = todoRepository.save(todo);
//        return newTodo.getId();
//    }
//
//    @GetMapping("/todo/{id}")
//    public Todo get(@PathVariable long id) throws EntityNotFoundException {
//        Todo todo = todoRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено"));
//        return todo;
//    }
//
//    @DeleteMapping("/todo/{id}")
//    public String delete(@PathVariable long id) throws EntityNotFoundException {
//        Optional<Todo> optionalTodo = todoRepository.findById(id);
//        if (!optionalTodo.isPresent()) {
//            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
//        } else todoRepository.deleteById(id);
//        return "Дело №" + id + " удалено из базы";
//    }
//
//    @PutMapping("/todo/{id}")
//    public String update(@PathVariable long id, Todo todo) throws EntityNotFoundException {
//        Optional<Todo> todoOptional = todoRepository.findById(id);
//        if (!todoOptional.isPresent()) {
//            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
//        } else {
//            todoOptional.get().setTodoString(todo.getTodoString());
//            todoRepository.save(todoOptional.get());
//        }
//        return "Дело №" + id + " изменено";
//    }
//
//    @DeleteMapping("/todo/")
//    public String deleteAll() {
//        todoRepository.deleteAll();
//        return "База очищена";
//    }



    //=============================================================================


    @GetMapping("/todo/")
    public Iterable<Todo> list() {

       // Iterable<Todo> todoIterable = taskService.allTodo();
        return taskService.allTodo();
    }

    @PostMapping("/todo/")
    public long add(Todo todo) {
       // Todo newTodo = taskService.addTodo(todo);
        return taskService.addTodo(todo);
    }

    @GetMapping("/todo/{id}")
    public Todo get(@PathVariable long id) throws EntityNotFoundException {
       // Todo todo = todoRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено"));
        return taskService.getTodo(id);
    }

    @DeleteMapping("/todo/{id}")
    public String delete(@PathVariable long id) throws EntityNotFoundException {
//        Optional<Todo> optionalTodo = todoRepository.findById(id);
//        if (!optionalTodo.isPresent()) {
//            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
//        } else todoRepository.deleteById(id);
        return taskService.deleteTodo(id);
    }

    @PutMapping("/todo/{id}")
    public String update(@PathVariable long id, Todo todo) throws EntityNotFoundException {

        return taskService.updateTodo(id, todo);
    }

    @DeleteMapping("/todo/")
    public String deleteAll() {
        return taskService.deleteAllTodo();
    }




}
