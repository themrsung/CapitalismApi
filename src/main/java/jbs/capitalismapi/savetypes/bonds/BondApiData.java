package jbs.capitalismapi.savetypes.bonds;

import javax.annotation.Nullable;
import java.util.Date;

public class BondApiData {
    public String uuid = null;
    public String debtorUuid = null;
    public String creditorUuid = null;
    public double amount = 0d;
    @Nullable
    public Date expiration = null;
}
