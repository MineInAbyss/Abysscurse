package de.miirpati.abysscurse.events.effectEvents;

import de.miirpati.abysscurse.events.effects.SoundEffect;
import org.bukkit.Sound;

import java.util.ArrayList;


public class SoundEffectEvent extends AbstractEffectEvent {

    public SoundEffectEvent(int delay, int power, long time, int iterations, double probability, ArrayList<Sound> sounds) {
        super();
        this.effect = new SoundEffect(delay, power, time, iterations, probability, sounds);
    }
}
