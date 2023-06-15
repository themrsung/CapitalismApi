package jbs.capitalismapi.savetypes.navigation;

public class LocationApiData {
    public LocationApiData(
            String world,
            double x,
            double y,
            double z,
            float yaw,
            float pitch
    ) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public LocationApiData() {}

    public String world = "world";
    public double x = 0d;
    public double y = 0d;
    public double z = 0d;
    public float yaw  = 0f;
    public float pitch = 0f;
}
