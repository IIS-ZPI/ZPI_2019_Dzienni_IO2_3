
public class Main implements IArithmeticsDiv {

    public static void main(String[] args) {
        System.out.println("Grupa: ZPI_2019_Dzienni_IO2_3, Rola: Developer, Team Leader: 209347");
        System.out.println("Bigoz005");
    }


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
