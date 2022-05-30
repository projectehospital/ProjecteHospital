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
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCCategoriaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCPlantillaGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCRolDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTornDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTreballadorDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCUnitatDAO;
import java.time.LocalDate;
import java.util.Scanner;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.MYSQLConnection;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author Victo
 */
public class Menu {

    static Scanner entrada;

    public static void menuInicial(Treballador treballador) throws DAOException, SQLException {
        /*Creem un objecte Treballador d'exemple amb el rol d'Administrador, si
        el treballador té rol d'Admin (coincideix) accedirà al menú 
        d'administració, en cas contrari anirà al menú d'usuari*/
        
        // es podria fer amb un case
        
        
        if (treballador.getRolTreballador().getTipusRol().equals("Administrador")) {
            // menu per l'administrador permet crear guardies 
            menuAdministracio(treballador);
        } else if (treballador.getRolTreballador().getTipusRol().equals("Cap de Unitat")) {
           // menu que permet la tria de quin menu es vol accedir
            menuAdmin(treballador);
        } else if (treballador.getRolTreballador().getTipusRol().equals("Usuari")) {
            // menu basic de triar guardia usuari !!! s'ha d'afegir opcions com llistar guardia
            menuUsuari(treballador);
        }
        
        
        
    }

    private static void menuAdmin(Treballador treballador) throws DAOException, SQLException {
        System.out.println("0. Sortir");
        System.out.println("1. Menú d'administració");
        System.out.println("2. Menú d'usuari (reservar guàrdia)");
        System.out.println("Escull el menú al qual vols accedir: ");
        int menu = entrada.nextInt();
        switch (menu) {
            case 0:
                break;
            case 1:
                menuAdministracio(treballador);
                break;
            case 2:
                menuUsuari(treballador);
                break;
            //Si l'usuari tria un número diferent a 1 ó 2 se li notificarà i es tornarà a cridar el mètode
            default:
                System.out.println("Tria un dels numeros associats als menús (1 ó 2)");
                menuAdmin(treballador);
                break;
        }
    }

