import java.util.ArrayList;
import java.util.Random;

public class Main{
    public static final int AGENT_GROUP_NUM = 12;
    public static final int AGENT_NUM_IN_AGENT_GROUP=10;
    public static final float BETA = 0.3f;
    public static final int UPDATE_NUM =100;

    private AgentGroup[] agentGroups;
    public AgentGroup[] getAgentGroups() {
        return agentGroups;
    }

    public static void main(String[] args) {
        Main main = new Main(AGENT_GROUP_NUM,AGENT_NUM_IN_AGENT_GROUP,BETA);
        main.createNetwork();


        for(int i = 0;i<UPDATE_NUM;i++){
            main.Learning();

            main.eval();
        }

    }

    Main(int agentGroupNum,int agentNum,float beta){
        agentGroups=new AgentGroup[agentGroupNum];
        for(int i =0;i<agentGroups.length;i++){
            agentGroups[i]= new AgentGroup(agentNum,beta,i,agentNum);
        }
    }

    public void createNetwork(){
        ArrayList<Agent> pickedUpAgents = new ArrayList<>();
        for(int i =0;i<agentGroups.length;i++){
            pickedUpAgents.addAll(agentGroups[i].pickupAgentsWithBeta());
        }
        connectPickedUpAgents(pickedUpAgents);
    }

    private void connectPickedUpAgents(ArrayList<Agent> agents){
        Random random = new Random();
        for(int i = 0 ;i<agents.size(); i++){
            Agent agent = agents.get(i);

            while(true){
                int randNum = random.nextInt(agents.size());
                Agent randomAgent=agents.get(randNum);

                if(randNum!=i && randomAgent.isConnected(agent)){
                    agent.connect(randomAgent);
                    break;
                }
            }
        }

    }

    public void Learning(){

    }

    public void eval(){

    }

}