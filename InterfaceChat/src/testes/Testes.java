/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author viniciuslucena
 */
public class Testes {
   
    public static void main(String args[]) {
        String str = "ViniciusLucena";
        int id = getIdByName(str);
        
       

    }

    private static int getIdByName(String usr) {
        int answer = 0;
        
        for(int i = 0; i < usr.length(); i++)
            answer += Character.getNumericValue(usr.charAt(i));
        
        return answer;
    }
    
    
}
