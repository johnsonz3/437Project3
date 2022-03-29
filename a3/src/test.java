import Sorts.BubbleSort2Algorithm;
import Sorts.QSortAlgorithm;

import java.lang.Math;
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
        ins.dump();
        ins.activate(false);


    }
    public static int[] populateArray(int size)
    {
        int[] array = new int[size];
        for(int i=0; i<size; i++)
        {
            array[i] = (int) (Math.random() * 999999)+1;
        }
        return array;
    }
}
