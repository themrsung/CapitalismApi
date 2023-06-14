package jbs.capitalismapi.savetypes.navigation;

import org.bukkit.Location;

public class LocationData {
    public LocationData(Location loc) {
        world = loc.getWorld().getName();
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();
    }
    public LocationData(LocationData ld) {
        world = ld.world;
        x = ld.x;
        y = ld.y;
        z = ld.z;
        yaw = ld.yaw;
        pitch = ld.pitch;
    }
    public LocationData() {

    }

    public String world = "world";
    public double x = 0d;
    public double y = 0d;
    public double z = 0d;
    public float yaw = 0f;
    public float pitch = 0f;
}
