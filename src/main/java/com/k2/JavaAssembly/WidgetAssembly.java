package com.k2.JavaAssembly;

import java.io.IOException;
import java.io.Writer;


public class WidgetAssembly<T> {
	
	public static <K> WidgetAssembly<K> create(AWidget<K> widget) {
		return new WidgetAssembly<K>(widget);
	}

	private AWidget<T> rootWidget;
	private WidgetAssembly(AWidget<T> widget) {
		this.rootWidget = widget;
	}

	public AWidget<T> root() {
		return rootWidget;
	}

	public Writer output(T obj, Writer out) throws IOException {
		return rootWidget.output(this, obj, out);
	}

	private String indentStr = "    ";
	public WidgetAssembly<T> setIndentStr(String indentStr) {
		this.indentStr = indentStr;
		if (indentLevel > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<indentLevel; i++) 
				sb.append(indentStr);
			indent = sb.toString();
		} else
			indent = "";	
		return this;
	}

	private int indentLevel = 0;
	private String indent = "";
	public String getIndent() {
		return indent;
	}
	
	public void indent() {
		indentLevel++;
		indent = indent+indentStr;
	}
	
	public void outdent() {
		if (indentLevel<=0) return;
		indentLevel--;
		indent = indent.substring(0, indent.length()-indentStr.length());
	}
	
	public void println(Writer w, String line) throws IOException {
		
		w.write(indent);
		w.write(line);
		w.write(System.lineSeparator());
		
	}

}
