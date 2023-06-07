package com.itclinical.struts2.helpers;
import java.util.Random;

public class StringGenHelper {   

    public static String createRandomAlphanumericString(int probability, int len){
        String output ="";
        Random random = new Random();		
        for(int i=0;i<len;++i){
            char outputCharacter = (char) random.nextInt(36);
            if(outputCharacter>25){
                output+=Integer.toString(35-outputCharacter);
            }
            else{
                outputCharacter +=65; 
                if(random.nextInt(100)+1>probability){
                    outputCharacter = Character.toLowerCase(outputCharacter);
                }
                output+=Character.toString(outputCharacter);
            }    
        }
        return output;
    }
}
