package de.miirpati.abysscurse.events;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.miirpati.abysscurse.events.effects.PotionEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;


public class CurseEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    //List of events
    ListMultimap<Event, Float> curseList = ArrayListMultimap.create();

    Entity entity;

    private boolean isCancelled;

    public CurseEvent(Entity entity, ListMultimap<Event, Float> eventList) {
        this.curseList = eventList;
        this.entity = entity;
        this.isCancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ListMultimap<Event, Float> getCurseList() { return curseList; }

    public Entity getEntity () { return entity; }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
