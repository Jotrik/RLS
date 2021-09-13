import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.Math.*;

public class TRocket extends TTarget implements Runnable{

    double dm = 5;
    double Cx = 100;
    double ro = 1.225;
    double mcrit = 100;
    double P0 = 10000;
    double S = 1;
    double Mfuel, MfuelStart;
    double Mrocket;
    int number;

    double rls_az;

    TRocket(double x, double y, double v, double angle, double Mrocket, double MfuelStart, int number)
    {
        this.currentT = 0;
        this.inf[0] = x;
        this.inf[1] = y;
        this.inf[2] = v;
        this.inf[3] = angle;
        this.Mrocket = Mrocket;
        this.MfuelStart = MfuelStart;
        this.number = number;
    }

    @Override
    public int move(double ti)
    {

        double dt = ti - currentT;
        //currentT += dt;
        Mfuel = MfuelStart - dm * currentT;

        if (Mfuel <= 0) return 1;

        double P = (Mfuel > mcrit) ? P0 : P0 * Mfuel / mcrit;
        double X = Cx * ro * inf[2] * inf[2] * S / 2;

        double[] Finf = new double[4];

        Finf[2] = (P - X) / (Mrocket + Mfuel);
        Finf[0] += inf[2]  * cos(inf[3]);
        Finf[1] += inf[2]  * sin(inf[3]);
        Ailer.ailer(inf, Finf, dt);

        currentT = ti;

        return 0;
    }

    public void go () {
        for (double i = 0; i < 5; i+= 0.003)
        {

            if (this.move(i) == 0)
                System.out.println("x=" + this.inf[0] + "   y="+ this.inf[1]+ "  "+ this.inf[2] + " " + this.number + "\n");

        }
    }



    @Override
    public void run() {


        File file = new File("C:\\Grafic.txt");

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String toFile = null;

        for (double i = 0; i < 4; i += 0.01) {

            if (this.move(i) == 0)
                System.out.println("x=" + this.inf[0] + "   y=" + this.inf[1] + "  " + this.inf[2] + " " + this.number + "\n");


            String s_i = String.format("%.5f", i);
            String s_Y = String.format("%.5f", this.inf[2]);
            toFile = s_i + "\t" + s_Y + "\n";

            if (this.number == 1) {
                try {
                    fileOutputStream.write(toFile.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }





    static public  class Ailer
    {
        public static double[] ailer(double[] inf, double[] Finf, double dt)
        {
            for (int i = 0; i < 3; i++) inf[i] = inf[i] + Finf[i] * dt;
            return inf;
        }
    }

}
