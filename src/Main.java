public class Main implements IArithmeticsDiff, IArithmeticsMult, IArithmeticsAdd{

    public static void main(String[] args) {

        System.out.println("Grupa: ZPI_2019_Dzienni_IO2_3, Rola: operations, Team Leader: nciosek");
        System.out.println("209347");
        System.out.println("piotrek281097");
        System.out.println("nciosek");
        System.out.println("Bigoz005");
    }

    @Override
    public double Addition(double A, double B) {
        return A + B;
    }
  
    @Override
    public double Difference(double A, double B) {
        return A - B;
    }

    @Override
    public double Multiplication(double A, double B) {
        return A * B;
    }
}
