#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
using namespace std;

struct Process {
    int id;
    int burst_time;
    int priority;
    int waiting_time;
    int turn_around_time;
    int completion_time;
};

// Function to perform priority scheduling
void priority_scheduling(vector<Process>& processes, int n) {
    // Sorting processes based on priority (higher priority = lower number)
    sort(processes.begin(), processes.end(), [](const Process& a, const Process& b) {
        return a.priority < b.priority;
    });

    // First process will have zero waiting time
    processes[0].waiting_time = 0;
    processes[0].completion_time = processes[0].burst_time;

    // Calculating waiting time and completion time for each process
    for (int i = 1; i < n; i++) {
        processes[i].waiting_time = processes[i - 1].completion_time;
        processes[i].completion_time = processes[i].waiting_time + processes[i].burst_time;
    }

    // Calculating turn around time
    for (int i = 0; i < n; i++) {
        processes[i].turn_around_time = processes[i].burst_time + processes[i].waiting_time;
    }

    // Calculate total waiting time and turn around time
    int total_waiting_time = 0, total_turn_around_time = 0;
    for (int i = 0; i < n; i++) {
        total_waiting_time += processes[i].waiting_time;
        total_turn_around_time += processes[i].turn_around_time;
    }

    // Print process information
    cout << "\nProcess  Burst Time  Priority  Waiting Time  Turn Around Time  Completion Time\n";
    for (const auto& process : processes) {
        cout << setw(7) << process.id << setw(11) << process.burst_time << setw(9)
             << process.priority << setw(14) << process.waiting_time << setw(17)
             << process.turn_around_time << setw(17) << process.completion_time << endl;
    }

    // Print average waiting time and turn around time
    cout << fixed << setprecision(2);
    cout << "\nAverage Waiting Time: " << (double)total_waiting_time / n << endl;
    cout << "Average Turn Around Time: " << (double)total_turn_around_time / n << endl;
}

int main() {
    cout << "Priority Scheduling (Non-Preemptive):" << endl;

    // Input number of processes
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    vector<Process> processes(n);

    // Input process IDs
    cout << "Enter process IDs: ";
    for (int i = 0; i < n; i++) {
        cin >> processes[i].id;
    }

    // Input burst times
    cout << "Enter burst times: ";
    for (int i = 0; i < n; i++) {
        cin >> processes[i].burst_time;
    }

    // Input priorities
    cout << "Enter priorities: ";
    for (int i = 0; i < n; i++) {
        cin >> processes[i].priority;
    }

    // Call priority scheduling function
    priority_scheduling(processes, n);

    return 0;
}
