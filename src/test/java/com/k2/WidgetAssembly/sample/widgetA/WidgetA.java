package com.k2.WidgetAssembly.sample.widgetA;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssembly;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetInterface;

public class WidgetA extends AWidget<WidgetAInterface> {

	public static WidgetA create() {
		return new WidgetA();
	}
	
	private WidgetA() {
		super(WidgetAInterface.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,WidgetAInterface> node, WidgetAInterface data, Writer out) throws IOException {
		node.println(out, "WIDGET A");
		if (data != null)
			node.println(out, data.getAlias()+"["+data.getId()+"]");
		
		return out;
	}
	

}
