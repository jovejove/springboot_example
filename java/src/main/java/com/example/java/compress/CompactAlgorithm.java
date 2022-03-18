package com.example.java.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactAlgorithm {
 
	/**
	 * 完成的结果文件--输出的压缩文件
	 */
	File targetFile;

	public CompactAlgorithm(File target) {
		targetFile = target;
		if (targetFile.exists()) {
			targetFile.delete();
		}
	}
 
	/**
	 * 压缩文件
	 * @param srcFile
	 */
	public void zipFiles(File srcFile) {
		try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFile))) {
			if(srcFile.isFile()){
				zipFile(srcFile, out, "");
			} else{
				File[] list = srcFile.listFiles();
				for (int i = 0; i < list.length; i++) {
					compress(list[i], out, "");
				}
			}
			System.out.println("压缩完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 压缩文件夹里的文件
	 * 起初不知道是文件还是文件夹--- 统一调用该方法
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			this.zipDirectory(file, out, basedir);
		} else {
			this.zipFile(file, out, basedir);
		}
	}
 
	/**
	 * 压缩单个文件
	 * @param srcFile
	 */
	public void zipFile(File srcFile, ZipOutputStream out, String basedir) {
		if (!srcFile.exists()) {
			return;
		}
		byte[] buf = new byte[1024];
		FileInputStream in = null;
		try {
			int len;
			in = new FileInputStream(srcFile);
			out.putNextEntry(new ZipEntry(basedir + srcFile.getName()));
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.closeEntry();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	/**
	 * 压缩文件夹
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	public void zipDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists()) {
			return;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}
 
	
	//测试
	public static void main(String[] args) {
		File f = new File("E:/Study/Java");
		new CompactAlgorithm(new File( "D:/test",f.getName()+".zip")).zipFiles(f);
	}
 
}
