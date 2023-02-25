import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    // define a process class to hold process information
    private static class Process {
        String name;
        int burstTime;
        int remainingTime;

        Process(String name, int burstTime) {
            this.name = name;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
        }
    }

    // define the Round Robin scheduling function
    public static void roundRobin(Process[] processes, int quantum) {
        Queue<Process> queue = new LinkedList<>();
        int n = processes.length;
        int time = 0;
        for (int i = 0; i < n; i++) {
            queue.add(processes[i]);
        }
        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            System.out.println("Executing " + currentProcess.name + " at time " + time);
            if (currentProcess.remainingTime > quantum) {
                currentProcess.remainingTime -= quantum;
                time += quantum;
                queue.add(currentProcess);
            } else {
                time += currentProcess.remainingTime;
                currentProcess.remainingTime = 0;
                System.out.println(currentProcess.name + " completed at time " + time);
            }
            if (queue.stream().anyMatch(process -> process.remainingTime > 0)) {
                continue;
            } else {
                break;
            }
        }
    }

    // example usage of the function
    public static void main(String[] args) {
        Process[] processes = {new Process("P1", 10), new Process("P2", 5), new Process("P3", 8)};
        int quantum = 2;
        roundRobin(processes, quantum);
    }
}