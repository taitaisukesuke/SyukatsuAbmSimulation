import java.util.Random;

public class Confidence {
    private final int value;
    private static final int[] nums={0,1};

    public Confidence(int percentage){
        Random rnd= new Random();
        int i= rnd.nextInt(2);
        this.value=nums[i];

//        Random rnd= new Random();
//        int i= rnd.nextInt(100);
//
//        if(percentage>i){
//            this.value=nums[0];
//        }else{
//            this.value=nums[1];
//        }

    }

    public int getValue() {
        return value;
    }

}
