package jbs.capitalismapi.savetypes.entities.companies;

import jbs.capitalismapi.savetypes.stocks.StockListingData;

import java.util.ArrayList;

public class SecuritiesExchangeData extends CompanyData {
    public SecuritiesExchangeData(CompanyData cd) {
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
    public SecuritiesExchangeData() {
        super();
    }

    public ArrayList<StockListingData> stockListingData = new ArrayList<StockListingData>();
    public float buyerFeeRate = 0f;
    public float sellerFeeRate = 0f;
}
