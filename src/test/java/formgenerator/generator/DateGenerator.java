package formgenerator.generator;

import formgenerator.TypeGenerator;

/**
 * Created by D on 2017/4/4.
 */
public class DateGenerator extends TypeGenerator {
    public String className = "";
    public String type = "date";

    @Override
    public String generate(String entityName, String name, int maxLength) {
        super.setParam(className,type);
        return super.generate(entityName, name, maxLength);
    }

    public static void main(String[] args) {
        DateGenerator ds = new DateGenerator();
        System.out.println(ds.generate("user_info","birth_date",50));
    }
}
