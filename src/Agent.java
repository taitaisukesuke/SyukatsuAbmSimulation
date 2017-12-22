import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;


public class Agent {
    ArrayList<Agent> connectedList = new ArrayList<>();
    private final AgentGroup myGroup;
    final int agentId;
    private final ArrayList<Performance> performances = new ArrayList<Performance>();
    int score = 0;
    private boolean isGifted=true;


    public Agent(int agentId, int capacity, AgentGroup myGroup) {
        this.agentId = agentId;
        this.myGroup = myGroup;

        int random = new Random().nextInt(100);
        int randomt = new Random().nextInt(100);

        for (int index = 0; index < capacity; index++) {
            this.performances.add(new Performance(new Appeal(random), new Talent(randomt)));
        }
    }

    public void setGifted(boolean isGifted){
        this.isGifted=isGifted;
    }

    public List<Confidence> getConfidences() {
        return performances.stream().map(performance -> performance.getAppeal().getConfidence()).collect(Collectors.toList());
    }

    public Confidence getConfidence(int num) {
        return performances.get(num).getAppeal().getConfidence();
    }

    public List<Belief> getBelieves() {
        return performances.stream().map(performance -> performance.getAppeal().getBelief()).collect(Collectors.toList());
    }

    public Belief getBelief(int num) {
        return performances.get(num).getAppeal().getBelief();
    }

    public void setBelief(int num, int value) {
        performances.get(num).setBelief(new Belief(value));
    }

    public List<Talent> getTalents() {
        return performances.stream().map(Performance::getTalent).collect(Collectors.toList());
    }

    public Talent getTalent(int num) {
        return performances.get(num).getTalent();
    }

    public int getSumTalent(){
        int sum=0;
        for(int i=0;i<performances.size();i++){
            sum+=performances.get(i).getTalent().getValue();
        }
        return sum;
    }


    public List<Appeal> getAppeals() {
        return performances.stream().map(Performance::getAppeal).collect(Collectors.toList());
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public Performance getPerformance(int num) {
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
     *
     * @return connectedList
     */
    public ArrayList<Agent> getConnectedList() {
        return connectedList;
    }


    public Agent[] connect(Agent opponent) {
        if (opponent != null && !isConnected(opponent) && !opponent.isConnected(this)) {
            connectedList.add(opponent);
            //1.opponentをconnectedListに加える
            opponent.connect(this);

            //2.opponent側のconnect関数も発動

            System.out.println(this.getMyGroup().getId() + "," + this.getAgentId() + "connected" + opponent.getMyGroup().getId() + "," + opponent.getAgentId());
            return new Agent[]{this, opponent};
            //3.opponentと自分をreturn
        } else {
            return null;
        }
    }

    public int sumConfidences() {
        int sumConfidences = 0;
        for (Confidence confidence : this.getConfidences()) {
            sumConfidences += confidence.getValue();
        }
        return sumConfidences;
    }

    public void updateMyBelieves() {
        if(isGifted){
            ArrayList<Integer> calculatedScores = new ArrayList<>();

           for(int i =0 ; i<this.getBelieves().size();i++){
               int calculatedScore =0;
               for(Agent higherAgent: getHighreScoreAgents()){
                   if(higherAgent.getBelief(i).getValue()==1 &&higherAgent.getTalent(i).getValue()==1 ){
                       calculatedScore+=1;
                   }else if(higherAgent.getBelief(i).getValue()==1 && higherAgent.getTalent(i).getValue()==-1){
                       calculatedScore-=1;
                   }
               }

               calculatedScores.add(calculatedScore);
           }



            int sumConfidences = this.sumConfidences();
            double learningProb = (double) sumConfidences / this.getConfidences().size();
            int count = 0;

            for(int i =0;i < calculatedScores.size();i++){
                double p = Math.random();

                if (p >= learningProb) {
                    if(calculatedScores.get(i) > 0){
                        this.setBelief(i,1);
                        count++;
                    }else if (calculatedScores.get(i) < 0){
                        this.setBelief(i,0);
                        count++;
                    }
                }
            }

            System.out.println(calculatedScores.size()+"中"+count + "個アップデート");

        }else{
            Agent champion = findChampion();
            if (champion != null) {
                int sumConfidences = this.sumConfidences();
                double learningProb = (double) sumConfidences / this.getConfidences().size();
                System.out.println(learningProb);
                //ForTest
                int count = 0;

                for (int index = 0; index < this.getConfidences().size(); index++) {
                    double p = Math.random();

                    if (p >= learningProb) {
                        if (champion.getTalent(index).getValue() == 1 && champion.getBelief(index).getValue() == 1) {
                            this.setBelief(index, 1);
                            count++;
                        } else if (champion.getTalent(index).getValue() == -1 && champion.getBelief(index).getValue() == 1) {
                            this.setBelief(index, 0);
                            count++;
                        }
                    }
                }

                System.out.println(count + "個アップデート");
            }

        }

    }


    private List<Agent> getHighreScoreAgents(){
        return connectedList.stream().filter(agent -> this.getScore()<agent.getScore()).collect(Collectors.toList());
    }

    public boolean isConnected(Agent agent) {
        return this.connectedList.contains(agent) && agent.getConnectedList().contains(this);
    }

//    一番高いAgent対象
//    public Agent findChampion(){
//
//        Agent champion = null;
//
//        for (int i=0;i<connectedList.size();i++){
//            if(this.getScore()<connectedList.get(i).getScore()){
//                if(champion == null){
//                    champion=connectedList.get(i);
//                }else if(champion.getScore() < connectedList.get(i).getScore()){
//                    champion=connectedList.get(i);
//                }
//            }
//        }
//        return champion;
//    }

//    能力が自分と近いと思われるもの（T＝C）
//    public Agent findChampion() {
//        Agent champion = this;
//        int champNum = 0;
//
//        for (Agent agent : connectedList) {
//            int sum = 0;
//            if (this.getScore() < agent.getScore()) {
//                for (int i = 0; i < agent.getTalents().size(); i++) {
//                    if (agent.getTalent(i).getValue() == champion.getConfidence(i).getValue()) {
//                        sum += 1;
//                    }
//                    if (sum >= champNum) {
//                        champion = agent;
//                        champNum = sum;
//                    }
//                }
//            }
//        }
//        return champion;
//    }

//    T=Cの数が一番多いもの
//    public Agent findChampion() {
//        Agent champion = this;
//        int champNum = 0;
//
//        for (Agent agent : connectedList) {
//            int sum = 0;
//            if (this.getScore() < agent.getScore()) {
//                for (int i = 0; i < agent.getTalents().size(); i++) {
//                    if (agent.getTalent(i).getValue() == agent.getConfidence(i).getValue()) {
//                        sum += 1;
//                    }
//                    if (sum >= champNum) {
//                        champion = agent;
//                        champNum = sum;
//                    }
//                }
//            }
//        }
//        return champion;
//    }

    //    自分以上のスコアのagentの多決
    public Agent findChampion() {

        Agent champion = null;

        for (int i = 0; i < connectedList.size(); i++) {
            if (this.getScore() < connectedList.get(i).getScore()) {
                if (champion == null) {
                    champion = connectedList.get(i);
                } else if (champion.getScore() < connectedList.get(i).getScore()) {
                    champion = connectedList.get(i);
                }
            }
        }
        return champion;
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
