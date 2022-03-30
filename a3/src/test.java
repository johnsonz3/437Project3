import Sorts.BubbleSort2Algorithm;
import Sorts.QSortAlgorithm;

import java.util.Random;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Instrumentation ins = new Instrumentation();
        ins.activate(true);
        ins.startTiming("main");

        ins.startTiming("populate");
        int[] array = populateArray(30000);
        ins.stopTiming("populate");
        int[] bubble = array.clone();
        int[] quick = array.clone();

        ins.startTiming("bubble sort");
        BubbleSort2Algorithm.sort(bubble);
        ins.stopTiming("bubble sort");

        ins.startTiming("quick sort");
        QSortAlgorithm.sort(quick);
        ins.stopTiming("quick sort");

        ins.stopTiming("main");
        ins.dump("output");
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
