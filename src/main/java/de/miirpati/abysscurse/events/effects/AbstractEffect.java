package de.miirpati.abysscurse.events.effects;

import de.miirpati.abysscurse.events.EffectListener;
import de.miirpati.abysscurse.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public abstract class AbstractEffect {
    Entity entity;
    long tRemaining; //per effect
    //* int duration;
    long tElapsed; //time is always in ticks here
    long delay;
    int iterations;
    int iterationsRemaining;
    double probability;
    float multiplier;
    int power;
    boolean delayNotRepeat;
    boolean notCancelled = true;
    private Plugin instance = Bukkit.getPluginManager().getPlugin("mineinabyss-curse"); //

    AbstractEffect(int delay, int power, long time, int iterations, double probability) {
        this.iterations = iterations;
        this.tRemaining = time;
        this.tElapsed = 0;
        this.power = power;
        this.iterationsRemaining = iterations;
        this.probability = probability;
        if (delay > -1) { //negative delay means that the delay will be applied to every iteration
            this.delay = delay;
            this.delayNotRepeat = true;
        } else {
            this.delay = -1 * delay;
            this.delayNotRepeat = false;
        }
    }

    public void applyEffect() {
        Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
            @Override
            public void run() {
                if (notCancelled) { //check whether not cancelled
                    if (Tools.rng.nextDouble() < probability) { //probability
                        effect();
                    }
                } else {
                    return;
                }
                if (delayNotRepeat) { //set delay to 0 if input was positive
                    delay = 0;
                }
                if (iterationsRemaining > 1) { //schedule repeated iterations
                    Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
                        @Override
                        public void run() {
                            repeatEffect();
                        }
                    }, (1 + tRemaining + delay));
                } else {cancel();}
            }
        }, delay);
    }

    public void cancel() {
        notCancelled = false;
        EffectListener.cleanCancelEffect(entity, this);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    } //same as setMultiplier

    public void setMultiplier(Float lengthMultiplier) { //this may look pointless, but it turned out to be easier for me to make the multiplier only be known after the event for the effect is already generated
        multiplier = lengthMultiplier;
        delay = Math.round(delay * multiplier);
        tRemaining = Math.round(tRemaining * multiplier);
    }

    private void repeatEffect() {
        if (notCancelled){
            if (iterationsRemaining <= 0) { //check whether not cancelled or done
                iterationsRemaining--;

                if (Tools.rng.nextDouble() < probability) { //probability
                    effect();
                }

                Bukkit.getScheduler().runTaskLater(instance, new Runnable() {//recursion for repeating effects
                    @Override
                    public void run() {
                        repeatEffect();
                    }
                }, (1 + tRemaining + delay));
            } else {cancel();}
        }
    }

    abstract void effect(); //actual implementation of effects
}