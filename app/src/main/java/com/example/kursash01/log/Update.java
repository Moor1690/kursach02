package com.example.kursash01.log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Update {



    public static void getWebsite() {
        System.out.println("Update.getWebsite();\t");

        File file = new File("/storage/emulated/0/Download/my");
        file.mkdir();
        Update.deleteOld();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://www.mirea.ru/schedule/").get();
                    String title = doc.title();
                    Elements links = doc.select(".uk-link-toggle");
                    ///////////////////////////////////////////////////////////////////////////////////////
                    System.out.println("FOR");
                    ///////////////////////////////////////////////////////////////////////////////////////
                    for (Element element: links) {
                        if (element.attr("abs:href").indexOf("xlsx")!=-1 && element.attr("abs:href").indexOf("экз")==-1 && element.attr("abs:href").indexOf("зач")==-1){

                            System.out.println(element.attr("abs:href"));
                            downloadFile(element.attr("abs:href"), Paths.get("/storage/emulated/0/Download/my"));
                            //saveUrl(element.attr("abs:href"), element.attr("abs:href"));
                        }
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
            }
        }).start();
    }


    public static void deleteOld() {

        System.out.println("\tdeleteOld\t");


                File file = new File("/storage/emulated/0/Download/my");
                File[] lis = file.listFiles();
                //System.out.println(lis[1].getPath());

                System.out.println(file.exists());
                System.out.println(file.getAbsolutePath());
                if (file.length() != 0) {
                    System.out.println("deleteOld " + file.length() + "\t" + file.getPath());
                    for (File file1 : lis) {
                        System.out.println("FORdeleteOld");
                        System.out.println("deleteOld() "+file1.getPath());
                        file1.delete();
                    }
                }

    }
    public static void downloadFile(String urlString, Path downloadDirectory) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // implement this method
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                InputStream inputStream = null;
                try {
                    inputStream = url.openStream();
                    System.out.println("inputStream "+ inputStream.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String fieName = urlString.substring(urlString.lastIndexOf("/"));
                Path destPath = Paths.get(downloadDirectory.toString(), fieName);

                try {
                    Files.copy(inputStream, destPath);
                    System.out.println("Files.copy " + destPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public static void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }
}