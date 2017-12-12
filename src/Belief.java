import java.util.Random;

public class Belief {
    private int value;
    private final int[] nums={0,1};

    public Belief(){
        Random rnd= new Random();
        int i= rnd.nextInt(2);
        this.value=nums[i];
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
