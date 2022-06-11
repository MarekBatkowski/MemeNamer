package com.company.mb;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class Main {

    private static File path;

    public static void main(String[] args)
    {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments provided, exactly 2 are required.");
            } else {
                path = new File(args[0]);
                if (!path.isDirectory())
                    throw new NotDirectoryException(args[0]);
                else{
                    Pattern pattern = Pattern.compile(args[1]);
                    File files[] = path.listFiles();
                    ArrayList<File> fileList = new ArrayList<>(Arrays.asList(path.listFiles()));
                    fileList.removeIf(f -> !pattern.matcher(f.getName()).find() || f.isDirectory());

                    if (fileList.size()==0)
                        throw new NoSuchElementException();
                    else {
                        System.out.println("Files found:");
                        for (File f : fileList)
                            System.out.println(f.getName());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n" + Messages.usageInfo);
        } catch (NotDirectoryException e) {
            System.out.println(e.getMessage() +" - " + Messages.incorrectDirectory + "\n" + Messages.usageInfo);
        } catch (NoSuchElementException e) {
            System.out.println(args[1] + " - " + Messages.noFilesMatchingFound + "\n" + Messages.usageInfo);
        }
    }
}
