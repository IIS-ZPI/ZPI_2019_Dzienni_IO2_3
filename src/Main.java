public class Main implements IArithmeticsDiff, IArithmeticsMult, IArithmeticsAdd, IArithmeticsDiv {

    public static void main(String[] args) {

        System.out.println("Grupa: ZPI_2019_Dzienni_IO2_3, Rola: operations, Team Leader: nciosek");
        System.out.println("209347");
        System.out.println("piotrek281097");
        System.out.println("nciosek");
        System.out.println("Bigoz005");
        //komentarz trzeci
    }

    @Override
    public double Addition(double A, double B) {
        return A + B;
        //komentarz pierwszy
        //komentarz 1 - Mikołaj
    }
    //komentarz zad5 piotr11
    @Override
    public double Difference(double A, double B) {
        return A - B;
        //komentarz 2 - Mikołaj
    }
    //komentarz zad5 piotr22
    @Override
    public double Multiplication(double A, double B) {
        return A * B;
        //komentarz drugi
        //komentarz 3 - Mikolaj
    }
    //komentarz zad5 piotr33
    @Override
    public double Division(double A, double B) {
        double C;
        if (B != 0) {
            C = A / B;
            return C;
        }else {
            System.out.println("B cannot be 0");
            return 0;
        }
    }
}
