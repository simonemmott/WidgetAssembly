package com.k2.WidgetAssembly.sample.sampleWidget;

import java.io.IOException;
import java.io.Writer;

import com.k2.WidgetAssembly.ATemplate;
import com.k2.WidgetAssembly.WidgetAssembly;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetAssembly.sample.widgetA.WidgetAInterface;
import com.k2.WidgetAssembly.sample.widgetB.WidgetBInterface;
import com.k2.WidgetFactory.WidgetFactory;

public class SampleTemplate extends ATemplate<SampleWidgetInterface> {

	public SampleTemplate(WidgetFactory widgetFactory) {
		super(widgetFactory, SampleWidgetInterface.class);
	}

	@Override
	public WidgetAssemblyNode<SampleWidgetInterface, SampleWidgetInterface> buildRootNode(
			WidgetAssembly<SampleWidgetInterface> widgetAssembly, WidgetFactory widgetFactory, WidgetAssemblyNode<?, ?> sourceNode) {
		
		WidgetAssemblyNode<SampleWidgetInterface, SampleWidgetInterface> rootNode;
		rootNode = WidgetAssemblyNode.rootNode(widgetAssembly, SampleWidgetInterface.class, widgetFactory.get(SampleWidgetInterface.class, "sample"));
		
		rootNode
			.bind("A", widgetFactory.get(WidgetAInterface.class, "sample"), "widgetA").up()
			.bind("A", widgetFactory.get(WidgetBInterface.class, "sample"), "widgetB").up()
			.contain("B", sourceNode, "C").up();
					
		return rootNode;
	}

}
