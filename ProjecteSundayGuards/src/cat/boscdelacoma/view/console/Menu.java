/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.business.entities.Treballador;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Victo
 */
public class Menu {

    static Scanner entrada;

    public static void MenuInicial(Treballador t) {
        /*Creem un objecte Treballador d'exemple amb el rol d'Administrador, si
        el treballador té rol d'Admin (coincideix) accedirà al menú 
        d'administració, en cas contrari anirà al menú d'usuari*/
        Treballador ex = new Treballador();
        Rol r = new Rol();
        r.setRol("Administrador");
        ex.setRol(r);
        if (t.getRol() == ex.getRol()) {
            menuAdmin();
        } else {
            menuUsuari();
        }

    }

    private static void menuAdmin() {
        System.out.println("1. Menú d'administració");
        System.out.println("2. Menú d'usuari (reservar guàrdia)");
        System.out.println("Escull el menú al qual vols accedir: ");
        int menu = entrada.nextInt();
        switch (menu) {
            case 1:
                menuAdministracio();
                break;
            case 2:
                menuUsuari();
                break;
            //Si l'usuari tria un número diferent a 1 ó 2 se li notificarà i es tornarà a cridar el mètode
            default:
                System.out.println("Tria un dels numeros associats als menús (1 ó 2)");
                menuAdmin();
                break;
        }
    }

    private static void menuUsuari() {
        //String perquè els mesos quedin alineats
        String mesos = "%n%-25s%-25s%-25s%-25s%n";
        System.out.printf(mesos, "1. Gener", "2. Febrer", "3. Març");
        System.out.printf(mesos, "4. Abril", "5. Maig", "6. Juny");
        System.out.printf(mesos, "7. Juliol", "8. Agost", "9. Setembre");
        System.out.printf(mesos, "10. Octubre", "11. Novembre", "12. Desembre");
        System.out.println("Tria el número del mes vols reservar una guàrdia: \n");

        //Entrem el mes al qual vol reservar guàrdia el treballador
        entrada = new Scanner(System.in);
        int mes = entrada.nextInt();

        //Creem un objecte LocalDate amb la data actual i posem en variables el mes actual i l'any actual
        LocalDate d = LocalDate.now();
        int mesActual = d.getMonthValue();
        int any = d.getYear();

        //Si el treballador vol reservar per un mes de l'any vinent farem que l'any de l'objecte LocalDate siqui el de l'any següent
        if (mes < mesActual) {
            d.withYear(any + 1);
        }

        //String perquè la capçalera estigui alineada
        String capcelera = "%n%-25s%-25s%-25s%-25s%-25s%n";

        System.out.printf(capcelera, "DIA", "UNITAT", "TORN", "CATEGORIA", "PLACES DISPONIBLES");
    }

    private static void menuAdministracio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
