package de.miirpati.abysscurse.curse.config;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

public class Universe {
    static ListMultimap<World, AreaEventGroup> groups = ArrayListMultimap.create();
    static final World DUMMY_WORLD = (World) new World.Spigot();

    public static void addGroup(World world, AreaEventGroup areaEventGroup) {
        groups.put(world, areaEventGroup);
    }

    public static ListMultimap<AreaEventGroup, AreaEventGroup.Area> getAreas(Location location) {
        ListMultimap<AreaEventGroup, AreaEventGroup.Area> returnList = ArrayListMultimap.create();

        World locWorld = location.getWorld();
        int x = location.getBlockX();
        short y = (short) location.getBlockY();
        int z = location.getBlockZ();

        for (AreaEventGroup g : groups.get(locWorld)) {
            for (AreaEventGroup.Area a : g.areas.values()) {
                if (a.active && a.checkBounds(x,y,z)) {
                    returnList.put(g, a);
                }
            }
        }

        for (AreaEventGroup g : groups.get(null)) {
            for (AreaEventGroup.Area a : g.areas.values()) {
                if (a.active && a.world == locWorld && a.checkBounds(x,y,z)) {
                    returnList.put(g, a);
                }
            }
        }

        return returnList;
    }

    public static ListMultimap<Event, Float> getCurseEvents (Location location) {//Curse Events and multipliers.
        ListMultimap<Event, Float> returnList = ArrayListMultimap.create();

        World locWorld = location.getWorld();
        int x = location.getBlockX();
        short y = (short) location.getBlockY();
        int z = location.getBlockZ();

        for (AreaEventGroup g : groups.get(locWorld)) { //for groups with defined world
            float multiplier = 1;
            boolean hasArea = false;
            for (AreaEventGroup.Area a : g.areas.values()) {
                if (a.active && a.checkBounds(x,y,z)) {
                    hasArea = true; //to know whether there was any area at all
                    multiplier = a.lengthMultiplier * multiplier; //stack all multipliers
                }
            }
            if (hasArea && multiplier != 0) {
                for (Event e : g.events) {
                    returnList.put(e, multiplier);
                }
            }
        }

        for (AreaEventGroup g : groups.get(null)) { //for groups spreading over multiple worlds
            float multiplier = 1;
            boolean hasArea = false;
            for (AreaEventGroup.Area a : g.areas.values()) {
                if (a.active && a.world == locWorld && a.checkBounds(x,y,z)) {
                    hasArea = true;
                    multiplier = a.lengthMultiplier * multiplier;
                }
            }
            if (hasArea && multiplier != 0) {
                for (Event e : g.events) {
                    returnList.put(e, multiplier);
                }
            }
        }

        return returnList;
    }
}
