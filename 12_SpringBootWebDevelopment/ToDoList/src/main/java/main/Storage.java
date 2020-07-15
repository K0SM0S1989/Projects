package main;

import response.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static int currentId = 1;
    private static ConcurrentHashMap<Integer, Todo> todoList = new ConcurrentHashMap<>();


    public static List<Todo> allTodo() {
        ArrayList<Todo> list = new ArrayList<>();
        list.addAll(todoList.values());
        return list;
    }

    public static int addTodo(Todo todo) {
        int id = currentId;
        todo.setId(id);
        todoList.put(currentId, todo);
        currentId++;
        return id;
    }

    public static Todo getTodo(int todoId) {
        if (todoList.containsKey(todoId)) {
            return todoList.get(todoId);
        } else return null;
    }

    public static String deleteTodo(int todoId) {
        todoList.remove(todoId);
        return "Дело № " + todoId + " удалено!";
    }

    public static String updateTodo(int todoId, Todo todo) {
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
