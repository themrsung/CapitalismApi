package jbs.capitalismapi.types.offers;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.offers.JobOfferData;
import jbs.capitalismapi.savetypes.offers.JobOfferType;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;

import java.util.UUID;

public class EmployeeOffer extends JobOffer {
    public EmployeeOffer(Company from, CapitalismPlayer to) {
        super(from, to);
    }
    public EmployeeOffer(EmployeeOffer eo) {
        super(eo);
    }
    public EmployeeOffer() {
        super();
    }

    @Override
    public void onAccepted() {
        super.onAccepted();
        from.addEmployee(to);
    }

    @Override
    public JobOfferData toData() {
        JobOfferData jod =  super.toData();
        jod.position = JobOfferType.EMPLOYEE;
        return jod;
    }

    public static EmployeeOffer fromData(JobOfferData jod, Capitalism plugin) {
        return new EmployeeOffer(
                plugin.getCompany(jod.fromSymbol),
                plugin.getPlayer(UUID.fromString(jod.toUuid))
        );
    }
}
