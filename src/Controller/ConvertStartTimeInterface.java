/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author bergs
 */
public interface ConvertStartTimeInterface {
    LocalTime convertTimestamp(LocalDateTime n);
}
