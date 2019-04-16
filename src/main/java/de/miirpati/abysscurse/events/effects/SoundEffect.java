package de.miirpati.abysscurse.events.effects;

import de.miirpati.abysscurse.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

//TODO

public class SoundEffect extends AbstractEffect{
    ArrayList<Sound> sounds;
    Player player; //I'm assuming at this point that there is no need for sounds with

    public SoundEffect(int delay, int power, long time, int iterations, double probability, ArrayList<Sound> sounds) {
        super(delay, power, time, iterations, probability);
        this.sounds = sounds;
        if (entity instanceof Player) {       //cast Entity to LivingEntity for the effect to be able to use
            this.player = (Player) entity;
        } else {
            Tools.warnLog("Warning: SoundEffect Entity is not Player");
        }
    }

    public void effect() {
        Location soundLocation = new Vector(Tools.rng.nextDouble(power,power*-1), Tools.rng.nextDouble(power,power*-1), Tools.rng.nextDouble(power,power*-1)).toLocation(player.getWorld()).add(player.getLocation());

        Sound sound = sounds.get(Tools.rng.nextInt(sounds.size()));

        player.playSound(soundLocation, sound, 1f, 1f);
    }
}
