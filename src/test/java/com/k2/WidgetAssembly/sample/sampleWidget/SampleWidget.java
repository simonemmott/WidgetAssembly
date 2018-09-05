package com.k2.WidgetAssembly.sample.sampleWidget;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssembly;

public class SampleWidget extends AWidget<SampleWidgetInterface> {

	public static SampleWidget create() {
		return new SampleWidget();
	}
	
	private SampleWidget() {
		super(SampleWidgetInterface.class);
	}

	@Override
	public Writer output(WidgetAssembly<?> wa, SampleWidgetInterface data, Writer out) throws IOException {

		wa.println(out, "SAMPLE WIDGET");
		wa.println(out, data.getName()+"("+data.getDescription()+")");
		wa.println(out, "Container A [");
		indentContainer("A", wa, data, out);
		wa.println(out, "]");
		wa.println(out, "Container B [");
		indentContainer("B", wa, data, out);
		wa.println(out, "]");
		
		return out;
	}

	

}
