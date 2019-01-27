import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public static void main(String[] args) {
        new main().run();
    }
    public void run(){
        //List of all array sizes of the array to be generated for each algorithm
        int valueList[] = new int[]{ 5000, 10000, 50000,  100000, 500000,
                1000000, 5000000, 10000000, 50000000, 100000000};
        int repetitions = 10;
        long sortHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        long seqHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        long recHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        //These arrays store the averages of each algorithm for every array size
        long finalResultListSort[] = new long[valueList.length];
        long finalResultListSeq[] = new long[valueList.length];
        long finalResultListRec[] = new long[valueList.length];
        for(int k = 0;k<valueList.length; k++){
            int numberCount = valueList[k];
            //These arrays are used to store the results of the repetitions, used for printing output and for calculating averages
            long sortedMinMaxList[] = new long[repetitions];
            long seqMinMaxList[] = new long[repetitions];
            long recMinMaxList[] = new long[repetitions];
            for(int i= 0;i<repetitions;i++){
//                sortedMinMaxList[i] = timeFunction(1,numberCount);
                seqMinMaxList[i] = timeFunction(2, numberCount);
                recMinMaxList[i] = timeFunction(3, numberCount);
                if(sortedMinMaxList[i]>sortHighest[k]){
                    sortHighest[k] = sortedMinMaxList[i];
                }
                if(seqMinMaxList[i]>seqHighest[k]){
                    seqHighest[k] = seqMinMaxList[i];
                }
                if(recMinMaxList[i]>recHighest[k]){
                    recHighest[k] = recMinMaxList[i];
                }
            }
            //These totals are used for calculating the averages of each algorithm
            long sortedTotal = 0;
            long  seqTotal = 0;
            long recTotal = 0;
            for (int i = 0; i<repetitions;i++){
                sortedTotal+= sortedMinMaxList[i];
                seqTotal+= seqMinMaxList[i];
                recTotal+= recMinMaxList[i];
            }
            //Printing of the final results
            System.out.println(ANSI_GREEN+"***Amount of numbers: "+ numberCount+"***"+"\n-----------------------------------------"+ ANSI_RESET);
            System.out.print("Sorted list results: ["+sortedMinMaxList[0]);
            printResults(sortedMinMaxList);
            finalResultListSort[k] = sortedTotal/repetitions;
            System.out.println("]\nSortedMinMax average duration: "+ (sortedTotal/repetitions)+" nanoseconds"+"\n-----------------------------------------");
            System.out.print("SeqMinMax list results: ["+seqMinMaxList[0]);
            printResults(seqMinMaxList);
            finalResultListSeq[k] = seqTotal/repetitions;
            System.out.println("]\nSeqMinMax average duration: "+ (seqTotal/repetitions)+" nanoseconds"+"\n-----------------------------------------");
            System.out.print("rec list results: ["+recMinMaxList[0]);
            printResults(recMinMaxList);
            finalResultListRec[k] = recTotal/repetitions;
            System.out.println("]\nRecMinMax average duration: "+ (recTotal/repetitions)+" nanoseconds");
        }
        System.out.print("\n"+ANSI_PURPLE+"****Final Results(In nanoseconds)****"+ANSI_RESET+"\n\nSorted Min Max Averages: ["+ finalResultListSort[0]);
        printResults(finalResultListSort);
        System.out.print("]\n\nSequential min max Averages: ["+finalResultListSeq[0]);
        printResults(finalResultListSeq);
        System.out.print("]\n\nRecursive min max Averages: ["+finalResultListRec[0]);
        printResults(finalResultListRec);
        System.out.print("]\n\nHighest times: \nSorted Min Max highest: ["+sortHighest[0]+"|");
        printResults(sortHighest);
        System.out.print("]\nSeqMinMax highest: ["+seqHighest[0]+"|");
        printResults(seqHighest);
        System.out.print("]\n");
        System.out.print("RecMinMax highest: ["+recHighest[0]+"|");
        printResults(recHighest);
        System.out.print("]\n");
    }
    /**
    * Function that captures the start time of the each algorithm before running it, as well as the end time, and then uses these two values to calculate the duration
     * choice: Used to select which algorithm to run(1 = SortedMinMax, 2 = SeqMinMax and 3 = RecMinMax)
    **/
    public long timeFunction(int choice,int amount){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0;i<amount;i++){
            list.add((int)(Math.random()*100)+1);
        }
        long startTime = System.nanoTime();
        switch (choice){
            case 1:
                SortedMinMax sorted = new SortedMinMax();
                sorted.getMinMax(list);
                break;
            case 2:
                SeqMinMax sequential = new SeqMinMax();
                sequential.getMinMax(list);
                break;
            case 3:
                RecMinMax recMinMax = new RecMinMax();
                recMinMax.getMinMax(list);
                break;
                default:
                    System.out.println("Invalid choice");
                    break;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return duration;
    }
    //Helper method used to more easily print the contents of an array
    public void printResults(long[] arrayToPrint){
        for (int i = 1; i<arrayToPrint.length; i++){
            System.out.print("|"+arrayToPrint[i]);
        }
    }
}
