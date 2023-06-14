package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.CapitalismApi;
import jbs.capitalismapi.savetypes.entities.EconomicEntityApiType;
import jbs.capitalismapi.savetypes.entities.companies.CompanyApiData;
import jbs.capitalismapi.savetypes.entities.players.PlayerApiData;
import jbs.capitalismapi.types.bonds.BondApi;
import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.types.entities.players.PlayerApi;
import jbs.capitalismapi.types.stocks.StockPortfolioApi;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class CompanyApi extends EconomicEntityApi {
    public CompanyApi(
            UUID uuid,
            String name,
            double balance,
            StockPortfolioApi stockPortfolio,
            ArrayList<BondApi> bondPortfolio,
            double stockOrderCollateralValue,
            @Nullable Location address,
            String symbol,
            @Nullable String description,
            double capital,
            int shareCount,
            ArrayList<PlayerApi> employees,
            ArrayList<PlayerApi> directors,
            @Nullable PlayerApi ceo
    ) {
        super(uuid, name, balance, stockPortfolio, bondPortfolio, stockOrderCollateralValue, address);
        this.symbol = symbol;
        this.description = description;
        this.capital = capital;
        this.shareCount = shareCount;
        this.employees = employees;
        this.directors = directors;
        this.ceo = ceo;
    }
    // Used in fromData()
    public CompanyApi(UUID uuid) {
        super(uuid);
    }
    public CompanyApi() { super(); }

    // Symbol of this company (unique)
    // 회사의 종목코드 (고유값)
    String symbol = null;

    // Description of this company
    // 회사 소개
    @Nullable
    String description = null;

    // Capital of this company
    // 자본금
    double capital = 0d;

    // Total issued share count
    // 발행주식수
    int shareCount = 1;

    // 직원
    ArrayList<PlayerApi> employees = new ArrayList<PlayerApi>();

    // 이사
    ArrayList<PlayerApi> directors = new ArrayList<PlayerApi>();

    // 대표이사
    @Nullable
    PlayerApi ceo = null;

    public String getSymbol() {
        return symbol;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public double getCapital() {
        return capital;
    }

    public int getShareCount() {
        return shareCount;
    }

    public ArrayList<PlayerApi> getEmployees() {
        return employees;
    }

    public ArrayList<PlayerApi> getDirectors() {
        return directors;
    }

    @Nullable
    public PlayerApi getCeo() {
        return ceo;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public void setEmployees(ArrayList<PlayerApi> employees) {
        this.employees = employees;
    }

    public void setDirectors(ArrayList<PlayerApi> directors) {
        this.directors = directors;
    }

    public void setCeo(@Nullable PlayerApi ceo) {
        this.ceo = ceo;
    }

    public CompanyApiData toData() {
        CompanyApiData data = new CompanyApiData(super.toData());

        data.symbol = symbol;
        data.description = description;
        data.capital = capital;
        data.shareCount = shareCount;

        for (PlayerApi employee : employees) {
            data.employeeUuids.add(employee.getUuid().toString());
        }

        for (PlayerApi director : directors) {
            data.directorUuids.add(director.getUuid().toString());
        }

        if (ceo != null) data.ceoUuid = ceo.getUuid().toString();

        data.type = EconomicEntityApiType.COMPANY_API;

        return data;
    }

    public static CompanyApi fromData(CompanyApiData data) {
        return new CompanyApi(UUID.fromString(data.uuid));
    }
}
