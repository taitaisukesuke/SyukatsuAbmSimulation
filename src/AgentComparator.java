import java.util.Comparator;

public class AgentComparator implements Comparator<Agent>{

    public int compare(Agent a,Agent b){
        int no1=a.getSumTalent();
        int no2=b.getSumTalent();

        if(no1>no2){
            return 1;
        }else if(no1==no2){
            return 0;
        }else{
            return -1;
        }
    }
}
