/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author bergs
 */
public interface ConvertTimeInterface {
    Timestamp convertTimestamp(Timestamp n);
}
