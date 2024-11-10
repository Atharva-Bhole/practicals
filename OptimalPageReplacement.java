import java.util.*;

public class OptimalPageReplacement {

    // Function to implement the Optimal Page Replacement Algorithm
    public static int optimalPageReplacement(int[] pages, int capacity) {
        // Initialize variables
        int pageFaults = 0;
        int n = pages.length;
        
        // To store the frames
        Set<Integer> frames = new HashSet<>();
        
        // To store the index of next occurrences of the pages in the future
        Map<Integer, Integer> futureReferences = new HashMap<>();
        
        // For every page in the page reference string
        for (int i = 0; i < n; i++) {
            // If page is not in memory, we have a page fault
            if (!frames.contains(pages[i])) {
                // If there is space in memory, just add the page
                if (frames.size() < capacity) {
                    frames.add(pages[i]);
                } else {
                    // If there is no space, we need to replace a page
                    int pageToRemove = findOptimalPageToReplace(pages, frames, i, n);
                    frames.remove(pageToRemove);
                    frames.add(pages[i]);
                }
                // Increment page faults
                pageFaults++;
            }
        }
        return pageFaults;
    }

    // Function to find the page that will not be used for the longest time
    private static int findOptimalPageToReplace(int[] pages, Set<Integer> frames, int currentIndex, int n) {
        int farthest = currentIndex;
        int pageToRemove = -1;
        
        // Iterate through each page in memory and find the one that will not be used for the longest time
        for (int page : frames) {
            // Find the next occurrence of each page after currentIndex
            int nextUse = findNextUse(pages, page, currentIndex, n);
            
            // If page is not used again, it is the best to replace
            if (nextUse == -1) {
                return page;
            }
            
            // Find the page that will be used farthest in the future
            if (nextUse > farthest) {
                farthest = nextUse;
                pageToRemove = page;
            }
        }
        return pageToRemove;
    }

    // Function to find the next use of a page after a given index
    private static int findNextUse(int[] pages, int page, int currentIndex, int n) {
        for (int i = currentIndex + 1; i < n; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return -1;  // If the page is not used again
    }

    // Main function to run the simulation
    public static void main(String[] args) {
        // Page reference string (sequence of pages requested)
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 7, 0};
        
        // Number of frames (capacity of memory)
        int capacity = 3;

        // Running the Optimal Page Replacement algorithm
        int pageFaults = optimalPageReplacement(pages, capacity);
        
        // Printing the result
        System.out.println("Total Page Faults: " + pageFaults);
    }
}













//Total Page Faults: 9
















































// The **Optimal Page Replacement Algorithm** is a technique used in operating systems to decide which page (or piece of data) to remove from memory when there is no free space available. The goal is to replace the page that will not be needed for the longest time in the future, so that we can minimize page faults (situations where a page is not in memory and needs to be loaded).

// ### Steps of the Algorithm:
// 1. **Page Reference String**: When a program requests data (like a page), this is called a page reference. A sequence of these requests is called the **page reference string**.

// 2. **Memory (Frames)**: The computer has a limited number of spaces (called frames) where it can keep pages in memory. If all frames are full and a new page is needed, the system has to remove one of the existing pages.

// 3. **Optimal Choice**: The optimal algorithm decides which page to remove by looking ahead into the future. It selects the page that will not be used for the longest time in the future. This is because removing a page that will be used soon would lead to more page faults.

// ### Example:
// Let's say the computer has 3 frames and the program needs the following pages in sequence:  
// `7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 7, 0`

// Here’s how the algorithm works:
// 1. The first page (7) is loaded into an empty frame.
// 2. The second page (0) is loaded into another empty frame.
// 3. The third page (1) is loaded into the last empty frame.
// 4. When the fourth page (2) is needed, all frames are full, so one page must be removed. The algorithm looks ahead to see when each page will be used again. It decides to remove page `7` because it is not needed for the longest time.

// The algorithm repeats this process, always replacing the page that will be used farthest in the future.

// ### Why is it called "Optimal"?
// It is called the **optimal** algorithm because it guarantees the minimum number of page faults. It always removes the page that won’t be used for the longest time in the future, thus minimizing future page misses (page faults).

// ### In Simple Terms:
// - Imagine a table with 3 spots to place your books.
// - You can only have 3 books at a time.
// - When a new book is needed, but there’s no space, you need to choose a book to remove.
// - The optimal algorithm removes the book that you won’t need for the longest time in the future, so you don’t have to keep replacing it over and over again.

// ### Output Example:
// For the page reference string `{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 7, 0}` with 3 frames, the algorithm results in **9 page faults**.

// This means that 9 times, the requested page was not in memory, and the system had to bring it into memory.