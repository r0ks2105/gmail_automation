package RestAssured;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "resAssureTestData")
    public CharSequence[] getListIDs(){
        String[][] headers = {{"name",
                "creationMethod"}
        };
        return headers[0];
    }
    @org.testng.annotations.DataProvider(name = "getCompanyBody")
    public Object[][] getBody(){
        Object[][] body = {{"{\"companyId\": \"\390021ac-841f-4742-86e2-1efbeaaf1b62\",\"skip\": 0,\"take\": 60"}};
        return body;
    }
}
