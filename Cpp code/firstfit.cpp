#include <iostream>
#include <vector>
#include <iomanip> // For table formatting

using namespace std;

class MemoryManagement {
protected:
    vector<int> memoryBlocks;  // Stores sizes of memory blocks
    vector<int> processSizes;  // Stores sizes of processes
    vector<int> allocatedBlock;  // Tracks allocated block for each process
    vector<int> blockSizeRem;  // Tracks remaining block size after allocation
    int numProcesses;

public:
    MemoryManagement() {
        // Input memory block sizes
        cout << "Enter sizes of the memory blocks (space-separated): ";
        int size;
        while (cin.peek() != '\n') {
            cin >> size;
            memoryBlocks.push_back(size);
        }
        cin.ignore();  // Clear the newline after input

        // Input number of processes
        cout << "Enter number of processes: ";
        cin >> numProcesses;

        // Input process sizes
        cout << "Enter the sizes of the processes (space-separated): ";
        for (int i = 0; i < numProcesses; i++) {
            cin >> size;
            processSizes.push_back(size);
        }

        // Initialize the allocation tracking
        allocatedBlock.resize(numProcesses, -1);  // All processes initially unallocated
        blockSizeRem = memoryBlocks;  // Copy memory block sizes
    }

    void printTable() {
        // Print table header
        cout << left << setw(10) << "Process" << setw(15) << "Process Size" << setw(15) << "Block Number" 
             << setw(15) << "Block Size" << setw(15) << "Unused Memory" << endl;

        for (int i = 0; i < numProcesses; i++) {
            if (allocatedBlock[i] != -1) {
                // Print details of allocated block
                cout << left << setw(10) << (i + 1) << setw(15) << processSizes[i] << setw(15) << (allocatedBlock[i] + 1)
                     << setw(15) << memoryBlocks[allocatedBlock[i]] << setw(15) << blockSizeRem[allocatedBlock[i]] << endl;
            } else {
                // Print N/A for unallocated process
                cout << left << setw(10) << (i + 1) << setw(15) << processSizes[i] << setw(15) << "N/A" 
                     << setw(15) << "-" << setw(15) << "-" << endl;
            }
        }
        cout << endl;
    }
};

class FirstFit : public MemoryManagement {
public:
    FirstFit() : MemoryManagement() {}

    void execute() {
        // Iterate over all processes to allocate memory using First Fit strategy
        for (int i = 0; i < numProcesses; i++) {
            for (int block = 0; block < memoryBlocks.size(); block++) {
                // Check if the block is large enough for the process
                if (blockSizeRem[block] >= processSizes[i]) {
                    // Allocate block to the process
                    allocatedBlock[i] = block;
                    // Update the remaining block size
                    blockSizeRem[block] -= processSizes[i];
                    break;  // Exit the loop as soon as the first fit is found
                }
            }
        }

        cout << "\nFIRST FIT - Memory Allocation:\n";
        printTable();
    }
};

int main() {
    FirstFit ff;
    ff.execute();
    return 0;
}
