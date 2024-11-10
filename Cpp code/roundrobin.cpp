#include <iostream>
#include <vector>
#include <iomanip>
using namespace std;

// Function to find waiting time for all processes
void findWaitingTime(const vector<int>& processes, int n, const vector<int>& burst_time, vector<int>& waiting_time, int quantum) {
    vector<int> remaining_burst_time(n);
    for (int i = 0; i < n; i++) {
        remaining_burst_time[i] = burst_time[i];
    }

    int t = 0;  // Current time

    // Keep traversing processes in round-robin until all are done
    while (true) {
        bool done = true;

        for (int i = 0; i < n; i++) {
            // Check if a process has remaining burst time
            if (remaining_burst_time[i] > 0) {
                done = false;  // There's a pending process

                if (remaining_burst_time[i] > quantum) {
                    // Increase current time by quantum
                    t += quantum;
                    // Decrease remaining burst time
                    remaining_burst_time[i] -= quantum;
                } else {
                    // If burst time is smaller or equal to quantum, finish process
                    t += remaining_burst_time[i];
                    waiting_time[i] = t - burst_time[i];
                    remaining_burst_time[i] = 0;
                }
            }
        }

        // If all processes are done
        if (done) {
            break;
        }
    }
}

// Function to find turn around time for all processes
void findTurnAroundTime(const vector<int>& processes, int n, const vector<int>& burst_time, const vector<int>& waiting_time, vector<int>& turn_around_time) {
    // Calculating turn around time by adding burst_time and waiting_time
    for (int i = 0; i < n; i++) {
        turn_around_time[i] = burst_time[i] + waiting_time[i];
    }
}

// Function for Round Robin scheduling
void roundRobin(const vector<int>& processes, int n, const vector<int>& burst_time, int quantum) {
    vector<int> waiting_time(n, 0);
    vector<int> turn_around_time(n, 0);

    // Function to find waiting time of all processes
    findWaitingTime(processes, n, burst_time, waiting_time, quantum);

    // Function to find turn around time for all processes
    findTurnAroundTime(processes, n, burst_time, waiting_time, turn_around_time);

    // Display processes with their burst time, waiting time, and turn around time
    cout << "\nProcess  Burst Time  Waiting Time  Turn Around Time" << endl;
    double total_waiting_time = 0;
    double total_turn_around_time = 0;
    for (int i = 0; i < n; i++) {
        total_waiting_time += waiting_time[i];
        total_turn_around_time += turn_around_time[i];
        cout << setw(7) << processes[i] << setw(11) << burst_time[i] 
             << setw(14) << waiting_time[i] << setw(17) << turn_around_time[i] << endl;
    }

    // Display average waiting time and turn-around time
    cout << fixed << setprecision(2);
    cout << "\nAverage Waiting Time: " << (total_waiting_time / n) << endl;
    cout << "Average Turn Around Time: " << (total_turn_around_time / n) << endl;
}

int main() {
    cout << "Round Robin Scheduling:" << endl;

    // Input number of processes
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    vector<int> processes(n);
    
    // Input process IDs
    cout << "Enter process IDs: ";
    for (int i = 0; i < n; i++) {
        cin >> processes[i];
    }

    // Input burst times
    vector<int> burst_time(n);
    cout << "Enter burst times: ";
    for (int i = 0; i < n; i++) {
        cin >> burst_time[i];
    }

    // Input time quantum
    int quantum;
    cout << "Enter time quantum: ";
    cin >> quantum;

    // Call the round robin function
    roundRobin(processes, n, burst_time, quantum);

    return 0;
}
