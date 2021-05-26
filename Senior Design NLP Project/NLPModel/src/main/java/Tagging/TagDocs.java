package Tagging;


import API.NLPAPI;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import org.apache.log4j.BasicConfigurator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TagDocs {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        tag("C:\\Users\\aalra\\Documents\\sd-may21-35-softwaredocumentationpos\\TrainingDir\\GradingData",
//                "C:\\Users\\aalra\\Documents\\sd-may21-35-softwaredocumentationpos\\TrainingDir\\model\\Model(Ahmad)4-4-2021",
//                "C:\\Users\\aalra\\Documents\\sd-may21-35-softwaredocumentationpos\\TrainingDir\\TaggingOut"
//                );
    }
    public static void tag(String inputDir,String modelDir,String outputDir) throws IOException, ClassNotFoundException {
        BasicConfigurator.configure();
        if (inputDir == null || modelDir == null || outputDir == null) {
            throw new IllegalArgumentException("Incomplete arguments");
        }
        File inputFolder = new File(inputDir);
        File[] listOfFiles = inputFolder.listFiles();
        boolean modelExists = new File(modelDir).isFile();
        CRFClassifier model;
        if (modelExists) {
            model = CRFClassifier.getClassifier(modelDir);


             for (File file : listOfFiles) {
                if (file.isFile()) {
                    String content = "";
                    Files.writeString(file.toPath(), content);
                    String tagged = NLPAPI.doTagging(model,content);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputDir + "\\" + file.getName().substring(0, file.getName().indexOf(".")) + ".xml", true));
                    writer.append(tagged);

                    writer.close();
                }
            }
        }
    }
}
