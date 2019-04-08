package de.miirpati.abysscurse.curse.config;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

public class Universe {
    ListMultimap<World, Group> groups = ArrayListMultimap.create();

    public void addGroup(World world, Group group) {
        groups.put(world, group);
    }

    public ListMultimap<Group, Group.Area> getAreas(Location location) {
        ListMultimap<Group, Group.Area> returnList = ArrayListMultimap.create();

        World locWorld = location.getWorld();
        int x = location.getBlockX();
        short y = (short) location.getBlockY();
        int z = location.getBlockZ();

        for (Group g : groups.get(locWorld)) {
            for (Group.Area a : g.areas.values()) {
                if (a.active && a.checkBounds(x,y,z)) {
                    returnList.put(g, a);
                }
            }
        }

        for (Group g : groups.get(null)) {
            for (Group.Area a : g.areas.values()) {
                if (a.active && a.world == locWorld && a.checkBounds(x,y,z)) {
                    returnList.put(g, a);
                }
            }
        }

        return returnList;
    }

    public ListMultimap<Event, Float> getCurseEvents (Location location) {//Curse Events and multipliers.
        ListMultimap<Event, Float> returnList = ArrayListMultimap.create();

        World locWorld = location.getWorld();
        int x = location.getBlockX();
        short y = (short) location.getBlockY();
        int z = location.getBlockZ();

        for (Group g : groups.get(locWorld)) { //for groups with defined world
            float multiplier = 1;
            boolean hasArea = false;
            for (Group.Area a : g.areas.values()) {
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

        for (Group g : groups.get(null)) { //for groups spreading over multiple worlds
            float multiplier = 1;
            boolean hasArea = false;
            for (Group.Area a : g.areas.values()) {
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
