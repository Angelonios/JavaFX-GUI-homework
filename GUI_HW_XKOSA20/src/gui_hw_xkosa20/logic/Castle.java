/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_hw_xkosa20.logic;

/**
 * This class defines a castle object. It contains information about it's name
 * location and image.
 * @author angel
 */
public class Castle {
    private String name;
    private String URL;
    
    public Castle(String name, String imagePath){
        this.name = name;
        this.URL = imagePath;
    }

    public String getName() {
        return name;
    }
    
    public String getImage() {
        return URL;
    }
}
