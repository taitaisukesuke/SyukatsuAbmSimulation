import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
public class AgentTest {
    Agent agent1;
    Agent agent2;
    Agent agent3;
    AgentGroup testAgentGroup;
    @Before
    public void setUp() throws Exception{
        testAgentGroup = new AgentGroup(3,0,1,3);
        agent1 = testAgentGroup.getAgents().get(0);
        agent2  =testAgentGroup.getAgents().get(1);
        agent3  = testAgentGroup.getAgents().get(2);

        agent1.setScore(1);
        agent2.setScore(2);
        agent3.setScore(3);
    }


    @Test
    public void connectメソッドによってエージェント同士をつなぐ(){

        agent1.connect(agent2);

        assertTrue(agent1.getConnectedList().contains(agent2));
        assertTrue(agent2.getConnectedList().contains(agent1));
    }



    @Test
    public void isConnectメソッドにconnectしたエージェントをいれるとTrueを返す(){

        agent1.connect(agent2);

        assertTrue(agent1.isConnected(agent2));
        assertTrue(agent2.isConnected(agent1));
    }

    @Test
    public void isConnectメソッドにconnectしていないエージェントをいれるとFalseを返す(){

        agent1.connect(agent2);

        assertFalse(agent1.isConnected(agent3));
        assertFalse(agent3.isConnected(agent1));
    }



    @Test
    public void connectメソッドを2回読んでも２回目はgetConnectedListに追加されない(){

        agent1.connect(agent2);
        agent1.connect(agent2);

        assertThat(agent1.getConnectedList().size(), CoreMatchers.is(1));
        assertThat(agent1.getConnectedList().size(), CoreMatchers.is(1));
    }


    @Test
    public void connectメソッドがつながったエージェント２個を返す(){

        Agent[] agents = agent1.connect(agent2);

        assertThat(agents.length,CoreMatchers.is(2));
        assertTrue(agents[0].equals(agent1));
        assertTrue(agents[1].equals(agent2));

    }
    @Test
    public void connectメソッドの引数にnullいれてもArrayListにnullが追加されない(){

        agent1.connect(null);

        assertFalse(agent1.getConnectedList().contains(null));
        assertThat(agent1.getConnectedList().size(), CoreMatchers.is(0));
    }

    @Test
    public void findChampionメソッドがもっともスコアの高いエージェント３を返す(){

        agent1.connect(agent2);
        agent1.connect(agent3);

        assertTrue(agent2.getScore()<agent3.getScore());
        assertThat(agent1.findChampion(),CoreMatchers.is(agent3));

    }

}
