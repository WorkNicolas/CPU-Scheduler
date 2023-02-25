import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Process {
    private int bt; //burst time
    private int pid; //process id
    private int at; //arrival time
    private int ft; //finishing time
    private int tat; //turnaround time
    private int wt; //waiting time
    private int pr; //priority
    Process (int pid) {
        this.bt = timeRandomizer();
        this.at = timeRandomizer();
        this.pid = pid;
        this.wt = 0;
    }
    public int timeRandomizer() {
        final int MAX = 14;
        final int MIN = 1;
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
    public int getFt() { return ft; }
    public int getWt() { return wt; }
    public int getTat() { return tat; }
    public int getPr() {
        return pr;
    }
    public void calculateFinishingTime(int bt_param, int at_param) {
        //finishing time = burst time + arrival time
        ft = bt_param + at_param;
    }
    public void calculateWaitingTimeFCFS(boolean burst, int wt_param, int bt_param) {
        //Waiting time = 0 for the first process, and for subsequent processes, it is the sum of the burst times of all previous processes
        if (burst) {
            wt_param += bt_param;
            wt = wt_param;
        } else {
            wt = 0;
        }
    }
    public void calculateTurnAroundTime(int bt_param, int wt_param) {
        //TurnaroundTime = Burst Time + Waiting Time
        tat = bt_param + wt_param;
    }
    public void startCalculations(boolean burst, int prev_wt, int prev_bt) {
        calculateFinishingTime(bt, at);
        calculateWaitingTimeFCFS(burst, prev_wt, prev_bt);
        calculateTurnAroundTime(bt, wt);
    }
    public void setPriority(int pr_param) {
        pr = pr_param;
    }
    @Override
    public String toString() {
        return "|P" + pid + "|";
    }
}
