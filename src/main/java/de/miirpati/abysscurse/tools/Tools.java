package de.miirpati.abysscurse.tools;

import java.util.Random;
import org.bukkit.*;
import java.util.SplittableRandom;

public class Tools {
    public static SplittableRandom rng = new SplittableRandom(); //please everything but "normal" java random

    public static boolean hooked = false;

    public static boolean forbidMilk = true;

    public void tellConsole(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public void setupTools (){
        //TODO
        //set some runtime values, run config parsers
    }


}
