#include <iostream>
#include <vector>
#include <numeric> // For accumulate to calculate sum

using namespace std;

class FCFS {
private:
    int numProcesses;
    vector<int> processes;
    vector<int> arrivalTimes;
    vector<int> burstTimes;
    vector<int> waitingTimes;
    vector<int> turnAroundTimes;
    vector<int> completionTimes;

public:
    FCFS() {
        cout << "Enter number of processes: ";
        cin >> numProcesses;

        processes.resize(numProcesses);
        arrivalTimes.resize(numProcesses);
        burstTimes.resize(numProcesses);
        waitingTimes.resize(numProcesses, 0);
        turnAroundTimes.resize(numProcesses, 0);
        completionTimes.resize(numProcesses, 0);

        cout << "Enter processes (space-separated): ";
        for (int i = 0; i < numProcesses; i++) {
            cin >> processes[i];
        }

        cout << "Enter arrival times (space-separated): ";
        for (int i = 0; i < numProcesses; i++) {
            cin >> arrivalTimes[i];
        }

        cout << "Enter burst times (space-separated): ";
        for (int i = 0; i < numProcesses; i++) {
            cin >> burstTimes[i];
        }
    }

    void calcWaitTime() {
        for (int i = 1; i < numProcesses; i++) { // Start from 1 since process 0 has no wait time
            waitingTimes[i] = completionTimes[i - 1] - arrivalTimes[i];
            if (waitingTimes[i] < 0) { // If the process arrives later than previous completion
                waitingTimes[i] = 0;
            }
        }
    }

    void calcTurnAroundTime() {
        for (int i = 0; i < numProcesses; i++) {
            turnAroundTimes[i] = burstTimes[i] + waitingTimes[i];
        }
    }

    void calcCompletionTime() {
        completionTimes[0] = arrivalTimes[0] + burstTimes[0]; // First process
        for (int i = 1; i < numProcesses; i++) {
            completionTimes[i] = completionTimes[i - 1] + burstTimes[i];
        }
    }

    void calcAvgWaitingTime() {
        double avgWaitingTime = accumulate(waitingTimes.begin(), waitingTimes.end(), 0.0) / numProcesses;
        cout << "Average Waiting Time: " << avgWaitingTime << endl;
    }

    void calcAvgTurnAroundTime() {
        double avgTurnAroundTime = accumulate(turnAroundTimes.begin(), turnAroundTimes.end(), 0.0) / numProcesses;
        cout << "Average Turn Around Time: " << avgTurnAroundTime << endl;
    }

    void printTable() {
        cout << "\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurn Around Time\tCompletion Time\n";
        for (int i = 0; i < numProcesses; i++) {
            cout << processes[i] << "\t\t" << arrivalTimes[i] << "\t\t" << burstTimes[i]
                 << "\t\t" << waitingTimes[i] << "\t\t" << turnAroundTimes[i]
                 << "\t\t" << completionTimes[i] << endl;
        }
    }

    void calculateAll() {
        calcCompletionTime();
        calcWaitTime();
        calcTurnAroundTime();
        printTable();
        calcAvgWaitingTime();
        calcAvgTurnAroundTime();
    }
};

int main() {
    cout << "\nFirst Come First Serve Scheduling:\n";
    FCFS obj;
    obj.calculateAll();
    return 0;
}
