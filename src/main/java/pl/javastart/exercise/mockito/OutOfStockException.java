package pl.javastart.exercise.mockito;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        System.out.println("Nie ma takiego produktu");
    }
}
