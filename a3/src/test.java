import java.util.Random;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Instrumentation ins = new Instrumentation();
        int ArraySize = 30000;
        testOverhead();
        ins.activate(true);
        ins.startTiming("main");
        ins.comment("The Array Size is: "+ArraySize);
        ins.startTiming("populate");
        int[] array = populateArray(ArraySize);
        ins.stopTiming("populate");
        int[] bubble = array.clone();
        int[] quick = array.clone();

        ins.startTiming("bubble sort");
        BubbleSort2Algorithm.sort(bubble, ins);
        ins.stopTiming("bubble sort");

        ins.startTiming("quick sort");
        QSortAlgorithm.sort(quick, ins);
        ins.stopTiming("quick sort");

        ins.stopTiming("main");
        ins.dump("PartialInstrumentation");
        ins.activate(false);
    }

    public static void testOverhead(){
        Instrumentation ins = new Instrumentation();
        ins.activate(true);
        ins.startTiming("main");
            for(int i=0;i<100;i++){
                ins.startTiming("Process Overhead");
                ins.stopTiming("Process Overhead");
            }
        ins.stopTiming("main");
        ins.dump("testing");
        ins.activate(false);
    }
    //unseeded to test variability
    public static int[] populateRandomArray(long size)
    {
        Random random = new Random();
        return random.ints(size, 1, 100000).toArray();
    }

    //seeded to test consistency
    public static int[] populateArray(long size)
    {
        Random seeded = new Random(437);
        return seeded.ints(size, 1, 100000).toArray();
    }

}
