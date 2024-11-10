#include <iostream>
#include <vector>
using namespace std;

class MemoryManagement {
protected:
    vector<int> memoryBlocks;
    int numProcesses;
    vector<int> processSizes;
    vector<int> allocatedBlock;
    vector<int> blockSizeRem;

public:
    // Constructor to initialize memory block sizes and process sizes
    MemoryManagement() {
        // Input memory block sizes
        cout << "Enter sizes of the memory blocks (space-separated): ";
        int blockSize;
        while (cin >> blockSize) {
            memoryBlocks.push_back(blockSize);
            if (cin.peek() == '\n') break;  // Stop when enter is pressed
        }

        // Input number of processes
        cout << "Enter number of processes: ";
        cin >> numProcesses;

        // Input process sizes
        cout << "Enter sizes of the processes (space-separated): ";
        int processSize;
        for (int i = 0; i < numProcesses; i++) {
            cin >> processSize;
            processSizes.push_back(processSize);
        }

        // Initialize allocatedBlock to track which block is allocated to each process
        allocatedBlock.resize(numProcesses, -1);
        // Copy the memory block sizes to keep track of remaining memory
        blockSizeRem = memoryBlocks;
    }

    // Method to print the memory allocation table
    void printTable() {
        // Print table header
        cout << "Process   " << "Process Size   " << "Block Number   " << "Block Size   " << "Unused Memory" << endl;

        // Iterate through each process
        for (int i = 0; i < numProcesses; i++) {
            if (allocatedBlock[i] != -1) {
                // Print details of the allocated block
                cout << i + 1 << "          "
                     << processSizes[i] << "             "
                     << allocatedBlock[i] + 1 << "             "
                     << memoryBlocks[allocatedBlock[i]] << "             "
                     << blockSizeRem[allocatedBlock[i]] << endl;
            } else {
                // If no block was allocated, print N/A
                cout << i + 1 << "          "
                     << processSizes[i] << "             "
                     << "N/A           -             -" << endl;
            }
        }
        cout << endl;
    }
};

class NextFit : public MemoryManagement {
public:
    NextFit() : MemoryManagement() {}

    // Method to execute Next Fit memory allocation
    void execute() {
        int j = 0;  // Start searching from the beginning of the block list

        // Iterate over all processes
        for (int i = 0; i < numProcesses; i++) {
            bool allocated = false;

            // Search for the next fit block, starting from where the last allocation was made
            for (int k = 0; k < memoryBlocks.size(); k++) {
                // If the block can accommodate the process
                if (blockSizeRem[j] >= processSizes[i]) {
                    // Allocate the block to the process
                    allocatedBlock[i] = j;
                    // Reduce the available size of the block
                    blockSizeRem[j] -= processSizes[i];
                    allocated = true;
                    break;
                }

                // Move to the next block, wrapping around if necessary
                j = (j + 1) % memoryBlocks.size();
            }

            // Move to the next block after the current allocation, if allocated
            if (allocated) {
                j = (j + 1) % memoryBlocks.size();
            }
        }

        cout << "\nNEXT FIT - Memory Allocation:\n";
        printTable();
    }
};

int main() {
    // Create an object of NextFit class and execute the allocation
    NextFit nf;
    nf.execute();

    return 0;
}
