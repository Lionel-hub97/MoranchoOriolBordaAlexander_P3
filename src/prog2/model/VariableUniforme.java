/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Daniel Ortiz
 */
public class VariableUniforme implements Serializable {
    private Random random;

    public VariableUniforme(long seed) {
        this.random = new Random(seed);
    }

    public int seguentValor() {
        return random.nextInt(100);
    }
}
