import static java.lang.Math.*;

public class RLS implements Runnable{
    double x;
    double y;
    double r_det;
    TRocket rocket1;
    TRocket rocket2;
    TRocket rocket3;
    RLS(double x, double y, double r_det, TRocket rocket1, TRocket rocket2, TRocket rocket3)
    {
        this.x = x;
        this.y = y;
        this.r_det = r_det;
        this.rocket1 = rocket1;
        this.rocket2 = rocket2;
        this.rocket3 = rocket3;
    }
    public void detection()
    {



            double D1 = sqrt(  pow (x - rocket1.inf[0], 2)  +  pow(y - rocket1.inf[1], 2) );
            double D2 = sqrt(  pow (x - rocket2.inf[0], 2)  +  pow(y - rocket2.inf[1], 2) );
            double D3= sqrt(  pow (x - rocket3.inf[0], 2)  +  pow(y - rocket3.inf[1], 2) );

            rocket1.rls_az = atan((y - rocket1.inf[1]) / (x - rocket1.inf[0]));
            rocket2.rls_az = atan((y - rocket2.inf[1]) / (x - rocket2.inf[0]));
            rocket3.rls_az = atan((y - rocket3.inf[1]) / (x - rocket3.inf[0]));

            if (r_det >= D1)
                System.out.println("!!!DETECKTED!!!     " + rocket1.rls_az + "   " + rocket1.number + "\n");

            if (r_det >= D2)
                System.out.println("!!!DETECKTED!!!     " + rocket2.rls_az + "   " + rocket2.number + "\n");

            if (r_det >= D3)
                System.out.println("!!!DETECKTED!!!     " + rocket3.rls_az + "   " + rocket3.number + "\n");

    }

    @Override
    public void run() {

        try {
            for (double i = 0; i < 4; i+= 0.01) {
                detection();
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}