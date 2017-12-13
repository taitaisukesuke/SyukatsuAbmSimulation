public class Performance {
    private int value;
    private Appeal appeal;
    private  Talent talent;

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
    public void setTalent(Talent talent) {
        this.talent = talent;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
