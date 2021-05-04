package TestCases;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "testData")
    public Object[][] getData(){
        Object[][] sendMessageData = {{"mytests4selenium@yopmail.com",
                "Test subject1",
                "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et " +
                "dolore magna aliqua.","Message sent."},
                {"mytests4selenium2@yopmail.com",
                        "Test subject2",
                        "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et " +
                "dolore magna aliqua222.",
                        "Message sent."},
                        {" ",
                        "Test subject2",
                        "Lorem ipsum dolor sit amet, " +
                                "consectetur adipiscing elit, sed do eiusmod " +
                                "tempor incididunt ut labore et " +
                                "dolore magna aliqua222.",
                        "Message sent."}
        };
        return sendMessageData;
    }
}
