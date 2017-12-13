import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
public class AgentGroupTest {
    AgentGroup testAgentGroup1;
    AgentGroup testAgentGroup2;
    AgentGroup testAgentGroup3;

    @Before
    public void setUp(){
        testAgentGroup1 = new AgentGroup(10,0f,1,10);
        testAgentGroup2 = new AgentGroup(10,0.3f,2,10);
        testAgentGroup3 = new AgentGroup(10,0.7f,3,10);
    }

    @Test
    public void getAgentsでagentが10個取得できる(){
        assertThat(testAgentGroup1.getAgents().size(), CoreMatchers.is(10));
    }

    @Test
    public void setTestAgentGroup1のpickupAgentsWithBetaが0個のエージェントを返す(){
        ArrayList<Agent> pickUppedAgents=testAgentGroup1.pickupAgentsWithBeta();
        assertThat(pickUppedAgents.size(),CoreMatchers.is(0));
    }


    @Test
    public void setTestAgentGroup2のpickupAgentsWithBetaが3個のエージェントを返す(){
        ArrayList<Agent> pickUppedAgents=testAgentGroup2.pickupAgentsWithBeta();
        assertThat(pickUppedAgents.size(),CoreMatchers.is(3));
    }

    @Test
    public void setTestAgentGroup3のpickupAgentsWithBetaが7個のエージェントを返す(){
        ArrayList<Agent> pickUppedAgents=testAgentGroup3.pickupAgentsWithBeta();
        assertThat(pickUppedAgents.size(),CoreMatchers.is(7));
    }

    @Test
    public void connectAllAgentsによってすべてのAgentがつながる(){
        testAgentGroup1.connectAllAgents();
        ArrayList<Agent> agents = testAgentGroup1.getAgents();

        for(int i = 0 ;i<agents.size();i++){
            for(int j =0;j<agents.size();j++){
                if(i!=j){
                    agents.get(i).isConnected(agents.get(j));
                }
            }
        }
    }






}
