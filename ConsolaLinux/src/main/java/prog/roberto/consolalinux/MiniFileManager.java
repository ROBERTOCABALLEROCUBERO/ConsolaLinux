/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog.roberto.consolalinux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
//Constructor vacio

    public MiniFileManager(String PWD) {
        this.PWD = PWD;
    }

    public String getPWD() {
        return this.PWD;
    }
//Devolver ruta

    public void setPWD(String PWD) throws Exception {

        File direct = new File(PWD);
        File rutarelativa = new File(getPWD() + "//" + PWD);
        if (direct.isDirectory() || rutarelativa.isDirectory()) {
            if ("..".equals(PWD)) {
                this.PWD = direct.getParentFile().getAbsolutePath();

            }
            if (changeDir(direct.getAbsolutePath()) || changeDir(rutarelativa.getAbsolutePath())) {
                this.PWD = direct.getAbsolutePath();
            } else {
                throw new Exception("No se puede cambiar de directorio a un fichero o no existe el directorio");

            }
            //Creo un nuevo objeto file para asignarlo a la ruta en caso de Cd,
            //En primera instancia el directorio es null.
        }

    }

    public void mkdir(String ruta) {

        File a = new File(getPWD() + "//" + ruta);

        if (a.mkdir()) {
            System.out.println("La carpeta se ha creado correctamente");
        } else {
            System.out.println(" La ruta es incorrecta, la carpeta ya existe o el usuario no tiene permisos de escritura");
        }
//Creo un directorio en una ruta que ya existe.
    }

    boolean changeDir(String dir) {
        File a = new File(dir);
        if (a.exists()) {
            return true;

        } else {
            return false;
        }
        //Devuelve true o false segun la existencia del archivo para luego lanzar una excepcion, este metodo comprueba su existencia

    }

    public void printList(boolean info) {
        File a = new File(getPWD());
        File[] lista = a.listFiles();
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
        //Creo dos arrayList y lo meto por directorios y ficheros para despues ordenarlos con el sort.
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
        //Recorro en caso de que fuese un Directorio y si no como un fichero.
    }

    public void rm(String borrar) throws FileNotFoundException {
        File del = new File(borrar);
        File rutarelativa = new File(getPWD() + "//" + PWD);

        if (del.exists() || rutarelativa.exists()) {
            if (del.isDirectory()) {
                File[] borro = del.listFiles();
                for (File borro1 : borro) {
                    if (borro1.isFile()) {
                        borro1.delete();
                    }

                }
                del.delete();

            }
            if (del.isFile()) {
                del.delete();

            }
        } else {

            throw new FileNotFoundException("Directorio o fichero no encontrado");
        }

    }

    ;

    public void mv(String origen, String destino) throws FileNotFoundException, IOException {
        File origen2 = new File(origen);
        File destino2 = new File(destino);

        if (origen2.exists() || (destino2.exists())) {

            if (origen2.getParentFile().getAbsolutePath().equalsIgnoreCase(destino2.getParentFile().getAbsolutePath())) {
                destino2.renameTo(origen2);}
//             else {
//                Files.move(origen2.toPath(), destino2.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            }

        } else {
            throw new FileNotFoundException("Directorio o fichero no encontrado");
        }
//Uso Files.move para mover y si no para renombrar con el rename.
    }

    public void info(String ruta) throws Exception {
//Este metodo solo recibe una ruta que es un string
        File infoabs = new File(ruta);
        //Para el relativo creo este objeto en caso de que se haya introducido algo, la ruta por defecto es null para facilitar el programa, aunque
        //se podria asignar un valor inicial que podría ser mas correcto, aunque en este caso he decidido iniciarlo a null y el getPWD+ruta inexistente.
        File inforelativa = new File(getPWD() + "//" + ruta);
        //Creo dos objetos, una va a ser nulo siempre y otro existira  o ambos no van a existir.
        //Declaro dos variables para almacenar informacion Megas y suma.
        long megas;
        long suma = 0;
        //Hago un if que lanza la excepcion en caso de que los dos sean falsos con el operador logico OR.
        if (infoabs.exists() || inforelativa.exists()) {
            //Busco segun el objeto si es un directorio o no, pero diferencio entre ruta relativa y ruta absoluta, por lo que aunque sea 
            //lo mismo cubro los casos en los que el usuario esta en un directorio concreto, pero quiere la informacion de otro con ruta absoluta o busca dentro de
            //ese directorio con ruta relativa.
            if (infoabs.isDirectory()) {
                File[] arraytamano = infoabs.listFiles();
                for (File c : arraytamano) {
                    suma += c.length();
                }
                //Para sacar el length correcto calculo el tamaño de los ficheros y los voy sumando y almacenando en una variable.
                megas = (suma / 1024) / 1024;
                System.out.println("En bytes: " + suma + "\n" + "En MB: " + megas);
            }
            if (inforelativa.isDirectory()) {
                File[] arraytamano = inforelativa.listFiles();
                for (File c : arraytamano) {
                    suma += c.length();
                }
                //Lo mismo, pero esto es para el caso de que sea relativo
                megas = (suma / 1024) / 1024;
                System.out.println("En bytes: " + suma + "\n" + "En MB: " + megas);
            }
            //Y otra vez la misma logica, pero con los ficheros el metodo length nos devuelve un valor exacto en bytes que
            //podemos pasar a MB a traves de dos divisiones por 1024.
            if (infoabs.isFile()) {
                megas = (infoabs.length() / 1024) / 1024;
                System.out.println("En bytes: " + infoabs.length() + "\n" + "En MB: " + megas);
            }
            //Caso relativo
            if (inforelativa.isFile()) {
                megas = (inforelativa.length() / 1024) / 1024;
                System.out.println("En bytes: " + inforelativa.length() + "\n" + "En MB: " + megas);

            }
            //Por ultimo la excepcion que se ve en el programa de ejemplo.
        } else {
            throw new FileNotFoundException("Directorio o fichero no encontrado");
        }
    }
}
