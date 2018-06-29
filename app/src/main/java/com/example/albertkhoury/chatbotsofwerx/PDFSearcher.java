package com.example.albertkhoury.chatbotsofwerx;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.lang.System;


import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;


public class PDFSearcher {

    public static void startSearch() {
        String searchWord = "linux file system";


        File folder = new File("/home/albertkhoury/AndroidStudioProjects/ChatBotSOFWERX/ChatBot/app/src/main/assets");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            //should add a break if not a pdf. might crash
            System.out.println("\n===Scanning PDF " + (i + 1) + " of " + listOfFiles.length + "===");

            if (listOfFiles[i].isFile()) {
                System.out.println("PDF: " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }

            File file = new File("/home/albertkhoury/AndroidStudioProjects/ChatBotSOFWERX/ChatBot/app/src/main/assets/" + listOfFiles[i].getName());
            PDDocument document = null;

            try {
                document = PDDocument.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


            int foundPageNumberArray[] = new int[0];

            try {
                foundPageNumberArray = searchPDFReturnPages(document, searchWord);
            } catch (IOException e) {
                e.printStackTrace();
            }

            printIntArray(foundPageNumberArray);

            System.out.println("Most likely on pages:");
            int mostLikelyPagesArray[] = mostLikelyPages(foundPageNumberArray);
            printIntArray(mostLikelyPagesArray);


            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }




    public static String redundantRemoverArray(String searchString){
        //TODO


        return "test for now lol";
    }

    public static String[] wordsSeparatedArray(String searchString){//add other permutations of original string here for added bonus
        String[] wordArray = searchString.split("\\s+");

        for (int i = 0; i < wordArray.length; i++) {//cleanup any non characters
            wordArray[i] = wordArray[i].replaceAll("[^\\w]", "");
        }

        return wordArray;
    }


    public static int[] searchPDFReturnPages(PDDocument document, String testString) throws IOException, IOException {
        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        int hitCount = 0;
        int hitPagesArray[] = new int[10000];//lol java garbage collector
        String searchStringInWordsStringArray[];//fix later
        searchStringInWordsStringArray = wordsSeparatedArray(testString);

        for (int i = 1; i < document.getNumberOfPages(); i++) {//page itteration
            pdfStripper.setStartPage(i);
            pdfStripper.setEndPage(i);
            String textFromPDFPage = pdfStripper.getText(document);

            for(int j = -1; j <  searchStringInWordsStringArray.length; j++){//note this prob wont itterate more then 8 times max
                String stringOrWordToBeSearched = testString;

                if (j > -1) {
                    stringOrWordToBeSearched = searchStringInWordsStringArray[j];
                }

                boolean isThere = textFromPDFPage.toLowerCase().contains(stringOrWordToBeSearched.toLowerCase());//lower case and everything is same standard
                if (isThere) {
                    //System.out.println("=== " + stringOrWordToBeSearched + " found on page number " + i + " ===");
                    //System.out.println(textFromPDFPage);
                    //System.out.println("\n\n");
                    hitCount++;
                    hitPagesArray[hitCount] = i;
                }
                if (isThere && j == -1){//whole string found on page
                    //break;
                }
            }
        }

        System.out.println("[" + testString + "]" + " content could be on " + hitCount + " pages:");


        return hitPagesArray;
    }

    public static int[] mostLikelyPages(int hitPages[]){
        int likelyPages[] = new int[5];
        int max_count = 1, res = hitPages[0];
        int curr_count = 1;
        int n = hitPages.length;

        for (int k = 0; k < 5; k++) {//Max 5 itterations dw about runtime complexity here
            for (int i = 1; i < n; i++){//worry about runtime complexity with this loop and all the 0s
                if(hitPages[i] == 0 ){
                    break;
                }




                if (hitPages[i] == hitPages[i - 1])
                    curr_count++;
                else {
                    if (curr_count > max_count){
                        max_count = curr_count;

                        res = hitPages[i - 1];
                    }
                    curr_count = 1;
                }
            }

            // If last element is most frequent
            if (curr_count > max_count){
                max_count = curr_count;
                res = hitPages[n - 1];
            }



            likelyPages[k] = res;



        }
        return likelyPages;
    }

    public static void printIntArray(int array[]){
        for(int i=0; i< array.length; i++){//yeah there's a better way to do this lol
            if (array[i] != 0) {
                System.out.print(array[i]+ " ");//I can return these as an array, string, or whatever needed
            }
            //could break after printing zeros but its nbd atm
        }
        System.out.println("");
    }

}
