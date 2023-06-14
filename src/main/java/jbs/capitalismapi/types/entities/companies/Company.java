package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityType;
import jbs.capitalismapi.savetypes.entities.companies.CompanyData;
import jbs.capitalismapi.savetypes.meetings.MeetingType;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;
import jbs.capitalismapi.types.meetings.*;
import jbs.capitalismapi.types.offers.JobOffer;
import jbs.capitalismapi.types.stocks.StockPortfolio;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class Company extends EconomicEntity {
    public Company(
            UUID uuid,
            String name,
            double balance,
            StockPortfolio stockPortfolio,
            String symbol,
            int shareCount
    ) {
        super(uuid, name, balance, stockPortfolio);
        this.capital = balance;
        this.symbol = symbol;
        this.shareCount = shareCount;


    }
    public Company(Company c) {
        super(c);
        symbol = c.symbol;
        description = c.description;
        shareCount = c.shareCount;
        capital = c.capital;
        employees = c.employees;
        directors = c.directors;
        ceo = c.ceo;
        dailyCeoPay = c.dailyCeoPay;
        dailyDirectorPay = c.dailyDirectorPay;
        dailyEmployeePay = c.dailyEmployeePay;
        currentlyOpenMeeting = c.currentlyOpenMeeting;
        outstandingJobOffers = c.outstandingJobOffers;

    }
    public Company(UUID uuid) {
        super(uuid);
    }
    public Company() {
        super();
        this.symbol = null;
    }

    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    int shareCount = 1;
    double capital = 0d;
    public double getFaceValue() {
        try {
            return capital / shareCount;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    public int getShareCount() {
        return shareCount;
    }

    public void changeShareCount(int delta) {
        this.shareCount += delta;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public double getCapital() {
        return capital;
    }

    public void changeCapital(double delta) {
        this.capital += delta;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    @Nullable
    String description = null;

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    ArrayList<CapitalismPlayer> employees = new ArrayList<CapitalismPlayer>();
    ArrayList<CapitalismPlayer> directors = new ArrayList<CapitalismPlayer>();
    @Nullable
    CapitalismPlayer ceo = null;

    public ArrayList<CapitalismPlayer> getEmployees() {
        return employees;
    }
    public void addEmployee(CapitalismPlayer player) {
        if (!employees.contains(player)) {
            employees.add(player);
        }
    }

    public void removeEmployee(CapitalismPlayer player) {
        employees.remove(player);
    }

    public void setEmployees(ArrayList<CapitalismPlayer> employees) {
        this.employees = employees;
    }

    public ArrayList<CapitalismPlayer> getDirectors() {
        return directors;
    }

    public void addDirector(CapitalismPlayer player) {
        if (!directors.contains(player)) directors.add(player);
    }

    public void removeDirector(CapitalismPlayer player) {
        directors.remove(player);
    }

    public void setDirectors(ArrayList<CapitalismPlayer> directors) {
        this.directors = directors;
    }

    public void sendJobOffer(JobOffer offer) {
        this.outstandingJobOffers.add(offer);
        offer.getTo().inboundJobOffers.add(offer);
    }

    @Nullable
    public CapitalismPlayer getCeo() {
        return ceo;
    }

    public void setCeo(@Nullable CapitalismPlayer ceo) {
        this.ceo = ceo;
    }
    public boolean isCeo(CapitalismPlayer player) {
        if (ceo == null) return false;
        return ceo.equals(player);
    }

    public double dailyCeoPay = 0d;
    public double dailyDirectorPay = 0d;
    public double dailyEmployeePay = 0d;

    @Nullable
    Meeting currentlyOpenMeeting = null;

    @Nullable
    public Meeting getCurrentlyOpenMeeting() {
        return currentlyOpenMeeting;
    }

    public void setCurrentlyOpenMeeting(@Nullable Meeting currentlyOpenMeeting) {
        this.currentlyOpenMeeting = currentlyOpenMeeting;
    }

    ArrayList<JobOffer> outstandingJobOffers = new ArrayList<JobOffer>();

    public ArrayList<JobOffer> getOutstandingJobOffers() {
        return outstandingJobOffers;
    }

    public void addOutstandingJobOffer(JobOffer offer) {
        outstandingJobOffers.add(offer);
    }

    public void removeOutstandingJobOffer(JobOffer offer) {
        outstandingJobOffers.remove(offer);
    }

    public void setOutstandingJobOffers(ArrayList<JobOffer> outstandingJobOffers) {
        this.outstandingJobOffers = outstandingJobOffers;
    }

    @Override
    public CompanyData toData() {
        CompanyData cd = new CompanyData(super.toData());

        cd.type = EconomicEntityType.COMPANY;

        cd.symbol = symbol;
        cd.capital = capital;
        cd.shareCount = shareCount;
        cd.description = description;
        for (CapitalismPlayer e : employees) {
            cd.employeeUuids.add(e.getUuid().toString());
        }
        for (CapitalismPlayer d : directors) {
            cd.directorUuids.add(d.getUuid().toString());
        }
        if (ceo != null) cd.ceoUuid = ceo.getUuid().toString();

        cd.dailyCeoPay = dailyCeoPay;
        cd.dailyDirectorPay = dailyDirectorPay;
        cd.dailyEmployeePay = dailyEmployeePay;

        for (JobOffer jo : outstandingJobOffers) {
            cd.outstandingJobOfferData.add(jo.toData());
        }

        if (currentlyOpenMeeting != null) {
            if (currentlyOpenMeeting instanceof CashDividendMeeting) {
                CashDividendMeeting cdm = (CashDividendMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = cdm.toData();
                cd.currentlyOpenMeetingType = MeetingType.CASH_DIVIDEND;
            } else if (currentlyOpenMeeting instanceof ChangeNameMeeting) {
                ChangeNameMeeting hcm = (ChangeNameMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.CHANGE_NAME;
            } else if (currentlyOpenMeeting instanceof FireCeoMeeting) {
                FireCeoMeeting hcm = (FireCeoMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.FIRE_CEO;
            } else if (currentlyOpenMeeting instanceof HireCeoMeeting) {
                HireCeoMeeting hcm = (HireCeoMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.HIRE_CEO;
            } else if (currentlyOpenMeeting instanceof LiquidateCompanyMeeting) {
                LiquidateCompanyMeeting hcm = (LiquidateCompanyMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.LIQUIDATE_COMPANY;
            } else if (currentlyOpenMeeting instanceof OtherMeeting) {
                OtherMeeting hcm = (OtherMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.HIRE_CEO;
            } else if (currentlyOpenMeeting instanceof StockIssueMeeting) {
                StockIssueMeeting hcm = (StockIssueMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.HIRE_CEO;
            } else if (currentlyOpenMeeting instanceof StockRetireMeeting) {
                StockRetireMeeting hcm = (StockRetireMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.HIRE_CEO;
            } else if (currentlyOpenMeeting instanceof StockSplitMeeting) {
                StockSplitMeeting hcm = (StockSplitMeeting) currentlyOpenMeeting;
                cd.currentlyOpenMeetingData = hcm.toData();
                cd.currentlyOpenMeetingType = MeetingType.HIRE_CEO;
            }
        }

        return cd;
    }

    public static Company fromData(CompanyData data) {
        return new Company(UUID.fromString(data.uuid));
    }
}
