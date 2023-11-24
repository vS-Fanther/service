package com.test.service.config;

import com.test.service.model.config.DataSourceConfig;
import com.test.service.model.config.MappingConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataSourceReader {

    private static List<DataSourceConfig> dataSources;

    private static void readYaml(String yamlFileName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = DataSourceReader.class
                .getClassLoader()
                .getResourceAsStream("ds.yaml");
        Object loadedObject = yaml.load(inputStream);
        if(loadedObject instanceof LinkedHashMap) {
            LinkedHashMap<String, ArrayList<LinkedHashMap>> map = (LinkedHashMap<String, ArrayList<LinkedHashMap>>) loadedObject;
            ArrayList<LinkedHashMap> list = map.get("data-sources");
            for (LinkedHashMap dataSourceMap : list) {
                DataSourceConfig dataSource = new DataSourceConfig();
                dataSource.setName((String) dataSourceMap.get("name"));
                dataSource.setStrategy((String) dataSourceMap.get("strategy"));
                dataSource.setUrl((String) dataSourceMap.get("url"));
                dataSource.setTable((String) dataSourceMap.get("table"));
                dataSource.setUser((String) dataSourceMap.get("user"));
                dataSource.setPassword((String) dataSourceMap.get("password"));
                LinkedHashMap mappingMap = (LinkedHashMap) dataSourceMap.get("mapping");
                MappingConfig mapping = new MappingConfig();
                mapping.setId((String) mappingMap.get("id"));
                mapping.setUsername((String) mappingMap.get("username"));
                mapping.setName((String) mappingMap.get("name"));
                mapping.setSurname((String) mappingMap.get("surname"));
                dataSource.setMapping(mapping);
                dataSources.add(dataSource);
            }
        }
    }

    public static List<DataSourceConfig> getDataSources() {
        if (dataSources == null) {
            dataSources = new ArrayList<>();
            readYaml("ds.yaml");
        }
        return dataSources;
    }
}
