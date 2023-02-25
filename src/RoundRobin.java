

import java.util.*;
public class RoundRobin {

    public static void main(String[] args){
        int nProcess,i,qt,count=0,temp,sq=0,BurstTime[],WaitingTime[],TurnAroundTime[],rem_BurstTime[];
        float AverageWaitingTime=0,AverageTurnAroundTime=0;

        BurstTime = new int[10];
        WaitingTime = new int[10];
        TurnAroundTime = new int[10];
        rem_BurstTime = new int[10];
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the number of process (maximum 10) = ");
        nProcess = s.nextInt();
        System.out.print("Enter the burst time of the process\n");
        for (i=0;i<nProcess;i++)
        {
            System.out.print("P"+i+" = ");
            BurstTime[i] = s.nextInt();
            rem_BurstTime[i] = BurstTime[i];
        }
        System.out.print("Enter the quantum time: ");
        qt = s.nextInt();
        while(true)
        {
            for (i=0,count=0;i<nProcess;i++)
            {
                temp = qt;
                if(rem_BurstTime[i] == 0)
                {
                    count++;
                    continue;
                }
                if(rem_BurstTime[i]>qt)
                    rem_BurstTime[i]= rem_BurstTime[i] - qt;
                else
                if(rem_BurstTime[i]>=0)
                {
                    temp = rem_BurstTime[i];
                    rem_BurstTime[i] = 0;
                }
                sq += temp;
                TurnAroundTime[i] = sq;
            }
            if(nProcess == count)
                break;
        }
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
        System.out.print("--------------------------------------------------------------------------------");
        for(i=0;i<nProcess;i++)
        {
            WaitingTime[i]=TurnAroundTime[i]-BurstTime[i];
            AverageWaitingTime=AverageWaitingTime+WaitingTime[i];
            AverageTurnAroundTime=AverageTurnAroundTime+TurnAroundTime[i];
            System.out.print("\n "+(i+1)+"\t\t "+BurstTime[i]+"\t\t\t "+TurnAroundTime[i]+"\t\t\t\t"+WaitingTime[i]+"\n");
        }
        System.out.println("Gantt Chart: ");
        for(i = 0; i < nProcess; i++) {
            System.out.print("P" +i+" ");
        }
        AverageWaitingTime=AverageWaitingTime/nProcess;
        AverageTurnAroundTime=AverageTurnAroundTime/nProcess;
        System.out.println("\nAverage waiting Time = "+AverageWaitingTime+"s"+"\n");
        System.out.println("Average turnaround time = "+AverageTurnAroundTime+"s");


    }
}
