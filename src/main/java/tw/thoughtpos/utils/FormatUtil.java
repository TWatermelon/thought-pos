package tw.thoughtpos.utils;


import java.text.DecimalFormat;

public class FormatUtil {
    private static final String FORMAT_CHAR = "0.00";
    public static String format(double num) {
        return  new DecimalFormat(FORMAT_CHAR).format(num);
    }
}
