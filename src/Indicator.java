import java.util.Random;
import java.util.ArrayList;


class Indicator{
    private int weight;
    private static final int[] choices={0,1};


    public  Indicator(){
        Random rnd = new Random();
        int num=rnd.nextInt(2);
        this.weight=choices[num];
        //未完成です。
    }

    public int evaluate(int apeal){

    }

}