import java.util.*;

public class Main{
    static ArrayList<Integer> arrList = new ArrayList<>();
    static LinkedList<Integer> linkList = new LinkedList<>();
    static Map<Integer,Integer> map = new HashMap<>();
    static Set<Integer> set = new HashSet<>();
    static Queue<Integer> queue = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();
    static Random rand = new Random();


    public static void Insertion(String DSname,Object DS, int value, int key) throws Exception {
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            if(DS instanceof Map<?,?> map){
                ((Map<Integer,Integer>) map).put(key+i,value);
            }
            else if(DS instanceof Stack<?> stack){
                ((Stack<Integer>) stack).push(value);
            }
            else if(DS instanceof Queue<?> queue){
                ((Queue<Integer>) queue).offer(value);
            }
            else if(DS instanceof Collection<?> col){
                ((Collection<Integer>) col).add(value);
            }
            else{
                throw new Exception("Not a Valid Data Structure");
            }
        }
        long end = System.nanoTime();
        System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
    }

    public static void InserTest() throws Exception {
        Insertion("ArrayList",arrList,26,0);
        Insertion("LinkedList",linkList,26,0);
        Insertion("HashMap",map,26,10);
        Insertion("HashSet",set,26,10);
        Insertion("Queue",queue,26,0);
        Insertion("Stack",stack,26,0);
    }

    public static void addsome(){
        for(int i=0;i<100000;i++){
            arrList.add(rand.nextInt(0,100));
            linkList.add(rand.nextInt(0,100));
            map.put(i,rand.nextInt(0,100));
            set.add(rand.nextInt(0,100));
            queue.add(rand.nextInt(0,100));
            stack.add(rand.nextInt(0,100));
        }
    }

    public static void Searching(String DSname, Object DS, int index, int key) throws Exception {
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            if (DS instanceof Map<?,?> map) {
                ((Map<Integer,Integer>) map).get(key);
            }
            else if (DS instanceof List<?> list) {
                ((List<Integer>) list).get(index);
            }
            else if (DS instanceof Collection<?> col) {
                ((Collection<Integer>) col).contains(key);
            }
            else {
                throw new Exception("Not a Valid Data Structure");
            }
        }
        long end = System.nanoTime();
        System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
    }

    public static void searchTest() throws Exception {
        Searching("ArrayList",arrList,50000,0); // middle of list
        Searching("LinkedList",linkList,50000,0); // middle of list
        Searching("HashMap",map,26,10);
        Searching("HashSet",set,26,10);
        Searching("Queue",queue,26,0);
        Searching("Stack",stack,26,0);
    }

    public static void Removing(String DSname, Object DS, int value, int key) throws Exception {
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            if(DS instanceof Map<?,?> map){
                ((Map<Integer,Integer>) map).remove(key+i);
            }
            else if(DS instanceof Stack<?> stack){
                ((Stack<Integer>) stack).pop();
            }
            else if(DS instanceof Queue<?> queue){
                ((Queue<Integer>) queue).poll();
            }
            else if(DS instanceof Collection<?> col){
                ((Collection<Integer>) col).remove(Integer.valueOf(value));
            }
        }
        long end = System.nanoTime();
        System.out.println("Average Time for " + DSname + ": " + ((end - start)/100000) + " ns per operation");
    }

    public static void deletionTest() throws Exception{
        Removing("ArrayList",arrList,26,0);
        Removing("LinkedList",linkList,26,0);
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