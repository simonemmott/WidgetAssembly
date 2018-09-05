package com.k2.JavaAssembly;

import java.util.ArrayList;
import java.util.Collection;

import com.k2.Util.ObjectUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Getter;

public class ContainedWidget<S,T> {

	public static <S,T> ContainedWidget<S,T> create(Class<S> sourceType, String containerAlias, AWidget<T> widget, String fieldAlias) {
		return new ContainedWidget<S,T>(sourceType, containerAlias, widget, fieldAlias);
	}
	
	private ContainedWidget(Class<S> sourceType, String containerAlias, AWidget<T> widget, String fieldAlias) {
		this.containerAlias = containerAlias;
		this.widget = widget;
		this.fieldAlias = fieldAlias;
		this.sourceType = sourceType;
		bindsCollection = ClassUtil.isCollection(sourceType, fieldAlias);
		if (bindsCollection) 
			collectionGetter = ClassUtil.getGetter(sourceType, Collection.class, fieldAlias);
		else
			getter = ClassUtil.getGetter(sourceType, widget.getWidgetInterface(), fieldAlias);
	}

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
	
	
}
