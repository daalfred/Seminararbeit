import java.io.*;

/**
 * Created by Michael on 05.11.2015.
 */
public class EKG {

    static double[] readBMP(String path, int width, int hight) throws IOException { //"Abtasten" des EKGs durch Auslesen der Werte einer .bmp Bitmap(Bilddatei)
        double[] ret = new double[hight];
        int k = width+3;
        char[] text = new char[k*hight];

        FileReader fr = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(fr);
        br.read(text);

        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                if(text[i*k+j]=='r'){
                    double l = (double) j;
                    ret[i]=(135-l)/95;
                    break;
                }
            }
        }


        return ret; //Rückgabe der Werte als int[] Array, das unten als vals[] verwendet wird
    }


    static void calcDFT(double[] vals, double periode, int accuracy){   //Berechnen der Diskreten Fourier-Transformation
        int acc = accuracy + 1; //"accuracy" (="Genauigkeit"), die von der Anzahl der verwendeten Frequenzen abhängt und das Frequenzspektrum nach oben abgrenzt
        double[] a = new double[acc];
        double[] b = new double[acc];
        int N = vals.length;
        int m = 0;
        double omega = 2*Math.PI/periode;

        for (int i = -N/2; i <= N/2 - 1; i++) { //Summen als for-Schleifen umgesetzt, i als Durchlaufvariable, m als Index
            m = (i+N)%N;
            a[0]+=vals[m];
        }
        a[0] = a[0]/N;

        for (int k = 1; k < acc; k++) { //Berechnen von a[k] und b[k]
            for (int i = -N/2; i <= N/2 - 1; i++) {
                m = (i+N)%N;
                a[k]+=vals[m]*Math.cos(k * omega * i * periode / N);
            }
            a[k] = 2*a[k]/N;

            for (int i = -N/2; i <= N/2 - 1; i++) {
                m = (i+N)%N;
                b[k]+=vals[m]*Math.sin(k * omega * i * periode / N);
            }
            b[k] = 2*b[k]/N;
        }


        System.out.print("f(x)="); //Ausgabe der fertigen Funktion f(x)
        for (int i = 0; i < acc; i++) {
            System.out.print("+(" + a[i] + " * cos(" + i + " * x*2*pi/" + periode + "))+(" + b[i] + "* sin(" + i + " * x*2*pi/" + periode + "))");
        }

    }
}
