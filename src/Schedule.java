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
                    srt(p);
                    exit = true;
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
                    mlqs(p);
                    exit = true;
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
    public void srt(Process[] p) {
        //Sorter: Arrival Time
        Arrays.sort(p, Comparator.comparing(Process::getAt));

        //Priority Queue to keep track of processes with shortest remaining time
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparing(Process::getRt).thenComparing(Process::getAt));

        //Initialize Variables
        int time = 0;
        int completed = 0;

        //Loop until all processes are completed
        while (completed < p.length) {
            //add newly arrived processes to the priority queue
            while (completed < p.length && p[completed].getAt() <= time) {
                pq.offer(p[completed]);
                completed++;
            }

            if (!pq.isEmpty()) {
                //get process with shortest remaining time
                Process curr = pq.poll();

                //Execute process for 1 unit of time
                curr.setRemainingTime(curr.getRt() - 1);

                //Update waiting time and remaining time of all processes
                for (Process process : pq) {
                    process.setTurnaroundTime(process.getTat() + 1);
                    process.setRemainingTime(process.getRt() - 1);
                }

                //Check if current process has completed
                if (curr.getRt() == 0) {
                    curr.calculateTurnAroundTime(time + 1 - curr.getAt(), curr.getWt());
                    curr.calculateWaitingTimeFCFS(false, curr.getWt(), curr.getBt());
                    curr.calculateFinishingTime(time + 1, curr.getAt());
                } else {
                    // Process not completed, add it back to the priority queue
                    pq.offer(curr);
                }
            }
            //Update time
            time++;
        }
        //Calculations
        double totalTat = 0;
        double totalWt = 0;
        for (int i = 0; i < p.length; i++) {
            totalTat = p[i].getTat();
            totalWt = p[i].getWt();
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

        //Sorter: Arrival Time
        ArrayList<Process> p_al = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            p_al.add(p[i]);
        }
        Collections.sort(p_al, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return Integer.compare(p1.getBt(), p2.getBt());
            }
        });
        Process[] p_1 = new Process[p.length];
        for (int i = 0; i < p.length; i++) {
            p_1[i] = p_al.get(i);
        }
        Queue<Process> rq = new LinkedList<>();
        //Round-Robin
        int index = 0;
        boolean trueBreak = true;
        while (trueBreak) {
            //Quantum Splicing
            if (p[index].getRt() > 0) {
                for (int i = 0; i < qua; i++) {
                    //Stops subtracting if rt <= 0
                    if (p[index].getRt() > 0) {
                        p[index].setRemainingTime(p[index].getRt() - 1);
                        rq.add(p[index]);
                    }
                }
            }
            //checks if all rt <= 0
            if (index == p.length - 1) {
                for (int i = 0; i < p.length; i++) {
                    if (p[index].getRt() <= 0) {
                        trueBreak = false;
                    } else {
                        trueBreak = true;
                        index = 0;
                    }
                }
            }
            index++;
        }

        displayProcessInfo(p, false);
        displayGanttChartQueue(rq);
    }
    public void mlqs(Process[] p) {
        Queue<Process> fcfsQueue = new LinkedList<>();
        Queue<Process> priorityQueue = new LinkedList<>();
        //SJF Queue
        ArrayList<Process> p_al = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            p_al.add(p[i]);
        }
        Collections.sort(p_al, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return Integer.compare(p1.getBt(), p2.getBt());
            }
        });
        //FCFS Queue
        for (int i = 0; i < p.length; i++) {
            fcfsQueue.add(p_al.get(i));
        }
        //Priority Scheduling
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            intList.add(i + 1);
        }
        Collections.shuffle(intList);
        for(int i = 0; i < p.length; i++) {
            p[i].setPriority(intList.get(i));
        }
        p_al = new ArrayList<>();
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
    public void displayGanttChartQueue(Queue<Process> rq) {
        ANSI_Colors color = new ANSI_Colors();
        for (int i = 0; i < rq.size(); i++) {
            //Display
            System.out.print(rq.peek().toString());
            rq.poll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
    public void displayProcessInfo(Process[] p, boolean prioritized) {
        //Process Info
        System.out.print("Process\tArrival Time\tBurst Time\tFinishing Time\tTurnaround Time\tWaiting Time\tResponse Time");
        if (prioritized) {
            System.out.print("\tPriority");
        }
        System.out.println();
        for (int i = 0; i < p.length; i++) {
            if (i > 0) {
                p[i].startCalculations(true, p[i - 1].getWt(), p[i - 1].getBt());
            } else {
                p[i].startCalculations(false, 0, 0);
            }
            System.out.print("|P" + p[i].getPid() + "|" + "\t" + p[i].getAt() + "\t\t\t\t" + p[i].getBt() + "\t\t\t" + p[i].getFt() + "\t\t\t\t" + p[i].getTat() + "\t\t\t\t" + p[i].getWt() + "\t\t\t\t" + Math.abs(p[i].getFt() - p[i].getAt()));
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
        System.out.printf(Locale.US, "Average Waiting Time: %.2f ms\n", wt_mean);

        //Calculate Turnaround Time Mean
        float tat_mean = 0;
        for (int i = 0; i < p.length; i++) {
            tat_mean += p[i].getTat();
        }
        tat_mean /= p.length;
        System.out.printf(Locale.US, "Average Turnaround Time: %.2f ms\n", tat_mean);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {

        }
    }
}
