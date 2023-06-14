package jbs.capitalismapi.types.config;

import jbs.capitalismapi.savetypes.navigation.LocationData;
import org.bukkit.Material;

import javax.annotation.Nullable;

public class Config {
    public Config() {}

    public String version = "5.1.3";

    // THIS IS ALSO USED AS A SAVE TYPE. ONLY DEFINE VARIABLES AND USE SAVE TYPES. NO FUNCTIONS OR METHODS.
    public String serverEntityUuid = null; // Will be ignored when null

    // Safeguards
    public boolean isDeployed = false;

    // LifeTicker
    public double startingCash = 86400d;
    public double respawnCash = 3600d;
    public double moneyRemovedPerSecond = 1d;
    public boolean removeMoneyInCompanies = false;
    public double moneyTakenPerDamageTaken = 10d;
    public double moneyGivenPerDamageDealtToPlayer = 9.5d;

    // Taxes
    public float dividendTaxRate = 0.15f;
    public float incomeTaxRate = 0.22f;

    // Trading
    public int minimumMaxStackSizeOfItemsToAutoDeliver = 64;
    public int slotsToKeepEmptyWhenAutomaticallyDelivering = 9;
    public Material[] doNotAutoDeliver = {
            Material.DIRT,
            Material.DIRT_PATH,
            Material.ROOTED_DIRT,
            Material.COARSE_DIRT
    };
    public Material[] unsupportedItems = {
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_SWORD,

            Material.STONE_AXE,
            Material.STONE_HOE,
            Material.STONE_PICKAXE,
            Material.STONE_SHOVEL,
            Material.STONE_SWORD,

            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_PICKAXE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,

            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,

            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,

            Material.NETHERITE_AXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD,

            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.LEATHER_HORSE_ARMOR,

            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,

            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.IRON_HORSE_ARMOR,

            Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_BOOTS,
            Material.GOLDEN_HORSE_ARMOR,

            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_HORSE_ARMOR,

            Material.NETHERITE_HELMET,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS,

            Material.FLINT_AND_STEEL,
            Material.FISHING_ROD,
            Material.BOW,
            Material.CROSSBOW,
            Material.TRIDENT,
            Material.SHIELD,
            Material.ELYTRA,
            Material.SHEARS,

            Material.TIPPED_ARROW,

            Material.POTION,
            Material.LINGERING_POTION,
            Material.SPLASH_POTION,

            Material.BOOK,
            Material.WRITABLE_BOOK,
            Material.WRITTEN_BOOK,
            Material.ENCHANTED_BOOK,

            Material.MAP,
            Material.FILLED_MAP,

            Material.SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.PINK_SHULKER_BOX
    };
    public int itemOrderIdShowLength = 10;


    // Companies
    public int companyNameMaxLength = 20;
    public int companyMaxShareCount = Integer.MAX_VALUE;
    public int companyDescriptionMaxLength = 30;
    public double normalCompanyMinimumCapital = 86400d;
    public double bankMinimumCapital = 30000000d;
    public double securitiesExchangeMinimumCapital = 30000000d;

    // Stocks
    public int stockOrderIdShowLength = 10;

    // Bonds
    public int bondUuidShowLength = 10;

    // Meetings
    public int daysUntilAutomaticMeetingFail = 3;
    public float meetingPassRatioToCastedVotes = 0.5f;
    public float meetingPassRatioToTotalShares = 0.33f;
    public float specialMeetingPassRatioToCastedVotes = 0.67f;
    public float specialMeetingPassRatioToTotalShares = 0.5f;


    // Navigation
    @Nullable
    public LocationData spawnPoint = null;

}
