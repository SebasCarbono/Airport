/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.core.models.storageManagement;

import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public interface GetItem<T> {
    T getItem(String id);
    ArrayList<T> getAllItems();
}
