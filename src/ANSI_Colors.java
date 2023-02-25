import java.util.Random;
public class ANSI_Colors {
    //Regular
    final String BLACK = "\u001b[30m";
    final String RED = "\u001b[31m";
    final String GREEN = "\u001b[32m";
    final String YELLOW = "\u001B[93m";
    final String BLUE = "\u001b[34m";
    final String MAGENTA = "\u001b[35m";
    final String CYAN = "\u001b[36m";
    final String WHITE = "\u001b[37m";
    final String[] COLOR_ARRAY = {BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE};
    //BG_Colors
    final String BLACK_BG = "\u001b[40m";
    final String RED_BG = "\u001b[41m";
    final String GREEN_BG = "\u001b[42m";
    final String YELLOW_BG = "\u001b[43m";
    final String BLUE_BG = "\u001b[44m";
    final String MAGENTA_BG = "\u001b[45m";
    final String CYAN_BG = "\u001b[46m";
    final String WHITE_BG = "\u001b[47m";
    final String[] COLOR_BG_ARRAY = {BLACK_BG, RED_BG, GREEN_BG, YELLOW_BG, BLUE_BG, MAGENTA_BG, CYAN_BG};
    public String colorRandomizer() {
        final int MAX = COLOR_ARRAY.length - 1;
        final int MIN = 0;
        double rand = (Math.random() * (MAX - MIN + 1) + MIN);
        return COLOR_ARRAY[(int) rand];
    }
    public String colorBackgroundRandomizer() {
        final int MAX = COLOR_BG_ARRAY.length - 1;
        final int MIN = 0;
        double rand = (Math.random() * (MAX - MIN + 1) + MIN);
        return COLOR_BG_ARRAY[(int) rand];
    }
}
