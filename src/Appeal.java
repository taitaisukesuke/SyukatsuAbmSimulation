public class Appeal {
    private Belief belief;
    private  Confidence confidence;



    public Appeal(int percentge){
        this.confidence = new Confidence(percentge);
        this.belief = new Belief();
    }

    public int getValue() {
        return this.belief.getValue()+this.confidence.getValue();
//        if(this.belief.getValue()==1 && this.confidence.getValue()==1){
//            return 1;
//        }else{
//            return 0;
//        }
    }


    public Belief getBelief() {
        return belief;
    }

    public void setBelief(Belief belief) {
        this.belief = belief;
    }

    public Confidence getConfidence() {
        return confidence;
    }
}
