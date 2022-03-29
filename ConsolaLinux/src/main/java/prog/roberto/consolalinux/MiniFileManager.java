/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog.roberto.consolalinux;

import java.io.File;
import java.text.SimpleDateFormat;
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
            } else {
                throw new Exception("No se puede cambiar de directorio a un fichero o no existe el directorio");

            }
        }

    }

    public void mkdir(String ruta) {

        File a = new File(getPWD() + "//" + ruta);

        if (a.mkdir()) {
            System.out.println("La carpeta se ha creado correctamente");
        } else {
            System.out.println(" La ruta es incorrecta, la carpeta ya existe o el usuario no tiene permisos de escritura");
        }

    }

    boolean changeDir(String dir) {
        File a = new File(dir);
        if (a.exists()) {
            return true;

        } else {
            return false;
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
                SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("El nombre es: " + c.getName());
                System.out.println("El tamaño es: " + c.length());
                System.out.println("La ultima fecha de modificacion es: " + gmtDateFormat.format(c.lastModified()));
            }
            for (File c : ficheros) {
                SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("El nombre es: " + c.getName());
                System.out.println("El tamaño es: " + c.length());
                System.out.println("La ultima fecha de modificacion es: " + gmtDateFormat.format(c.lastModified()));
            }

        } else {
            for (File c : directorios) {
                System.out.println("El nombre es: " + c.getName());
            }
            for (File c : ficheros) {
                System.out.println("El nombre es: " + c.getName());
            }
        }
    }

    public void mv(String origen, String destino) {
        File origen2 = new File (origen, destino);

    }
}
