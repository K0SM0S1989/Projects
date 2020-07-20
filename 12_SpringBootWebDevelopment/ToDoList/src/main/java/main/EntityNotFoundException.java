package main;

public class EntityNotFoundException extends Exception{
    private String message;
    private long todoId;


    public static EntityNotFoundException createWith(long todoId, String message) {
        return new EntityNotFoundException(todoId, message);
    }

    public EntityNotFoundException(long todoId, String message) {
        this.todoId = todoId;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
