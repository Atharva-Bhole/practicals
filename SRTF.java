import java.util.Scanner;

class SRTF {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();
        
        int[] process = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        
        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter arrival time for Process[" + (i+1) + "]: ");
            arrivalTime[i] = s.nextInt();
            
            System.out.print("Enter burst time for Process[" + (i+1) + "]: ");
            burstTime[i] = s.nextInt();
            
            process[i] = i + 1;  // Process number
            remainingTime[i] = burstTime[i];  // Initialize remaining burst time
        }

        int complete = 0, time = 0, minRemaining = Integer.MAX_VALUE;
        int shortest = 0;  // Index of the process with the shortest remaining time
        boolean foundProcess = false;
        
        // Execute until all processes are complete
        while (complete != n) {
            // Find process with the minimum remaining time among the arrived processes
            for (int j = 0; j < n; j++) {
                if (arrivalTime[j] <= time && remainingTime[j] < minRemaining && remainingTime[j] > 0) {
                    minRemaining = remainingTime[j];
                    shortest = j;
                    foundProcess = true;
                }
            }

            if (!foundProcess) {
                time++;  // If no process is found, increment time and continue
                continue;
            }
            
            // Process the shortest remaining time process
            remainingTime[shortest]--;
            minRemaining = remainingTime[shortest];  // Update minRemaining to current process's remaining time
            if (minRemaining == 0) minRemaining = Integer.MAX_VALUE;
            
            // If a process is completely executed
            if (remainingTime[shortest] == 0) {
                complete++;
                foundProcess = false;
                
                int finishTime = time + 1;
                waitingTime[shortest] = finishTime - burstTime[shortest] - arrivalTime[shortest];
                
                if (waitingTime[shortest] < 0) waitingTime[shortest] = 0;
                
                turnAroundTime[shortest] = burstTime[shortest] + waitingTime[shortest];
            }
            
            time++;
        }

        // Calculate total waiting time and turnaround time
        int totalWait = 0, totalTat = 0;
        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            totalWait += waitingTime[i];
            totalTat += turnAroundTime[i];
            System.out.println("P" + process[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] +
                    "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        float waitAvg = (float) totalWait / n;
        float tatAvg = (float) totalTat / n;

        System.out.printf("\nAverage Waiting Time: %.2f", waitAvg);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", tatAvg);
    }
}




























































































//SHORTEST REMANNING TIME FIRST ALGO
//CRATRERIA:BRUST TIME
// MODE:PREEMPTIVE
// TAT=CT-AT
// WT= TAT-BT
// RT={CPU FIRST TIME-AT}

//YAHA PE PAHILE ARIVAL TIME KE HISAB SE PROCESS LOAD KARO
//AUR AGAR AAK SE JYADA PROCESS HO TO UNHE BRUST TIME KE HISAB SE SORT KARO
//YAHA PURI PROCESS KO PURE EXECUTE NAHI HONE DENE HAI DEKHNA HAI KE BECH ME KOHI AUR SHOIRTEST PROCESS TO NAHI AAYA HAI
