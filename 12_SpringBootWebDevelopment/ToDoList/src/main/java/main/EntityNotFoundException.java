package main;

public class EntityNotFoundException extends Exception{
    private long todoId;
    public static EntityNotFoundException createWith(long todoId) {
        return new EntityNotFoundException(todoId);
    }

    public EntityNotFoundException(long todoId) {
        this.todoId = todoId;
    }

    @Override
    public String getMessage() {
        return "Дело № '" + todoId + "' не найдено";
    }
}
