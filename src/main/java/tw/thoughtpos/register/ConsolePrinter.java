package tw.thoughtpos.register;

public class ConsolePrinter implements PosPrinter {
    @Override
    public void print(String content) {
        System.out.print(content);
    }
}
