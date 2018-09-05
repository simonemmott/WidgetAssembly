package com.k2.JavaAssembly.sample.widgetA;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.k2.JavaAssembly.AWidget;
import com.k2.JavaAssembly.WidgetAssembly;
import com.k2.JavaAssembly.sample.sampleWidget.SampleWidgetInterface;

public class WidgetA extends AWidget<WidgetAInterface> {

	public static WidgetA create() {
		return new WidgetA();
	}
	
	private WidgetA() {
		super(WidgetAInterface.class);
	}

	@Override
	public Writer output(WidgetAssembly<?> wa, WidgetAInterface data, Writer out) throws IOException {
		wa.println(out, "WIDGET A");
		if (data != null)
			wa.println(out, data.getAlias()+"["+data.getId()+"]");
		
		return out;
	}
	

}
