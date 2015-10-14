/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textodelimitado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddizoya
 */
public class Textodelimitado {

    PrintWriter pw;
    BufferedReader br;
    String f1 = "/datos/local/ddizoya/Escritorio/arrays.txt";
    File fichero;
    String[] cod = {"p1", "p2", "p3"};
    String[] desc = {"parafusos", "cravos", "tachas"};
    int[] prezo = {3, 4, 5};
    Product producto;
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    public void escritura() {

        try {
            fichero = new File(f1);
            if (!fichero.exists()) {
                fichero.createNewFile();
            }

            pw = new PrintWriter(fichero);

            int i = 0;
            while (i < cod.length) {

                pw.println(cod[i] + "\t" + desc[i] + "\t" + prezo[i]);
                i++;
            }
            pw.flush();
            pw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Textodelimitado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Textodelimitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lectura() {
        try {
            br = new BufferedReader(new FileReader(fichero));

            while (br.ready()) {
                String[] contenido = br.readLine().split("\t");
                producto = new Product(contenido[0], contenido[1], Integer.parseInt(contenido[2]));
                System.out.println(""
                        + "\nCódigo: " + producto.getCodigo()
                        + "\nDescripción: " + producto.getDescripcion()
                        + "\nPrecio: " + nf.format((double) producto.getPrecio()));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Textodelimitado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Textodelimitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Textodelimitado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Textodelimitado obj = new Textodelimitado();
        obj.escritura();
        obj.lectura();
    }

}
