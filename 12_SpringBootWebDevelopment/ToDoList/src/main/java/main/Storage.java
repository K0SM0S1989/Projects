package main;

import main.models.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Storage {
    private static  AtomicLong currentId = new AtomicLong(1);

    private static ConcurrentHashMap<Long, Todo> todoList = new ConcurrentHashMap<>();


    public static List<Todo> allTodo() {
        ArrayList<Todo> list = new ArrayList<>();
        list.addAll(todoList.values());
        return list;
    }

    public static long addTodo(Todo todo) {
        long id = currentId.get();
        todo.setId(id);
        todoList.put(currentId.get(), todo);
        currentId.incrementAndGet();
        return id;
    }

    public static Todo getTodo(long todoId) {
        if (todoList.containsKey(todoId)) {
            return todoList.get(todoId);
        } else return null;
    }

    public static String deleteTodo(long todoId) {
        todoList.remove(todoId);
        return "Дело № " + todoId + " удалено!";
    }

    public static String updateTodo(long todoId, Todo todo) {
        if (!todoList.containsKey(todoId)) {
            return null;
        } else {
            todo.setId(todoId);
            todoList.put(todoId, todo);
            return "Дело № " + todoId + " изменено!";
        }
    }

    public static String deleteAllTodo() {
        todoList.clear();
        return "Список дел очищен";
    }

}
