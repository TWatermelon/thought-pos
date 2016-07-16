package tw.thoughtpos.register;

public class ConsolePrinter implements PosPrinter {
    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
