package com.example.kursash01.log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Par {
    public int indexX;
    public int indexY;
    public  int test = 0;
    public String gotovo = " ";
    public StringBuilder otvet = new  StringBuilder();

    //////////////////////////////////////////////////////////////
    public String [][] mstr;
    //////////////////////////////////////////////////////////////


    public String find (String group,String [][] mstr, String [][] kabinet) throws IOException {
        //group = "ИКБО-27-20";
        System.out.println(group);
        File myFolder = new File("/storage/emulated/0/Download/my");
        File[] files = myFolder.listFiles();
        //SearchFile(group, "/storage/emulated/0/Download/my");
        for (int i = 0; files.length > i; i++){
            if (SearchFile(group,files[i].toString()) == 1) {
                if (SearchGroup(group, files[i].toString()) == 1) {
                    printExel(files[i].toString(), mstr, kabinet);
                    gotovo = "группа найдена";
                    break;
                }
            }
        }
        if (gotovo.equals(" ")){
            gotovo = "группа не найдена";
        }
        return  gotovo;
    }

    public int SearchFile(String group, String puti) throws IOException {

        /////////////////////////////////////////////////////////////////////////////////////получение года
        int kurs, p1;
        Date date = new Date();
        if (date.getMonth() > 6){
            p1 = 1;
        }else {
            p1 = 0;
        }

        System.out.println("SearchFile\t" + date.getTime());
        System.out.println("puti\t" + puti);
        Integer i = new Integer(group.substring(8,10));

        kurs = ((date.getYear())-100) + p1 - i;

        String strKurs = Integer.toString(kurs);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        /////////////////////////////////////////////////////////////////////////////////////
        String fileName;
        String pattern;

        switch (group.charAt(0)){
            case  ('К'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,4);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(5,6);
                System.out.println("И\tИИИ");
                pattern = "ИИИ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            case ('И'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,4);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(5,6);
                System.out.println("И\tИИТ");
                pattern = "ИИТ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;

            case ('Б'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,4);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(5,6);
                System.out.println('Б');
                System.out.println("И\tИКБ");
                pattern = "ИКБ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            case ('Э'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,6);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(7,8);
                System.out.println('Э');
                System.out.println("И\tИПТИП");
                pattern = "ИПТИП" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            case ('Р'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,5);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(6,7);
                System.out.println('Р');
                System.out.println("И\tИРЭИ");
                pattern = "ИРЭИ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            case ('У'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,4);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(5,6);
                System.out.println('У');
                System.out.println("И\tИТУ");
                pattern = "ИТУ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
            case ('Г'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,4);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(5,6);
                System.out.println('Г');
                System.out.println("И\tИТУ");
                pattern = "ИТУ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            case ('Х'):

                fileName = (puti.substring(puti.lastIndexOf("/"))).substring(1,5);
                fileName += (puti.substring(puti.lastIndexOf("/"))).substring(6,7);
                System.out.println('Х');
                System.out.println("И\tИТХТ");
                pattern = "ИТХТ" + strKurs;
                System.out.println("fileName.matches(pattern)\t" + fileName.matches(pattern));
                if (fileName.matches(pattern)){
                    return 1;
                }
                return 0;
            default:
                System.out.println("КодВыбораПоУмолчанию");
                return  0;
        }


    }





    public int SearchGroup(String group, String puti) throws IOException {
        test = 0;
        System.out.println(puti);
        FileInputStream fileInputStream = new FileInputStream(puti);
        Workbook wb = new XSSFWorkbook(fileInputStream);
        int x = 0;
        Row row = wb.getSheetAt(0).getRow(1);
        //System.out.println("row: " + row);

        //for (Row row : wb.getSheetAt(1)){
        //Row row = wb.getSheetAt(0).getRow(1);
        Cell c = row.getCell(0);
        //System.out.println("Cell c\t" + c.toString());
        //x++;
        //System.out.println("\t\t" + x);
        //System.out.println("ROW\t");
        for (Cell cell : row){
            Cell r = cell;

            if (cell == null){
                continue;
            }else if (r.toString().length()<10){
                continue;
            }
            System.out.println("r.toString()\t" + r.toString().substring(0,10));
            System.out.println("getCellText(cell)\t" + getCellText(cell).substring(0,10));
            System.out.println("getCellText(cell).substring(0,9).equals(group)\t" + getCellText(cell).substring(0,10).equals(group));
            if (getCellText(cell).substring(0,10).equals(group)){
                indexY = r.getRowIndex();
                indexX = r.getColumnIndex();
                test = 1;
                break;
            }
        }
        //}
        return test;
    }

    public String printExel(String puti, String [][] mstr, String [][]  kabinet) throws IOException{
        FileInputStream fis = new FileInputStream(puti);
        Workbook wb1 = new XSSFWorkbook(fis);
        int newDay = 0;
        //System.out.println(indexY + "\tindexY");
        int indexRowPredmet = indexY + 2;
        //System.out.println(indexRowPredmet + "\tindexRowPredmet");
        for (int i = 0; i < 6; i++){
            for (int i2 = 0; i2 < 12; i2++){
                String predmet = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + i2 + newDay).getCell(indexX)) + "\t";
                //System.out.println(indexRowPredmet+ "\t");
                String type = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + i2 + newDay).getCell(indexX+1)) + "\t";
                String taech = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + i2 + newDay).getCell(indexX+2)) + "\t";
                String location = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + i2 + newDay).getCell(indexX+3)) + "\n";

                otvet.append(indexRowPredmet + "\t");
                otvet.append(i2 + "\t");
                mstr[i][i2] = predmet;
                //mstr[i][i2+1] = location;
                System.out.println("predmet\t" + predmet);
                System.out.println("location\t" + location);
                kabinet[i][i2] = type + " " + location;
                System.out.println("mstr[" + i + "][" +i2 + "]\t" + mstr[i][i2]);
                System.out.println("kabinet[" + i + "][" +i2 + "]\t" + kabinet[i][i2]);

                otvet.append(newDay + "\t");


                otvet.append(predmet);
                otvet.append(type);
                otvet.append(taech);
                otvet.append(location);



                ////////////////////////
//                mstr[i][i2] = predmet;
                ////////////////////////
            }
            newDay += 12;
        }
        System.out.println("otvet\t" + otvet+"\t"+"\t");
        return otvet.toString();


    }

    public String getCellText(Cell cell){



        String res="";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                res = cell.getRichStringCellValue().getString();
                //System.out.println("тест " + cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    res = cell.getDateCellValue().toString();
                }
                else {
                    res = Double.toString(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                res = Boolean.toString(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                res = cell.getCellFormula().toString();
                break;
            default:
                break;
        }
        return res;
    }

}