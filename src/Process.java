import java.util.Random;
public class Process {
    private int bt; //burst time
    private int pid; //process id
    private int at; //arrival time
    Process (int pid) {
        this.bt = timeRandomizer();
        this.at = timeRandomizer();
        this.pid = pid;
    }
    public int timeRandomizer() {
        int MAX = 30;
        int MIN = 1;
        double rand = (Math.random() * (MAX - MIN + 1) + MIN);
        return (int) rand;
    }
    public int getPid() {
        return pid;
    }
    public int getBt() {
        return bt;
    }
    public int getAt() {
        return at;
    }
}
