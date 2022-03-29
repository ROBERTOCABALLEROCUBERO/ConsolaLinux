/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog.roberto.consolalinux;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author DAW
 */
public class MiniFileManager {

    private String PWD;

    public MiniFileManager() {
    }

    public MiniFileManager(String PWD) {
        this.PWD = PWD;
    }

    public String getPWD() {
        return this.PWD;
    }

    public void setPWD(String PWD) throws Exception {
        File direct = new File(PWD);
        if (direct.isDirectory()) {
            if (changeDir(direct.getAbsolutePath())) {
                this.PWD = direct.getAbsolutePath();
            }
        } else {
            throw new Exception("No se puede cambiar de directorio a un fichero");
        }

    }

    boolean changeDir(String dir) throws Exception {
        File a = new File(dir);
        if (a.exists()) {
            return true;

        } else {
            throw new Exception("No se ha encontrado la ruta");
        }
    }

    public void printList(boolean info) {
        File a = new File(getPWD());
        File[] lista = a.listFiles();
        int movimiento;
        File auxiliar;
        ArrayList<File> directorios = new ArrayList();
        ArrayList<File> ficheros = new ArrayList();
        for (File lista1 : lista) {
            if (lista1.isDirectory()) {
                directorios.add(lista1);

            }
            if (lista1.isFile()) {
                ficheros.add(lista1);
            }
        }
        Arrays.sort(directorios.toArray());
        Arrays.sort(ficheros.toArray());

        if (info) {

            for (File c : directorios) {
                System.out.println("El nombre es: " + c.getName());
                System.out.println("El tamaño es: " + c.length());
                System.out.println("La ultima fecha de modificacion es: " + c.lastModified());
            }
            for (File c : ficheros) {
                System.out.println("El nombre es: " + c.getName());
                System.out.println("El tamaño es: " + c.length());
                System.out.println("La ultima fecha de modificacion es: " + c.lastModified());
            }

        } else {
            for (File lista1 : lista) {
                System.out.println("El nombre es: " + lista1.getName());
            }
        }
    }
}
