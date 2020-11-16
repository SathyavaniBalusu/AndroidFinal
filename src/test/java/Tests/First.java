package Tests;

import BusinessLogic.Login;
import ExcelValidation.ExcelOperation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static ExcelValidation.ExcelOperation.cols;
import static ExcelValidation.ExcelOperation.rows;

public class First {
    @Test(dataProvider = "Authentication")
    public static void newmethod(String UserName, String Password) throws MalformedURLException, InterruptedException {
        System.out.println("in method");
        Login L= new Login();
        L.Loginpage(UserName,Password);
    }
    @DataProvider(name = "Authentication", parallel = false)
    public Object[][] Authentication() throws Exception {
        ExcelOperation.setExcelFile("C:\\Users\\Sathya\\MagentoProject\\src\\test\\resources\\Data.xlsx", "Sheet1");
        Object[][] array = ExcelOperation.getTableArray("C:\\Users\\Sathya\\MagentoProject\\src\\test\\resources\\Data.xlsx", "Sheet1");
        int i, j;
        int Trows = rows();
        int Tcols = cols();
        for (i = 1; i < Trows; i++) {
            for (j = 0; j < Tcols; j++) {
                System.out.println(array[i][j]);
            }
            System.out.println("-------------");
        }
        return array;
    }
}
