public class Performance {
    private Appeal appeal;
    private final Talent talent;

    public Performance(Appeal appeal,Talent talent){
        this.appeal=appeal;
        this.talent=talent;
    }

    public Appeal getAppeal() {
        return appeal;
    }
    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }
    public Talent getTalent() {
        return talent;
    }
//    public int getValue() {
//        return  appeal.getValue()*talent.getValue();
//    }

//    public void setBelief(Belief belief){
//        this.appeal.setBelief(belief);
//    }

}
