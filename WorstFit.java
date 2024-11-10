import java.util.Scanner;

public class WorstFit {

    // Method to implement Worst Fit allocation strategy
    public static void worstFit(int[] blockSize, int m, int[] processSize, int n) {
        // Array to store the block assigned to each process
        int[] allocation = new int[n];

        // Initially, no block is assigned to any process
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Iterate through each process to find the worst fit block
        for (int i = 0; i < n; i++) {
            // Initialize index for worst fit block
            int worstIdx = -1;

            // Find the block that is the largest and can accommodate the process
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) { // Block can fit the process
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx]) {
                        worstIdx = j; // Update worst fit block index
                    }
                }
            }

            // If a block was found, allocate it to the process
            if (worstIdx != -1) {
                allocation[i] = worstIdx; // Allocate worst fit block to process
                blockSize[worstIdx] -= processSize[i]; // Reduce available size in the block
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

        // Call worst fit function
        worstFit(blockSize, m, processSize, n);
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
// 1             212             5
// 2             417             2
// 3             112             4
// 4             426             Not Allocated




















































// In simple terms, the **Worst Fit** memory allocation strategy is like trying to fit items into the biggest available spaces. Here’s how it works:

// 1. **Memory Blocks and Processes**: 
//    - Imagine you have several large containers (memory blocks), each with a certain amount of space (size).
//    - You also have a set of items (processes) that need a specific amount of space to fit into a container.

// 2. **Goal of Worst Fit**:
//    - The Worst Fit strategy tries to put each item (process) into the container (memory block) with the *most* available space that can fit the item.
//    - The idea is that using the biggest container first leaves smaller containers available for smaller items later on.

// 3. **Steps in the Program**:
//    - **Input**: You enter the sizes of your containers and the sizes of your items.
//    - **Allocation Process**:
//      - For each item, look for the biggest container that has enough space for it.
//      - If found, place the item there and update the space left in that container.
//      - If no container is large enough, mark the item as “Not Allocated.”
//    - **Output**: The program shows which container each item was placed in, or if it wasn’t allocated due to lack of space.

// 4. **Example Output Explained**:
//    - Suppose you have containers of sizes `[100, 500, 200, 300, 600]` and items of sizes `[212, 417, 112, 426]`.
//    - The program would allocate items as follows:
//      - **Item 1 (212)** goes into the largest container that can fit it, which is the container of size **600**.
//      - **Item 2 (417)** fits into the next largest container, which is **500**.
//      - **Item 3 (112)** goes into the **300**-sized container.
//      - **Item 4 (426)** doesn’t fit into any of the remaining containers, so it is marked as "Not Allocated."

// 5. **Pros and Cons of Worst Fit**:
//    - **Pros**: This method can leave smaller containers free, which might be useful for smaller items in the future.
//    - **Cons**: It can leave containers with small leftover spaces that aren’t usable for larger items, which is called **external fragmentation**.