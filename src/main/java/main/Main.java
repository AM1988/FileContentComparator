package main;

import CompareTool.CreateHtmlDocument;
import CompareTool.Comparator;


public class Main {


    public static void main(String[] args) {

        Comparator.startCompareFiles();
        CreateHtmlDocument.renameReport("report.html");

    }
}
