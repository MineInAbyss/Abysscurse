package de.miirpati.abysscurse.events.effects;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;

//TODO

public class SoundEffect extends AbstractEffect{
    ArrayList<Sound> sounds;
    LivingEntity living;

    public SoundEffect(int delay, int power, long time, int iterations, float multiplier, double probability, ArrayList<Sound> sounds) {
        super(delay, power, time, iterations, probability);
        this.sounds = sounds;
        if (entity instanceof LivingEntity) {       //cast Entity to LivingEntity for the effect to be able to use
            this.living = (LivingEntity) entity;
        } else {
            Bukkit.getConsoleSender().sendMessage("Warning: SoundEffect Entity is not LivingEntity");
        }
    }

    public void effect() {
        //TODO
    }
}
