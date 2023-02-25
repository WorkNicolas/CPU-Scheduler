import java.util.*;

public class Schedule extends Thread {
    int id;
    Process[] p_array;
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
            p[i] = createProcess(i+1);
        }

        //CPU Scheduling Menu
        while (!exit) {
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
                    fcfs(p);
                    exit = true;
                    break;
                case 2:
                    System.out.println("Input: Shortest-Job First");
                    sjf(p);
                    exit = true;
                    break;
                case 3:
                    System.out.println("Input: Shortest Remaining Time");
                    break;
                case 4:
                    System.out.println("Input: Priority Scheduling");
                    prioritySched(p);
                    exit = true;
                    break;
                case 5:
                    System.out.println("Input: Round-Robin Scheduling");
                    roundRobin(p);
                    exit = true;
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
        displayProcessInfo(p, false);
        displayGanttChart(p);
    }
    public void sjf(Process[] p) {
        ArrayList<Process> p_al = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            p_al.add(p[i]);
        }
        Collections.sort(p_al, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return Integer.compare(p1.getBt(), p2.getBt());
            }
        });
        for (int i = 0; i < p.length; i++) {
            p[i] = p_al.get(i);
        }
        displayProcessInfo(p, false);
        displayGanttChart(p);
    }
    public void prioritySched(Process[] p) {
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            intList.add(i + 1);
        }
        Collections.shuffle(intList);
        for(int i = 0; i < p.length; i++) {
            p[i].setPriority(intList.get(i));
        }
        ArrayList<Process> p_al = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            p_al.add(p[i]);
        }
        Collections.sort(p_al, new Comparator<Process>() {
            public int compare(Process p2, Process p1) {
                return Integer.compare(p1.getPr(), p2.getPr());
            }
        });
        for (int i = 0; i < p.length; i++) {
            p[i] = p_al.get(i);
        }
        displayProcessInfo(p, true);
        displayGanttChart(p);
    }
    public void roundRobin(Process[] p) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Quantum Slice: ");
        int qua = Integer.valueOf(sc.nextLine());
        ArrayList<Process> rq = new ArrayList<>();
        ArrayList<Process> completedProcess = new ArrayList<>();
        int currentTime = 0;
        int totalBurstTime = 0;

        //Sorter
        Arrays.sort(p, Comparator.comparingInt(Process::getAt));

        //First Process
        rq.add(p[0]);

        //Loop until all processes are compelte
        while (!rq.isEmpty() || currentTime < totalBurstTime) {
            Process currentProcess = rq.get(0);
            rq.remove(0);

            //Execute the current process for a quantum slice
            int remainingTime = currentProcess.getRt();
            if (remainingTime > qua) {
                currentProcess.setRemainingTime(remainingTime - qua);
                currentTime += qua;
            } else {
                currentTime += remainingTime;
                currentProcess.setRemainingTime(0);
                currentProcess.setTurnaroundTime(currentTime - currentProcess.getAt());
                completedProcess.add(currentProcess);
            }

            //Add new processes that have arrived
            for (Process process : p) {
                if (process.getAt() <= currentTime && !rq.contains(process) && !completedProcess.contains(process)) {
                    rq.add(process);
                }
            }

            // Add the current process back to the end of the ready queue if it still has remaining time
            if (currentProcess.getRt() > 0) {
                rq.add(currentProcess);
            }
        }
        displayProcessInfo(p, false);
        displayGanttChartQueue(p);
    }
    public void displayGanttChart(Process[] p) {
        ANSI_Colors color = new ANSI_Colors();

        System.out.println("Gantt Chart");
        int rand, tempRand = -1;
        for (int i = 0; i < p.length; i++) {

            //Color Randomizer
            do {
                rand = color.colorBackgroundRandomizer();
            } while (rand == tempRand);

            tempRand = rand;
            for (int j = 0; j <= p[i].getBt(); j++) {
                //Display Loop
                String processColor = color.COLOR_BG_ARRAY[rand];
                System.out.print(processColor + p[i].toString());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    public void displayGanttChartQueue(Process[] p) {
        ANSI_Colors color = new ANSI_Colors();

        System.out.println("\n\nGantt Chart");
        int rand, tempRand = -1;
        for (int i = 0; i < p.length; i++) {

            //Color Randomizer
            do {
                rand = color.colorBackgroundRandomizer();
            } while (rand == tempRand);

            tempRand = rand;
            for (int j = 0; j <= p[i].getBt(); j++) {
                //Display Loop
                String processColor = color.COLOR_BG_ARRAY[rand];
                System.out.print(processColor + p[i].toString());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    public void displayProcessInfo(Process[] p, boolean prioritized) {
        //Process Info
        System.out.print("Process\tArrival Time\tBurst Time\tFinishing Time\tTurnaround Time\tWaiting Time");
        if (prioritized) {
            System.out.print("\tPriority");
        }
        System.out.println();
        for (int i = 0; i < p.length; i++) {
            if (i > 0) {
                p[i] .startCalculations(true, p[i - 1].getWt(), p[i - 1].getBt());
            } else {
                p[i].startCalculations(false, 0, 0);
            }
            System.out.print(p[i].toString() + "\t" + p[i].getAt() + "\t\t\t\t" + p[i].getBt() + "\t\t\t" + p[i].getFt() + "\t\t\t\t" + p[i].getTat() + "\t\t\t\t" + p[i].getWt());
            if (prioritized) {
                System.out.print("\t\t\t\t" + p[i].getPr());
            }
            System.out.println();
        }
        //Mean
        //Calculate Waiting Time Mean
        float wt_mean = 0;
        for (int i = 0; i < p.length; i++) {
            wt_mean += p[i].getWt();
        }
        wt_mean /= p.length;
        System.out.printf(Locale.US, "Average Waiting Time: %.2f\n", wt_mean);

        //Calculate Turnaround Time Mean
        float tat_mean = 0;
        for (int i = 0; i < p.length; i++) {
            tat_mean += p[i].getTat();
        }
        tat_mean /= p.length;
        System.out.printf(Locale.US, "Average Turnaround Time: %.2f\n", tat_mean);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {

        }
    }
}
