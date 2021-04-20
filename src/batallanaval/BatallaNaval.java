/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batallanaval;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class BatallaNaval {

    /**
     * @param args the command line arguments
     */
    
    
    
    
    static String jugador[][] = new String[7][7];
    static String computadora[][] = new String[7][7];
    static boolean ultimoAtaque = false;
    static int ultimoX = 0;
    static int ultimoY = 0;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
//        
        jugador = inicializarTablero(jugador);
        computadora = inicializarTablero(computadora);

        System.out.println("Hola!!!!");
        
        String tableroCentral = "";

        llenarTablero(jugador);
        llenarTablero(computadora);
        
        String html = htmlTableroCompleto(jugador);
//        JOptionPane.showMessageDialog(null, html, "Tablero del jugador", JOptionPane.PLAIN_MESSAGE);
//
//        html = htmlTableroCompleto(computadora);
//        JOptionPane.showMessageDialog(null, html, "Tablero de la computadora", JOptionPane.PLAIN_MESSAGE);
        
        while(true){
        
            //Ataca el jugador
            html = htmlTableroCompleto(jugador);
            JOptionPane.showMessageDialog(null, html, "Tablero del jugador", JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null, "TURNO DEL JUGADOR");
            ataque(computadora);
            
            html = htmlTableroCompleto(computadora);
            JOptionPane.showMessageDialog(null, html, "Tablero de la computadora", JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null, "TURNO DE LA COMPUTADORA");
            ataqueAutomatico(jugador);
        }
        
        
//        String message = "<html><body><div width='200px' align='right'>This is some text!</div></body></html>";
//        JLabel messageLabel = new JLabel(message);
//        JOptionPane.showConfirmDialog(null, messageLabel);
    }
    
    public static String htmlTableroCompleto(String[][] tablero){
        String stablero = "                  BATALLA NAVAL             \n" + 
        "<html>" +
        "<head>" +
        "<style type='text/css'>" +
        "td { width:20px; height:20px; border: solid 2px #00BFFF; text-align: center; background-color:#00FFFF; }" +
        "table { border: solid 3px #00BFFF; }" +
        "#P { color:white; background-color:#8A2BE2; }" +
        "#S { color:white; background-color:#FF7F50; }" +
        "#A { color:white; background-color:#B8860B; }" +
        "#D { color:white; background-color:#FF1493; }" +
        "#X { color:white; background-color:#A52A2A; }" +
        "#O { color:white; background-color:#3CB371; }" +
        "#M { color:white; background-color:#8A2BE2; }" +
        "div {  background-color:#7FFFD4; }" + 
        "</style>" +
        "</head>" +
        "<body>" +
        "<div width='200px' height='220px' align='center' valign = 'middle'> " +
        "<table> " +
        htmlTableroCuadricula(tablero) +
        "</table>" +
        "</div>" +
        "</body>" + 
        "</html>";
        
        return stablero;
    }
    
    public static String htmlTableroCuadricula(String[][] tablero){
        Random numeroRandom = new Random();
        int numeroAleatorio = numeroRandom.nextInt(7);
        
        String sTablero = "";
        
        sTablero += "<tr>"
                + "<td></td>"
                + "<td>0</td>"
                + "<td>1</td>"
                + "<td>2</td>"
                + "<td>3</td>"
                + "<td>4</td>"
                + "<td>5</td>"
                + "<td>6</td></tr>";
        for(int x = 0; x < 7; x++){
            sTablero += "<tr>";
            sTablero += "<td>" + x + "</td>";
            for(int y = 0; y < 7; y++){
                sTablero += htmlColor(tablero[x][y]) + "  " + tablero[x][y] + "  " + "</td>";
            }
            sTablero += "</tr>";
        }
        
        return sTablero;
    }
    
    public static String htmlColor(String tipoBarco){
        String tdColor = "";
        switch(tipoBarco){
            case "X":
                return "<td id=\"X\">";
            case "O":
                return "<td id=\"O\">";
            case "P":
                return "<td id=\"P\">";
            case "S":
                return "<td id=\"S\">";
            case "A":
                return "<td id=\"A\">";
            case "D":
                return "<td id=\"D\">";
            default:
                return "<td background-color:#FFFFFF >";
        }
    }
    
    public static String[][] inicializarTablero(String[][] tablero){
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 7; y++){
                tablero[x][y] = " ";
            }
        }
        return tablero;
    }
    
    public static void mostrarTablero(String[][] tablero){
        
        String tableroString = "";
        
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 7; y++){
                tableroString += "[" + tablero[x][y] + "]";
            }
            tableroString += "\n";
        }
        tableroString += "\n";
        JOptionPane.showMessageDialog(null, tableroString, "Tablero del jugador", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void llenarTablero(String [][] tablero){
        
        //Portaaviones
        
        ponerBarco(5, tablero);
        
        //Submarinos
        
        ponerBarco(4, tablero);
        ponerBarco(4, tablero);
        
        //Acorazados
        
        ponerBarco(3, tablero);
        ponerBarco(3, tablero);
        ponerBarco(3, tablero);
        
        //Destructores
        
        ponerBarco(2, tablero);
        ponerBarco(2, tablero);
        ponerBarco(2, tablero);
        ponerBarco(2, tablero);
        
    }
    
    public static void ponerBarco(int tipoBarco, String[][] matriz){
        Random aleatorio = new Random();
        boolean flag = true;
        boolean direccion = true;
        int x, y;
        
        while(flag){
            direccion = direccionBarco();
            if(direccion){
                x = aleatorio.nextInt(6);
                y = aleatorio.nextInt(8 - tipoBarco);
                if(comprobarBarco(direccion, x, y, tipoBarco, matriz)){
                    pintarBarco(direccion, x, y, tipoBarco, matriz);
                    flag = false;
                }
            } else {
                x = aleatorio.nextInt(8-tipoBarco);
                y = aleatorio.nextInt(6);
                if(comprobarBarco(direccion, x, y, tipoBarco, matriz)){
                    pintarBarco(direccion, x, y, tipoBarco, matriz);
                    flag = false;
                }
            }
        }
        
    }
    
    public static void pintarBarco(boolean direccion, int fila, int columna, int tipo, String[][] matriz){
        
        if(direccion){
            for(int y = columna; y < columna + tipo; y++){
                matriz[fila][y] = tipoBarco(tipo);
            }
        } else {
            for(int x = fila; x < fila + tipo; x++){
                matriz[x][columna] = tipoBarco(tipo);
            }
        }
    }
    
    public static boolean comprobarBarco(boolean direccion, int fila, int columna, int tipo, String[][] matriz){
        boolean flag = true;
        
        if(direccion){
            for(int y = columna; y < columna + tipo; y++){
                if(!matriz[fila][y].equals(" ")){
                    flag = false;
                }
            }
        } else {
            for(int x = fila; x < fila + tipo; x++){
                if(!matriz[x][columna].equals(" ")){
                    flag = false;
                }
            }
        }
        
        return flag;
    }
    
    public static boolean direccionBarco(){
        Random aleatorio = new Random();
        boolean direccion  = aleatorio.nextBoolean();
        return direccion;
    }
    
    public static String tipoBarco(int tipo){
        switch(tipo){
            case 1:
                return "X";
            case 0:
                return "O";
            case 5:
                return "P";
            case 4:
                return "S";
            case 3:
                return "A";
            case 2:
                return "D";
            default:
                return "U";
        }
    }
    
    //*JUGABILIDAD*//
    
    public static void ataque(String[][] matrizAtacada){
        int fila = 0;
        int columna = 0;
        
        do {
            fila = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la fila a atacar: "));
            if(!(fila > -1 && fila < 7)){
                JOptionPane.showMessageDialog(null, "Digite un número de 0 a 6");
            }
        } while(!(fila > -1 && fila < 7));
        
        do {
            columna = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la columna a atacar: "));
            if(!(columna > -1 && columna < 7)){
                JOptionPane.showMessageDialog(null, "Digite un número de 0 a 6");
            }
        } while(!(columna > -1 && columna < 7));
        
        aquelepegue(fila, columna, matrizAtacada);   
    }
    
    public static void ataqueAutomatico(String[][] matrizAtacada){
        Random aleatorio = new Random();
        boolean direccion = true;
        int x, y;
        
        
        if(ultimoAtaque){
            direccion = direccionBarco();
            if(direccion){
                switch(ultimoX){
                    case 0:
                        ultimoX++;
                        break;
                    case 6:
                        ultimoX--;
                        break;
                    default:
                        ultimoX++;
                        break;
                }
            } else {
                switch(ultimoY){
                    case 0:
                        ultimoY++;
                        break;
                    case 6:
                        ultimoY--;
                        break;
                    default:
                        ultimoY++;
                        break;
                }
            }
            
            aquelepegueAutomatico(ultimoX, ultimoY, matrizAtacada);
            
        } else {
            x = aleatorio.nextInt(6);
            y = aleatorio.nextInt(6);
            aquelepegueAutomatico(x, y, matrizAtacada);
        }
        
        
    }
    
    public static boolean comprobarAtaque(int fila, int columna, String[][] matrizAtacada){
        if(matrizAtacada[fila][columna].equals(" ")){
            return false;
        } else {
            return true;
        }
    }
    
    public static String aquelepegue(int fila, int columna, String[][] matrizAtacada){
        switch(matrizAtacada[fila][columna]){
            case " ":
                System.out.println("Le diste al mar");
                matrizAtacada[fila][columna] = "O";
                return "Le diste al mar";
            case "X":
                System.out.println("Ya habìas tirado ahí");
                matrizAtacada[fila][columna] = "X";
                return "Ya habìas tirado ahí";
            case "O":
                System.out.println("Ya habìas tirado ahí");
                matrizAtacada[fila][columna] = "O";
                return "Ya habìas tirado ahí";
            case "P":
                System.out.println("Le diste a un portaaviones");
                matrizAtacada[fila][columna] = "X";
                return "Le diste a un portaaviones";
            case "S":
                System.out.println("Le diste a un submarino");
                matrizAtacada[fila][columna] = "X";
                return "Le diste a un submarino";
            case "A":
                System.out.println("Le diste a un acorazado");
                matrizAtacada[fila][columna] = "X";
                return "Le diste a un acorazado";
            case "D":
                System.out.println("Le diste a un destructor");
                matrizAtacada[fila][columna] = "X";
                return "Le diste a un destructor";
            default:
                return "";
        } 
    }
    
    
    public static void aquelepegueAutomatico(int fila, int columna, String[][] matrizAtacada){
        switch(matrizAtacada[fila][columna]){
            case " ":
                System.out.println("La computadora le dio al mar");
                ultimoAtaque = false;
                matrizAtacada[fila][columna] = "O";
                break;
            case "X":
                System.out.println("Ya habìas tirado ahí");
                ultimoAtaque = false;
                matrizAtacada[fila][columna] = "X";
                break;
            case "O":
                System.out.println("Ya habìas tirado ahí");
                ultimoAtaque = false;
                matrizAtacada[fila][columna] = "O";
                break;
            case "P":
                System.out.println("La computadora le dio a un portaaviones");
                matrizAtacada[fila][columna] = "X";
                ultimoAtaque = true;
                ultimoX = fila;
                ultimoY = columna;
                break;
            case "S":
                System.out.println("La computadora le dio a un submarino");
                matrizAtacada[fila][columna] = "X";
                ultimoAtaque = true;
                ultimoX = fila;
                ultimoY = columna;
                break;
            case "A":
                System.out.println("La computadora le dio a un acorazado");
                matrizAtacada[fila][columna] = "X";
                ultimoAtaque = true;
                ultimoX = fila;
                ultimoY = columna;
                break;
            case "D":
                System.out.println("La computadora le dio a un destructor");
                matrizAtacada[fila][columna] = "X";
                ultimoAtaque = true;
                ultimoX = fila;
                ultimoY = columna;
                break;
            default:
                break;
        }
    }
}