public class Performance {
    private int value;
    private Appeal appeal;
    private final Talent talent;

    public Performance(Appeal appeal,Talent talent){
        this.appeal=appeal;
        this.talent=talent;
        this.value = appeal.getValue()*talent.getValue();
    }

    public Appeal getAppeal() {
        return appeal;
    }
    public Talent getTalent() {
        return talent;
    }
    public int getValue() {
        return value;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
