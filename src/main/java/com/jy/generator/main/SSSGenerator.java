package com.jy.generator.main;

import java.util.Scanner;

import com.jy.generator.template.spring.ApplicationClassFile;
import com.jy.generator.template.spring.ApplicationYmlResourceFile;
import com.jy.generator.template.springmvc.ControllerClassFile;
import com.jy.generator.template.common.PojoClassFile;
import com.jy.generator.template.common.PomXmlResourceFile;
import com.jy.generator.template.springdatajpa.RepositoryInterfaceFile;
import com.jy.generator.template.common.ResultClassFile;
import com.jy.generator.template.service.ServiceClassFile;
import com.jy.generator.template.service.ServiceInterfaceFile;

/**
* @author 周杰
* @time 2017年10月9日 下午12:07:26
*/
public class SSSGenerator {
	
	public static void generate(String baseDir, String projectName, String pkgDir, String model) throws Exception{
		String resourcesDir = "src/main/resources";
		String javaDir = "src/main/java";
		
//		PomXmlResourceFile pxrf = new PomXmlResourceFile(baseDir+"/"+projectName);
//		pxrf.generate();
//		
//		ApplicationYmlResourceFile ayrf = new ApplicationYmlResourceFile(baseDir+"/"+projectName+"/"+resourcesDir);
//		ayrf.generate();
//		
//		ApplicationClassFile acf = new ApplicationClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir, toPackage(pkgDir));
//		acf.generate();
		
		PojoClassFile pcf = new PojoClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/model", toPackage(pkgDir), "model", model);
		pcf.generate();
		RepositoryInterfaceFile rif = new RepositoryInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/repository",toPackage(pkgDir),"repository",model);
		rif.generate();
		
//		ResultClassFile rcf = new ResultClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/util", toPackage(pkgDir), "util");
//		rcf.personalGenerate();
//		ServiceInterfaceFile sif = new ServiceInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/service",toPackage(pkgDir),"service",model);
//		sif.generate();
		
		ServiceClassFile scf = new ServiceClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/service",toPackage(pkgDir),"service",model);
		scf.generate();
		ControllerClassFile ccf = new ControllerClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+pkgDir+"/ctrl", toPackage(pkgDir),"ctrl", model);
		ccf.generate();
		
	}
	
	public static void main(String[] args) throws Exception {
		generate("d:", "moneysign", "com/jy/sys/summary", "UserSummary");
		
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("BaseDir:");
//		String baseDir = scanner.next();
//		System.out.print("ProjectName:");
//		String projectName = scanner.next();
//		String resourcesDir = "src/main/resources";
//		String javaDir = "src/main/java";
//		System.out.print("PackageDir:");
//		String packageDir = scanner.next();
//		
//		PomXmlResourceFile pxrf = new PomXmlResourceFile(baseDir+"/"+projectName);
//		pxrf.generate();
//		
//		ApplicationYmlResourceFile ayrf = new ApplicationYmlResourceFile(baseDir+"/"+projectName+"/"+resourcesDir);
//		ayrf.generate();
//		
//		ApplicationClassFile acf = new ApplicationClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir, toPackage(packageDir));
//		acf.generate();
//		
//		ResultClassFile rcf = new ResultClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/util", toPackage(packageDir), "util");
//		rcf.personalGenerate();
//		String model;
//		while(true) {
//			System.out.print("PojoName(input \"exit\" to exit):");
//			model = scanner.next();
//			if(model.equals("exit")) {
//				break;
//			}
//			PojoClassFile pcf = new PojoClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/model", toPackage(packageDir), "model", model);
//			pcf.generate();
//			RepositoryInterfaceFile rif = new RepositoryInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/repository",toPackage(packageDir),"repository",model);
//			rif.generate();
//			ServiceInterfaceFile sif = new ServiceInterfaceFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/service",toPackage(packageDir),"service",model);
//			sif.generate();
//			ServiceClassFile scf = new ServiceClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/service/impl",toPackage(packageDir),"service.impl",model);
//			scf.generate();
//			ControllerClassFile ccf = new ControllerClassFile(baseDir+"/"+projectName+"/"+javaDir+"/"+packageDir+"/controller", toPackage(packageDir),"controller", model);
//			ccf.generate();
//		}
	}
	
	private static String toPackage(String packageDir) {
		return packageDir.replaceAll("/", ".");
	}
}
