package com.Excel;

import java.io.File;
import java.io.IOException;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelOperate {

	public void writeNewExcel(String fileName, StringBuffer contents) {
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		Label label = null;
		String[] lines = contents.toString().split("\n");
		int lineNum = lines.length;
		String[] tmp = lines[0].split("#");
		try {
			book = Workbook.createWorkbook(new File(fileName));
			sheet = book.createSheet("Page1", 0);

			for (int i = 0; i < lineNum; i++) {
				tmp = lines[i].split("#");
				for (int j = 0; j < tmp.length; j++) {
					label = new Label(j, i, tmp[j]);
					sheet.addCell(label);
				}
			}
			book.write();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


}
