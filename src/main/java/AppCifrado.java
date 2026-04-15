
import java.awt.BorderLayout;   
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import criptografia.CifradoAtbash;
import criptografia.CifradoCesar;
import criptografia.CifradoPlayfair;
import criptografia.CifradoRailFence;
import criptografia.CifradoVigenere;

public class AppCifrado extends JFrame {

    private JLabel labelTituloIzquierdo, labelTituloDerecho, labelDescripcion, labelParametro;
    private JTextArea areaTextoIzquierda, areaTextoDerecha;
    private JTextField campoParametro;
    private JPanel panelConfiguracion;
    private JButton botonAccionPrincipal, botonIntercambio;
    private String cifradoSeleccionado = "César";
    private boolean modoCifrar = true;

    public AppCifrado() {
        setTitle("Laboratorio 1: Criptografía Clásica");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        //Panel de selección y configuración
        JPanel panelNorte = new JPanel(new GridLayout(3, 1));
        
        JLabel labelInstruccion = new JLabel("Escoja el cifrado", SwingConstants.CENTER);
        labelInstruccion.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel panelBotonesCifrado = new JPanel();
        String[] nombresCifrados = {"César", "Atbash", "Vigenere", "Rail Fence", "Playfair"};
        for (String nombre : nombresCifrados) {
            JButton btn = new JButton(nombre);
            btn.addActionListener(e -> seleccionarCifrado(nombre));
            panelBotonesCifrado.add(btn);
        }

        //Panel de Parámetro Dinámico
        panelConfiguracion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelParametro = new JLabel("Número de Desplazamiento: ");
        campoParametro = new JTextField("", 15);
        panelConfiguracion.add(labelParametro);
        panelConfiguracion.add(campoParametro);
        
        panelNorte.add(labelInstruccion);
        panelNorte.add(panelBotonesCifrado);
        panelNorte.add(panelConfiguracion);
        add(panelNorte, BorderLayout.NORTH);

        //Panel de descripción
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        
        labelDescripcion = new JLabel("<html><div style='text-align: center;'><b>Cifrado César:</b> Este algoritmo es de sustitución monoalfabética. Funciona desplazando cada letra del mensaje un número fijo de posiciones a través del alfabeto.</div></html>");
        labelDescripcion.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        labelDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(labelDescripcion, BorderLayout.NORTH);

        JPanel panelCuadros = new JPanel(new GridLayout(1, 3, 10, 10));
        panelCuadros.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //Cuadro Izquierdo
        JPanel pIzquierdo = new JPanel(new BorderLayout());
        labelTituloIzquierdo = new JLabel("Texto a cifrar");
        areaTextoIzquierda = new JTextArea(10, 20);
        pIzquierdo.add(labelTituloIzquierdo, BorderLayout.NORTH);
        pIzquierdo.add(new JScrollPane(areaTextoIzquierda), BorderLayout.CENTER);

        //Botón de Intercambio
        botonIntercambio = new JButton("\u21C4"); 
        botonIntercambio.setFont(new Font("SansSerif", Font.BOLD, 22)); 
        botonIntercambio.addActionListener(e -> alternarModo());
        JPanel pCentro = new JPanel(new GridBagLayout()); 
        pCentro.add(botonIntercambio);

        //Cuadro Derecho
        JPanel pDerecho = new JPanel(new BorderLayout());
        labelTituloDerecho = new JLabel("Texto cifrado");
        areaTextoDerecha = new JTextArea(10, 20);
        areaTextoDerecha.setEditable(false);
        pDerecho.add(labelTituloDerecho, BorderLayout.NORTH);
        pDerecho.add(new JScrollPane(areaTextoDerecha), BorderLayout.CENTER);

        panelCuadros.add(pIzquierdo);
        panelCuadros.add(pCentro);
        panelCuadros.add(pDerecho);
        panelCentral.add(panelCuadros, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        //Panel Inferior
        JPanel panelInferior = new JPanel();
        botonAccionPrincipal = new JButton("Cifrar");
        botonAccionPrincipal.setPreferredSize(new Dimension(150, 40));
        botonAccionPrincipal.addActionListener(e -> ejecutarProceso());
        
        panelInferior.add(botonAccionPrincipal);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void seleccionarCifrado(String nombre) {
        this.cifradoSeleccionado = nombre;
        campoParametro.setText("");

        switch (nombre) {
            case "César" -> {
                labelDescripcion.setText("<html><b>Cifrado César:</b> Este algoritmo es de sustitución monoalfabética. Funciona desplazando cada letra del mensaje un número fijo de posiciones a través del alfabeto.</html>");
                labelParametro.setText("Número de Desplazamiento: ");
                panelConfiguracion.setVisible(true);
            }
            case "Atbash" -> {
                labelDescripcion.setText("<html><b>Cifrado Atbash:</b> Es un tipo particular de sustitución donde el alfabeto se invierte por completo. La primera letra ('A') se sustituye por la última ('Z'), la segunda ('B') por la penúltima ('Y'), etc.</html>");
                panelConfiguracion.setVisible(false); // Atbash no usa clave
            }
            case "Vigenere" -> {
                labelDescripcion.setText("<html><b>Cifrado Vigenère:</b> Se le conoce como el cifrado polialfabético. A diferencia del César, utiliza una palabra clave para aplicar diferentes desplazamientos a cada letra.</html>");
                labelParametro.setText("Palabra Clave: ");
                panelConfiguracion.setVisible(true);
            }
            case "Rail Fence" -> {
                labelDescripcion.setText("<html><b>Rail Fence:</b> Es un cifrado de transposición, lo que significa que no cambia las letras, sino que las desordena. El mensaje se escribe en un patrón de 'zigzag' sobre un número determinado de filas y luego se lee fila por fila.</html>");
                labelParametro.setText("Número de Raíles: ");
                panelConfiguracion.setVisible(true);
            }
            case "Playfair" -> {
                labelDescripcion.setText("<html><b>Cifrado Playfair:</b>  Es un cifrado simétrico que sustituye pares de letras (digramas) en lugar de letras sueltas. Utiliza una matriz de 5x5 construida a partir de una palabra clave.</html>");
                labelParametro.setText("Palabra Clave: ");
                panelConfiguracion.setVisible(true);
            }
        }
        
        areaTextoIzquierda.setText("");
        areaTextoDerecha.setText("");
        panelConfiguracion.revalidate();
        panelConfiguracion.repaint();
    }

    private void alternarModo() {
        modoCifrar = !modoCifrar;
        if (modoCifrar) {
            labelTituloIzquierdo.setText("Texto a cifrar");
            labelTituloDerecho.setText("Texto cifrado");
            botonAccionPrincipal.setText("Cifrar");
        } else {
            labelTituloIzquierdo.setText("Texto a descifrar");
            labelTituloDerecho.setText("Texto descifrado");
            botonAccionPrincipal.setText("Descifrar");
        }
    }

    private void ejecutarProceso() {
        String input = areaTextoIzquierda.getText();
        String parametro = campoParametro.getText();
        String resultado = "";

        //Validar que haya texto ingresado
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el texto a procesar.");
            return;
        }

        //Validar que el parámetro no esté vacío
        if (!cifradoSeleccionado.equals("Atbash") && parametro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el parámetro requerido.");
            return;
        }

        try {
            if (modoCifrar) {
                resultado = switch (cifradoSeleccionado) {
                    case "César" -> CifradoCesar.cifrar(input, Integer.parseInt(parametro));
                    case "Atbash" -> CifradoAtbash.cifrar(input);
                    case "Vigenere" -> CifradoVigenere.cifrar(input, parametro);
                    case "Rail Fence" -> CifradoRailFence.cifrar(input, Integer.parseInt(parametro));
                    case "Playfair" -> CifradoPlayfair.cifrar(input, parametro);
                    default -> "";
                };
            } else {
                resultado = switch (cifradoSeleccionado) {
                    case "César" -> CifradoCesar.descifrar(input, Integer.parseInt(parametro));
                    case "Atbash" -> CifradoAtbash.descifrar(input);
                    case "Vigenere" -> CifradoVigenere.descifrar(input, parametro);
                    case "Rail Fence" -> CifradoRailFence.descifrar(input, Integer.parseInt(parametro));
                    case "Playfair" -> CifradoPlayfair.descifrar(input, parametro);
                    default -> "";
                };
            }
            areaTextoDerecha.setText(resultado);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido en el campo de configuración.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el proceso: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppCifrado().setVisible(true);
        });
    }
}