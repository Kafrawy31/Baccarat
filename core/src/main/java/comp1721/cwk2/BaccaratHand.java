package comp1721.cwk2;

// Implement the BaccaratHand class here
public class BaccaratHand extends CardCollection{

    public BaccaratHand() {
    }



    public boolean isNatural(){

        if(value() ==8||value() ==9){
            return true;
        }
        else
            return false;
    }


    @Override
    public String toString() {
        String replaced = String.format("%s", cards).replace('[',' ') ;
        replaced = replaced.replace(']',' ');
        replaced = replaced.replace(',',' ');
        return replaced;
    }




}