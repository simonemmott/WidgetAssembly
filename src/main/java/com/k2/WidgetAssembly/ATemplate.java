package com.k2.WidgetAssembly;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.WidgetFactory.WidgetFactory;

public abstract class ATemplate<T> extends AWidget<T> {
	
	public ATemplate(WidgetFactory widgetFactory, Class<T> widgetInterface) {
		super(widgetInterface);
		
		this.widgetFactory = widgetFactory; 
		
	}
	
	private WidgetFactory widgetFactory;
	
	public abstract WidgetAssemblyNode<T,T> buildRootNode(WidgetAssembly<T> widgetAssembly, WidgetFactory widgetFactory, WidgetAssemblyNode<?,?> sourceNode);
	

	public Writer output(WidgetAssemblyNode<?,T> node, T obj, Writer out, boolean isFirst, boolean isLast) throws IOException {
		
		WidgetAssemblyNode<T,T> rootNode = buildRootNode(node.getAssembly(), widgetFactory, node);;

		return rootNode.output(obj, out, isFirst, isLast);
		
	}
	

}
