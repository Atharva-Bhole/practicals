import java.util.Scanner;

public class Roundrobin {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int num, quantum, time = 0, remainingProcesses;
        int[] burstTime = new int[10];
        int[] remainingTime = new int[10];
        int[] waitingTime = new int[10];
        int[] turnaroundTime = new int[10];

        // Taking input for number of processes
        System.out.print("Enter number of processes (MAX 10): ");
        num = s.nextInt();
        remainingProcesses = num;

        // Input burst time for each process
        System.out.print("Enter burst time for each process:\n");
        for (int i = 0; i < num; i++) {
            System.out.print("P[" + (i + 1) + "]: ");
            burstTime[i] = s.nextInt();
            remainingTime[i] = burstTime[i]; // Initialize remaining time
            waitingTime[i] = 0; // Initialize waiting time
        }

        // Input the time quantum
        System.out.print("Enter time quantum: ");
        quantum = s.nextInt();

        System.out.print("\nGantt Chart:\n0"); // Start Gantt chart

        // Round Robin Scheduling Logic
        int i = 0;
        while (remainingProcesses != 0) {
            if (remainingTime[i] > quantum) {
                remainingTime[i] -= quantum;
                time += quantum;
                System.out.print(" | P[" + (i + 1) + "] | " + time);
            } else if (remainingTime[i] > 0) {
                time += remainingTime[i];
                remainingTime[i] = 0;
                System.out.print(" | P[" + (i + 1) + "] | " + time);
                remainingProcesses--;

                // Calculate waiting time for completed process
                waitingTime[i] = time - burstTime[i];
                turnaroundTime[i] = time;
            }
            i = (i + 1) % num; // Cycle through processes
        }

        // Calculate and display average waiting time and turnaround time
        double totalWaitingTime = 0, totalTurnaroundTime = 0;
        System.out.println("\n\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (i = 0; i < num; i++) {
            System.out.println("P[" + (i + 1) + "]\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        System.out.printf("\nAverage Waiting Time: %.2f", totalWaitingTime / num);
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTurnaroundTime / num);

        s.close();
    }
}


//output

// Enter number of processes (MAX 10): 3
// Enter burst time for each process:
// P[1]: 24
// P[2]: 3
// P[3]: 3
// Enter time quantum: 4

// Gantt Chart:
// 0 | P[1] | 4 | P[2] | 7 | P[3] | 10 | P[1] | 14 | P[1] | 18 | P[1] | 22 | P[1] | 26 | P[1] | 30
// Process	Burst Time	Waiting Time	Turnaround Time
// P[1]		24		6		30
// P[2]		3		7		10
// P[3]		3		7		10

// Average Waiting Time: 6.67
// Average Turnaround Time: 16.67



//formulas
// TAT=CT-AT
// WT=TAT-BT
// RT={CPU FIRST TIME-AT}





















































































//TIME QUANTUM
//JAB PROCESS READY QUEUE ME HOTA HAI MATLAB SABSE PAHILE RAM KE ANDAR LAKE 
// AATE HAI TAB PROCESS KO VAHA SE SELECT KARKE RUNNING QUEUE ME LEKE JATE HAI(CPU)
// SUPPOSE TIME QUANTUM HAI 2 TO CPU USS PROCESS KO 2 TIME TAK HE CHALAYEGA BAD ME VAHA PROCESS READY QUQUE ME CHALA JAYENGA AUR DUSRE PROCES RUN HONGS AND VAHA READY QUEUE KA SEQ IMP HAI,,,
// ROUND ROBIN ME 2 QUEUE KO MAINTAIN KARO AAK 
// TIME QUANTUM COMPLETE HONE KE BADD CPU SWITCH KARTE RAHTA HAI
//READY QUEUE AND RUNNING QUEUE