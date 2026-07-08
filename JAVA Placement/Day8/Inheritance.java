package Day8;

public class Inheritance {

    // // Multilevel inheritance
    // static class GrandFather{
    //     void Land(){
    //         System.out.println("Grand Father's Land");
    //     }
    //     void Gold(){
    //         System.out.println("Grand Father's Gold");
    //     }
    // }
    // static class Father extends GrandFather{
    //     void House(){
    //         System.out.println("Father is having House");
    //     }
    // }
    // static class Son extends Father{
    //     void Bike(){
    //         System.out.println("Son is having Bike");
    //     }
    // }
    // public static class Main{
    //     public static void main(String[] args){
    //         Son s = new Son();
    //         s.Land();
    //         s.Gold();
    //         s.House();
    //         s.Bike();
    //     }
    // }


    static class MarvelHeroes {
        void universe() {
            System.out.println("Marvel Universe");
        }
    }
    static class IronMan extends MarvelHeroes {
        void suit() {
            System.out.println("Iron Man uses an armored suit");
        }
    }
    static class SpiderMan extends MarvelHeroes {
        void web() {
            System.out.println("Spider-Man uses web shooters");
        }
    }
    public static class Main {
        public static void main(String[] args) {
            IronMan ironMan = new IronMan();
            ironMan.universe();
            ironMan.suit();
            SpiderMan spiderMan = new SpiderMan();
            spiderMan.universe();
            spiderMan.web();
        }
    }
}