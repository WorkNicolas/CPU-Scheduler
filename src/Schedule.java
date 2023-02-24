import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class Schedule extends Thread {
    int id;
    Process[] p_array;
    Queue<Process> rq = new LinkedList<>(); //ready queue
    Schedule() {

    }
    public Process createProcess(int id) {
        Process p = new Process(id);
        return p;
    }
    //scanner
    public void createSchedule() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        Process[] p;

        //Initialize processes
        while (true) {
            System.out.print("Number of Processes: ");
            int input = Integer.valueOf(sc.nextLine());
            if (input > 0) {
                p = new Process[input];
                break;
            } else {
                System.out.println("Input must be greater than 0");
            }
        }

        for (int i = 0; i < p.length; i++) {
            p[i] = createProcess(i);
        }

        //CPU Scheduling Menu
        while (exit) {
            System.out.println("Creating new CPU schedule");
            System.out.println("Choose CPU schedule");
            System.out.println("[1] First-Come-First-Serve");
            System.out.println("[2] Shortest-Job First");
            System.out.println("[3] Shortest Remaining Time");
            System.out.println("[4] Priority Scheduling");
            System.out.println("[5] Round-Robin Scheduling");
            System.out.println("[6] Multilevel Queue Scheduling");

            switch(Integer.valueOf(sc.nextLine())) {
                case 1:
                    System.out.println("Input: First-Come-First-Serve");
                    break;
                case 2:
                    System.out.println("Input: Shortest-Job First");
                    break;
                case 3:
                    System.out.println("Input: Shortest Remaining Time");
                    break;
                case 4:
                    System.out.println("Input: Priority Scheduling");
                    break;
                case 5:
                    System.out.println("Input: Round-Robin Scheduling");
                    break;
                case 6:
                    System.out.println("Input: Multilevel Queue Scheduling");
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }
    public void fcfs(Process[] p) {
        //Add process to ready queue
        for (int i = 0; i < p.length; i++) {
            rq.add(p[i]);
        }

    }
    //Parameters: process and finish time
    public int calculateWaitingTime(Process p, int ft) {
        //waiting time of a process = finish time of that process - execution time - arrival time
        int wt = ft - p.getBt() - p.getAt();
        return wt;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {

        }
    }
}
