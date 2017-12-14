public class Appeal {
    private Belief belief;
    private  Confidence confidence;



    public Appeal(){
        this.confidence = new Confidence();
        this.belief = new Belief();
    }

    public int getValue() {
       return this.belief.getValue() + this.confidence.getValue();
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
