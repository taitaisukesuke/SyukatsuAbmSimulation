import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Agent {
    HashSet< Agent> connectedList = new HashSet<>();
    AgentGroup myGroup;
    int agentId;
    ArrayList<Confidence> confidences;
    ArrayList<Belief> believes;
    ArrayList<Talent> talents;
    ArrayList<Appeal> appeals;
    ArrayList<Performance> performances;
    int score = 0;


    public ArrayList<Confidence> getConfidences() {
        return confidences;
    }
    public void setConfidences(ArrayList<Confidence> confidences) {
        this.confidences = confidences;
    }

    public ArrayList<Belief> getBelieves() {
        return believes;
    }
    public void setBelieves(ArrayList<Belief> believes) {
        this.believes = believes;
    }

    public ArrayList<Talent> getTalents() {
        return talents;
    }
    public void setTalents(ArrayList<Talent> talents) {
        this.talents = talents;
    }

    public ArrayList<Appeal> getAppeals() {
        return appeals;
    }
    public void setAppeals(ArrayList<Appeal> appeals) {
        this.appeals = appeals;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }
    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }


    public Agent(){

    }

    public Agent[] connect(Agent opponent) {
        connectedList.add(opponent);
        //1.opponentをconnectedListに加える
        opponent.connect(this);
        //2.opponent側のconnect関数も発動
        Agent[] connectedAgents = {opponent,this};
        return connectedAgents;
        //3.opponentと自分をreturn
    }

    public AgentGroup getMyGroup() {
        return myGroup;
    }

    public int getAgentId() {
        return agentId;
    }

    public Agent findChampion(){
        Agent champion= null;
        Agent[] agents = (Agent[]) connectedList.toArray();
        for(int i=0;i<agents.length;i++){
            if(champion==null){
                champion=agents[i];
            }else if(champion.getScore()<agents[i].getScore()){
                champion=agents[i];
            }
        }
        return champion;
    }

    public void updateMyBelieves(){
        Agent champion = findChampion();
        for(int index = 0;index<confidences.size();index++){
            if (champion.getTalents().get(index) == 1 && champion.getBelieves().get(index) == 1) {
                this.believes.add(index, 1);
            } else if (champion.getTalents().get(index) == -1 && champion.getBelieves().get(index) == 1) {
                this.believes.add(index, 0);
            }
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return agentId == agent.agentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId);
    }
}
