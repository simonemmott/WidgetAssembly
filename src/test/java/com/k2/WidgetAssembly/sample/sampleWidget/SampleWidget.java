package com.k2.WidgetAssembly.sample.sampleWidget;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssembly;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetFactory.annotation.Widget;

@Widget("sample")
public class SampleWidget extends AWidget<SampleWidgetInterface> {

	public static SampleWidget create() {
		return new SampleWidget();
	}
	
	public SampleWidget() {
		super(SampleWidgetInterface.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,SampleWidgetInterface> node, SampleWidgetInterface data, Writer out, boolean isFirst, boolean isLast) throws IOException {

		node.println(out, "SAMPLE WIDGET");
		node.println(out, data.getName()+"("+data.getDescription()+")");
		node.println(out, "Container A [");
		node.indentContainer("A", data, out);
		node.println(out, "]");
		node.println(out, "Container B [");
		node.indentContainer("B", data, out);
		node.println(out, "]");
		
		return out;
	}

	

}
