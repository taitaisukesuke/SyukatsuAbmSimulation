import java.util.Random;

public class Appeal {
//    private Belief belief;
//    private  Confidence confidence;

    int value;
    private static final int[] nums={1,-1};

    public Appeal(int percentage){
//        this.confidence = new Confidence(percentage);
//        this.belief = new Belief();
        Random rnd= new Random();
        int i= rnd.nextInt(2);
        this.value = nums[i];
    }

//    public int getValue() {
////        return this.belief.getValue()+this.confidence.getValue();
//        if(this.belief.getValue()==1 && this.confidence.getValue()==1){
//            return 1;
//        }else{
//            return 0;
//        }
//    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


//    public Belief getBelief() {
//        return belief;
//    }
//
//    public void setBelief(Belief belief) {
//        this.belief = belief;
//    }
//
//    public Confidence getConfidence() {
//        return confidence;
//    }
}
