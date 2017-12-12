public class Appeal {
    private int  value;
    private Belief b;
    private  Confidence c;

    public Appeal(Belief b,Confidence c){
        this.b=b;
        this.c=c;
    }

    public int getValue() {
        value=this.b.getValue()+this.c.getValue();
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
