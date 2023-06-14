package jbs.capitalismapi.savetypes.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityData;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingType;
import jbs.capitalismapi.savetypes.offers.JobOfferData;

import javax.annotation.Nullable;
import java.util.ArrayList;


public class CompanyData extends EconomicEntityData {
    public CompanyData(EconomicEntityData eed) {
        super(eed);
    }
    public CompanyData() {
        super();
    }

    public String symbol = null;
    public String description = null;
    public double capital = 0d;
    public int shareCount = 1;
    public ArrayList<String> employeeUuids = new ArrayList<String>();
    public ArrayList<String> directorUuids = new ArrayList<String>();
    @Nullable
    public String ceoUuid = null;
    @Nullable
    public MeetingData currentlyOpenMeetingData = null;
    @Nullable
    public MeetingType currentlyOpenMeetingType = null;
    public double dailyCeoPay = 0d;
    public double dailyDirectorPay = 0d;
    public double dailyEmployeePay = 0d;
    public ArrayList<JobOfferData> outstandingJobOfferData = new ArrayList<JobOfferData>();
}
