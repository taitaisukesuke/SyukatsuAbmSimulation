import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class AgentGroup {
    ArrayList<Agent> agents=new ArrayList<>() ;
    private final int agentsNum;
    private final float beta;
    private final int id;


    public AgentGroup(int agentsNum,float beta,int id, int agentCapacity){
        this.agentsNum=agentsNum;
        this.beta=beta;
        this.id=id;
        for(int i=0;i<agentsNum;i++){
            agents.add(new Agent(i, agentCapacity,this));
        }
        connectAllAgents();
    }


    //for visualize
    public AgentGroup(int agentsNum, float beta, int id,int agentCapacity, double centerX, double centerY,Canvas canvas) {
        this.agentsNum = agentsNum;
        this.beta = beta;
        this.id = id;

        for(int i=0;i<agentsNum;i++){
            agents.add(new Agent(i, agentCapacity,this,centerX+60*Math.cos((double) (i+1)/ agentsNum*2*Math.PI),centerY+60*Math.sin((double)(i+1)/agentsNum*2*Math.PI),canvas));
        }
        connectAllAgents();
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }
    public int getAgentsNum() {
        return agentsNum;
    }
    public float getBeta() {
        return beta;
    }
    public int getId() {
        return id;
    }


    public ArrayList<Agent> pickupAgentsWithBeta() {
        int a;
        ArrayList<Agent> pickupAgents=new ArrayList<>();
        a= (int) (this.agentsNum * this.beta);
        for(int i=0;i<a;i++) {
            while(true){
                int random = new java.util.Random().nextInt(agentsNum);

                if(!pickupAgents.contains(agents.get(random))){
                    pickupAgents.add(agents.get(random));
                    break;
                }
            }
        }
        return pickupAgents;
    }

    public void connectAllAgents(){
        for(int i=0;i<getAgents().size();i++){
            for(int j=0;j<getAgents().size();j++){
                agents.get(i).connect(agents.get(j));
            }

        }
    }//自分のグループに属するエージェントとつながる(connect関数)


    public void learningAllAgents(){
        for(Agent agent :agents){
           agent.updateMyBelieves();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentGroup that = (AgentGroup) o;
        return id == that.id;
    }
}