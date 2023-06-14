package jbs.capitalismapi.savetypes.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityApiData;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CompanyApiData extends EconomicEntityApiData {
    // Used in CompanyApi.toData()
    public CompanyApiData(EconomicEntityApiData entityData) {
        super(entityData);
    }
    public CompanyApiData() { super(); }
    public String symbol = null;
    @Nullable
    public String description = null;
    public double capital = 0d;
    public int shareCount = 1;
    public ArrayList<String> employeeUuids = new ArrayList<String>();
    public ArrayList<String> directorUuids = new ArrayList<String>();
    @Nullable
    public String ceoUuid = null;
}
