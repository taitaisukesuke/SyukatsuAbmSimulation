import java.util.ArrayList;
import java.util.Random;

class Company{
    Indicator[] indicators;
    private final int[] nums={0,1};

    public Company(int len){
        for (int i = 0 ; i < len ; i++){
            this.indicators[i] = new Indicator();
        }
    }

    public int evaluate(Agent agent){
        int result =0;
        for(int i=0;i<this.indicators.length;i++)
        {
            indicators[i].evaluate(agent.performances.get(i));
            result=result+indicators[i].evaluate(agent.performances.get(i));
        }
        return result;
    }
}