import java.util.*;

public class PagingSimulationFIFO {
    static int frames; // Number of frames in memory
    static int pageFaults = 0; // Counter for page faults

    // Method to implement FIFO page replacement algorithm
    public static void fifoPageReplacement(int[] pages, int numPages) {
        // Queue to store pages in memory
        LinkedList<Integer> memory = new LinkedList<>();

        // Set to store pages in memory (for quick lookup)
        Set<Integer> pageSet = new HashSet<>();

        for (int i = 0; i < numPages; i++) {
            int currentPage = pages[i];

            // If the page is not already in memory, it is a page fault
            if (!pageSet.contains(currentPage)) {
                // If memory is full, remove the oldest page (FIFO)
                if (memory.size() == frames) {
                    int oldestPage = memory.removeFirst();
                    pageSet.remove(oldestPage);
                }

                // Add the current page to memory
                memory.add(currentPage);
                pageSet.add(currentPage);

                // Increment page fault count
                pageFaults++;
                System.out.println("Page fault occurred! Loaded page: " + currentPage);
            } else {
                System.out.println("Page " + currentPage + " is already in memory.");
            }

            // Display current memory state
            System.out.println("Current memory state: " + memory);
        }
    }

    public static void main(String[] args) {
        // Sample input: sequence of pages to be referenced
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        // Number of frames in memory
        frames = 3;

        System.out.println("Page reference sequence: " + Arrays.toString(pages));

        // Call FIFO page replacement algorithm
        fifoPageReplacement(pages, pages.length);

        // Output the total number of page faults
        System.out.println("\nTotal page faults: " + pageFaults);
    }
}











//OUTPUT

// Page reference sequence: [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3]
// Page fault occurred! Loaded page: 7
// Current memory state: [7]
// Page fault occurred! Loaded page: 0
// Current memory state: [7, 0]
// Page fault occurred! Loaded page: 1
// Current memory state: [7, 0, 1]
// Page fault occurred! Loaded page: 2
// Current memory state: [0, 1, 2]
// Page 0 is already in memory.
// Current memory state: [0, 1, 2]
// Page fault occurred! Loaded page: 3
// Current memory state: [1, 2, 3]
// Page 0 is already in memory.
// Current memory state: [1, 2, 3]
// Page fault occurred! Loaded page: 4
// Current memory state: [2, 3, 4]
// Page 2 is already in memory.
// Current memory state: [2, 3, 4]
// Page fault occurred! Loaded page: 3
// Current memory state: [3, 4, 2]
// Page 0 is already in memory.
// Current memory state: [3, 4, 2]
// Page 3 is already in memory.
// Current memory state: [3, 4, 2]

// Total page faults: 8





















































// Sure! Let's break it down in simple language:

// ### What is Paging?
// - **Paging** is a memory management technique that divides the computer's memory into small fixed-size chunks called **pages**. Similarly, a program's data is also divided into pages. These pages can be loaded into the computer's memory in a non-contiguous manner (meaning they don't have to be in a single block).

// ### What is FIFO (First In, First Out)?
// - **FIFO** is a simple way of managing the pages in memory. When the memory is full, FIFO will replace the **oldest** page (the one that was loaded first) with the new page. Think of it like a queue — the first page to enter is the first one to leave when space is needed.

// ### How Does the Code Work?

// 1. **The Pages**:
//    - In the code, we have an array of pages that represent the sequence of pages a program will need (like a list of pages being accessed).

// 2. **Memory**:
//    - The computer's memory can only hold a limited number of pages at a time. We define how many pages (frames) can be stored in memory using a variable called `frames`.

// 3. **Page Faults**:
//    - A **page fault** occurs when the program tries to access a page that is not currently in memory. If the page isn't in memory, we have to load it, which counts as a page fault.

// 4. **FIFO Logic**:
//    - When a page is needed but is not in memory, we check if memory is full:
//      - If it **isn't full**, we simply load the new page.
//      - If it **is full**, we remove the oldest page from memory (the page that was added first) and replace it with the new page.

// 5. **Memory Status**:
//    - After each page access, we print out the current pages in memory to see which pages are being stored at any given moment.

// ### Simple Example:
// Let's say you have 3 pages of memory (3 frames), and the program tries to access pages in this order:

// ```
// Pages accessed: 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3
// ```

// - **Step 1**: Access page 7 — **page fault**, load page 7 into memory.
// - **Step 2**: Access page 0 — **page fault**, load page 0 into memory.
// - **Step 3**: Access page 1 — **page fault**, load page 1 into memory (memory is full now).
// - **Step 4**: Access page 2 — **page fault**, remove page 7 (oldest), and load page 2 into memory.
// - **Step 5**: Access page 0 — **no page fault**, page 0 is already in memory.
// - **Step 6**: Access page 3 — **page fault**, remove page 1 (oldest), and load page 3 into memory.
// - **Step 7**: Access page 4 — **page fault**, remove page 2 (oldest), and load page 4 into memory.

// And so on.

// ### Final Output:

// ```
// Total page faults: 8
// ```

// This means there were 8 instances where the program had to load a page because it wasn't in memory.

// ### Summary:
// - **FIFO** simply means that the first page loaded into memory is the first one to be replaced when new pages come in and memory is full.
// - The program counts how many times pages had to be loaded (page faults) and prints out the state of memory after each page access.
// - FIFO is easy to understand but may not always be the most efficient way of managing memory, especially if the program accesses pages in a way that causes a lot of page faults.