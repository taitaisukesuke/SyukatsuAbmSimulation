import java.util.Random;
import java.util.ArrayList;


class Indicator{
    private static final int[] choices = {0,1};
    private final int weight;

    public Indicator(){
        Random rnd = new Random();
        int num = rnd.nextInt(2);
        this.weight = choices[num];
    }

    public int evaluate(Performance performance) {

        Random rnd = new Random();
        int probability = rnd.nextInt(10);

        int result = 0;
        if(performance.getAppeal().getValue() == 1 && performance.getTalent().getValue() == 1){
            result = 1;
        }else if(performance.getAppeal().getValue() == 0 && performance.getTalent().getValue() == -1){
            result = 0;
        }else if(performance.getAppeal().getValue() == 1 && performance.getTalent().getValue() == -1){
            if(probability < 6){
                result = 1;
            }else{
                result = -1;
            }
        }else if(performance.getAppeal().getValue() == 0 && performance.getTalent().getValue() == 1){
            if(probability < 2){
                result = 1;
            }else{
                result = 0;
            }
        }
//        result = performance.getValue() *  weight;
        return result * weight;
    }

//    public int evaluateTalent(Performance performance){
//       int talent= performance.getTalent().getValue();
//
//       return talent *weight;
//    }

}
