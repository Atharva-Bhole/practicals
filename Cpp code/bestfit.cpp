#include <iostream>
#include <vector>
#include <limits>

using namespace std;

class MemoryManagement {
protected:
    vector<int> memoryBlocks; // Memory block sizes
    vector<int> processSizes; // Process sizes
    vector<int> allocatedBlock; // To track allocated block for each process
    vector<int> blockSizeRem; // To track remaining block size after allocation
    int numProcesses; // Number of processes

public:
    MemoryManagement() {
        int numBlocks;
        cout << "Enter the number of memory blocks: ";
        cin >> numBlocks;

        cout << "Enter sizes of the memory blocks (space-separated): ";
        memoryBlocks.resize(numBlocks);
        for (int i = 0; i < numBlocks; i++) {
            cin >> memoryBlocks[i];
        }

        cout << "Enter number of processes: ";
        cin >> numProcesses;

        cout << "Enter the sizes of the processes (space-separated): ";
        processSizes.resize(numProcesses);
        for (int i = 0; i < numProcesses; i++) {
            cin >> processSizes[i];
        }

        // Initialize variables
        allocatedBlock.resize(numProcesses, -1); // Track allocated block for each process (-1 if not allocated)
        blockSizeRem = memoryBlocks; // Remaining size after allocation is initially same as memory block size
    }

    void printTable() {
        // Print table header
        cout << left << "Process" << "\t"
             << "Process Size" << "\t"
             << "Block Number" << "\t"
             << "Block Size" << "\t"
             << "Unused Memory" << endl;

        for (int i = 0; i < numProcesses; i++) {
            // If a block is allocated to the process, print details
            if (allocatedBlock[i] != -1) {
                cout << left << i + 1 << "\t\t"
                     << processSizes[i] << "\t\t"
                     << allocatedBlock[i] + 1 << "\t\t"
                     << memoryBlocks[allocatedBlock[i]] << "\t\t"
                     << blockSizeRem[allocatedBlock[i]] << endl;
            } else {
                // If no block allocated, print N/A
                cout << left << i + 1 << "\t\t"
                     << processSizes[i] << "\t\t"
                     << "N/A" << "\t\t"
                     << "-" << "\t\t"
                     << "-" << endl;
            }
        }
        cout << endl;
    }
};

class BestFit : public MemoryManagement {
public:
    BestFit() : MemoryManagement() {}

    void execute() {
        // Iterate over all processes to allocate memory using best fit strategy
        for (int i = 0; i < numProcesses; i++) {
            int bestBlock = -1; // Track best fit block for the current process

            for (int block = 0; block < memoryBlocks.size(); block++) {
                if (blockSizeRem[block] >= processSizes[i]) {
                    // If the block is large enough for the process, check if it is the best fit
                    if (bestBlock == -1 || blockSizeRem[bestBlock] > blockSizeRem[block]) {
                        bestBlock = block;
                    }
                }
            }

            // If a best fit block is found, allocate it to the process
            if (bestBlock != -1) {
                allocatedBlock[i] = bestBlock;
                blockSizeRem[bestBlock] -= processSizes[i];
            }
        }

        cout << "\nBEST FIT - Memory Allocation:\n";
        printTable();
    }
};

int main() {
    BestFit bf;
    bf.execute();
    return 0;
}
