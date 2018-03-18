package pl.javastart.exercise.mockito;

public class NoMoney extends RuntimeException {
    public NoMoney() {
        System.out.println("Masz zbyt mało pieniędzy");
    }
}
