package net.xaviersala.farwest;

import java.awt.event.KeyEvent;
import java.util.Random;

import acm.program.GraphicsProgram;

/**
 * Hello world!
 *
 */
public class App extends GraphicsProgram {
    /**
     * Altura de la pantalla.
     */
    private static final int SCREENHEIGHT = 600;
    /**
     * Amplada de la pantalla.
     */
    private static final int SCREENWIDTH = 800;

    /**
     * Espera en cada passada.
     */
    private static final int RETARD = 50;
    /**
     * Número de plantes.
     */
    private static final int NUMPLANTES = 10;

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5046186790508838483L;

    /**
     * Classe que conté les imatges del joc.
     */
     private ArmariImatges armari = ArmariImatges.getInstance();

     /**
      * Protagonista del joc que es controla per l'usuari.
      */
     private CowBoy protagonista;
    /**
     * Generador de números.
     */
     private Random r;
     /**
      * Execució del programa.
      */
    public final void run() {



        carregarImatges();

        int principal = armari.addCowBoy("cowboy.jpg",
                posicioAleatoria(SCREENWIDTH),
                posicioAleatoria(SCREENHEIGHT));
        protagonista =  (CowBoy) armari.getPersonatge(principal);

        armari.addCowBoy("cowboy2.jpg", 0, 0);

        for (int i = 0; i < NUMPLANTES; i++) {
            armari.addPersonatge(new CosaEstatica("arbre.jpg",
                    posicioAleatoria(SCREENWIDTH),
                    posicioAleatoria(SCREENHEIGHT)));
        }


        while (true) {
            pause(RETARD);
            armari.mou();
        }


        // System.out.println("Hello World!");
    }


    /**
     * Carrega les imatges del joc en l'armari.
     */
    private void carregarImatges() {
        final String[] imatges = {"cowboy.jpg", "cowboy2.jpg", "bala.jpg",
                "arbre.jpg"};
        // ", "cactus.jpg"};
        armari.setPantalla(this);
        // Iniciar el sistema: Loading ...
        for (String imatge: imatges) {
            armari.setImatge(imatge);
        }
    }

    /**
     * Prem una tecla i es mou en la direcció que toca.
     * @param e event
     */
    @Override
    public final void keyPressed(final KeyEvent e) {
       protagonista.setVelocitat(2);
        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
            protagonista.setDireccio(90);
            break;
        case KeyEvent.VK_DOWN:
            protagonista.setDireccio(270);
            break;
        case KeyEvent.VK_LEFT:
            protagonista.setDireccio(180);
            break;
        case KeyEvent.VK_RIGHT:
            protagonista.setDireccio(0);
            break;
        case KeyEvent.VK_SPACE:
            // posar la velocitat a zero
            protagonista.dispara();
            protagonista.setVelocitat(0);
            break;
        default:
            break;
        }
    }

    /**
     * Deixa anar la tecla.
     * @param e event
     */
    @Override
    public final void keyReleased(final KeyEvent e) {
        protagonista.setVelocitat(0);
    }

    /**
     * Obtenir un valor aleatòri.
     * @param max valor màxim
     * @return número a retornar
     */
    final int posicioAleatoria(final int max) {
        return r.nextInt(max);
    }
    /**
     * Inicialitza el sistema.
     */
    public final void init() {

        setSize(SCREENWIDTH, SCREENHEIGHT);
        addKeyListeners(this);
        r = new Random();
    }
}