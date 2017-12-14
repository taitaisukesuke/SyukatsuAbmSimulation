import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.CsvWriter;
import com.orangesignal.csv.handlers.StringArrayListHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Main{
    public static final int AGENT_GROUP_NUM = 12;
    public static final int AGENT_NUM_IN_AGENT_GROUP=10;
    public static final float BETA = 0.3f;
    public static final int UPDATE_NUM =100;
    public static final int gyomuNum = 120;

    private AgentGroup[] agentGroups;
    private final Company company;
    public AgentGroup[] getAgentGroups() {
        return agentGroups;
    }

    public static void main(String[] args) {
        Main main = new Main(AGENT_GROUP_NUM,AGENT_NUM_IN_AGENT_GROUP,BETA);

        System.out.println("start to create network");
        main.createNetwork();


        for(int i = 0;i<UPDATE_NUM;i++){
            System.out.println("start learning");
            main.Learning();

            System.out.println("start evaluating");
            main.eval();
        }
    }

    Main(int agentGroupNum,int agentNum,float beta){
        agentGroups=new AgentGroup[agentGroupNum];
        for(int i =0;i<agentGroups.length;i++){
            agentGroups[i]= new AgentGroup(agentNum,beta,i,gyomuNum);
        }

        List<String[]> first = new ArrayList<>();
        first.add( Arrays.stream(agentGroups)
                .flatMap(agentGroup -> agentGroup.agents.stream().map(agent -> agent.getMyGroup().getId()+"--"+agent.getAgentId()))
                .toArray(String[]::new));

        first.add( Arrays.stream(agentGroups)
                .flatMap(agentGroup -> agentGroup.agents.stream().map(agent -> String.valueOf(agent.getConfidences().stream().mapToInt(Confidence::getValue).sum())))
                .toArray(String[]::new));

        try {
            Csv.save(first, new FileOutputStream("example.csv",true), new CsvConfig(), new StringArrayListHandler());

        } catch (IOException e) {
            e.printStackTrace();
        }
        company = new Company(gyomuNum);
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

                if(randNum!=i && !randomAgent.isConnected(agent)){
                    agent.connect(randomAgent);

                    break;
                }
            }
        }
    }

    public void Learning(){
        for(AgentGroup agentGroup :agentGroups){
            agentGroup.learningAllAgents();
        }
    }

    public void eval(){
        List<String[]> result = new ArrayList<>();
        result.add(Arrays.stream(agentGroups)
                .flatMap(agentGroup -> agentGroup.agents.stream().map(agent -> String.valueOf(company.evaluate(agent))))
                .toArray(String[]::new));

        try {
            Csv.save(result, new FileOutputStream("example.csv",true), new CsvConfig(), new StringArrayListHandler());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}