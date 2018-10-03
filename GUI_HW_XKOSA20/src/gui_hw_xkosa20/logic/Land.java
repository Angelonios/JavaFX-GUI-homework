/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.logic;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * This object represents a land. Each land has 5 castles.
 * @author angel
 */
public class Land {
    private List<String> castleNames;
    private List<Castle> castles;
    private String name;
    
    public Land(String name, List<String> castleNames){
        this.castleNames = castleNames;
        castles = new ArrayList<>();
        this.name = name;
        init();
    }
    
    public String getName(){
        return name;
    }

    public List<String> getCastleNames() {
        return castleNames;
    }
    
    public List<Castle> getCastles(){
        return castles;
    }
    
    /**
     * This method initializes all castle objects.
     */
    private void init(){
        for(String name : castleNames){
            castles.add(new Castle(name, transformToURL(name)));
        }
    }
    
    /**
     * This method concatenates name of picture folder, then the next folder 
     * name of land and lastly the name of the castle.
     * 
     * Since the output of this program should be in Czech language, some
     * names of objects have diacritics.
     * The removal of diacritics in names of objects, in order to create valid
     * URLs to objects images, the class Normalizer has been used.
     * the normalize() method return a string, which can be edited by method
     * replaceAll().
     * The first parameter of this method - "\\p{InCombiningDiacriticalMarks}+"
     * targets all characters with diacritics.
     * the outcome is the same String as the name of a given object, but without
     * diacritics.
     * @param castleName
     * @return 
     */
    private String transformToURL(String castleName){
        return "/gui_hw_xkosa20/pictures/" 
               + Normalizer.normalize(name.split(" ")[0], Normalizer.Form.NFKD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
               + "/"
               + Normalizer.normalize(castleName, Normalizer.Form.NFKD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
               + ".jpg";
    }
}
