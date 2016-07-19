package tw.thoughtpos.register;

public class ConsolePrinter implements IPrinter {
    @Override
    public void print(String content) {
        System.out.print(content);
    }
}
