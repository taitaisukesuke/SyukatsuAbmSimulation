import java.util.ArrayList;
import java.util.HashSet;



public class Agent {
    Set<int Agent> connectedList = new HashSet<>();
    AgentGroup myGroup;
    ArrayList<AgentGroup> connectedGroup = new ArrayList<AgentGroup>();


    public Agent() {

    }

    public Agent[] connect(Agent opponent) {
        //1.opponentをconnectedListに加える
        //2.opponent側のconnect関数も発動
        //3.opponentと自分をreturn
    }

    public void makeCluster(){

    }

}
