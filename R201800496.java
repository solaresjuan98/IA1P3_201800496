package IA1jun23;

import robocode.*;
import java.awt.Color;

/**
 * R201800496 - un robot realizado por Juan Antonio Solares
 */
public class R201800496 extends Robot {
    boolean girarDerecha = true;
    boolean moverAdelante = true;

    public void run() {
        // La inicialización del robot debe ir aquí
        setColors(Color.red, Color.blue, Color.green);

        // Bucle principal del robot
        while (true) {
            if (moverAdelante) {
                avanzar(200);
            } else {
                girarDerecha(45);
                retroceder(200);
                girarIzquierda(90);
                avanzar(200);
            }

            // Removí el código de disparo aquí ya que se maneja en onScannedRobot
        }
    }

    /**
     * onScannedRobot: qué hacer cuando se detecta otro robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        double distancia = e.getDistance();

        if (distancia > 200) {
            avanzar(200);
        } else {
            retroceder(100);
        }

        esquivarDisparo(e.getBearing());

        double giro = getHeading() + e.getBearing() - getGunHeading();
        girarCañonDerecha(giro);

        disparar(calcularPotenciaDisparo(distancia));
    }

    /**
     * onHitByBullet: qué hacer cuando eres golpeado por una bala
     */
    public void onHitByBullet(HitByBulletEvent e) {
        if (girarDerecha) {
            girarIzquierda(45);
            girarDerecha = false;
        } else {
            girarDerecha(45);
            girarDerecha = true;
        }
    }

    /**
     * onHitWall: qué hacer cuando chocas contra una pared
     */
    public void onHitWall(HitWallEvent e) {
        if (moverAdelante) {
            retroceder(400);
            if (girarDerecha) {
                girarIzquierda(90);
                girarDerecha = false;
            } else {
                girarDerecha(90);
                girarDerecha = true;
                moverAdelante = false;
            }
        } else {
            avanzar(400);
            moverAdelante = true;
            if (girarDerecha) {
                girarIzquierda(90);
                girarDerecha = false;
            } else {
                girarDerecha(90);
                girarDerecha = true;
            }
        }
    }

    private void avanzar(int distancia) {
        ahead(distancia);
    }

    private void retroceder(int distancia) {
        back(distancia);
    }

    private void girarDerecha(double grados) {
        turnRight(grados);
    }

    private void girarIzquierda(double grados) {
        turnLeft(grados);
    }

    private void girarCañonDerecha(double grados) {
        turnGunRight(grados);
    }

    private void disparar(int potencia) {
        fire(potencia);
    }

    private void esquivarDisparo(double bearing) {
        if (bearing >= 0) {
            girarIzquierda(90);
        } else {
            girarDerecha(90);
        }
    }

    private int calcularPotenciaDisparo(double distancia) {
        if (distancia < 100) {
            return 1;
        } else if (distancia < 200) {
            return 1;
        } else {
            return 1;
        }
    }
}
