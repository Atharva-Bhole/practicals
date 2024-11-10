#include <iostream>
#include <vector>
#include <iomanip>
#include <algorithm>
using namespace std;

// Function to find the waiting time for all processes
void findWaitingTime(const vector<int>& processes, int n, const vector<int>& burst_time, vector<int>& waiting_time) {
    // Waiting time for the first process is always 0
    waiting_time[0] = 0;

    // Calculating waiting time for each process
    for (int i = 1; i < n; i++) {
        waiting_time[i] = burst_time[i - 1] + waiting_time[i - 1];
    }
}

// Function to calculate turn-around time
void findTurnAroundTime(const vector<int>& processes, int n, const vector<int>& burst_time, const vector<int>& waiting_time, vector<int>& turn_around_time) {
    // Calculating turnaround time by adding burst_time and waiting_time
    for (int i = 0; i < n; i++) {
        turn_around_time[i] = burst_time[i] + waiting_time[i];
    }
}

// Function to sort processes by burst time using SJF logic
void sjfScheduling(vector<int>& processes, int n, vector<int>& burst_time) {
    vector<int> waiting_time(n, 0);
    vector<int> turn_around_time(n, 0);
    
    // Pair processes with their burst times
    vector<pair<int, int>> processes_burst(n);
    for (int i = 0; i < n; i++) {
        processes_burst[i] = make_pair(processes[i], burst_time[i]);
    }

    // Sorting the processes by their burst time
    sort(processes_burst.begin(), processes_burst.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
        return a.second < b.second; // Compare by burst time
    });

    // Unzipping sorted processes and burst times
    for (int i = 0; i < n; i++) {
        processes[i] = processes_burst[i].first;
        burst_time[i] = processes_burst[i].second;
    }
    
    // Function to find waiting time of all processes
    findWaitingTime(processes, n, burst_time, waiting_time);
    
    // Function to find turn around time for all processes
    findTurnAroundTime(processes, n, burst_time, waiting_time, turn_around_time);
    
    // Display processes with their burst time, waiting time, and turn-around time
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
    cout << "Shortest Job First (SJF) Scheduling:" << endl;

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

    // Call the SJF scheduling function
    sjfScheduling(processes, n, burst_time);

    return 0;
}
