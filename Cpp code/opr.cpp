#include <iostream>
#include <vector>
#include <sstream>
#include <algorithm>
using namespace std;

// Helper function to check if a page is in future references
bool isInFuture(const vector<int>& referenceString, int start, int page) {
    for (int i = start; i < referenceString.size(); i++) {
        if (referenceString[i] == page) {
            return true;
        }
    }
    return false;
}

// Helper function to find the next occurrence of a page
int nextOccurrence(const vector<int>& referenceString, int start, int page) {
    for (int i = start; i < referenceString.size(); i++) {
        if (referenceString[i] == page) {
            return i;
        }
    }
    return -1; // Not found
}

// Helper function to find the index of the page that will be used the latest (or never)
int findMaxIndex(const vector<int>& occurance) {
    int maxIndex = 0;
    int maxValue = occurance[0];

    for (int i = 1; i < occurance.size(); i++) {
        if (occurance[i] > maxValue) {
            maxIndex = i;
            maxValue = occurance[i];
        }
    }
    return maxIndex;
}

int main() {
    // Input: Number of frames
    cout << "Enter the number of frames: ";
    int capacity;
    cin >> capacity;

    // Frame list and variables
    vector<int> frames;
    int fault = 0;
    string pf;

    // Input: Reference string
    cout << "Enter the reference string: ";
    cin.ignore(); // To consume newline
    string line;
    getline(cin, line);
    stringstream ss(line);
    vector<int> referenceString;
    int temp;
    while (ss >> temp) {
        referenceString.push_back(temp);
    }

    // Output table header
    cout << "\nString\t";
    for (int i = 0; i < capacity; i++) {
        cout << i << " ";
    }
    cout << "Fault\n";

    // Occurrence array to keep track of the next use of a page
    vector<int> occurance(capacity, -1); // Fill with -1 to indicate no future occurrence yet

    // Processing the reference string
    for (int i = 0; i < referenceString.size(); i++) {
        int currentPage = referenceString[i];

        // If page is not in frames (page fault)
        if (find(frames.begin(), frames.end(), currentPage) == frames.end()) {
            if (frames.size() < capacity) {
                // Add the page to frames if there's space
                frames.push_back(currentPage);
            } else {
                // Finding page to replace using LRU logic
                for (int j = 0; j < frames.size(); j++) {
                    int pageInFrame = frames[j];
                    if (!isInFuture(referenceString, i + 1, pageInFrame)) {
                        // If the page is not used again, replace it
                        frames[j] = currentPage;
                        break;
                    } else {
                        // Find next occurrence of each page
                        occurance[j] = nextOccurrence(referenceString, i + 1, pageInFrame);
                    }
                }
                // Replace the page with the one having the farthest next occurrence
                int indexToReplace = findMaxIndex(occurance);
                frames[indexToReplace] = currentPage;
            }
            fault++;
            pf = "Yes";
        } else {
            pf = "No";
        }

        // Output the current state of frames and whether it was a fault
        cout << "   " << currentPage << "\t\t";
        for (int x : frames) {
            cout << x << " ";
        }
        for (int x = frames.size(); x < capacity; x++) {
            cout << "  ";
        }
        cout << pf << endl;
    }

    // Output total requests, faults, and fault rate
    cout << "\nTotal requests: " << referenceString.size() << endl;
    cout << "Total Page Faults: " << fault << endl;
    cout << "Fault Rate: " << (static_cast<double>(fault) / referenceString.size()) * 100 << "%" << endl;

    return 0;
}
