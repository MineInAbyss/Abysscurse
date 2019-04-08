package de.miirpati.abysscurse.events.effectEvents;

import de.miirpati.abysscurse.events.effects.AbstractEffect;
import de.miirpati.abysscurse.events.effects.PotionEffect;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;


public class AbstractEffectEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    AbstractEffect effect;
    Entity entity;
    Float multiplier = 1.0f;
    private boolean isCancelled;

    AbstractEffectEvent() {
        this.isCancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public AbstractEffect getEffect() { return this.effect; }

    public void setEntityAndMultiplier(Entity entity, Float multiplier) {
        this.entity = entity;
        this.multiplier = multiplier;
        effect.setEntity(this.entity);
        effect.setMultiplier(this.multiplier);
    }

    public Entity getEntity() { return entity; }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
        effect.cancel();
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
