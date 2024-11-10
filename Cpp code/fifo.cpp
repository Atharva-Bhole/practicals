#include <iostream>
#include <vector>
#include <algorithm> // Include for std::find
#include <iomanip>   // For setting precision of float

using namespace std;

int main() {
    int capacity, fault = 0, top = 0;
    string pf = "No";

    // Input the number of frames
    cout << "Enter the number of frames: ";
    cin >> capacity;

    // Frame array to store the pages and reference string
    vector<int> f;
    vector<int> s;
    int num;

    // Input the reference string
    cout << "Enter the reference string (space-separated): ";
    while (cin >> num) {
        s.push_back(num);
        if (cin.peek() == '\n') break;  // Stop reading when newline is encountered
    }

    // Output column headers
    cout << "\nString | Frame →\t";
    for (int i = 0; i < capacity; i++) {
        cout << i << " ";
    }
    cout << "Fault\n   ↓\n";

    // FIFO Page Replacement Algorithm
    for (int i = 0; i < s.size(); i++) {
        if (find(f.begin(), f.end(), s[i]) == f.end()) {  // Page fault occurs
            if (f.size() < capacity) {
                f.push_back(s[i]);  // Add page if there is space
            } else {
                f[top] = s[i];  // Replace the page in FIFO order
                top = (top + 1) % capacity;
            }
            fault++;
            pf = "Yes";
        } else {
            pf = "No";  // No page fault
        }

        // Print current reference and frame state
        cout << "   " << s[i] << "\t\t";
        for (int j = 0; j < f.size(); j++) {
            cout << f[j] << " ";
        }
        for (int j = 0; j < capacity - f.size(); j++) {
            cout << "  ";
        }
        cout << pf << endl;
    }

    // Calculate and print statistics
    cout << "\nTotal requests: " << s.size() << endl;
    cout << "Total Page Faults: " << fault << endl;
    cout << "Fault Rate: " << fixed << setprecision(2) << (float(fault) / s.size()) * 100 << "%" << endl;

    return 0;
}
