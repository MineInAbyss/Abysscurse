package de.miirpati.abysscurse;

import de.miirpati.abysscurse.events.EffectListener;
import de.miirpati.abysscurse.tools.Tools;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.SplittableRandom;
import org.bukkit.*;

public class Abysscurse extends JavaPlugin {
    public SplittableRandom rng = new SplittableRandom();

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new EffectListener(), this);

        if (Tools.hooked) hooked(); //checks whether running with mineinabyss
        else standalone();
    }

    @Override
    public void onDisable(){
        getServer().getServicesManager().unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void standalone(){

    }

    private void hooked(){

    }
}