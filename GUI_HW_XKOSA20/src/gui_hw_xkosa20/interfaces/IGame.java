/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.interfaces;

import gui_hw_xkosa20.logic.Land;
import java.util.List;

/**
 * This interface defines how to interact with object Game.
 * @author angel
 */
public interface IGame {
    
    /**
     * This method shows list of castle names.
     * @param landName name of chosen land
     * @return list of names of castles from chosen land
     */
    public List<String> selectLand(String landName);
    
    /**
     * This method shows an image of one castle from chosen land.
     * @return URL of image
     */
    public String displayImage();
    
    /**
     * This method evaluates whether the selected answer (castle name) is
     * correct (the name belongs to the displayed castle).
     * @param answer chosen answer
     * @return true/false if right/wrong
     */
    public boolean selectAnswer(String answer);
    
    /**
     * Returns a list of names of lands.
     * @return list of names of lands
     */
    public List<String> getLands();
    
    /**
     * Returns the current land, if selected.
     * @return current land
     */
    public Land getCurrentLand();
    
    /**
     * This method gives information whether a castle is displayed or not
     * @return true/false if yes/no
     */
    public boolean isImageDisplayed();
    
    /**
     * Hides image.
     */
    public void hideImage();
    
}
