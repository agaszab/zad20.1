package pl.javastart.exercise.mockito;

public class Illegal extends RuntimeException {
    public Illegal() {

        System.out.println("Nie mamy w sprzedaży nielegalnych produktów!");
    }
}