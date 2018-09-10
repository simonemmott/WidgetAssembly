package com.k2.WidgetAssembly;

import java.io.IOException;
import java.io.Writer;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.Util.ObjectUtil;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Getter;

public class WidgetAssemblyNode<S,T> {

//	public static <S,T> WidgetAssemblyNode<S,T> create(Class<S> sourceType, String containerAlias, AWidget<T> widget, String fieldAlias) {
//		return new WidgetAssemblyNode<S,T>(sourceType, containerAlias, widget, fieldAlias);
//	}

	@SuppressWarnings("unchecked")
	public static <S,T> WidgetAssemblyNode<?, T> rootNode(WidgetAssembly assembly, Class<S> sourceType, AWidget<T> widget) {
		return new WidgetAssemblyNode(assembly, sourceType, widget);
	}
	

	private WidgetAssemblyNode(WidgetAssembly assembly, Class<S> sourceType, AWidget<T> widget) {
		this.assembly = assembly;
		this.widget = widget;
		this.sourceType = sourceType;
		this.fieldAlias = "";
		this.bindsCollection = false;
		this.collectionGetter = null;
		this.getter = null;
		this.parentNode = null;
	}

	private WidgetAssemblyNode(WidgetAssembly assembly, WidgetAssemblyNode parentNode, Class<S> sourceType, String containerAlias, AWidget<T> widget, String fieldAlias) {
		this.assembly = assembly;
		this.containerAlias = containerAlias;
		this.widget = widget;
		this.fieldAlias = fieldAlias;
		this.sourceType = sourceType;
		bindsCollection = ClassUtil.isCollection(sourceType, fieldAlias);
		if (bindsCollection) 
			collectionGetter = ClassUtil.getGetter(sourceType, Collection.class, fieldAlias);
		else
			getter = ClassUtil.getGetter(sourceType, widget.getWidgetInterface(), fieldAlias);
		this.parentNode = parentNode;
	}
	
	private final WidgetAssembly assembly;
	private final WidgetAssemblyNode<?,S> parentNode;

	private Getter<S,T> getter;
	private Getter<S,Collection> collectionGetter;
	
	private Class<S> sourceType;
	public Class<S> getSourceType() {
		return sourceType;
	}
	private String containerAlias;
	public String getContainerAlias() {
		return containerAlias;
	}
	
	private AWidget<T> widget;
	public AWidget<T> getWidget() {
		return widget;
	}
	
	private String fieldAlias;
	public String getFieldAlias() {
		return fieldAlias;
	}

	public T getBoundData(S source) {
		if (getter==null)
			return null;
		return getter.get(source);
	}
	private boolean bindsCollection;
	public boolean bindsCollection() {
		return bindsCollection;
	}

	public Collection<T> getBoundCollection(S source) {
		if (collectionGetter==null)
			return new ArrayList<T>(0);
		Collection<T> c = collectionGetter.get(source);
		if (c==null)
			return new ArrayList<T>(0);
		return c;
	}

	private Map<String, List<WidgetAssemblyNode>> containers;

	public WidgetAssemblyNode<?,T> bind(String containerAlias, AWidget<?> widget, String fieldAlias) {
		if (containers == null) containers = new HashMap<String, List<WidgetAssemblyNode>>();
		List<WidgetAssemblyNode> containedWidgets = containers.get(containerAlias);
		if (containedWidgets == null) {
			containedWidgets = new ArrayList<WidgetAssemblyNode>();
			containers.put(containerAlias, containedWidgets);
		}
		
		WidgetAssemblyNode<?,T> node = new WidgetAssemblyNode(assembly, this, this.getWidget().getWidgetInterface(), containerAlias, widget, fieldAlias);
		containedWidgets.add(node);
		return node;
	}


	public WidgetAssemblyNode<?,S> up() {
		return parentNode;
	}


	public List<WidgetAssemblyNode> getContainedNodes(String containerAlias) {
		if (containers == null) containers = new HashMap<String, List<WidgetAssemblyNode>>();
		List<WidgetAssemblyNode> containedWidgets = containers.get(containerAlias);
		return (containedWidgets == null)? new ArrayList<WidgetAssemblyNode>(0): containedWidgets;
	}
	
	public List<WidgetAssemblyNode> getContainedNodes() {
		List<WidgetAssemblyNode> list = new ArrayList<WidgetAssemblyNode>();
		if (containers == null)
			return list;
		for (List<WidgetAssemblyNode> nodes : containers.values()) 
			list.addAll(nodes);
		return list;
	}

	@SuppressWarnings("unchecked")
	public void writeContainer(String containerAlias, T data, Writer out) throws IOException {
		for (WidgetAssemblyNode an : getContainedNodes(containerAlias)) {
			if (an.bindsCollection()) {
				for (Object obj : an.getBoundCollection(data))
					an.output(obj, out);
			} else {
				an.output(an.getBoundData(data), out);
			}
		}
		
	}
	
	public void indent() {
		assembly.indent();
	}
	
	public void outdent() {
		assembly.outdent();
	}

	public void indentContainer(String containerAlias, T data, Writer out) throws IOException {
		assembly.indent();
		try  {
			writeContainer(containerAlias, data, out);
		} finally {
			assembly.outdent();
		}
		
	}


	public Writer output(T obj, Writer out) throws IOException {
		return widget.output(this, obj, out);
	}


	public void println(Writer w, String line) throws IOException {
		assembly.println(w, line);
	}

	public void println(Writer w, String input, Object ... replacements) throws IOException {
		assembly.println(w, StringUtil.replaceAll(input, "{}", replacements));
	}

	public void println(Writer w) throws IOException {
		assembly.println(w);
	}


	public WidgetAssembly getAssembly() {
		return assembly;
	}




	
}
