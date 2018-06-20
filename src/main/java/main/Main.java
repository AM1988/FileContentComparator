package main;

import CompareTool.CreateHtmlDocument;
import CompareTool.ListFilesUtil;
import CompareTool.PropertyUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        System.out.println("Starting comparing content...");

        ListFilesUtil listFilesUtil = new ListFilesUtil();


        try {
            PropertyUtil propertyUtil = new PropertyUtil();
            if (propertyUtil.getPropertyByKey("report_format").length >= 1) {
                CreateHtmlDocument.WriteToFile("<html>\n" + " <head>\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                        + "  <title>Report</title>\n" +
                        " </head>\n" +
                        " <body>\n");

                for (String format : propertyUtil.getPropertyByKey("report_format")
                        ) {


                    CreateHtmlDocument.WriteToFile("<h1>\nReport format is " + format.replace("/", "") + "\n</h1>\n");
                    System.out.println("Report format is " + format.replace("/", "") + "\n");

                    String[] listBaselineFiles = listFilesUtil.listFiles(propertyUtil.getPropertyByKey("baselineFolder")[0] + "/" + format + "/");
                    Arrays.sort(listBaselineFiles);

                    CreateHtmlDocument.WriteToFile("<p>\n" + "Files in baseline forder : " + "<p>\n" + Arrays.toString(listBaselineFiles) + "</p>" + "\n");
                    System.out.println(Arrays.toString(listBaselineFiles) + "\n");


                    String[] listResultFiles = listFilesUtil.listFiles(propertyUtil.getPropertyByKey("resultFolder")[0] + "/" + format + "/");
                    Arrays.sort(listResultFiles);
                    CreateHtmlDocument.WriteToFile("Files in result forder : " + "<p>\n" + Arrays.toString(listResultFiles) + "</p>" + "\n");
                    System.out.println(Arrays.toString(listResultFiles) + "\n");

                    CreateHtmlDocument.WriteToFile("<p>\n" + "Baseline folder: " + propertyUtil.getPropertyByKey("baselineFolder")[0] +"/"+ format+"/" + "</p>\n");
                    System.out.println("Baseline folder: " + propertyUtil.getPropertyByKey("baselineFolder")[0] + "/" + format+"/");
CreateHtmlDocument.WriteToFile("<p>Baseline folder contains " + listFilesUtil.listFiles(propertyUtil.getPropertyByKey("baselineFolder")[0] +"/"+ format+"/").length + " files</p>\n");
                    System.out.println("Baseline folder contains " + listFilesUtil.listFiles(propertyUtil.getPropertyByKey("baselineFolder")[0] + "/" + format +"/").length + " files\n");


                    CreateHtmlDocument.WriteToFile("<p>\n" + "Result folder: " + propertyUtil.getPropertyByKey("resultFolder")[0] + "/"+format+"/" + "</p>\n");
                    System.out.println("Result folder: " + propertyUtil.getPropertyByKey("resultFolder")[0] + "/"+format+"/");
                    CreateHtmlDocument.WriteToFile("<p>Result folder contains " + (listFilesUtil.listFiles(propertyUtil.getPropertyByKey("resultFolder")[0] + "/"+format+"/")).length + " files</p>\n");
                    System.out.println("Result folder contains " + (listFilesUtil.listFiles(propertyUtil.getPropertyByKey("resultFolder")[0] +"/"+ format+"/")).length + " files\n");


                    int countOfFailedResults = 0;
                    int totalComparision = 0;
                    for (String fileInBaselineFolder : listBaselineFiles) {

                        for (String fileInResultFolder : listResultFiles) {

                            if (fileInBaselineFolder.equals(fileInResultFolder)) {
                                totalComparision++;

                                File file1 = new File(propertyUtil.getPropertyByKey("baselineFolder")[0] + "/" + format + "/" + fileInBaselineFolder);
                                File file2 = new File(propertyUtil.getPropertyByKey("resultFolder")[0] +"/"+ format+ "/" + fileInResultFolder);
                                boolean isTwoEqual = FileUtils.contentEquals(file1, file2);


                                if (isTwoEqual) {

CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                    System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");

                                    CreateHtmlDocument.WriteToFile("<p style=\"color:green\">SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                            + propertyUtil.getPropertyByKey("resultFolder")[0] +"/"+ format+"/" + fileInResultFolder + "</p>" + "\n");

                                    System.out.println("SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                            + propertyUtil.getPropertyByKey("resultFolder")[0] + "/" + format + "/"+ fileInResultFolder + "\n");


                                } else {
                                    countOfFailedResults++;
                                    CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                    System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");
                                    CreateHtmlDocument.WriteToFile("<p style=\"color:red\">ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                            + propertyUtil.getPropertyByKey("resultFolder")[0] +"/"+ format+"/" + fileInResultFolder +"</p>" +"\n");

                                    System.out.println("ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                            + propertyUtil.getPropertyByKey("resultFolder")[0] + "/" + format +"/"+ fileInResultFolder +"\n");

                                }


                            } else {
                                continue;
                            }

                        }


                    }
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================</h2>\n");
                    System.out.println("==========================================\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total comparision = " + totalComparision + "</h2>\n");
                    System.out.println("Total comparision = " + totalComparision + "\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total Failed  = " + countOfFailedResults + "</h2>\n");
                    System.out.println("Total Failed  = " + countOfFailedResults + "\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================\n</h2>\n</body>\n" +
                            "</html>");
                    System.out.println("==========================================\n");


                    continue;

                }


            }
            CreateHtmlDocument.renameReport("report.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
