package de.miirpati.abysscurse.events.effects;

import de.miirpati.abysscurse.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

//TODO

public class DeathEffect extends AbstractEffect{
    LivingEntity living;

    public DeathEffect(int delay, int power, long time, int iterations, double probability) {
        super(delay, power, time, iterations, probability);
        if (entity instanceof LivingEntity) {
            this.living = (LivingEntity) entity;
        } else {
            Tools.warnLog("Warning: DeathEffect Entity is not LivingEntity");
        }
    }

    public void effect() {
        /*
        if(living instanceof Player) {
            //TODO DROPS AND DEATH MESSAGE
            Bukkit.getServer().getPluginManager().callEvent(new PlayerDeathEvent((Player) living, ));
        } else {
            Bukkit.getServer().getPluginManager().callEVent(new EntityDeathEvent(living, notCancelled));
        }
        */

        living.setHealth(-1.0);
    }
}
