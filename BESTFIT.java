import java.util.Scanner;

public class BESTFIT {

    // Method to implement Best Fit allocation strategy
    public static void bestFit(int[] blockSize, int m, int[] processSize, int n) {
        // Array to store the block assigned to each process
        int[] allocation = new int[n];

        // Initially, no block is assigned to any process
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Iterate through each process to find suitable blocks
        for (int i = 0; i < n; i++) {
            // Initialize index for best fit block
            int bestIdx = -1;

            // Find the best fit block for the current process
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {  // Block can fit the process
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j;  // Update best fit block index
                    }
                }
            }

            // If a block was found, allocate it to the process
            if (bestIdx != -1) {
                allocation[i] = bestIdx;  // Allocate best fit block to process
                blockSize[bestIdx] -= processSize[i];  // Reduce available size in the block
            }
        }

        // Output the allocation result
        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.println(allocation[i] + 1);
            } else {
                System.out.println("Not Allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of memory blocks
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];

        System.out.println("Enter the sizes of the memory blocks:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = sc.nextInt();
        }

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];

        System.out.println("Enter the sizes of the processes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = sc.nextInt();
        }

        // Call best fit function
        bestFit(blockSize, m, processSize, n);
        sc.close();
    }
}





// Enter number of memory blocks: 5
// Enter the sizes of the memory blocks:
// 100 500 200 300 600
// Enter number of processes: 4
// Enter the sizes of the processes:
// 212 417 112 426

// Process No.   Process Size    Block No.
// 1             212             4
// 2             417             2
// 3             112             1
// 4             426             Not Allocated








































































// The **Best Fit** memory allocation strategy is another approach to allocating memory blocks to processes, aimed at minimizing wasted space by selecting the block that leaves the smallest leftover space after allocation. It’s commonly used when there is a need for better memory utilization and reduced fragmentation compared to the First Fit strategy.

// ### How Best Fit Works

// 1. **Memory Blocks**:
//    - Memory is divided into a list of blocks of various sizes. Each block represents a portion of memory that can be assigned to a process.

// 2. **Process Allocation**:
//    - When a process requests memory, Best Fit scans the list of blocks and finds **the smallest block that is large enough** to meet the process's requirements.
//    - After finding the best fit, the process is allocated to this block, and any remaining part of the block (if it isn’t a perfect fit) is left as free space.

// 3. **Example**:
//    Suppose we have memory blocks with sizes `[100, 500, 200, 300, 600]`, and three processes require memory in the following order:
//    - Process 1 requests **212 units**.
//    - Process 2 requests **417 units**.
//    - Process 3 requests **112 units**.

//    Using Best Fit:
//    - **Process 1** finds that the **300** unit block is the closest fit for **212 units**, leaving **88 units** free after allocation.
//    - **Process 2** finds the **500** unit block is the closest fit for **417 units**, leaving **83 units** free.
//    - **Process 3** finds that the **100** unit block is a perfect fit for **112 units**, leaving no leftover space.

// ### Advantages of Best Fit

// 1. **Minimizes Fragmentation**:
//    - By allocating the smallest possible block, Best Fit reduces the amount of leftover memory, which helps in minimizing **external fragmentation** (unused space between allocated blocks).

// 2. **Efficient Memory Utilization**:
//    - Best Fit tends to use memory more efficiently by using blocks that match process requirements closely, which conserves larger blocks for larger future requests.

// ### Disadvantages of Best Fit

// 1. **Higher Search Time**:
//    - To find the best match, Best Fit must check each available block, which can be time-consuming, especially if there are many blocks or frequent memory allocation requests.

// 2. **Small, Isolated Fragments**:
//    - Best Fit often leaves very small, unusable fragments of memory scattered throughout, as it tends to leave small gaps in blocks.

// 3. **Potential Need for Compaction**:
//    - Over time, small unused memory fragments build up, requiring **memory compaction** (reorganizing memory to eliminate small gaps) to improve efficiency.

// ### Comparison to Other Allocation Strategies

// - **First Fit**: Best Fit typically results in less fragmentation than First Fit but is slower in allocation due to the need for a complete scan of the blocks.
// - **Worst Fit**: Unlike Worst Fit (which seeks the largest available block to ensure the largest remaining space), Best Fit leaves the least amount of leftover space in each allocation.

// ### Use Cases

// Best Fit is suited for systems where:
//    - **Memory efficiency** is more critical than **allocation speed**.
//    - The application has a relatively long-running lifecycle, where reducing fragmentation and conserving memory is essential.
//    - The operating system has moderate or advanced **memory management capabilities** to handle compaction or coalescing of fragmented spaces over time.

// In summary, Best Fit is a **memory-efficient allocation strategy** that helps in minimizing fragmentation by using the smallest block that fits the request. It is ideal for scenarios where efficient memory usage is more important than quick allocation times.