import java.util.ArrayList;

public class AgentGroup {
    ArrayList<Agent> agents=new ArrayList<>() ;
    int agentsNum;
    float beta;
    int id;


    public AgentGroup(int agentsNum,float beta,int id){
        this.agentsNum=agentsNum;
        this.beta=beta;
        this.id=id;
        for(int i=0;i<agentsNum;i++){
            agents.add(new Agent());
        }
    }

    public ArrayList<Agent> pickupAgentswithBeta() {
        //βに基づいてエージェントを選ぶ関数

    }

    public void connectAllAgents(){
        //自分のグループに属するエージェントとつながる(connect関数)
    }

}
