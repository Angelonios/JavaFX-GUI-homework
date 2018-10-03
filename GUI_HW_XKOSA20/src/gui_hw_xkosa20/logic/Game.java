/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.logic;

import gui_hw_xkosa20.interfaces.IGame;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This is object represents the game itself.
 * How to play game:
 *  1) User chooses a land and the game will return a list of names of
 *     castles from chosen land.
 *  2) Then user has to ask the game to display a picture of a castle from 
 *     chosen land.
 *  3) After a picture is displayed, the user the chooses a name, which he 
 *     thinks it belongs to the castle. After his choice the game generates a 
 *     pop-up window with the result.
 * @author angel
 */
public class Game implements IGame{
    
    /*Map of lands and their castles*/
    private static Map<String, List<String>> LANDS_2_CASTLES;
    /*Names of lands.*/
    private List<String> landNames;
    /*References to lands.*/
    private List<Land> lands;
    
    /*Identifier of currently selected castle.*/
    private Castle displayedCastle;
    /*Identifier of currently selected land.*/
    private Land currentLand;
    
    public Game() throws IllegalAccessException, 
                         InstantiationException{
        init();
    }
    
    @Override
    public List<String> selectLand(String landName) {
        currentLand = getLand(landName);
        return currentLand.getCastleNames();
    }

    @Override
    public String displayImage(){
        displayedCastle = pickRandom();
        return displayedCastle.getImage();
    }
    
    @Override
    public boolean selectAnswer(String answer) {
        if(answer.equals(displayedCastle.getName())){
            return true;
        }
        return false;
    }
    
    @Override
    public List<String> getLands(){
        return landNames;
    }
    
    @Override
    public Land getCurrentLand() {
        return currentLand;
    }

    @Override
    public boolean isImageDisplayed(){
        if(displayedCastle == null){
            return false;
        }
        return true;
    }
    
    @Override
    public void hideImage(){
        displayedCastle = null;
    }
    /**
     * Picks random castle from current land.
     */
    private Castle pickRandom() {
        Random rand = new Random();
        return currentLand.getCastles().get(rand.nextInt(5));
    }
    
    private Land getLand(String landName){
        for(Land l : lands){
            if(l.getName().equals(landName)){
                return l;
            }
        }
        return null;
    }
    
    /**
     * This method initializes names and instances of game objects.
     * Identifiers of currently selected objects are set to null.
     * 
     * Then field which holds names of lands is initialized with an empty
     * ArrayList.
     * 
     * Initialization of values of these fields is done using reflection.
     * Fields of class Texts, which contains string constants of names of all 
     * objects, are fetched and their value extracted.
     * 
     * While extracting field values of Class Texts the LANDS_2_CASTLES field
     * is being filled with keys and their corresponding values.
     * 
     * Once all names of all objects are stored in map field, the field that 
     * holds references to land objects is also initialized.
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException 
     */
    private void init() throws IllegalAccessException,
                               InstantiationException{
        
        currentLand = null;
        displayedCastle = null;

        LANDS_2_CASTLES = new HashMap<>();        
        landNames = new ArrayList<>();
        
        List<String> jihocesky_list = new ArrayList<>(); 
        List<String> karlovarsky_list = new ArrayList<>(); 
        List<String> stredocesky_list = new ArrayList<>(); 
        
        Field[] fields = Texts.class.getDeclaredFields();
        for(Field f : fields){
            String name = (String) f.get(Texts.class.newInstance());
            String[] ID = f.getName().split("_");
            if(ID.length < 2){
                landNames.add(name);
            }
            else if(ID[0].equals("JC")){
                jihocesky_list.add(name);
            }
            else if(ID[0].equals("KV")){
                karlovarsky_list.add(name);
            }
            else if(ID[0].equals("SC")){
                stredocesky_list.add(name);
            }
        }
        
        /*Initialization of HashMap using previously initialized 
          lists of names of objects*/
        LANDS_2_CASTLES.put(Texts.JIHOCESKY, jihocesky_list);
        LANDS_2_CASTLES.put(Texts.KARLOVARSKY, karlovarsky_list);
        LANDS_2_CASTLES.put(Texts.STREDOCESKY, stredocesky_list);

        /*Initialize land objects using hashmap containing names of objects.*/
        lands = new ArrayList<>();
        Set<String> keySet = LANDS_2_CASTLES.keySet();
        keySet.forEach(
            (key) -> {lands.add(new Land(key, LANDS_2_CASTLES.get(key)));
        });
    }
}
