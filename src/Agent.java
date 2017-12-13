import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Agent {
    ArrayList<Agent> connectedList = new ArrayList<>();
    AgentGroup myGroup;
    int agentId;
    ArrayList<Confidence> confidences = new ArrayList<Confidence>();
    ArrayList<Belief> believes = new ArrayList<Belief>();
    ArrayList<Talent> talents = new ArrayList<Talent>();
    ArrayList<Appeal> appeals = new ArrayList<Appeal>();
    ArrayList<Performance> performances = new ArrayList<Performance>();
    int score = 0;


    public Agent(int agentId, int capacity, AgentGroup myGroup){
        this.agentId = agentId;
        this.myGroup = myGroup;

        for(int index = 0;index < capacity;index++){
            addRandomProperties(index);
        }
    }



    private void addRandomProperties(int index){
        this.confidences.add(new Confidence());
        this.believes.add(new Belief());
        this.talents.add(new Talent());
        this.appeals.add(new Appeal(this.believes.get(index),this.confidences.get(index)));
        this.performances.add(new Performance(/*Talent, Appealが必要では？*/));
    }

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



    /**
     * テスト用ゲッター
     * @return connectedList
     */
    public ArrayList<Agent> getConnectedList() {
        return connectedList;
    }



    public Agent[] connect(Agent opponent) {
        if(opponent !=null && !isConnected(opponent)&& !opponent.isConnected(this) ){
            connectedList.add(opponent);
            //1.opponentをconnectedListに加える
            opponent.connect(this);
            //2.opponent側のconnect関数も発動
            return new Agent[]{this,opponent};
            //3.opponentと自分をreturn
        }else{
            return null;
        }
    }

    public AgentGroup getMyGroup() {
        return myGroup;
    }

    public int getAgentId() {
        return agentId;
    }

    public Agent findChampion(){
        Agent champion= null;
        for(int i=0;i<connectedList.size();i++){
            if(champion==null){
                champion=connectedList.get(i);
            }else if(champion.getScore()<connectedList.get(i).getScore()){
                champion=connectedList.get(i);
            }
        }
        return champion;
    }


    public int sumConfidences(){
        int sumConfidences = 0;
        for(Confidence confidence : this.confidences){
            sumConfidences += confidence.getValue();
        }
        return sumConfidences;
    }

    public void updateMyBelieves(){
        Agent champion = findChampion();
        int sumConfidences = this.sumConfidences();
        double learningProb = sumConfidences / this.confidences.size();
        double p = Math.random();
        if(p>=learningProb){
            for(int index = 0;index<confidences.size();index++){
                if (champion.getTalents().get(index).getValue() == 1 && champion.getBelieves().get(index).getValue() == 1) {
                    this.believes.get(index).setValue(1);
                }else if(champion.getTalents().get(index).getValue() == -1 && champion.getBelieves().get(index).getValue() == 1) {
                    this.believes.get(index).setValue(0);
                }
            }
        }
    }

    public boolean isConnected(Agent agent){
        return this.connectedList.contains(agent) && agent.getConnectedList().contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return agentId == agent.agentId &&
                Objects.equals(myGroup, agent.myGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId);
    }
}
