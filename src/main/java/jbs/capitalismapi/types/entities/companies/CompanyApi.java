package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.types.entities.players.PlayerApi;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CompanyApi {
    String symbol = null;
    @Nullable
    String description = null;
    double capital = 0d;
    int shareCount = 1;
    ArrayList<PlayerApi> employees = new ArrayList<PlayerApi>();
    ArrayList<PlayerApi> directors = new ArrayList<PlayerApi>();
    @Nullable
    PlayerApi ceo = null;

}
