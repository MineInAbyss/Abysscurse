package de.miirpati.abysscurse.events;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.miirpati.abysscurse.events.effectEvents.AbstractEffectEvent;
import de.miirpati.abysscurse.events.effects.AbstractEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;


public class EffectListener implements Listener { //For managing active effects
    private static Plugin instance = Bukkit.getPluginManager().getPlugin("mineinabyss-curse");
    private static ListMultimap<Entity, AbstractEffect> entityEffectList = ArrayListMultimap.create();

    @EventHandler(priority = EventPriority.MONITOR)
    public void CurseEvent(CurseEvent curseEvent){
        if(curseEvent.isCancelled()) {return;}
        ListMultimap<Event, Float> curseList = curseEvent.getCurseList();
        Entity entity = curseEvent.getEntity();

        for (Event e : curseList.keys()) {
            if (e instanceof AbstractEffectEvent) {
                for (Float m : curseList.get(e)) { //Events using my more standardized system
                    ((AbstractEffectEvent) e).setEntityAndMultiplier(entity, m);
                    Bukkit.getServer().getPluginManager().callEvent(e);
                }
            } else {
                for (Float m : curseList.get(e)) { //the rest
                    Bukkit.getServer().getPluginManager().callEvent(e);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void AbstractEffectEvent(AbstractEffectEvent effectEvent){
        if(effectEvent.isCancelled()) {return;}
        Entity entity = effectEvent.getEntity();

        entityEffectList.put(entity, effectEvent.getEffect());
    }

    public void removeEffects(Entity entity) { entityEffectList.removeAll(entity); }

    public static void cleanCancelEffect(Entity entity, AbstractEffect effect) {
        Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
            @Override
            public void run() {
                entityEffectList.remove(entity, effect);
            }}, (1));
    }
}
