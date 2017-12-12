import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Agent {
    Set< Agent> connectedList ;
    AgentGroup myGroup;
    ArrayList<Belief> beliefs;
    ArrayList<Apeal> apeals;
    ArrayList<Performance> performances;
    ArrayList<Talent> talents;
    double p;


    public Agent() {

    }

    public Agent[] connect(Agent opponent) {
        //1.opponentをconnectedListに加える
        //2.opponent側のconnect関数も発動
        //3.opponentと自分をreturn
    }

    public void UpdateMyBelief(Agent agent){
        //learningする
    }

    private Agent findChampion(){
        //コネクトしてる中で一番優秀な人
    }


}
