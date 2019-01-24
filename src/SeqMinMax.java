import java.util.ArrayList;

public class SeqMinMax {
    public void getMinMax(ArrayList<Integer> list){
        int min = list.get(0);
        int max = list.get(0);
        for (Integer number: list) {
            if (number<min){
                min = number;
            }
            if(number>max){
                max = number;
            }
        }
    }
}
