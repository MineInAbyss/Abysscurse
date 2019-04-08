package de.miirpati.abysscurse.events.effectEvents;

import de.miirpati.abysscurse.events.effects.AbstractEffect;
import de.miirpati.abysscurse.events.effects.PotionEffect;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;


public class PotionEffectEvent extends AbstractEffectEvent {

    public PotionEffectEvent(int delay, int power, long time, int iterations, double probability, ArrayList<PotionEffectType> effects) {
        super();
        this.effect = new PotionEffect(delay, power, time, iterations, probability, effects);
    }
}
