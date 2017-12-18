import java.util.Random;

public class Talent {
    int value;
    private static final int[] nums={1,0};

    public Talent(){
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
