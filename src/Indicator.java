import java.util.Random;
import java.util.ArrayList;


class Indicator{
    private static final int[] choices = {0,1};
    private  int weight = 0;

    public  Indicator(){
        Random rnd = new Random();
        int num = rnd.nextInt(2);
        this.weight = choices[num];
    }

    public int evaluate(Performance performance)
    {
        int result;
        result=performance.value*weight;
        return result;
    }

}