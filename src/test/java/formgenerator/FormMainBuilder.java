package formgenerator;

import formgenerator.Tool.Tool;
import formgenerator.generator.DateGenerator;
import formgenerator.generator.IntGenerator;
import formgenerator.generator.VarcharGenerator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by D on 2017/4/4.
 */
public class FormMainBuilder {
    private HashMap<String, TypeGenerator> generatorMap = new HashMap<>();

    public FormMainBuilder() {
        generatorMap.put("DATE",new DateGenerator());


        generatorMap.put("INT",new IntGenerator());
        generatorMap.put("BIGINT",new IntGenerator());
        generatorMap.put("TINYINT",new IntGenerator());


        generatorMap.put("VARCHAR",new VarcharGenerator());
    }

    public String generate(ResultSet rs) throws SQLException {
        ResultSetMetaData data = rs.getMetaData();
        rs.next();
        StringBuilder sb = new StringBuilder();
        String tableName = Tool.toCamelCase(data.getTableName(1));
        for(int i = 1 ; i<= data.getColumnCount() ; i++){
            sb.append(generatorMap.get(data.getColumnTypeName(i)).generate(tableName,data.getColumnName(i),data.getColumnDisplaySize(i))).append("\n");
        }
        return sb.toString();
    }
}
