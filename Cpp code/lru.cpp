#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int capacity, fault = 0;
    string pf = "No";
    vector<int> f, st;

    // Input number of frames (capacity)
    cout << "Enter the number of frames: ";
    cin >> capacity;

    // Input reference string
    cout << "Enter the reference string: ";
    vector<int> s;
    int num;
    while (cin >> num) {
        s.push_back(num);
        if (cin.peek() == '\n') break;  // Stop when enter is pressed
    }

    // Print header
    cout << "\nString | Frame →\t";
    for (int i = 0; i < capacity; i++) {
        cout << i << " ";
    }
    cout << "Fault\n   ↓\n";

    for (int i : s) {
        auto it = find(f.begin(), f.end(), i);

        if (it == f.end()) {
            // Page fault, not found in frame
            if (f.size() < capacity) {
                f.push_back(i);
                st.push_back(f.size() - 1);  // Track index of added page
            } else {
                int ind = st.front();  // Least recently used page
                st.erase(st.begin());  // Remove the LRU page
                f[ind] = i;            // Replace the LRU page with new page
                st.push_back(ind);      // Track the new page index
            }
            pf = "Yes";
            fault++;
        } else {
            // Page hit, update LRU tracking
            int index = distance(f.begin(), it);
            st.erase(find(st.begin(), st.end(), index));  // Remove old position
            st.push_back(index);                          // Add to the end (most recent)
            pf = "No";
        }

        // Output the current status of frames and page fault status
        cout << "   " << i << "\t\t";
        for (int x : f) {
            cout << x << " ";
        }
        for (int x = 0; x < capacity - f.size(); x++) {
            cout << "  ";
        }
        cout << " " << pf << endl;
    }

    // Output total page faults and fault rate
    cout << "\nTotal Requests: " << s.size() << "\nTotal Page Faults: " << fault 
         << "\nFault Rate: " << (fault / (double)s.size()) * 100 << "%" << endl;

    return 0;
}
