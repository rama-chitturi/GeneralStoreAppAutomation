package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel extends TestBase{

	public ArrayList<String> getExcel(String FilePath,int colnum) throws IOException
	{

		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb =  new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);

		ArrayList<String> datalist = new ArrayList<String>();

		XSSFRow row;
		String data = null;

		for(int rownum=1;rownum<=sh.getLastRowNum();rownum++)
		{
			row = sh.getRow(rownum);
			DataFormatter formatter = new DataFormatter();	
			data = formatter.formatCellValue(row.getCell(colnum));
			datalist.add(data);
		}
		return datalist;	
	}


	public void setExcel(String FilePath, String dataText, int rownum, int colnum) throws IOException
	{
		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);  	
		XSSFSheet sh = wb.getSheetAt(0);
		XSSFRow row;
		row = sh.getRow(rownum);
		row.createCell(colnum).setCellValue(dataText);
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
	}


	public void setExcelCartItems(String FilePath, String dataItemName, String dataItemCost, int rownum, int colnum, String sheetCreationStatus) throws IOException
	{
		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);  	
		XSSFSheet sh = null;
		XSSFRow row = null;
		if(sheetCreationStatus.equalsIgnoreCase("clearSheetTrue"))
		{
			wb.removeSheetAt(0);
			sh = wb.createSheet();
			sh=wb.getSheetAt(0);
			row=sh.createRow(0);

			row.createCell(0).setCellValue("Store Item Name");
			row.createCell(1).setCellValue("Store Item value");
			row.createCell(2).setCellValue("Total cart value- Fetched");
			row.createCell(3).setCellValue("Total cart value-validated");
			row.createCell(4).setCellValue("Validation Status");
			row = sh.createRow(1);
		}
		else 
		{
			sh=wb.getSheetAt(0);
			row = sh.createRow(rownum);
		}
		row.createCell(colnum).setCellValue(dataItemName);
		row.createCell(colnum+1).setCellValue(dataItemCost);
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
	}

	public int getExcelLastRowCount(String FilePath, int Sheetnum) throws IOException
	{
		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb =  new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		int lastRowNumValue = sh.getLastRowNum();
		return lastRowNumValue;
	}

	public void addItemDetailsToExcel(String itemName, String itemCost, String sheetCreation) throws IOException
	{

		String FilePath = "Files//Cart_Items_Details.xlsx";
		int rownum = getExcelLastRowCount(FilePath,0);
		setExcelCartItems(FilePath, itemName, itemCost, rownum+1, 0, sheetCreation);
	}

	public void getCartTotal(String FilePath, float cartValuefetched) throws IOException
	{
		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb =  new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		XSSFRow row=sh.getRow(1);
		String data;
		float validatedCartValue = 0;
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			row=sh.getRow(i);
			DataFormatter formatter = new DataFormatter();	
			data = formatter.formatCellValue(row.getCell(1));
			String datalist=data.substring(1);
			validatedCartValue = validatedCartValue+ Float.parseFloat(datalist);
		}
		sh.getRow(1).createCell(2).setCellValue(cartValuefetched);
		sh.getRow(1).createCell(3).setCellValue(validatedCartValue);

		if(cartValuefetched == validatedCartValue) 
		{
			System.out.println("Validation of cart value is done with result:" + validatedCartValue);
			sh.getRow(1).createCell(4).setCellValue("Cart value validation is correct");
		}
		else
		{
			sh.getRow(1).createCell(4).setCellValue("Cart value validation is wrong");
		}

		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();	
	}}


