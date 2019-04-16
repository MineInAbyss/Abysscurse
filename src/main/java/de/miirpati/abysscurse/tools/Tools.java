package de.miirpati.abysscurse.tools;

import java.util.Random;

import de.miirpati.abysscurse.AbyssCurse;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.SplittableRandom;
import java.util.logging.Logger;

public class Tools {
    public static SplittableRandom rng = new SplittableRandom(); //please everything but "normal" java random

    public static boolean isMineInAbyss = false;

    public static boolean forbidMilk = true;

    public static boolean logPlayerInUnmanagedAsWarning = true;

    public static Logger getPluginLogger(){
        return JavaPlugin.getPlugin(AbyssCurse.class).getLogger();
    }

    public static void warnLog(String message){
        getPluginLogger().warning(message);
    }

    public static void setupTools (){
        //TODO
        //set some runtime values, run config parsers
    }
}
