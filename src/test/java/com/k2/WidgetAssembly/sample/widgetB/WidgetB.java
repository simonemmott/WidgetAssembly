package com.k2.WidgetAssembly.sample.widgetB;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssembly;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetInterface;

public class WidgetB extends AWidget<WidgetBInterface> {

	public static WidgetB create() {
		return new WidgetB();
	}
	
	private WidgetB() {
		super(WidgetBInterface.class);
	}

	@Override
	public Writer output(WidgetAssembly<?> wa, WidgetBInterface data, Writer out) throws IOException {

		wa.println(out, "WIDGET B");
		if (data != null)
			wa.println(out, data.getAlias()+"["+data.getId()+"]");
		
		return out;
	}
	

}