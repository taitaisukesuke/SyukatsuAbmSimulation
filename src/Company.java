import java.util.ArrayList;

class Company{
    Indicator[] indicators;

    public Company(){
        //ここでつくる
    }

    public int evaluate(Agent agent){
        int result =0;
        for(int i=0;i<this.indicators.length;i++)
        {
            indicators[i].evaluate(agent.performances.get(i));
            result=result+indicators[i].evaluate(agent.performances.get(i));
        }
        return result;
        //評価する
    }
}