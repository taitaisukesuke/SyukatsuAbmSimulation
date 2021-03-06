

import javafx.scene.canvas.Canvas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;



public class Agent {
    ArrayList<Agent> connectedList = new ArrayList<>();
    private final AgentGroup myGroup;
    private final int agentId;
    private final ArrayList<Performance> performances = new ArrayList<Performance>();
    int score = 0;

    private final double x;
    private final  double y;
    private final Canvas canvas;

    public Agent(int agentId, int capacity, AgentGroup myGroup){
        this.agentId = agentId;
        this.myGroup = myGroup;


        int random = new Random().nextInt(100);

        for(int index = 0;index < capacity;index++){
            this.performances.add(new Performance(new Appeal(random),new Talent(random)));
        }

        this.x=0;
        this.y=0;
        this.canvas=null;
    }

    public Agent( int agentId,int capacity,AgentGroup myGroup, double x, double y,Canvas canvas) {
        this.myGroup = myGroup;
        this.agentId = agentId;
        this.canvas= canvas;
        this.x = x;
        this.y = y;

        canvas.getGraphicsContext2D().fillOval(x,y,10,10);

        int random = new Random().nextInt(100);

        for(int index = 0;index < capacity;index++){
            this.performances.add(new Performance(new Appeal(random),new Talent(random)));
        }
    }

    public List<Confidence> getConfidences() {
        return performances.stream().map(performance -> performance.getAppeal().getConfidence()).collect(Collectors.toList());
    }

    public Confidence getConfidence(int num){
        return performances.get(num).getAppeal().getConfidence();
    }

    public List<Belief> getBelieves() {
        return performances.stream().map(performance -> performance.getAppeal().getBelief()).collect(Collectors.toList());
    }

    public Belief getBelief(int num){
        return performances.get(num).getAppeal().getBelief();
    }

    public void setBelief(int num,int value){
        performances.get(num).setBelief(new Belief(value));
    }

    public List<Talent> getTalents() {
        return performances.stream().map(Performance::getTalent).collect(Collectors.toList());
    }

    public Talent getTalent(int num){return performances.get(num).getTalent();}

    public List<Appeal> getAppeals() {
        return performances.stream().map(Performance::getAppeal).collect(Collectors.toList());
    }

    public List<Performance> getPerformances() {
        return performances;
    }
    public Performance getPerformance(int num){
        return performances.get(num);
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public AgentGroup getMyGroup() {
        return myGroup;
    }
    public int getAgentId() {
        return agentId;
    }


    /**
     * テスト用ゲッター
     * @return connectedList
     */
    public ArrayList<Agent> getConnectedList() {
        return connectedList;
    }


    /**
     *   visualize用ゲッター
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Agent[] connect(Agent opponent) {
        if(opponent !=null && !isConnected(opponent)&& !opponent.isConnected(this) ){
            connectedList.add(opponent);
            //1.opponentをconnectedListに加える
            opponent.connect(this);
            canvas.getGraphicsContext2D().strokeLine(getX()+5,getY()+5,opponent.getX()+5,opponent.getY()+5);
            //2.opponent側のconnect関数も発動
            System.out.println(this.getMyGroup().getId()+","+this.getAgentId()+"connected" + opponent.getMyGroup().getId()+","+opponent.getAgentId());
            return new Agent[]{this,opponent};
            //3.opponentと自分をreturn
        }else{
            return null;
        }
    }



    public Agent findChampion(){
//        Random rnd = new Random();
//        int i = rnd.nextInt(connectedList.size());
////        Agent champion = this.connectedList.get(i);
        Agent champion= null;

        for (Agent aConnectedList : connectedList) {
            if (this.getScore() < aConnectedList.getScore()) {
                if (champion == null) {
                    champion = aConnectedList;
                } else if (champion.getScore() < aConnectedList.getScore()) {
                    champion = aConnectedList;

                }
            }
        }
        return champion;
    }


    public int sumConfidences(){
        int sumConfidences = 0;
        for(Confidence confidence : this.getConfidences()){
            sumConfidences += confidence.getValue();
        }
        return sumConfidences;
    }

    public void updateMyBelieves(){
        Agent champion = findChampion();
        if(champion!=null){
            int sumConfidences = this.sumConfidences();
            double learningProb = (double) sumConfidences / this.getConfidences().size();
            System.out.println(learningProb);
            //ForTest
            int count = 0;

            for(int index = 0;index< this.getConfidences().size();index++){
                double p = Math.random();

                if( p >= learningProb ){
                    if (champion.getTalent(index).getValue() == 1 && champion.getBelief(index).getValue() == 1) {
                        this.setBelief(index,1);
                        count++;
                    }else if(champion.getTalent(index).getValue() == 0 && champion.getBelief(index).getValue() == 1) {
                        this.setBelief(index,0);
                        count++;
                    }
                }
            }

            System.out.println(count+"個アップデート");
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
