import java.util.ArrayList;
import java.util.Random;

class Company{
    Indicator[] indicators;
    private final int[] nums={0,1};

    public Company(int len){
        indicators=new Indicator[len];

        for (int i = 0 ; i < len ; i++){
            this.indicators[i] = new Indicator();
        }
        System.out.print("Company:");
        System.out.println(indicators);
    }

    public int evaluate(Agent agent){
        int result =0;
        for(int i=0;i<this.indicators.length;i++)
        {
            result=result+ indicators[i].evaluate(agent.getPerformances().get(i));
        }

        System.out.print("groupId:" +agent.getMyGroup().getId()+"agentId: "+agent.getAgentId());
        System.out.print(" result: " + result);
        agent.setScore(result);
        return result;
    }
    public int evaluateTalent(Agent agent){
        int result =0;


        for(int i=0;i<this.indicators.length;i++)
        {
            result=result+ indicators[i].evaluateTalent(agent.getPerformances().get(i));
        }

        System.out.print("groupId:" +agent.getMyGroup().getId()+"agentId: "+agent.getAgentId());
        System.out.print(" talent_result: " + result);
        return result;

    }


}