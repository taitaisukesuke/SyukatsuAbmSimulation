import java.util.Random;
import java.util.ArrayList;


class Indicator{
    private static final int[] choices = {0,1};
    private final int weight;

    public  Indicator(){
        Random rnd = new Random();
        int num = rnd.nextInt(2);
        this.weight = choices[num];
    }

    public int evaluate(Performance performance)
    {
        int result;
        result = performance.getValue() *  weight;
        return result;
    }

    public int evaluateTalent(Performance performance){
       int talent= performance.getTalent().getValue();

       return talent *weight;
    }

}