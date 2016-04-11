package com.kazaf.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.kazaf.domain.Bill;
import com.kazaf.domain.Gym;

public class RWExcel {

	private DateConvertor DC = new DateConvertor();



	// 针对于excel2007以下版本
	public List readExcel4(String file) throws FileNotFoundException, IOException {

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		HSSFWorkbook Billbook = new HSSFWorkbook(fs);
		HSSFSheet Billsheet = Billbook.getSheetAt(0);

		int recordnum = Billsheet.getLastRowNum() + 1;
		HSSFRow Billrow = Billsheet.getRow(0);
		int columnum = Billrow.getLastCellNum();
		HSSFCell Billcell = Billrow.getCell(0);

		List<Bill> billlist = new ArrayList<Bill>();
		Bill billtemp;

		for (int i = 0; i < recordnum; i++) {
			billtemp = new Bill();
			Billrow = Billsheet.getRow(i);
			for (int j = 1; j < columnum; j++) {

				Billcell = Billrow.getCell(j);
				parseExceltoBill(Billcell, billtemp, j);

			}
			billlist.add(billtemp);

		}

		return billlist;

	}

	// 针对于excel2007以上版本
	public List readExcel5(String file, int page, String type) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {
		if (type == "gym") page = page + 13;
		InputStream ins = new FileInputStream(new File(file));
		Workbook wb = WorkbookFactory.create(ins);
		ins.close();
		Sheet excelsheet = wb.getSheetAt(page);
		int recordnum = excelsheet.getLastRowNum() + 1;
		Row excelrow = excelsheet.getRow(0);
		int columnum = excelrow.getLastCellNum();
		Cell excelcell;
		List resultlist=null;

		if (type == "bill") {
			List<Bill> billlist = new ArrayList<Bill>();
			Bill billtemp;
			for (int i = 1; i < recordnum; i++) {
				billtemp = new Bill();
				excelrow = excelsheet.getRow(i);
				for (int j = 0; j < columnum; j++) {
					excelcell = excelrow.getCell(j);
					parseExceltoBill(excelcell, billtemp, j);
				}
				billlist.add(billtemp);
				System.out.println();
			}
			resultlist = billlist;
		}

		else if (type == "gym") {
			List<Gym> gymlist = new ArrayList<Gym>();
			Gym gymtemp;
			for (int i = 1; i < recordnum; i++) {
				gymtemp = new Gym();
				excelrow = excelsheet.getRow(i);
				for (int j = 0; j < columnum; j++) {
					excelcell = excelrow.getCell(j);
					parseExceltoGym(excelcell, gymtemp, j);
				}
				gymlist.add(gymtemp);
				System.out.println();
			}
			resultlist = gymlist;
		}

		OutputStream out = new FileOutputStream(file);
		wb.write(out);
		return resultlist;

	}

	//读取账单信息到数据库中
	private String parseExceltoBill(Cell cell, Bill billtemp, int j) {
		String result = new String();

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
				billtemp.setBill_date(DC.toDate(result));
				System.out.print("输出时间");
				System.out.print("billtemp日期" + billtemp.getBill_date());
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				result = sdf.format(date);
				System.out.print("输出日期");
			} else {
				double value = cell.getNumericCellValue();
				float tempvalue = (float) value;
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
				switch (j) {
				case 2: {
					billtemp.setBill_cost(tempvalue);
					System.out.print("输出cost" + billtemp.getBill_cost());
					break;
				}
				case 3: {
					billtemp.setBill_level((int) tempvalue);
					System.out.print("输出level" + billtemp.getBill_level());
					break;
				}
				}
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			billtemp.setBill_comments(result);
			System.out.print("输出String");
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			System.out.print("输出Blank");
		default:
			result = "";
			System.out.print("输出default");
			break;
		}
		return result;
	}

	//读取健身信息到数据库中
	private String parseExceltoGym(Cell cell, Gym gymtemp, int j) {
		String result = new String();

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
				gymtemp.setGym_date(DC.toDate(result));
				System.out.print("输出时间");
				System.out.print("billtemp日期" + gymtemp.getGym_date());
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			switch (j) {
			case 1: {
				gymtemp.setGym_item(result);
				System.out.print("输出item" + gymtemp.getGym_item());
				break;
			}
			case 2: {
				gymtemp.setGym_comments(result);
				System.out.print("输出comments" + gymtemp.getGym_comments());
				break;
			}
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			System.out.print("输出Blank");
			break;
		default:
			result = "";
			System.out.print("输出default");
			break;
		}
		return result;
	}

}
