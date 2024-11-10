import java.util.*;

public class PAGESIMULRU {
    static final int MEMORY_SIZE = 3; // Memory capacity (number of frames)
    static LinkedList<Integer> memory = new LinkedList<>();
    static int pageFaults = 0;

    public static void accessPage(int page) {
        // If page is not in memory, it's a page fault
        if (!memory.contains(page)) {
            pageFaults++;
            // If memory is full, remove the least recently used (LRU) page
            if (memory.size() == MEMORY_SIZE) {
                memory.removeFirst(); // Remove the LRU page (front of the list)
            }
        } else {
            // If the page is already in memory, move it to the end (most recently used)
            memory.remove(Integer.valueOf(page));
        }
        memory.add(page); // Add the new page (or moved page) to the end
    }

    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5}; // Pages accessed in sequence

        for (int page : pages) {
            accessPage(page);
            System.out.println("Memory state: " + memory);
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }
}





























// Memory state: [1]
// Memory state: [1, 2]
// Memory state: [1, 2, 3]
// Memory state: [2, 3, 4]
// Memory state: [3, 4, 1]
// Memory state: [4, 1, 2]
// Memory state: [1, 2, 5]
// Total Page Faults: 6
















































































// ### Explanation of LRU (Least Recently Used) Algorithm:

// The **LRU (Least Recently Used)** algorithm is a page replacement strategy used in operating systems to manage memory. It is designed to decide which pages (or pieces of data) should be removed from memory when new data needs to be loaded, but memory is full.

// #### How LRU Works:
// 1. **Memory Management**: Imagine your computer memory has limited space, and pages (data) are stored in this memory. The LRU algorithm keeps track of the **most recently used** pages and the **least recently used** pages.
   
// 2. **When a Page is Accessed**: 
//    - If the page is already in memory, it becomes the **most recently used**.
//    - If the page is **not in memory**, it needs to be loaded into memory. If memory is full, the page that has not been used for the longest time (the **least recently used**) is removed to make room for the new page.
   
// 3. **Usage Scenario**: Consider that you have limited memory space (e.g., 3 pages). If you access new pages, and your memory is full, the page that hasn't been used for the longest time is removed. When new pages are accessed, they are placed in memory and marked as the most recently used.

// 4. **Page Replacement**:
//    - If a page is accessed again after some time, it is considered **most recently used** and is placed at the end of the memory list (in a real system, this might be represented as the most recent position in memory).
//    - The **least recently used page** is the one that is removed from memory when space is needed.

// #### Example:
// Consider the following sequence of page accesses:
// ```
// 1, 2, 3, 4, 1, 2, 5
// ```

// - Initially, pages 1, 2, and 3 are loaded into memory.
// - When page 4 is accessed, memory is full, so page 1 (the least recently used) is removed, and page 4 is loaded.
// - Then, when page 1 is accessed again, it is reloaded into memory, replacing the least recently used page (which is now page 2).
// - Similarly, page 5 replaces the least recently used page, which is now page 3.

// #### Key Characteristics of LRU:
// - **Efficient**: LRU tends to keep the pages that are frequently used in memory, which can improve performance.
// - **Fair**: It does not favor any specific page over others. Instead, it simply removes the page that hasn't been used for the longest time.
// - **Common in Real Systems**: LRU is used in many operating systems and applications because it helps to manage memory efficiently by maintaining the most useful data in memory.

// In summary, LRU is a simple yet effective strategy that removes the least recently used pages from memory when it needs to make space for new data. This ensures that frequently accessed data stays in memory, while less useful data is removed.