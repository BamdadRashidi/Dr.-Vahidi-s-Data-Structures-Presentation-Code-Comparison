import java.util.*;

public class Main{
    static ArrayList<Integer> arrList = new ArrayList<>();
    static LinkedList<Integer> linkList = new LinkedList<>();
    static Map<Integer,Integer> map = new HashMap<>();
    static Set<Integer> set = new HashSet<>();
    static Queue<Integer> queue = new ArrayDeque<>();
    static Stack<Integer> stack = new Stack<>();
    static Random rand = new Random();
    static volatile int blackhole = 0;

    public static void Insertion(String DSname,Object DS, int value, int key) throws Exception {
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            if(DS instanceof Map<?,?> map){
                ((Map<Integer,Integer>) map).put(key+i,value);
                blackhole ^= value; 
            }
            else if(DS instanceof Stack<?> stack){
                ((Stack<Integer>) stack).push(value);
                blackhole ^= value; 
            }
            else if(DS instanceof Queue<?> queue){
                ((Queue<Integer>) queue).offer(value);
                blackhole ^= value;
            }
            else if(DS instanceof Collection<?> col){
                ((Collection<Integer>) col).add(value);
                blackhole ^= value;
            }
            else{
                throw new Exception("Not a Valid Data Structure");
            }
        }
        long end = System.nanoTime();
        System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
    }


    public static void resetDSes(){
        arrList.clear();
        linkList.clear();
        map.clear();
        set.clear();
        queue.clear();
        stack.clear();
    }

    public static void InserTest() throws Exception {
        resetDSes(); 
        Insertion("ArrayList",arrList,26,0);
        Insertion("LinkedList",linkList,26,0);
        Insertion("HashMap",map,26,10);
        Insertion("HashSet",set,26,10);
        Insertion("Queue",queue,26,0);
        Insertion("Stack",stack,26,0);
    }

    public static void addsome(){
        resetDSes();
        for(int i=0;i<100000;i++){
            int r = rand.nextInt(0,100);
            arrList.add(r);
            linkList.add(r);
            map.put(i,r);
            set.add(r);
            queue.add(r);
            stack.add(r);
        }
    }

    public static void Searching(String DSname, Object DS, int index, int key) throws Exception {
        int local = 0;
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            if (DS instanceof Map<?,?> map) {
                Integer v = ((Map<Integer,Integer>) map).get(key);
                if(v != null) local ^= v;
            }
            else if (DS instanceof List<?> list) {
                local ^= ((List<Integer>) list).get(index);
            }
            else if (DS instanceof Collection<?> col) {
                if(((Collection<Integer>) col).contains(key)) local++;
            }
            else {
                throw new Exception("Not a Valid Data Structure");
            }
        }
        long end = System.nanoTime();

        blackhole ^= local;

        System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
    }

    public static void searchTest() throws Exception {
        addsome();
        Searching("ArrayList",arrList,50000,0); // middle of list
        Searching("LinkedList",linkList,50000,0); // middle of list
        Searching("HashMap",map,26,10);
        Searching("HashSet",set,26,10);
        Searching("Queue",queue,26,0);
        Searching("Stack",stack,26,0);
    }

    public static void Removing(String DSname, Object DS, int value, int key) throws Exception {

    int local = 0;
    long start = System.nanoTime();
    for(int i = 0; i < 100000; i++) {
        if(DS instanceof Map<?,?> map){
            Integer v = ((Map<Integer,Integer>) map).remove(key+i);
            if(v != null) local ^= v;
        }
        else if(DS instanceof Stack<?> stack){
            boolean removed = ((Stack<Integer>) stack).remove(Integer.valueOf(value));
            if(removed) local++;

        }
        else if(DS instanceof Queue<?> queue){
            boolean removed = ((Queue<Integer>) queue).remove(Integer.valueOf(value));
            if(removed) local++;

        }
        else if(DS instanceof Collection<?> col){
            boolean removed = ((Collection<Integer>) col).remove(Integer.valueOf(value));
            if(removed) local++;
        }
    }
    long end = System.nanoTime();
    blackhole ^= local;
    System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
}


    public static void deletionTest() throws Exception{
        addsome();
        Removing("ArrayList",arrList,26,0);
        Removing("LinkedList",linkList,26,0);
        Removing("HashMap",map,26,10);
        Removing("HashSet",set,26,10);
        Removing("Queue",queue,26,0);
        Removing("Stack",stack,26,0);
    }

    public static void main(String[] args) throws Exception {
        // JVM warmup. Not that important.
        System.out.println("WARMUP");
        System.out.print("\n");
        for(int i=0;i<5;i++){
            int temp = i + 1;
            System.out.println("WARMUP PHASE: " + temp);
            arrList.clear();
            linkList.clear();
            map.clear();
            set.clear();
            queue.clear();
            stack.clear();
            InserTest();
            searchTest();
            deletionTest();
            System.out.println("\n");
        }
        System.out.println("END OF WARMUP");
        System.out.print("\n");
        System.out.print("\n");

        // The actual code
        System.out.println("START");
        System.out.print("\n");
        System.out.println("================ INSERTION TEST ================");
        InserTest();
        System.out.print("\n");
        System.out.println("================ ADDING NUMBERS ================");
        addsome();
        System.out.print("\n");
        System.out.println("================ INSERTION TEST ================");
        InserTest();
        System.out.print("\n");
        System.out.println("================ SEARCHING TEST ================");
        searchTest();
        System.out.print("\n");
        System.out.println("================ REMOVING TEST ================");
        deletionTest();
        System.out.print("\n");
        System.out.println("FINISH");
    }
}



// Time Complexity / Speed that the devs experience 95% of the time.
/*
    ArrayList:
        - Adding:    O(1)
        - Searching: O(N)
        - Removing:  O(N)

    LinkedList:
        - Adding:    O(1)
        - Searching: O(N)
        - Removing:  O(N)

    HashSet:
        - Adding:    O(1)
        - Searching: O(1)
        - Removing:  O(1)

    HashMap:
        - Adding:    O(1)
        - Searching: O(1)
        - Removing:  O(1)

    Queue:
        - Adding:    O(1)
        - Searching: O(N)
        - Removing:  O(N)

    Stack:
        - Adding:    O(1)
        - Searching: O(N)
        - Removing:  O(N)
*/


// TIPS:
/*
    HashMap may be slightly slower than HashSet because it stores key-value pairs instead of only keys.
*/

// blackhole prevents the JVM compiler from over-optimizing actual test operations since we have a ton of redundancy.
/* 
    Java uses (Just-In-Time) compiler which cause it to pay attention to codes and object references first
    then as it finds redundant code, decides to optimize them. we do the warmup to bring it to the optimized state.
*/ 