package formgenerator.generator;

import formgenerator.TypeGenerator;

/**
 * Created by D on 2017/4/4.
 */
public class IntGenerator extends TypeGenerator {

    public String className = "";
    public String type = "text";

    @Override
    public String generate(String entityName, String name, int maxLength) {
        super.setParam(className,type);
        return super.generate(entityName, name, maxLength);
    }

    public static void main(String[] args) {
        IntGenerator ds = new IntGenerator();
        System.out.println(ds.generate("user_info","birth_date",50));
    }
}
