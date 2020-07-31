package main.serv;

import main.EntityNotFoundException;
import main.models.Todo;
import main.repo.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    private final TodoRepository todoRepository;

    public TaskServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public Iterable<Todo> allTodo() {
        Iterable<Todo> todoIterable = todoRepository.findAll();
        return todoIterable;
    }

    public long addTodo(Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return newTodo.getId();
    }

    public Todo getTodo(long todoId) throws EntityNotFoundException {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> EntityNotFoundException.createWith(todoId, "Дело №" + todoId + " не найдено"));
        return todo;
    }

    public String deleteTodo(long todoId) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (!optionalTodo.isPresent()) {
            throw EntityNotFoundException.createWith(todoId, "Дело №" + todoId + " не найдено");
        } else todoRepository.deleteById(todoId);
        return "Дело №" + todoId + " удалено из базы";
    }

    public String updateTodo(long todoId, Todo todo) throws EntityNotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (!todoOptional.isPresent()) {
            throw EntityNotFoundException.createWith(todoId, "Дело №" + todoId + " не найдено");
        } else {
            todoOptional.get().setTodoString(todo.getTodoString());
            todoRepository.save(todoOptional.get());
        }
        return "Дело №" + todoId + " изменено";
    }

    public String deleteAllTodo() {
        todoRepository.deleteAll();
        return "База очищена";
    }
}
