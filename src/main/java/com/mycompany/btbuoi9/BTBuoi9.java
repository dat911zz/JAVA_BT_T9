/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.btbuoi9;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 *
 * @author datcy
 */
public class BTBuoi9 {

    public static void main(String[] args) {
        System.out.println("Hello \\ World!");
        
        MyLib.SQLUtil sql = new MyLib.SQLUtil("DAT911ZZ", "NhaSach");
        System.out.println(sql.execScalar("SELECT COUNT(*) as c FROM Sach;"));
        var lst = sql.execReader("SELECT * FROM Sach");
        lst.forEach((Object t) -> {
            System.out.println(t.toString());
        });
//        lst.forEach( consumer);
        new MainFrame().show();
    }
}
