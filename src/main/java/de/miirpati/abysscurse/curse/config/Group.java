package de.miirpati.abysscurse.curse.config;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.ArrayList;

public class Group {
    World world = null;
    boolean enabled = true;
    Conditions conditions;
    ArrayList<Event> events = new ArrayList<>();
    ListMultimap<World, Area> areas = ArrayListMultimap.create();

    /* TODO check whether it is even needed
    public float getMultiplier(Location location) {//preferable when areas are spread around multiple worlds
        float multiplier = 1;
        World locWorld = location.getWorld();
        int x = location.getBlockX();
        short y = (short) location.getBlockY();
        int z = location.getBlockZ();

        for (Area a : areas.get(world)) {
            if (a.active && a.world == locWorld && a.checkBounds(x,y,z)) {
                multiplier = a.lengthMultiplier * multiplier;
            }
        }
        return multiplier;
    }

    public float getMultiplier(int x, short y, int z) {//assumes that all areas are in one world
        float multiplier = 1;
        if(areas.size() == 0) {return 0;}
        for (Area a : areas.values()) {
            if (a.active && a.checkBounds(x,y,z)) {
                multiplier = a.lengthMultiplier * multiplier;
            }
        }
        return multiplier;
    }*/

    public static class Area {
        World world;
        boolean active = true;
        float powerMultiplier = 1; //not in use as of now
        float lengthMultiplier = 1;
        int minX = 0;
        short minY = 0;
        int minZ = 0;
        int maxX = 0;
        short maxY = 0;
        int maxZ = 0;

        Area(int minX, short minY, int minZ, int maxX, short maxY, int maxZ) {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
        }

        public void deactivate(){active = false;}

        public void activate() {active = true;}

        public void setWorld(World world) {this.world = world;}

        public void setPowerMultiplier(float powerMultiplier) {this.powerMultiplier = powerMultiplier;}

        public void setLengthMultiplier(float lengthMultiplier) {this.lengthMultiplier = lengthMultiplier;}

        public boolean checkBounds(int x, short y, int z){
            if (x >= minX && x <= maxX) {
                if (z >= minZ && z <= maxZ) {
                    return y >= minY && y <= maxY;
                }
            }
            return false;
        }
    }

    public static class Conditions {

        enum condition {
            CHANGEPOS,
            VECTOR
        }

        ListMultimap<Enum, Double> multimap = ArrayListMultimap.create();

        Conditions(ListMultimap<Enum, Double> multimap){
            this.multimap = multimap;
        }

        public boolean checkconditions(){return false;} //TODO

        public Location locChange(Location currentLocation) {return currentLocation;} //TODO better use that in combination with above
    }
}
