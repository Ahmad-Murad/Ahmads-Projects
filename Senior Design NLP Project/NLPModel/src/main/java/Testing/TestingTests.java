package Testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestingTests {

    public static void main(String[] args) throws IOException {
        TestResults results = JSONCompare.doTest("D:\\__SENIOR_DESIGN__\\sd-may21-35-softwaredocumentationpos\\TrainingDir\\OriginalOutput", "D:\\__SENIOR_DESIGN__\\sd-may21-35-softwaredocumentationpos\\TrainingDir\\TaggedOutput");
        System.out.println(results);
    }
}
