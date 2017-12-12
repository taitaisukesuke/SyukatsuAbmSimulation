import java.util.Random;
import java.util.ArrayList;


class Indicator{
    private static final int[] choices={0,1};
    private final int weight =0;

    public  Indicator(){
        Random rnd = new Random();


        int ran = (10);
        System.out.println(ran);

        //未完成です。
    }

    public int evaluate(Performance performance)
    {
        int result;
        result=performance.value*weight;
        return result;
    }

}