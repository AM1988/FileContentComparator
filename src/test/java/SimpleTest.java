import CompareTool.PropertyUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void getProperty(){
        PropertyUtil propertyUtil = new PropertyUtil();
        Assert.assertTrue(propertyUtil.getPropertyByKey("REPORT_FORMAT").length >= 1);

    }
}
