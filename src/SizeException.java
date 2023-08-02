public class SizeException extends RuntimeException{
    public SizeException (int size) {
        super("Вы ввели неподходящее количество данных: " +size+ ", введите заново");
    }
}
