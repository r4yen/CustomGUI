package customguimaker.customguimaker;

public class Material {

    public static org.bukkit.Material getMaterialFromString(String material) {
        org.bukkit.Material m = org.bukkit.Material.getMaterial(material);

        return m;
    }

    public static String getStringFromMaterial(org.bukkit.Material material) {
        String m = material.name();

        return m;
    }

}