    private static void menuUsuari(Treballador treballador) throws DAOException, SQLException {
        System.out.println("1.Llistar Guardies ");
        System.out.println("2.Apuntar-se a guardia");
        System.out.println("3.Cancelar guardia");
        short x = entrada.nextShort();
        
        switch(x){
            
            case 1 :
                   // llista guardia depenent de la categoria del treballador
                try{
                   LocalDate dia = entrarDataGuardia(treballador);
                   System.out.println("Es mostraran les guardies del dia:" + dia.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

                       JDBCGuardiaDAO guardies = new JDBCGuardiaDAO();
                       List<Guardia> llistaGuardies = new ArrayList<Guardia>();
                       llistaGuardies = guardies.getPerData(dia, treballador.getCategoriaTreballador());
                       
                       for (Guardia Guardy : llistaGuardies) {
                           System.out.println(Guardy.getId() + " " + Guardy.getDia() + " " + Guardy.getUnitat().getTipusUnitat()
                            + " " + Guardy.getTorn().getTipusTorn() + " " + Guardy.getCategoria().getTipusCategoria() + " " + Guardy.getPlacesDisponibles());
                       }
                       
                       
               
                   
            }catch(DAOException e) {
                    System.out.println("Error al llistar Guardies d'una data" + e.getMessage());

            }
            
                break;
            case 2 :
                  // es demana la data i les dades de la guardia a la que es vol apuntar
                LocalDate dia = entrarDataGuardia(treballador);
                System.out.println("La data a apuntar-se es:" + dia.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                JDBCGuardiaDAO guard = new JDBCGuardiaDAO();
                Torn tor = triarTorn();
                Unitat unit = triarUnitat();
                Categoria categ = triarCategoria();
                
                Guardia gApuntarse = guard.getPerDades(dia , unit , tor , categ);
                
                // apuntem el treballador a la guardia obtingent-la amb l'id
                treballador.apuntarseAGuardia(gApuntarse.getId());
                System.out.println("El treballador s'ha apuntat a la guardia correctament");
                break;
                
            case 3 :
                // cancelarem una guardia a la que el treballador ja esta apuntat
                
                LocalDate diaG = entrarDataGuardia(treballador);
                System.out.println("La data a apuntar-se es:" + diaG.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                JDBCGuardiaDAO guardi = new JDBCGuardiaDAO();
                Torn torn = triarTorn();
                Unitat unita = triarUnitat();
                Categoria catego = triarCategoria();
                
                Guardia gDesapuntarse = guardi.getPerDades(diaG , unita , torn , catego);
                
                // apuntem el treballador a la guardia obtingent-la amb l'id
                treballador.desapuntarseDeGuardia(gDesapuntarse.getId());
                System.out.println("El treballador s'ha desapuntat de la guardia correctament");
                break;
                
                
                
                
        }
        
    }

    private static void menuAdministracio(Treballador treballador) throws DAOException {
        
        // s'ha de comprovar si es cap de unitat o administrador per capar privilegis
        
        System.out.println("0. Sortir");
        System.out.println("1. Crear Guàrdia");
        System.out.println("2. Eliminar Guàrdia");
        System.out.println("3. Afegir Treballador"); // falta codificar
        System.out.println("Tria una opcio");
        
        
        System.out.println("Tria el número de l'acció que vols fer: ");
        int accio = entrada.nextInt();
        switch (accio) {
            case 0:
                break;
            case 1:
               // crear guardia 
                while(true) {
                
                LocalDate dataGuardia = entrarDataGuardia(treballador);

                //Funcio per triar el tipus de torn que tindrà l'objecte torn
                Torn torn = triarTorn();
                
                //Funcio per triar el tipus de unitat que tindrà l'objecte unitat
                Unitat unitat = triarUnitat();
 
                
                //Funcio per triar el tipus de categoria que tindrà l'objecte categoria
                Categoria categoria = triarCategoria();
                
                JDBCGuardiaDAO comprG = new JDBCGuardiaDAO();
                Guardia g = comprG.getPerDades(dataGuardia , unitat , torn , categoria);
                
                    if (g != null) {
                        System.out.println("La guardia ja existeix!!");
                        menuAdministracio(treballador);
                        
                    }
                
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
                
                menuAdministracio(treballador);
                
            case 2:
                
                // eliminar guardia
                mostrarMesos();
                LocalDate datGuarEliminar = entrarDataGuardia(treballador); 
                System.out.println("La data a eliminar sera:" + datGuarEliminar.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                
                Torn tor = triarTorn();
                Unitat u = triarUnitat();
                Categoria c = triarCategoria();
                
               try { 
                JDBCGuardiaDAO guarEliminar = new JDBCGuardiaDAO();
                Guardia gEliminar = new Guardia();
                // construim la data a eliminar per pasarla per parametre
                gEliminar.setCategoria(c);
                gEliminar.setTorn(tor);
                gEliminar.setUnitat(u);
                gEliminar.setDia(datGuarEliminar);
                
                guarEliminar.delete(gEliminar);
                
               } catch (DAOException e) {
               
                   System.out.println("Error al elimiar la guardia" + e.getMessage());
               }
                System.out.println("La guardia s'ha eliminat correctament");

                break;
            case 3: 
              // afegirem un treballador nou al registre de treballadors
                
                JDBCTreballadorDAO trebNou = new JDBCTreballadorDAO();
                Treballador treb = new Treballador();
                
                System.out.println("A continuacio s'han d'entrar les dades del treballador a crear");
                System.out.println("Entra el nom del treballador");
                String nom = entrada.nextLine();
                System.out.println("Entra el DNI del treballador [000000000X]");
                String dni = entrada.nextLine();
                System.out.println("Entra el passwd del trebllador");
                String passwd = entrada.nextLine();
                System.out.println("Entra el tipus de contracte del treballador [ (Jornada Complerta) / (Jornada Parcial) ]");
                String tipusContracte = entrada.nextLine();
                while(true){
                
              if (tipusContracte.equals("Jornada Complerta") || tipusContracte.equals("Jornada Parcial")) {
                        break;
                    }
                        System.out.println("El tipus de contracte no es correcte");
                        System.out.println("Entra el tipus de contracte del treballador [ (Jornada Complerta) / (Jornada Parcial) ]");
                        tipusContracte = entrada.nextLine(); 
                }
                System.out.println("Te guardies fetes el treballador? [s / n]");
                String resp = entrada.nextLine();
                
                if (resp.equalsIgnoreCase("s")) {
                    System.out.println("Entra el numero de guardies fetes ");
                    long gFetes;
                    System.out.println("Entra el numero de guardies previstes");
                    long gPrevistes;
                    
                    
                } 
                long gFetes = 0;
                long gPrevistes = 0;
                // obtenim la data de naixement del treballador
                System.out.println("Entra la data de naixement del treballador [yyyy/mm/dd]");
                String arrData[] = entrada.nextLine().split("[/]");
                LocalDate dataNaix = LocalDate.of(Integer.parseInt(arrData[0]), Integer.parseInt(arrData[1]),Integer.parseInt(arrData[2]));
                System.out.println("Entra el rol de treballador a afegir [ (Cap de Unitat) / (Usuari) ]");
                String rol = entrada.nextLine();
                while(true) {
                    
                        if (rol.equals("Cap de Unitat") || rol.equals("Usuari")) {
                                break;
                        }
                        System.out.println("No s'ha escrit be el rol");
                        System.out.println("Entra el rol de treballador a afegir [ (Cap de Unitat) / (Usuari) ]");
                        rol = entrada.nextLine();
                }
                JDBCRolDAO jRol = new JDBCRolDAO();
                Rol rolTreb = jRol.getPerNom(rol);
                System.out.println("Entra la categoria del treballador [ (Infermeria) / (TCAI) ] ");
                String categoria = entrada.nextLine();
                while(true) {
                    
                        if (rol.equals("Infermeria") || rol.equals("TCAI")) {
                                break;
                        }
                        System.out.println("No s'ha escrit be la categoria");
                        System.out.println("Entra la categoria del treballador [ (Infermeria) / (TCAI) ] ");
                        categoria = entrada.nextLine();
                }
                System.out.println("Es cap d'unitat el treballador? [s / n] ");
                     resp = entrada.nextLine();
                    if (resp.equalsIgnoreCase("s")) {
                        System.out.println("Entrem el nom de la Unitat [ (Unitat 1) /(Unitat 2) / (Unitat 3) / (Unitat 4) / (Urgencies)");
                        String unitat = entrada.nextLine();      
                        while(true) {
                            
                            if ( unitat.equals("Unitat 1") || 
                                 unitat.equals("Unitat 2") || 
                                 unitat.equals("Unitat 3") ||
                                 unitat.equals("Unitat 4") ||
                                 unitat.equals("Urgencies") ) {
                                
                                break;
                            }
                            System.out.println("Nom de unitat incorrecte");
                System.out.println("Entrem el nom de la Unitat [ (Unitat 1) /(Unitat 2) / (Unitat 3) / (Unitat 4) / (Urgencies)");            
                            unitat = entrada.nextLine();   
                        }
                } else {
                    
                        String unitat = "No es cap";
                        
                    }
                
                    Treballador treballadorNou = new Treballador(dni , nom , dataNaix, passwd ,gFetes , gPrevistes , tipusContracte ,  );
 
                     
                
                
                
                
            case 4:
               // afegir treballador 
                
            default:
                menuAdministracio(treballador);
                break;
        }
    }

    private static Torn triarTorn() throws DAOException {
        JDBCTornDAO jdTorn = new JDBCTornDAO();
        Torn torn =  new Torn();
        System.out.println("Torn de la guàrdia:");
        System.out.println("1. Dia");
        System.out.println("2. Nit");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                torn = jdTorn.getPerNom("Dia");
                break;
            case 2:
                torn = jdTorn.getPerNom("Nit");
                break;
            default:
                System.out.println("Tria Dia(1) o Nit(2)");
                triarTorn();
        }
        
        return torn;
    }

    private static Unitat triarUnitat() throws DAOException {
        JDBCUnitatDAO jdUnitat = new JDBCUnitatDAO();
        Unitat unitat = new Unitat();
        
        System.out.println("Unitat de la guàrdia:");
        System.out.println("1. Unitat 1");
        System.out.println("2. Unitat 2");
        System.out.println("3. Unitat 3");
        System.out.println("4. Unitat 4");
        System.out.println("5. Urgencies");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                unitat = jdUnitat.getPerNom("Unitat 1");
                break;
            case 2:
                unitat =  jdUnitat.getPerNom("Unitat 2");
                break;
            case 3:
               unitat =   jdUnitat.getPerNom("Unitat 3");
                break;
            case 4:
              unitat =    jdUnitat.getPerNom("Unitat 4");
                break;
            case 5:
              unitat =    jdUnitat.getPerNom("Urgencies");
                break;
            default:
                System.out.println("Tria una Unitat(1, 2, 3, 4) o Urgencies(5)");
                triarUnitat();
        }
        
        return unitat;
    }

    private static Categoria triarCategoria() throws DAOException {
        
        JDBCCategoriaDAO jdCategoria = new JDBCCategoriaDAO();
        Categoria categoria = new Categoria();
        
        System.out.println("Categoria de la guàrdia:");
        System.out.println("1. Infermeria");
        System.out.println("2. TCAI");
        int x = entrada.nextShort();
        switch (x) {
            case 1:
                categoria = jdCategoria.getPerNom("Infermeria");
                break;
            case 2:
                  categoria = jdCategoria.getPerNom("TCAI");
                break;
            default:
                System.out.println("Tria Infermeria(1) o TCAI(2)");
                triarCategoria();
        }
        return categoria;
    }

    private static void mostrarMesos() {
        
        System.out.println("1.Gener      2. Febrer    3. Març");
        System.out.println("4.Abril      5. Maig      6. Juny");
        System.out.println("7.Juliol     8. Agost     9. Setembre");
        System.out.println("10.Octubre  11. Novembre 12. Desembre");
    }

    private static LocalDate mostrarDiumengesDelMes(int any, int mes , Treballador t) throws DAOException {
            int errors = 0;
           // comprovacio de les dates entrada
          while(true) {
            
               if (any >= 2022 && (mes >= 1 && mes <=12)) {
                   break;
               }
               if (any < 2022) {

                   System.out.print("Entra un any correcte: ");
                   any = entrada.nextInt();
                   System.out.println();
               } 
               
               if (mes < 1 || mes > 12) {

                   System.out.print("Entra un mes correcte:");
                   mes = entrada.nextInt();
                   System.out.println();
               }
               if (errors >= 5) {
                   System.out.println("Vols tornar al menu? [s / n]");
                   String resposta = entrada.nextLine();
                   errors = 0;
                   
                   if (resposta.equals("s")) {
                       menuAdministracio(t);
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
    System.out.println("Diumenges disponibles");
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
          
              
        System.out.println("Entra el dia de la guardia");
        int dia = entrada.nextInt();
        
        /* si la llista de dies possibles no conte el dia triat 
        fa un bucle fins que el dia es correcte*/
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

    private static LocalDate entrarDataGuardia(Treballador t) throws DAOException {
         System.out.println("Entra l'any de la guardia");
                int any = entrada.nextShort();
                System.out.println("Entra l'mes de la guardia");
                int mes = entrada.nextShort();
                
                /* el metode mostrarDiumengesDelMes() mostra els dies diponibles 
                    i construeix la data de la guardia a crear o a eliminar
                   */
                
                return mostrarDiumengesDelMes(any , mes , t);
                
    }

    private static void mostrarPlantilla(Unitat unitat , Torn torn , Categoria categoria) throws DAOException {
        
           var jdbcplantilla = new JDBCPlantillaGuardiaDAO();
           PlantillaGuardia plG= new PlantillaGuardia();
           try{
           plG = jdbcplantilla.get(categoria.getTipusCategoria(), unitat.getTipusUnitat(), torn.getTipusTorn());
           System.out.printf("La guardia es crear amb la seguent plantilla : %d  %s  %s  %s %d " , plG.getId() , plG.getNomUnitatPlantilla(),
                  plG.getNomCategoriaPlantilla() , plG.getNomTornPlantilla() , plG.getId());
           } catch(DAOException e){
               System.out.println("Error al mostrar plantilla" + e.getMessage());
           }

    }

    private static long obtenirPlaces(Unitat unitat , Categoria categoria , Torn torn) {
            
        
        // obtenim les places que hi ha en una unitat categoria i torn especific
            var jdbcplantilla = new JDBCPlantillaGuardiaDAO();
           PlantillaGuardia plG= new PlantillaGuardia();
           try{
           plG = jdbcplantilla.get(categoria.getTipusCategoria(), unitat.getTipusUnitat(), torn.getTipusTorn());
               if (plG.getPlacesPlantilla() == 0) {
                   System.out.println("Error places es null o 0");
               }
           return plG.getPlacesPlantilla();
           } catch(DAOException e){
               System.out.println("Error al obtenir places plantilla" + e.getMessage());
           }
        return -1;
    }
    
    private static long notificacio(Unitat unitat, Categoria categoria, Torn torn) throws EmailException{
        
        while(true){
        
        Email email= new SimpleEmail();
        email.setHostName("smtf.googlemail.com"); //nom del servidor del correu
        email.setSmtpPort(465); //port de sortida 
        email.setAuthenticator(new DefaultAuthenticator("rigobertamartinez12@gmail.com", "rigobert12"));
        email.setSSLOnConnect(true);//comprova protocol d'enviar i rebre emails
        email.setFrom("rigobertamartinez12@gmail.com", "Rigo"); // Marc serà el nom d'on es rebrà 
        email.setSubject("Bon dia");
        email.setMsg("Hola, has reservat la guàrdia el dia"+g.getGuardia());
        email.addTo("mgrabulosa071@boscdelacoma.cat");//correu electrònic on s'envia el correu
        email.send();
        }
    }
    
    }
    
    
    

