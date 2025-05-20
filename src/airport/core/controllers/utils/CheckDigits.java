/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.utils;

/**
 *
 * @author Sebas
 */
public class CheckDigits {
    public static boolean longerThat(long number, int n) {
        return String.valueOf(Math.abs(number)).length() > n;
    }
}
