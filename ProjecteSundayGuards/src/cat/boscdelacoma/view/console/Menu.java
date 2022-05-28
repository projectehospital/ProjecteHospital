/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Categoria;
import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.PlantillaGuardia;
import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.business.entities.Torn;
import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.dao.contracts.PlantillaGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCPlantillaGuardiaDAO;
import java.time.LocalDate;
import java.util.Scanner;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        // es podria fer amb un case
        
        
        if (t.getRolTreballador().getTipusRol().equals("Administrador")) {
            // menu per l'administrador permet crear guardies 
            menuAdministracio();
        } else if (t.getRolTreballador().getTipusRol().equals("CAP")) {
           // menu que permet la tria de quin menu es vol accedir
            menuAdmin();
        } else if (t.getRolTreballador().getTipusRol().equals("Usuari")) {
            // menu basic de triar guardia usuari !!! s'ha d'afegir opcions com llistar guardia
            menuUsuari();
        }
        
        
        
    }

    private static void menuAdmin() {
        System.out.println("0. Sortir");
        System.out.println("1. Menú d'administració");
        System.out.println("2. Menú d'usuari (reservar guàrdia)");
        System.out.println("Escull el menú al qual vols accedir: ");
        int menu = entrada.nextInt();
        switch (menu) {
            case 0:
                break;
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
        mostrarMesos();
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

    private static void menuAdministracio() throws DAOException {
        
        // s'ha de comprovar si es cap de unitat o administrador per capar privilegis
        
        System.out.println("0. Sortir");
        System.out.println("1. Crear Guàrdia");
        System.out.println("2. Eliminar Guàrdia");
        System.out.println("3. Crear un any de guàrdies segons la plantilla"); // falta codificar
        System.out.println("4. Afegir Treballador"); // falta codificar
        
        
        
        System.out.println("Tria el número de l'acció que vols fer: ");
        int accio = entrada.nextInt();
        switch (accio) {
            case 0:
                break;
            case 1:
               
                while(true) {
                
                LocalDate dataGuardia = entrarDataGuardia();

                // comprovar que la guardia no esta ja creada
                
                
                
                
                //Funcio per triar el tipus de torn que tindrà l'objecte torn
                Torn torn = triarTornAdmin();
                
                //Funcio per triar el tipus de unitat que tindrà l'objecte unitat
                Unitat unitat = triarUnitatAdmin();
 
                
                //Funcio per triar el tipus de categoria que tindrà l'objecte categoria
                Categoria categoria = triarCategoriaAdmin();
                
                System.out.println("Les places es definiran per defecte , si les vols entrar a ma prem s sino n  [s / n]");
                String resposta = entrada.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        
                         System.out.println("Places de la guàrdia:");
                         long places = entrada.nextShort();
                         Guardia novaGuardia = new Guardia(dataGuardia , unitat , torn , categoria , places );
                    } else {
                        System.out.println("Es definiran les places per defecte");
                        System.out.println("Vols mostrar la plantilla?");
                        resposta = entrada.nextLine();
                        if (resposta.equalsIgnoreCase("s")) {
                            mostrarPlantilla(unitat , torn , categoria);
        
                        } 
                       
                         long places = obtenirPlaces(unitat , categoria , torn);
                         Guardia novaGuardia = new Guardia(dataGuardia , unitat , torn , categoria , places );
                    }

                    // si l'entrada es n no es seguira cfreant la guardia
                    System.out.println("Vols crear una nova guardia? [s / n]");
                     resposta = entrada.nextLine();
                    if (resposta.equalsIgnoreCase("n")) {
                        break;
                    }
            
                   
                }
                
            case 2:
                mostrarMesos();
                System.out.println("A quin mes vols eliminar una guàrdia? (Escriu el número)");
                short mes = entrada.nextShort();
                switch (mes) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                }
                break;
            case 3: 
                mostrarPlantilla();
            //Si l'usuari tria un número diferent a 1 ó 2 se li notificarà i es tornarà a cridar el mètode
            default:
                System.out.println("Tria un dels numeros associats als menús (1 ó 2)");
                menuAdministracio();
                break;
        }
    }

    private static Torn triarTornAdmin() {
        Torn torn =  new Torn();
        System.out.println("Torn de la guàrdia:");
        System.out.println("1. Dia");
        System.out.println("2. Nit");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                torn.setTipusTorn("Dia");
                break;
            case 2:
                torn.setTipusTorn("Nit");
                break;
            default:
                System.out.println("Tria Dia(1) o Nit(2)");
                triarTornAdmin();
        }
        
        return torn;
    }

    private static Unitat triarUnitatAdmin() {
        
        Unitat unitat = new Unitat();
        
        System.out.println("Unitat de la guàrdia:");
        System.out.println("1. Unitat 1");
        System.out.println("2. Unitat 2");
        System.out.println("3. Unitat 3");
        System.out.println("4. Unitat 4");
        System.out.println("5. Urgències");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                unitat.setTipusUnitat("Unitat 1");
                break;
            case 2:
                unitat.setTipusUnitat("Unitat 2(s'ha de especificar el numero places)");
                break;
            case 3:
                unitat.setTipusUnitat("Unitat 3");
                break;
            case 4:
                unitat.setTipusUnitat("Unitat 4");
                break;
            case 5:
                unitat.setTipusUnitat("Urgències");
                break;
            default:
                System.out.println("Tria una Unitat(1, 2, 3, 4) o Urgències(5)");
                triarUnitatAdmin();
        }
        
        return unitat;
    }

    private static Categoria triarCategoriaAdmin() {
        
        Categoria categoria = new Categoria();
        
        System.out.println("Categoria de la guàrdia:");
        System.out.println("1. Infermeria");
        System.out.println("2. TCAI");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                categoria.setTipusCategoria("Infermer/a");
                break;
            case 2:
                categoria.setTipusCategoria("TCAI");
                break;
            default:
                System.out.println("Tria Infermer/a(1) o TCAI(2)");
                triarCategoriaAdmin();
        }
        return categoria;
    }

    private static void mostrarMesos() {
        
        System.out.println("1.Gener      2. Febrer    3. Març");
        System.out.println("4.Abril      5. Maig      6. Juny");
        System.out.println("7.Juliol     8. Agost     9. Setembre");
        System.out.println("10.Octubre  11. Novembre 12. Desembre");
    }

    private static LocalDate mostrarDiumengesDelMes(int any, int mes) throws DAOException {
            int errors = 0;
           // comprovacio de les dates entrada
          while(true) {
            
               if (any >= 2022 && (mes >= 1 && mes <=12)) {
                   break;
               }
               if (any < 2022) {
                   System.out.println("No es poden crear guardies d'aquest any!!");
                   System.out.print("Entra un any correcte: ");
                   any = entrada.nextInt();
                   System.out.println();
               } 
               
               if (mes < 1 || mes > 12) {
                   System.out.println("No es poden crear guardies d'aquest mes!!");
                   System.out.print("Entra un mes correcte:");
                   mes = entrada.nextInt();
                   System.out.println();
               }
               if (errors >= 5) {
                   System.out.println("Vols cancelar la creacio de la guardia? [s / n]");
                   String resposta = entrada.nextLine();
                   
                   if (resposta.equals("s")) {
                       menuAdministracio();
                   }
              }

                    }
          
        /* mostrem diumenges del l'any i el mes triat i capturem els dies triats
           per crear-los posteriorment*/
    
       // array de dies possibles a triar , fem servir una LinkedList per tema de velocitat
        List<Integer> dies = new LinkedList<Integer>();
        
        LocalDate now = LocalDate.of(any, Month.JANUARY, 1);
    // busca el primer diumenge de l'any
    LocalDate diumenge = now.with(firstInMonth(DayOfWeek.SUNDAY));
    System.out.println("Diumenges disponibles per crear");
    do {
        // bucle que obte tots els diumenges
        if (diumenge.getMonthValue() == mes) {
            System.out.println(diumenge.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            // afegim els dies possibles del mes indicat
            dies.add(diumenge.getDayOfMonth());
            diumenge = diumenge.plus(Period.ofDays(7));
            
            
            
        } else {
            
            diumenge = diumenge.plusMonths(1);
            diumenge = diumenge.with(firstInMonth(DayOfWeek.SUNDAY));
            
        }
        
        
 
    } while (diumenge.getYear() == any);
          
              
        System.out.println("Entra el dia de la guardia a crear");
        int dia = entrada.nextInt();
        
        /* si la llista de dies possibles no conte el dia triat 
        fa un bucle fins que el dies es correcte*/
        while(!dies.contains(dia)) {
        
            System.out.println("El dia entrat no es un diumenge dels disponibles!!");
            System.out.println("Entra un dels diumenges disponibles:");
            for (Integer dy : dies) {
                System.out.print(dy + " ");
                
            }
            System.out.println();
            dia = entrada.nextInt();
        }
        
        
        /* restem un a l'any ja que el bucle comprova fins al desembre i quan pasa
         a l'any seguent para el bucle*/
        return LocalDate.of(diumenge.getYear() - 1, diumenge.getMonthValue(), dia);
        
        
    }

    private static LocalDate entrarDataGuardia() {
         System.out.println("Entra l'any de la guardia a crear");
                int any = entrada.nextShort();
                System.out.println("Entra l'mes de la guardia a crear");
                int mes = entrada.nextShort();
                
                /* el metode mostrarDiumengesDelMes() mostra els dies diponibles 
                    i construeix la data de la guardia a crear
                   */
                
                return mostrarDiumengesDelMes(any , mes);
                
    }

    private static void mostrarPlantilla(Unitat unitat , Torn torn , Categoria categoria) throws DAOException {
        
           var jdbcplantilla = new JDBCPlantillaGuardiaDAO();
           PlantillaGuardia plG= new PlantillaGuardia();
           try{
           plG = jdbcplantilla.get(categoria.getTipusCategoria(), unitat.getTipusUnitat(), torn.getTipusTorn());
           System.out.printf("La guardia es crear amb la seguent plantilla : %d  %s  %s  %s %d " , plG.getIdPlantilla() , plG.getNomUnitatPlantilla(),
                  plG.getNomCategoriaPlantilla() , plG.getNomTornPlantilla() , plG.getIdPlantilla());
           } catch(DAOException e){
               System.out.println("Error al mostrar plantilla" + e.getMessage());
           }

    }

    private static long obtenirPlaces(Unitat unitat , Categoria categoria , Torn torn) {
            
            var jdbcplantilla = new JDBCPlantillaGuardiaDAO();
           PlantillaGuardia plG= new PlantillaGuardia();
           try{
           plG = jdbcplantilla.get(categoria.getTipusCategoria(), unitat.getTipusUnitat(), torn.getTipusTorn());
           return plG.getPlacesPlantilla();
           } catch(DAOException e){
               System.out.println("Error al obtenir places plantilla" + e.getMessage());
           }
        return -1;
    }
    
    
    
    
    
}
