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

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public int getAgentsNum() {
        return agentsNum;
    }

    public void setAgentsNum(int agentsNum) {
        this.agentsNum = agentsNum;
    }

    public float getBeta() {
        return beta;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Agent> pickupAgentswithBeta() {
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

        //βに基づいてエージェントを選ぶ関数

    }

    public void connectAllAgents(){
        for(int i=0;i<getAgents().size();i++){
            for(int j=0;i<getAgents().size();j++){
                agents.get(i).connect(agents.get(j));
            }

        }
    }//自分のグループに属するエージェントとつながる(connect関数)


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentGroup that = (AgentGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
