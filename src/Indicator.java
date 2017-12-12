import java.util.Random;
import java.util.ArrayList;


class Indicator{
<<<<<<< HEAD
    private static final int[] choices={0,1};
    private final int weight =0;
=======
    private int weight;
    private static final int[] choices={0,1};

>>>>>>> 1847aaffa81b85c4a151640149852bf037999528

    public  Indicator(){
        Random rnd = new Random();
        int num=rnd.nextInt(2);
        this.weight=choices[num];
        //未完成です。
    }

    public int evaluate(Performance performance)
    {
        int result;
        result=performance.value*weight;
        return result;
    }

}