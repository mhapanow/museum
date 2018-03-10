package com.ciessa.museum.tools;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings({"rawtypes","unchecked"})
public class CollectionFactory {

	public static <T> List<T> createList () {
		return (List<T>) createCollection(ArrayList.class);
	}

	public static <T> List<T> createList (Collection<T> collection) {
		List<T> newList = createCollection(ArrayList.class);
		newList.addAll(collection);
		return newList;
	}

	public static <T> List<T> createList (List<T> list) {
		List<T> newList = createCollection(ArrayList.class);
		newList.addAll(list);
		return newList;
	}
	
	public static <T> List<T> createList (T [] array) {
		return Arrays.asList(array);
	}

	public static <T> Set<T> createSet () {
		return createCollection(HashSet.class);
	}

	public static <T> Set<T> createSet (Comparator<T> comparator) {
		return new TreeSet<T>(comparator);
	}

	public static <T> Collection<T> createCollection () {
		return createCollection(ArrayList.class);
	}

	public static Collection<?> safeCreateCollection (Class clazz) {
		if (null == clazz || Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers()) || !Collection.class.isAssignableFrom(clazz)) {
			clazz = ArrayList.class;
		}
		return createCollection(clazz);
	}

	public static <T extends Collection<?>> T createCollection (Class<T> clazz) {
		try {
			return ReflectionUtil.instantiate(clazz);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> Collection<T> createCollection (Collection<T> collection) {
		return createList(collection);
	}

	public static <K, V> Map<K,V> safeCreateMap (Class clazz) {
		if (null == clazz || Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers()) || !Map.class.isAssignableFrom(clazz)) {
			clazz = HashMap.class;
		}
		return createMap(clazz);
	}

	public static <K, V> Map<K,V> createMap () {
		try {
			return ReflectionUtil.instantiate(HashMap.class);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <K, V> Map<K,V> createMap (Class<? extends Map> clazz) {
		try {
			return ReflectionUtil.instantiate(clazz);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}

}
