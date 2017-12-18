import java.util.Random;

public class Talent {
    int value;
    private static final int[] nums={1,0};

    public Talent(int percentage){
        Random rnd= new Random();
        int i= rnd.nextInt(100);
        if(percentage>i){
            this.value=nums[0];
        }else{
            this.value=nums[1];
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
