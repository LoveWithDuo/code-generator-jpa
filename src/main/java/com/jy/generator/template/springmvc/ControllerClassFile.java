package com.jy.generator.template.springmvc;

import java.util.LinkedList;
import java.util.List;

import com.jy.generator.base.JavaFile;


/**
* @author 周杰
* @time 2017年10月8日 下午3:42:46
*/
public class ControllerClassFile extends JavaFile {
	private String model;
	public ControllerClassFile(String fileDir, String mainPackage, String curPackage, String model) {
		super(fileDir, getClassName(model).append(".java").toString(), mainPackage+"."+curPackage, CLASS_TYPE, getClassName(model).toString());
		this.model = model;
		List<String> imports = new LinkedList<>();
		List<String> classAnnotations = new LinkedList<>();
		List<String> extendss = new LinkedList<>();
		List<String> implementss = new LinkedList<>();
		List<JavaFile.Field> fields = new LinkedList<>();
		List<Method> methods = new LinkedList<>();
		
		//imports
		imports.add(getImportName(mainPackage, model).toString());
		imports.add(new StringBuilder().append(mainPackage).append(".").append("service").append(".").append(getModelClass(model)).append("Service").toString());
		imports.add(new StringBuilder().append("com.jy.common.ResultInfo").toString());
		imports.add("java.util.HashMap");
		imports.add("java.util.List");
		imports.add("java.util.Map");
		imports.add("org.springframework.beans.factory.annotation.Autowired");
		imports.add("org.springframework.data.domain.Page");
		imports.add("org.springframework.web.bind.annotation.RequestMapping");
		imports.add("org.springframework.web.bind.annotation.PostMapping");
		imports.add("org.springframework.web.bind.annotation.RequestBody");
		imports.add("org.springframework.web.bind.annotation.RestController");
		
		//classAnnotations
		classAnnotations.add("RestController");
		classAnnotations.add(new StringBuilder().append("RequestMapping(\"/").append(getModelName(model)).append("\")").toString());
		
		//extendss none
		
		//implementss none
		
		//fields
		List<String> fannotations = new LinkedList<>();
		fannotations.add("Autowired");
		String fmodifier = "private";
		String ftype = new StringBuilder().append(getModelClass(model)).append("Service").toString();
		String fname = getModelName(model).append("Service").toString();
		String value = null;
		Field field = new Field(fannotations, fmodifier , ftype, fname, value);
		fields.add(field);
		
		//methods
		List<String> mannotations = new LinkedList<>();
		String mmodifier = "public";
		String resultType = "ResultInfo";
		String name;
		String args;
		List<String> exceptions = null;
		String methodBody = null;
		//add
		mannotations.add("PostMapping(\"/add\")");
		name = "add";
		args = new StringBuilder().append("@RequestBody ").append(getModelClass(model)).append(" ").append(getModelName(model)).toString();
		methodBody = buildMethod(new String[] {
				new StringBuilder().append("return ResultInfo.success(").append(getModelName(model).append("Service").append(".add(").append(getModelName(model)).append(")")).append(");").toString()
		});
		Method method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//delete
		mannotations = new LinkedList<>();
		mannotations.add("PostMapping(\"/delete\")");
		name = "delete";
		args = "String id";
		methodBody = buildMethod(new String[] {
				getModelName(model).append("Service.delete(id);").toString(),
				new StringBuilder().append("return ResultInfo.success();").toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//update
		mannotations = new LinkedList<>();
		mannotations.add("PostMapping(\"/update\")");
		name = "update";
		args = getModelClass(model).append(" ").append(getModelName(model)).toString();
		methodBody = buildMethod(new String[] {
				new StringBuilder().append("return ResultInfo.success(").append(getModelName(model).append("Service").append(".update(").append(getModelName(model)).append(")")).append(");").toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//get
		mannotations = new LinkedList<>();
		mannotations.add("PostMapping(\"/get\")");
		name = "get";
		args = "String id";
		methodBody = buildMethod(new String[] {
				getModelClass(model).append(" ").append(getModelName(model)).append(" = ").append(getModelName(model)).append("Service.get(id);").toString(),
				new StringBuilder().append("if(").append(getModelName(model)).append("==null){throw new RuntimeException();}").toString(),
				new StringBuilder().append("return ResultInfo.success(").append(getModelName(model)).append(");").toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getAll
		mannotations = new LinkedList<>();
		mannotations.add("PostMapping(\"/getAll\")");
		name = "getAll";
		args = null;
		methodBody = buildMethod(new String[] {
				new StringBuilder().append("return ResultInfo.success(").append(getModelName(model).append("Service.getAll()")).append(");").toString()
		});
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		//getPage
		mannotations = new LinkedList<>();
		mannotations.add("PostMapping(\"/getPage\")");
		name = "getPage";
		args = "Integer page, Integer size";
		methodBody = buildMethod(new String[] {
				"Page<"+getModelClass(model)+"> pageBean = "+getModelName(model)+"Service.getPage(page,size);",
				"Map<String, Object> data = new HashMap<String, Object>();",
				"data.put(\"total\",pageBean.getTotalElements());",
				"data.put(\"rows\",pageBean.getContent());",
				"return ResultInfo.success(data);"
		});
		
		method = new Method(mannotations, mmodifier, resultType, name, args, exceptions, methodBody);
		methods.add(method);
		
		super.init(imports, classAnnotations, extendss, implementss, fields, methods);
	}
	public static StringBuilder getModelName(String model) {
		StringBuilder modelName = new StringBuilder(model.toLowerCase());
		//modelName.append("_model");
		return modelName;
	}
	public static StringBuilder getModelClass(String model) {
		StringBuilder modelClass = new StringBuilder();
		modelClass.append((model.charAt(0)+"").toUpperCase());
		modelClass.append(model.substring(1));
		return modelClass;
	}
	public static StringBuilder getClassName(String model) {
		StringBuilder className = new StringBuilder();
		className.append(getModelClass(model));
		className.append("Ctrl");
		return className;
	}
	public static StringBuilder getImportName(String packagei,String model) {
		StringBuilder importName = new StringBuilder();
		importName.append(packagei);
		importName.append(".model.");
		importName.append(getModelClass(model));
		return importName;
	}
	public static StringBuilder getImportName(String packagei,String and,String name) {
		StringBuilder importName = new StringBuilder();
		importName.append(packagei);
		importName.append(".");
		importName.append(and);
		importName.append(".");
		importName.append(name);
		return importName;
	}
	private static String buildMethod(String[] lines) {
		StringBuilder method = new StringBuilder();
		method
		.append("\t\t")
		.append("try{")
		.append("\n");
		if(lines != null) {
			for (String line : lines) {
				method
				.append("\t\t\t")
				.append(line)
				.append("\n");
			}
		}
		method
		.append("\t\t")
		.append("}catch(Exception e){")
		.append("\n")
		.append("\t\t\t")
		.append("e.printStackTrace();")
		.append("\n")
		.append("\t\t\t")
		.append("return ResultInfo.failure();")
		.append("\n")
		.append("\t\t")
		.append("}")
		.append("\n")
		.toString();
		return method.toString();
	}
}
