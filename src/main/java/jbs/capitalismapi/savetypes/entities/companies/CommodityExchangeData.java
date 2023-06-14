package jbs.capitalismapi.savetypes.entities.companies;

import jbs.capitalismapi.savetypes.trading.orders.CommodityOrderData;

import java.util.ArrayList;

public class CommodityExchangeData extends CompanyData {
    public CommodityExchangeData(CompanyData cd) {
        super(cd);
        symbol = cd.symbol;
        description = cd.description;
        capital = cd.capital;
        shareCount = cd.shareCount;
        employeeUuids = cd.employeeUuids;
        directorUuids = cd.directorUuids;
        ceoUuid = cd.ceoUuid;
        currentlyOpenMeetingData = cd.currentlyOpenMeetingData;
        currentlyOpenMeetingType = cd.currentlyOpenMeetingType;
        outstandingJobOfferData = cd.outstandingJobOfferData;
        dailyCeoPay = cd.dailyCeoPay;
        dailyDirectorPay = cd.dailyDirectorPay;
        dailyEmployeePay = cd.dailyEmployeePay;
    }
    public CommodityExchangeData() {
        super();
    }

    public ArrayList<CommodityOrderData> orderData = new ArrayList<CommodityOrderData>();
    public float buyerFeeRate = 0f;
    public float sellerFeeRate = 0f;
}
