package jbs.capitalismapi.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jbs.capitalismapi.savetypes.entities.EconomicEntityApiData;
import jbs.capitalismapi.savetypes.entities.EconomicEntityApiType;
import jbs.capitalismapi.savetypes.entities.companies.CompanyApiData;
import jbs.capitalismapi.savetypes.entities.players.PlayerApiData;
import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.types.entities.companies.CompanyApi;
import jbs.capitalismapi.types.entities.players.PlayerApi;
import org.bukkit.Bukkit;
import org.codehaus.plexus.util.FileUtils;

import java.io.*;
import java.util.ArrayList;

public class ApiData {
    public ApiData(
            ArrayList<EconomicEntityApi> entities,
            ArrayList<EconomicEntityApiData> entityData
    ) {
        this.entities = entities;
        this.entityData = entityData;
    }
    public ApiData() {}

    public ArrayList<EconomicEntityApi> entities = new ArrayList<EconomicEntityApi>();
    public ArrayList<EconomicEntityApiData> entityData = new ArrayList<EconomicEntityApiData>();

    public static ApiData loadData(String path) {
        ArrayList<EconomicEntityApi> entityList = new ArrayList<EconomicEntityApi>();
        ArrayList<EconomicEntityApiData> dataList = new ArrayList<EconomicEntityApiData>();

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            File entitiesFolder = new File(path + "/entities");
            File entityTypesFolder = new File(path + "/entitytypes");

            File[] entityFiles = entitiesFolder.listFiles();
            File[] entityTypeFiles = entityTypesFolder.listFiles();

            try {
                if (entityFiles == null || entityTypeFiles == null || entityFiles.length != entityTypeFiles.length) throw new UnknownError();

                for (int i = 0; i < entityFiles.length; i++) {
                    File f = entityFiles[i];
                    File tf = entityTypeFiles[i];

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(tf));
                        String typeAsString = reader.readLine();
                        reader.close();

                        EconomicEntityApiType t = EconomicEntityApiType.valueOf(typeAsString);

                        switch (t) {
                            case PLAYER_API:
                                PlayerApiData pd = mapper.readValue(f, PlayerApiData.class);
                                dataList.add(pd);
                                entityList.add(PlayerApi.fromData(pd));
                                break;
                            case COMPANY_API:
                                CompanyApiData cd = mapper.readValue(f, CompanyApiData.class);
                                dataList.add(cd);
                                entityList.add(CompanyApi.fromData(cd));
                                break;
                            case ECONOMIC_ENTITY_API:
                                EconomicEntityApiData eed = mapper.readValue(f, EconomicEntityApiData.class);
                                dataList.add(eed);
                                entityList.add(EconomicEntityApi.fromData(eed));
                                break;
                        }
                    } catch (IllegalArgumentException error) {
                        Bukkit.getLogger().info("Invalid entity type file: " + tf.getName());
                    }
                }
            } catch (UnknownError error) {
                Bukkit.getLogger().info("[CapitalismApi] No entity files found.");
            }

        } catch (IOException error) {
            Bukkit.getLogger().info("[CapitalismApi] Error loading entity data: " + error.getMessage());
        }

        return new ApiData(
                entityList,
                dataList
        );
    }

    public boolean saveData(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        new File(path).mkdirs();

        try {
            String entityFolderPath = path + "/entities";
            String entityTypeFolderPath = path + "/entitytypes";

            new File(entityFolderPath).mkdirs();
            new File(entityTypeFolderPath).mkdirs();

            FileUtils.cleanDirectory(entityFolderPath);
            FileUtils.cleanDirectory(entityTypeFolderPath);

            for (EconomicEntityApi e : entities) {
                EconomicEntityApiType t = EconomicEntityApiType.ECONOMIC_ENTITY_API;

                if (e instanceof PlayerApi) {
                    t = EconomicEntityApiType.PLAYER_API;
                } else if (e instanceof CompanyApi) {
                    t = EconomicEntityApiType.COMPANY_API;
                }

                PrintWriter o = new PrintWriter(entityTypeFolderPath + "/" + e.getUuid().toString() + ".yml");
                o.println(t.toString());
                o.close();

                File f = new File(entityFolderPath + "/" + e.getUuid().toString() + ".yml");
                switch (t) {
                    case ECONOMIC_ENTITY_API:
                        mapper.writeValue(f, e.toData());
                        break;
                    case PLAYER_API:
                        PlayerApi pa = (PlayerApi) e;
                        mapper.writeValue(f, pa.toData());
                        break;
                    case COMPANY_API:
                        CompanyApi ca = (CompanyApi) e;
                        mapper.writeValue(f, ca.toData());
                        break;
                }
            }
        } catch (IOException error) {
            Bukkit.getLogger().info("[CapitalismApi] Error saving entity data: " + error.getMessage());
            return false;
        }

        return true;
    }
}
