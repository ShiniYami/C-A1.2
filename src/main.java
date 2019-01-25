import java.util.ArrayList;


public class main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public static void main(String[] args) {
        new main().run();
    }
    public void run(){


        int valueList[] = new int[]{ 5000, 10000, 50000,  100000, 500000,
                1000000, 5000000, 10000000, 50000000, 100000000 };
        int repetitions = 10;
        long sortHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        long seqHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        long recHighest[] = new long[]{0,0,0,0,0,0,0,0,0,0};
        long finalResultListSort[] = new long[valueList.length];
        long finalResultListSeq[] = new long[valueList.length];
        long finalResultListReq[] = new long[valueList.length];
        for(int k = 0;k<valueList.length; k++){
            int numberCount = valueList[k];
            long sortedMinMaxList[] = new long[repetitions];
            long seqMinMaxList[] = new long[repetitions];
            long reqMinMaxList[] = new long[repetitions];
            for(int i= 0;i<repetitions;i++){
//                sortedMinMaxList[i] = timeFunction(1,numberCount);
                seqMinMaxList[i] = timeFunction(2, numberCount);
                reqMinMaxList[i] = timeFunction(3, numberCount);
                if(sortedMinMaxList[i]>sortHighest[k]){
                    sortHighest[k] = sortedMinMaxList[i];
                }
                if(seqMinMaxList[i]>seqHighest[k]){
                    seqHighest[k] = seqMinMaxList[i];
                }
                if(reqMinMaxList[i]>recHighest[k]){
                    recHighest[k] = reqMinMaxList[i];
                }
            }
            long sortedTotal = 0;
            long  seqTotal = 0;
            long reqTotal = 0;
            for (int i = 0; i<repetitions;i++){
                sortedTotal+= sortedMinMaxList[i];
                seqTotal+= seqMinMaxList[i];
                reqTotal+= reqMinMaxList[i];
            }
            System.out.println(ANSI_GREEN+"***Amount of numbers: "+ numberCount+"***"+"\n-----------------------------------------"+ ANSI_RESET);
            System.out.print("Sorted list results: [");
            for(int i = 0;i<repetitions;i++){
                System.out.print(sortedMinMaxList[i]+" | ");
            }
            finalResultListSort[k] = sortedTotal/repetitions;
            System.out.println("]\nSortedMinMax average duration: "+ (sortedTotal/repetitions)+" nanoseconds"+"\n-----------------------------------------");
            System.out.print("SeqMinMax list results: [");
            for(int i = 0;i<repetitions;i++){
                System.out.print(seqMinMaxList[i]+" | ");
            }
            finalResultListSeq[k] = seqTotal/repetitions;
            System.out.println("]\nSeqMinMax average duration: "+ (seqTotal/repetitions)+" nanoseconds"+"\n-----------------------------------------");
            System.out.print("Req list results: [");
            for(int i = 0;i<repetitions;i++){
                System.out.print(reqMinMaxList[i]+" | ");
            }
            finalResultListReq[k] = reqTotal/repetitions;
            System.out.println("]\nRecMinMax average duration: "+ (reqTotal/repetitions)+" nanoseconds");
        }
        System.out.print("\n"+ANSI_PURPLE+"****Final Results(In nanoseconds)****"+ANSI_RESET+"\n\nSorted Min Max Averages: ["+ finalResultListSort[0]);
        for (int i = 1; i<finalResultListSort.length; i++){
            System.out.print("|"+finalResultListSort[i]);
        }
        System.out.print("]\n\nSequential min max Averages: ["+finalResultListSeq[0]);
        for (int i = 1; i<finalResultListSeq.length; i++){
            System.out.print("|"+finalResultListSeq[i]);
        }
        System.out.print("]\n\nRecursive min max Averages: ["+finalResultListReq[0]);
        for (int i = 1; i<finalResultListReq.length; i++){
            System.out.print("|"+finalResultListReq[i]);
        }
        System.out.print("\n\nHighest times: \nSorted Min Max highest: [");
        System.out.print(sortHighest[0]+"|");
        for(int i = 1;i<sortHighest.length;i++){
            System.out.print(sortHighest[i]+"|");
        }
        System.out.print("]\n SeqMinMax highest: ["+seqHighest[0]+"|");
        for(int i = 1;i<seqHighest.length;i++){
            System.out.print(seqHighest[i]+"|");
        }
        System.out.print("]\n");
        System.out.print("RecMinMax highest: ["+recHighest[0]+"|");
        for(int i = 1;i<recHighest.length;i++){
            System.out.print(recHighest[i]+"|");
        }
        System.out.print("]\n");
    }

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
}
