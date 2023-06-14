package jbs.capitalismapi.types.trading.orders;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.trading.orders.CommodityOrderData;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;
import jbs.capitalismapi.types.trading.CommodityPortfolioEntry;
import org.bukkit.Material;

import java.util.Date;
import java.util.UUID;

public class CommodityOrder {
    public CommodityOrder(
            UUID uuid,
            CapitalismPlayer sender,
            Material material,
            Date time,
            double price,
            int quantity,
            double feePaid,
            float feeRate,
            double moneyCollateral,
            CommodityPortfolioEntry materialCollateral
    ) {
        this.uuid = uuid;
        this.sender = sender;
        this.material = material;
        this.time = time;
        this.price = price;
        this.quantity = quantity;
        this.feePaid = feePaid;
        this.feeRate = feeRate;
        this.moneyCollateral = moneyCollateral;
        this.materialCollateral = materialCollateral;
    }
    public CommodityOrder(
            CapitalismPlayer sender,
            Material material,
            double price,
            int quantity,
            float feeRate
    ) {
        this.uuid = UUID.randomUUID();
        this.sender = sender;
        this.material = material;
        this.time = new Date();
        this.price = price;
        this.quantity = quantity;
        this.feePaid = price * quantity * feeRate;
        this.feeRate = feeRate;
    }
    public CommodityOrder(CommodityOrder co) {
        uuid = co.uuid;
        sender = co.sender;
        material = co.material;
        time = co.time;
        price = co.price;
        quantity = co.quantity;
        feePaid = co.feePaid;
        feeRate = co.feeRate;
        moneyCollateral = co.moneyCollateral;
        materialCollateral = co.materialCollateral;
    }
    public CommodityOrder() {
        uuid = null;
        sender = null;
        material = null;
        time = null;
        price = 0d;
        quantity = 0;
        feePaid = 0d;
        feeRate = 0f;
        moneyCollateral = 0d;
        materialCollateral = new CommodityPortfolioEntry();
    }
    UUID uuid;
    CapitalismPlayer sender;
    Material material;
    Date time;
    double price;
    int quantity;
    double feePaid;
    float feeRate;
    double moneyCollateral = 0d;
    CommodityPortfolioEntry materialCollateral;

    public void onCancelled() {
        sender.changeBalance(moneyCollateral);
        sender.getUnreceivedCommodityPortfolio().addEntry(materialCollateral);
    }

    public void onFulfilled(double price, int quantity) {
        float fulfillmentRatio = (float) quantity / this.quantity;
        double actualFee = price * quantity * feeRate;
        double feeRefund = feePaid * fulfillmentRatio - actualFee;

        // Refund overpaid fees
        sender.changeBalance(actualFee);
        this.feePaid -= feeRefund;
        this.moneyCollateral -= feeRefund;

        // Reduce quantity
        this.quantity -= quantity;
    }

    public double getVolume() { return price * quantity; }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CapitalismPlayer getSender() {
        return sender;
    }

    public void setSender(CapitalismPlayer sender) {
        this.sender = sender;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(double feePaid) {
        this.feePaid = feePaid;
    }

    public float getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(float feeRate) {
        this.feeRate = feeRate;
    }

    public double getMoneyCollateral() {
        return moneyCollateral;
    }

    public void setMoneyCollateral(double moneyCollateral) {
        this.moneyCollateral = moneyCollateral;
    }

    public CommodityPortfolioEntry getMaterialCollateral() {
        return materialCollateral;
    }

    public void setMaterialCollateral(CommodityPortfolioEntry materialCollateral) {
        this.materialCollateral = materialCollateral;
    }

    public CommodityOrderData toData() {
        CommodityOrderData cod = new CommodityOrderData();

        cod.uuid = uuid.toString();
        cod.senderUuid = sender.getUuid().toString();
        cod.material = material;
        cod.time = time;
        cod.price = price;
        cod.quantity = quantity;
        cod.feePaid = feePaid;
        cod.feeRate = feeRate;
        cod.moneyCollateral = moneyCollateral;
        if (materialCollateral != null) cod.materialCollateralData = materialCollateral.toData();

        return cod;
    }

    public static CommodityOrder fromData(CommodityOrderData cod, Capitalism plugin) {
        return new CommodityOrder(
                UUID.fromString(cod.uuid),
                plugin.getPlayer(UUID.fromString(cod.senderUuid)),
                cod.material,
                cod.time,
                cod.price,
                cod.quantity,
                cod.feePaid,
                cod.feeRate,
                cod.moneyCollateral,
                CommodityPortfolioEntry.fromData(cod.materialCollateralData)
        );
    }
}
