#include <iostream>
#include <vector>
#include <iomanip>
#include <sstream>

using namespace std;

class MemoryManagement {
protected:
    vector<int> memoryBlocks;
    int numProcesses;
    vector<int> processSizes;
    vector<int> allocatedBlock;
    vector<int> blockSizeRem;

public:
    MemoryManagement() {
        // Input memory block sizes
        cout << "Enter sizes of the memory blocks (space-separated): ";
        string input;
        getline(cin, input);
        stringstream ss(input);
        int blockSize;
        while (ss >> blockSize) {
            memoryBlocks.push_back(blockSize);
        }

        // Input number of processes
        cout << "Enter number of processes: ";
        cin >> numProcesses;
        cin.ignore(); // Clear the newline character from the buffer

        // Input process sizes
        processSizes.resize(numProcesses);
        cout << "Enter sizes of the processes (space-separated): ";
        for (int i = 0; i < numProcesses; i++) {
            cin >> processSizes[i];
        }

        // Initialize allocatedBlock to track which block is allocated to each process
        allocatedBlock.resize(numProcesses, -1); // No block allocated initially

        // Copy the memory block sizes to keep track of remaining memory
        blockSizeRem = memoryBlocks;
    }

    void printTable() {
        // Print table header
        cout << setw(10) << "Process" 
             << setw(15) << "Process Size" 
             << setw(15) << "Block Number" 
             << setw(15) << "Block Size" 
             << setw(15) << "Unused Memory" << endl;

        for (int i = 0; i < numProcesses; i++) {
            if (allocatedBlock[i] != -1) {
                cout << setw(10) << (i + 1) 
                     << setw(15) << processSizes[i] 
                     << setw(15) << (allocatedBlock[i] + 1) 
                     << setw(15) << memoryBlocks[allocatedBlock[i]] 
                     << setw(15) << blockSizeRem[allocatedBlock[i]] << endl;
            } else {
                cout << setw(10) << (i + 1) 
                     << setw(15) << processSizes[i] 
                     << setw(15) << "N/A" 
                     << setw(15) << "-" 
                     << setw(15) << "-" << endl;
            }
        }
        cout << endl;
    }
};

class WorstFit : public MemoryManagement {
public:
    WorstFit() : MemoryManagement() {}

    void execute() {
        // Iterate over all processes
        for (int i = 0; i < numProcesses; i++) {
            int worstBlock = -1;
            // Search for the worst block (largest available block)
            for (int block = 0; block < memoryBlocks.size(); block++) {
                // Check if the block can accommodate the process
                if (blockSizeRem[block] >= processSizes[i]) {
                    if (worstBlock == -1 || blockSizeRem[worstBlock] < blockSizeRem[block]) {
                        worstBlock = block;
                    }
                }
            }

            // Allocate the worst block to the process if found
            if (worstBlock != -1) {
                allocatedBlock[i] = worstBlock;
                blockSizeRem[worstBlock] -= processSizes[i];
            }
        }

        cout << "WORST FIT - Memory Allocation:" << endl;
        printTable();
    }
};

int main() {
    WorstFit wf;
    wf.execute();
    return 0;
}
