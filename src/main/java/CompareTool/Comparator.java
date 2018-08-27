package CompareTool;


import java.io.File;
import java.util.Arrays;

public class Comparator {


    public static void startCompareFiles() {


        ListFilesUtil listFilesUtil = new ListFilesUtil();


        try {
            PropertyUtil propertyUtil = new PropertyUtil();
            if (propertyUtil.getPropertyByKey("REPORT_FORMAT").length >= 1) {
                CreateHtmlDocument.WriteToFile("<html>\n" + " <head>\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                        + "  <title>Report</title>\n" +
                        " </head>\n" +
                        " <body>\n");

                for (String format : propertyUtil.getPropertyByKey("REPORT_FORMAT")
                        ) {


                    CreateHtmlDocument.WriteToFile("<h1>\nReport format is " + format.replace(propertyUtil.pathSeparator(), "") + "\n</h1>\n");
                    //System.out.println("Report format is " + format.replace(propertyUtil.pathSeparator(), "") + "\n");

                    String[] listBaselineFiles = listFilesUtil.listFiles(propertyUtil.getPropertyByKey("BASELINE_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator());
                    Arrays.sort(listBaselineFiles);

                    CreateHtmlDocument.WriteToFile("<p>\n" + "Files in baseline forder : " + "<p>\n" + Arrays.toString(listBaselineFiles) + "</p>" + "\n");
                    //System.out.println(Arrays.toString(listBaselineFiles) + "\n");


                    String[] listResultFiles = listFilesUtil.listFiles(propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator());
                    Arrays.sort(listResultFiles);
                    CreateHtmlDocument.WriteToFile("Files in result forder : " + "<p>\n" + Arrays.toString(listResultFiles) + "</p>" + "\n");
                    //System.out.println(Arrays.toString(listResultFiles) + "\n");

                    CreateHtmlDocument.WriteToFile("<p>\n" + "Baseline folder: " + propertyUtil.getPropertyByKey("BASELINE_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + "</p>\n");
                    //System.out.println("Baseline folder: " + propertyUtil.getPropertyByKey("baselineFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator());
                    CreateHtmlDocument.WriteToFile("<p>Baseline folder contains " + listFilesUtil.listFiles(propertyUtil.getPropertyByKey("BASELINE_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator()).length + " files</p>\n");
                    //System.out.println("Baseline folder contains " + listFilesUtil.listFiles(propertyUtil.getPropertyByKey("baselineFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator()).length + " files\n");


                    CreateHtmlDocument.WriteToFile("<p>\n" + "Result folder: " + propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + "</p>\n");
                    //System.out.println("Result folder: " + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator());
                    CreateHtmlDocument.WriteToFile("<p>Result folder contains " + (listFilesUtil.listFiles(propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator())).length + " files</p>\n");
                    //System.out.println("Result folder contains " + (listFilesUtil.listFiles(propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator())).length + " files\n");

                    /*if (format.equals("dfgdg") || format.equals("df")) {
                        int countOfFailedResults = 0;
                        int totalComparison = 0;
                        for (String fileInBaselineFolder : listBaselineFiles) {

                            for (String fileInResultFolder : listResultFiles) {


                                if (fileInBaselineFolder.equals(fileInResultFolder)) {
                                    totalComparison++;

                                    File file1 = new File(propertyUtil.getPropertyByKey("baselineFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInBaselineFolder);
                                    File file2 = new File(propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder);
                                    boolean isTwoEqual = FileUtils.contentEquals(file1, file2);


                                    if (isTwoEqual) {

                                        CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                        //System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");

                                        CreateHtmlDocument.WriteToFile("<p style=\"color:green\">SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                                + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "</p>" + "\n");

                                        //System.out.println("SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                         //       + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "\n");


                                    } else {

                                        countOfFailedResults++;
                                        CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                        //System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");
                                        CreateHtmlDocument.WriteToFile("<p style=\"color:red\">ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                                + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "</p>" + "\n");

                                        //System.out.println("ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                        //        + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "\n");


                                    }


                                } else {
                                    continue;
                                }

                            }


                        }
                        CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================</h2>\n");
                        //System.out.println("==========================================\n");
                        CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total comparison = " + totalComparison + "</h2>\n");
                        //System.out.println("Total comparison = " + totalComparison + "\n");
                        CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total Failed  = " + countOfFailedResults + "</h2>\n");
                        //System.out.println("Total Failed  = " + countOfFailedResults + "\n");
                        CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================\n</h2>\n</body>\n" +
                                "</html>");
                        //System.out.println("==========================================\n");


                        continue;

                    }*/

                    //else {
                    int countOfFailedResults = 0;
                    int totalComparison = 0;
                    for (String fileInBaselineFolder : listBaselineFiles) {

                        for (String fileInResultFolder : listResultFiles) {


                            if (fileInBaselineFolder.equals(fileInResultFolder)) {
                                totalComparison++;

                                File file1 = new File(propertyUtil.getPropertyByKey("BASELINE_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInBaselineFolder);
                                File file2 = new File(propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder);


                                if (HashGenerator.Hash.toHex(HashGenerator.Hash.MD5.checksum(file1)).equals(HashGenerator.Hash.toHex(HashGenerator.Hash.MD5.checksum(file2)))) {

                                    CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                    //System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");

                                    CreateHtmlDocument.WriteToFile("<p style=\"color:green\">SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                            + propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "</p>" + "\n");

                                    //System.out.println("SUCCESS ! Content of baseline file " + fileInBaselineFolder + " is equal "
                                    //       + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "\n");


                                } else {

                                    countOfFailedResults++;
                                    CreateHtmlDocument.WriteToFile("<p>===================== File " + fileInBaselineFolder + "=====================</p>\n");
                                    //System.out.println("===================== File " + fileInBaselineFolder + "=====================\n");
                                    CreateHtmlDocument.WriteToFile("<p style=\"color:red\">ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                            + propertyUtil.getPropertyByKey("RESULT_FOLDER")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "</p>" + "\n");

                                    //System.out.println("ERROR ! Content of baseline file " + fileInBaselineFolder + " is NOT equal "
                                    //        + propertyUtil.getPropertyByKey("resultFolder")[0] + propertyUtil.pathSeparator() + format + propertyUtil.pathSeparator() + fileInResultFolder + "\n");
                                    CreateHtmlDocument.WriteToFile("<p style=\"color:black\">Baseline file MD5: " + HashGenerator.Hash.toHex(HashGenerator.Hash.MD5.checksum(file1)) + "</p>\n");
                                    CreateHtmlDocument.WriteToFile("<p style=\"color:black\">Result file MD5: " + HashGenerator.Hash.toHex(HashGenerator.Hash.MD5.checksum(file2)) + "</p>\n");


                                }


                            } else {
                                continue;
                            }

                        }


                    }
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================</h2>\n");
                    //System.out.println("==========================================\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total comparison = " + totalComparison + "</h2>\n");
                    //System.out.println("Total comparison = " + totalComparison + "\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">Total Failed  = " + countOfFailedResults + "</h2>\n");
                    //System.out.println("Total Failed  = " + countOfFailedResults + "\n");
                    CreateHtmlDocument.WriteToFile("<h2 style=\"color:blue\">==========================================\n</h2>\n</body>\n" +
                            "</html>");
                    //System.out.println("==========================================\n");


                    continue;
                    //}


                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


