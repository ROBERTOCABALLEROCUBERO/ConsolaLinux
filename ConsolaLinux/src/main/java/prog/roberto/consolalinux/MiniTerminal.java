/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog.roberto.consolalinux;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author DAW
 */
public class MiniTerminal {

    /**
     */
    public static void listacomandos() {
        System.out.println("\npwd: Muestra cual es la carpeta actual.\n"
                + " cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.\n"
                + " ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n"
                + "archivos, ambos ordenados alfabéticamente).\n"
                + " ll: Como ls pero muestra también el tamaño y la fecha de última modificación.\n"
                + " mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.\n"
                + " rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. "
                + " Si tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.\n"
                + " mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.\n"
                + " help: Muestra una breve ayuda con los comandos disponibles.\n"
                + " exit: Termina el programa. \n");

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in);
        MiniFileManager llamada = new MiniFileManager();
        boolean salir = false;
        String cmd;

        do {
            listacomandos();
            System.out.println("Introduce el comando");
            cmd = leer.nextLine();
            String[] comando = cmd.split(" ");
            System.out.println(comando[0]);

            if (comando[0].equalsIgnoreCase("pwd")) {
                System.out.println(llamada.getPWD());
            }
            if (comando[0].equalsIgnoreCase("cd")) {
                try {
                    llamada.setPWD(comando[1]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
            }
            if (comando[0].equalsIgnoreCase("ls")) {
                llamada.printList(false);
            }
            if (comando[0].equalsIgnoreCase("ll")) {
                llamada.printList(true);
            }
            if (comando[0].equalsIgnoreCase("mkdir")) {
                llamada.mkdir(comando[1]);
            }
            if (comando[0].equalsIgnoreCase("mv")) {
                llamada.mv(comando[1], comando[2]);
            }
            if (comando[0].equalsIgnoreCase("help")) {
                listacomandos();
            }
            if (comando[0].equalsIgnoreCase("exit")) {
                salir = true;
            }
        } while (salir == false);

    }

}