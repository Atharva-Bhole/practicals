import java.util.Scanner;

public class FirstFitMemoryAllocation {
    
    // Function to simulate the First Fit memory allocation
    static void firstFit(int[] blockSizes, int[] processSizes) {
        int[] allocation = new int[processSizes.length];  // Array to store allocated block indexes for processes

        // Initialize all allocations as -1, meaning initially no process is allocated
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Process each process one by one
        for (int i = 0; i < processSizes.length; i++) {
            for (int j = 0; j < blockSizes.length; j++) {
                // If block j is big enough for process i and is unallocated
                if (blockSizes[j] >= processSizes[i]) {
                    // Assign block j to process i
                    allocation[i] = j;
                    
                    // Reduce available memory in this block
                    blockSizes[j] -= processSizes[i];
                    
                    break; // Move to the next process after allocation
                }
            }
        }

        // Print the allocation result
        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSizes.length; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSizes[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1); // Display block number (1-based index)
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input block sizes
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        int[] blockSizes = new int[numBlocks];
        System.out.println("Enter the sizes of the blocks:");
        for (int i = 0; i < numBlocks; i++) {
            blockSizes[i] = scanner.nextInt();
        }

        // Input process sizes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        int[] processSizes = new int[numProcesses];
        System.out.println("Enter the sizes of the processes:");
        for (int i = 0; i < numProcesses; i++) {
            processSizes[i] = scanner.nextInt();
        }

        // Perform First Fit allocation
        firstFit(blockSizes, processSizes);

        scanner.close();
    }
}












// Enter the number of memory blocks: 5
// Enter the sizes of the blocks:
// 100 500 200 300 600
// Enter the number of processes: 4
// Enter the sizes of the processes:
// 212 417 112 426




// Process No.   Process Size   Block No.
// 1             212            2
// 2             417            5
// 3             112            2
// 4             426            Not Allocated






















































//
// First Fit is a simple and commonly used **memory allocation** strategy in operating systems, particularly in **dynamic memory allocation**. Its primary goal is to allocate memory to processes efficiently by finding the first available block of memory that is large enough to satisfy a process's memory request.

// ### How First Fit Works

// 1. **Memory Blocks**:
//    - Memory is divided into a series of blocks of varying sizes. These blocks are kept in a list, representing all available memory in the system.

// 2. **Process Allocation**:
//    - When a process requests memory, First Fit scans the list of blocks from the beginning.
//    - It searches for the **first block** large enough to meet the memory requirement of the process.
//    - Once a suitable block is found, the requested amount of memory is allocated to the process, and the remaining part of the block (if any) is left as free memory.

// 3. **Example**:
//    Suppose we have memory blocks of sizes `[100, 500, 200, 300, 600]`, and three processes require memory in the following order:
//    - Process 1 requests **212 units**.
//    - Process 2 requests **417 units**.
//    - Process 3 requests **112 units**.

//    Using First Fit:
//    - **Process 1** finds the first block of **500** units (second in the list), which can accommodate **212 units**. It takes **212 units**, leaving **288 units** in that block.
//    - **Process 2** then finds the fifth block of **600** units (first block in the list that’s big enough), takes **417 units**, and leaves **183 units**.
//    - **Process 3** finds the leftover **288 units** in the second block and takes **112 units**, leaving **176 units**.

// ### Advantages of First Fit

// 1. **Fast Allocation**:
//    - Since it only looks for the first suitable block, First Fit typically requires less search time compared to other strategies (like Best Fit, which needs to find the smallest possible fit).
//    - This makes First Fit a faster choice, especially when there are many blocks.

// 2. **Simple Implementation**:
//    - The algorithm is straightforward to implement as it only needs a single pass through the memory blocks to find the first match.

// ### Disadvantages of First Fit

// 1. **External Fragmentation**:
//    - As memory is allocated, small fragments of unallocated memory are left over in various blocks. These fragments might not be large enough to accommodate future processes, causing **external fragmentation** (wasted space between blocks).

// 2. **Wasted Space**:
//    - Although it finds the first block quickly, it may not use memory space as efficiently as other strategies, such as Best Fit, which aims to minimize wasted space by finding the best possible fit.

// 3. **Potential Inefficiency Over Time**:
//    - Over time, First Fit may lead to an inefficient arrangement of blocks, requiring periodic **memory compaction** (reorganizing memory to eliminate gaps) to maximize usable space.

// ### Use Cases

// First Fit is effective in systems where:
//    - Speed is prioritized over memory efficiency.
//    - The system handles a moderate number of processes or short-lived processes, so fragmentation isn’t as severe.
//    - Simplicity and quick allocation are preferred, such as in smaller embedded systems or certain real-time operating systems.

// In summary, First Fit is a **quick and simple memory allocation technique** that is best for environments where **allocation speed** is essential and **fragmentation** can be managed or isn’t a major concern.