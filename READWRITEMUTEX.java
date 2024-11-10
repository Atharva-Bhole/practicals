import java.util.*;

public class READWRITEMUTEX {

    // Mutex and database simulation variables
    static int mutex = 1;        // Mutex to control access to read count
    static int database = 1;     // Controls access to the shared resource (database)
    static int Read_Count = 0;   // Count of active readers

    // Reader function to simulate readers trying to access the database
    static void Reader() throws Exception {
        while (true) {
            mutex = wait(mutex);          // Lock mutex to update read count
            Read_Count = Read_Count + 1;  // Increment count of readers
            
            if (Read_Count == 1) {        // If it's the first reader
                database = wait(database); // Lock database to prevent writers
            }
            mutex = signal(mutex);        // Release mutex

            System.out.println(Read_Count + " User Reading the Data.........");

            mutex = wait(mutex);          // Lock mutex to update read count after reading
            Read_Count = Read_Count - 1;  // Decrement read count
            
            if (Read_Count == 0) {        // If no readers left
                database = signal(database); // Release database for writers
            }
            mutex = signal(mutex);        // Release mutex

            System.out.println("Reading Finished!!!!!!");
            break;                        // Exit loop after one reading session
        }
    }

    // Writer function to simulate writers trying to access the database
    static void Writer() throws Exception {
        while (true) {
            database = wait(database);      // Lock database for writing (no readers/writers)
            System.out.println("Writing on the database......");
            database = signal(database);    // Release database after writing
            System.out.println("Writing Finished!!!!!.");
            break;                          // Exit loop after one writing session
        }
    }

    // Custom wait function to simulate semaphore wait (P) operation
    static int wait(int mutex) {
        while (mutex <= 0) break;  // Wait if mutex is locked
        mutex = mutex - 1;         // Decrement to lock
        return mutex;
    }

    // Custom signal function to simulate semaphore signal (V) operation
    static int signal(int database) {
        database = database + 1;  // Increment to unlock
        return database;
    }

    // Main function to initiate readers and writers
    public static void main(String[] args) throws Exception {
        Writer();  // First writer tries to access the database
        Reader();  // First reader tries to access the database
        Reader();  // Second reader tries to access the database
    }
}

















//output
// Thread Writer1 is WRITING: Good Morning
// Thread Writer1 has finished WRITING
// Thread Reader1 is READING: Good Morning
// Thread Reader1 has FINISHED READING
// Thread Writer2 is WRITING: Good Morning
// Thread Writer2 has finished WRITING
// Thread Reader2 is READING: Good Morning
// Thread Reader2 has FINISHED READING
// Thread Writer3 is WRITING: Good Morning
// Thread Writer3 has finished WRITING
// Thread Reader3 is READING: Good Morning
// Thread Reader3 has FINISHED READING


































































//yat producre ani consumer asto......basic concept mhange na ek thread execute zle ke dusri execute hoen aata semaphore mhange na ek variable mhanu shakto apan semaphore discrobed ny dijikstra in year 1965 ,...used for managing the concurrent processes by using the integer values...........semaphore che 2 types astat 1)binary and 2)counting .......aata binary madhi na 2 conditions a p(s) ie wait,sleep,down and v(S) signal,wake-up,up................. 

// p(semaphore s)
// {
//   while(s==0);
//   s=s-1;
// }

// v(semaphore s)
// {
//   s=s+1;
// }

// 2 process astat cs asto 