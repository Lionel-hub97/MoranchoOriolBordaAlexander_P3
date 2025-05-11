/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    private Adaptador adaptador;

    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        // Afegir codi adicional si fos necessari:
        this.adaptador = new Adaptador();
    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        Menu<OpcionsMenuPrincipal> menuPrincipal = new Menu<>("Menu Principal", OpcionsMenuPrincipal.values());

        String[] descripcions = {
                "Gestio de barres de control",
                "Gestio del reactor",
                "Gestio del sistema de refrigeracio",
                "Mostrar estat de la central",
                "Mostrar bitacola",
                "Mostrar incidencies",
                "Obtenir demanda satisfeta",
                "Finalitzar dia",
                "Guardar dades",
                "Carrega dades",
                "Sortir"
        };
        menuPrincipal.setDescripcions(descripcions);

        Scanner sc = new Scanner(System.in);
        OpcionsMenuPrincipal opcio;

        do {
            menuPrincipal.mostrarMenu();
            opcio = menuPrincipal.getOpcio(sc);

            switch (opcio) {
                case GESTIO_BARRES_DE_CONTROL:
                    gestioBarresDeControl(sc);
                    break;
                case GESTIO_REACTOR:
                    gestioReactor(sc);
                    break;
                case GESTIO_SISTEMA_REFRIGERACIO:
                    gestioSistemaRefrigeracio(sc);
                    break;
                case MOSTRAR_ESTAT_CENTRAL:
                    System.out.println(adaptador.mostraEstat());
                    break;
                case MOSTRAR_BITACOLA:
                    System.out.println(adaptador.mostraBitacola());
                    break;
                case MOSTRAR_INCIDENCIES:
                    System.out.println(adaptador.mostraIncidencies());
                    break;
                case OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL:
                    System.out.println("Demanda de Potencia: " + demandaPotencia);
                    System.out.println("Potencia satisfeta: " + adaptador.calculaPotencia());
                    System.out.println("Percentatge de la demanda satisfeta: " + ((adaptador.calculaPotencia()/demandaPotencia)*100) + "%");
                    break;
                case FINALITZAR_DIA:
                    finalitzaDia();
                    break;
                case GUARDAR_DADES:
                    System.out.println("Indica el nom de fitxer: ");
                    try {
                        adaptador.guardaDades(sc.nextLine());
                    }
                    catch (CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case CARREGA_DADES:
                    System.out.println("Indica el nom de fitxer: ");

                    try {
                        adaptador.carregaDades(sc.nextLine());
                    } catch (CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case SORTIR:
                    System.out.println("Sortint del sistema...");
                    break;
                default:
                    System.out.println("Opció no implementada encara.");
            }

        } while (opcio != OpcionsMenuPrincipal.SORTIR);


    }

    private void gestioBarresDeControl(Scanner sc) {
        Menu<OpcionsBarresDeControl> menu =
                new Menu<>("Gestio Barres de Control", OpcionsBarresDeControl.values());

        String[] descripcions = {
                "Mostrar insercio de les barres",
                "Establir insercio de les barres",
                "Sortir del submenú"
        };
        menu.setDescripcions(descripcions);

        OpcionsBarresDeControl opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio) {
                case OBTENIR_INSERCIO_BARRES:
                    System.out.println("Insercio actual: " + adaptador.getInsercioBarres());
                    break;
                case ESTABLIR_INSERCIO_BARRES:
                    System.out.print("Nova insercio (0-100): ");
                    float in = sc.nextFloat();
                    sc.nextLine();
                    try {
                    adaptador.setInsercioBarres(in);
                    } catch (CentralUBException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println("Insercio establerta a: " + in);
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcio != OpcionsBarresDeControl.SORTIR);
    }

    private void gestioReactor(Scanner sc) {
        Menu<OpcionsReactor> menuReactor =
                new Menu<>("Gestio del Reactor", OpcionsReactor.values());

        String[] descripcions = {
                "Activar el reactor",
                "Desactivar el reactor",
                "Mostrar estat del reactor",
                "Sortir del submenú"
        };
        menuReactor.setDescripcions(descripcions);

        OpcionsReactor opcio;
        do {
            menuReactor.mostrarMenu();
            opcio = menuReactor.getOpcio(sc);

            switch (opcio) {
                case ACTIVAR_REACTOR:
                    // Crida a l'adaptador per activar el reactor
                    try {
                        adaptador.activaReactor();
                    } catch (CentralUBException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println("Reactor activat.");
                    break;
                case DESACTIVAR_REACTOR:
                    // Crida a l'adaptador per desactivar
                    adaptador.desactivaReactor();
                    System.out.println("Reactor desactivat.");
                    break;
                case MOSTRAR_ESTAT:
                    // Mostrar estat del reactor
                    System.out.println("Estat Reactor: \n" + adaptador.mostraReactor());
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcio != OpcionsReactor.SORTIR);
    }

    private void gestioSistemaRefrigeracio(Scanner sc) {
        Menu<OpcionsSistemaRefrigeracio> menu =
                new Menu<>("Sistema de Refrigeracio", OpcionsSistemaRefrigeracio.values());

        String[] descripcions = {
                "Activar totes les bombes",
                "Desactivar totes les bombes",
                "Activar bomba especifica",
                "Desactivar bomba especifica",
                "Mostrar estat de les bombes",
                "Sortir del submenú"
        };
        menu.setDescripcions(descripcions);

        OpcionsSistemaRefrigeracio opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio) {
                case ACTIVAR_TOTES_LES_BOMBES:
                    try {
                        adaptador.activarBombes();
                        System.out.println("Totes les bombes activades.");
                    } catch (CentralUBException e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    break;
                case DESACTIVAR_TOTES_LES_BOMBES:
                    try {
                        adaptador.desactivarBombes();
                        System.out.println("Totes les bombes desactivades.");
                    } catch (CentralUBException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case ACTIVAR_BOMBA:
                    try {
                        System.out.print("Introdueix l'ID de la bomba (0-3): ");
                        int idActiva = sc.nextInt();
                        sc.nextLine();
                        adaptador.activaBomba(idActiva);
                        System.out.println("Bomba " + idActiva + " activada.");
                    } catch (CentralUBException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case DESACTIVAR_BOMBA:
                    System.out.print("Introdueix l'ID de la bomba (0-3): ");
                    int idDesactiva = sc.nextInt();

                    adaptador.desactivaBomba(idDesactiva);
                    System.out.println("Bomba " + idDesactiva + " desactivada.");
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println("Estat actual de les bombes: " + adaptador.mostraSistemaRefrigeracio());
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcio != OpcionsSistemaRefrigeracio.SORTIR);
    }


    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }



    //ENUMS
    public enum OpcionsMenuPrincipal {
        GESTIO_BARRES_DE_CONTROL,
        GESTIO_REACTOR,
        GESTIO_SISTEMA_REFRIGERACIO,
        MOSTRAR_ESTAT_CENTRAL,
        MOSTRAR_BITACOLA,
        MOSTRAR_INCIDENCIES,
        OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL,
        FINALITZAR_DIA,
        GUARDAR_DADES,
        CARREGA_DADES,
        SORTIR
    }

    public enum OpcionsBarresDeControl {
        OBTENIR_INSERCIO_BARRES,
        ESTABLIR_INSERCIO_BARRES,
        SORTIR
    }
    public enum OpcionsReactor {
        ACTIVAR_REACTOR,
        DESACTIVAR_REACTOR,
        MOSTRAR_ESTAT,
        SORTIR
    }

    public enum OpcionsSistemaRefrigeracio {
        ACTIVAR_TOTES_LES_BOMBES,
        DESACTIVAR_TOTES_LES_BOMBES,
        ACTIVAR_BOMBA,
        DESACTIVAR_BOMBA,
        MOSTRAR_ESTAT,
        SORTIR
    }
}
