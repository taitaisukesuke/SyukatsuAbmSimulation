import java.util.Random;

public class Confidence {
    private final int value;
    private static final int[] nums={0,1};

    public Confidence(){
        Random rnd= new Random();
        int i= rnd.nextInt(2);
        this.value=nums[i];
    }

    public int getValue() {
        return value;
    }

}
