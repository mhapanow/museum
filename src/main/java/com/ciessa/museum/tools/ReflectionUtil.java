package com.ciessa.museum.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ReflectionUtil {

	/**
	 * Returns the class for the specified attribute.
	 * 
	 * @param attributeName
	 * @return the attribute class, or null if it does not exists (or a SecurityException arises)
	 */
	public static Class<?> getAttributeType (Class<?> clazz, String attributeName) {
		//Here be dragons
		Field attributeField = retrieveField(clazz, attributeName);
		if (isMultipleValued(attributeField.getType())) {
			try {
				// TODO: Checks GAE news features to validate support for ParameterizedTypeImpl.
				//return (Class<?>)((ParameterizedTypeImpl) attributeField.getGenericType()).getActualTypeArguments()[0];
				return (Class<?>)attributeField.getGenericType();
			} catch (ClassCastException cce) {
				//
			}
		}
		return attributeField.getType();
	}

	public static boolean isMultipleValued (Class<?> clazz, String attributeName) {
		return isMultipleValued(retrieveField(clazz, attributeName).getType());
	}

	private static boolean isMultipleValued (Class<?> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}

	public static Field retrieveField (Class<?> clazz, String attributeName) {
		Field attributeField = null;

		Class<?> inspectedClass = clazz;
		while (null == attributeField && null != inspectedClass) {
			try {
				attributeField = inspectedClass.getField(attributeName);
			} catch (SecurityException e) {
				return null;
			} catch (NoSuchFieldException e) {
				try {
					attributeField = inspectedClass.getDeclaredField(attributeName);
				} catch (SecurityException e1) {
					return null;
				} catch (NoSuchFieldException e1) {
					inspectedClass = inspectedClass.getSuperclass();
				}
			}
		}
		return attributeField;
	}

	public static Method retrieveMethod (Class<?> clazz, String methodName) {
		return retrieveMethod(clazz, methodName, new Class[]{});
	}

	public static Method retrieveMethod (Class<?> clazz, String methodName, Class<?> ... parameterTypes) {
		Method method = null;

		Class<?> inspectedClass = clazz;
		while (null == method && null != inspectedClass) {
			try {
				method = inspectedClass.getMethod(methodName, parameterTypes);
			} catch (SecurityException e) {
				return null;
			} catch (NoSuchMethodException e) {
				try {
					method = inspectedClass.getDeclaredMethod(methodName, parameterTypes);
				} catch (SecurityException e1) {
					return null;
				} catch (NoSuchMethodException e1) {
					inspectedClass = inspectedClass.getSuperclass();
				}
			}
		}
		return method;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getAttributeValue (String attribute, Object target) throws IllegalArgumentException, IllegalAccessException {
		return (T)getFieldValue(retrieveField(target.getClass(), attribute), target);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue (Field field, Object target) throws IllegalArgumentException, IllegalAccessException {
		boolean originalAccessibility = field.isAccessible();
		field.setAccessible(true);
		T value;
		value = (T) field.get(target);
		field.setAccessible(originalAccessibility);
		return value;
	}

	public static void setFieldValue (Field field, Object target, Object value) throws IllegalArgumentException, IllegalAccessException {
		boolean originalAccessibility = field.isAccessible();
		field.setAccessible(true);
		field.set(target, value);
		field.setAccessible(originalAccessibility);
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod (Method method, Object target, Object ... parameters) throws IllegalArgumentException, IllegalAccessException, 
																							   InvocationTargetException {
		boolean originalAccessibility = method.isAccessible();
		method.setAccessible(true);
		T retVal = (T) method.invoke(target, parameters);
		method.setAccessible(originalAccessibility);
		return retVal;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue (String attributeName, Object target) 
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		if (attributeName.contains(".")) {
			StringTokenizer attributeTokenizer = new StringTokenizer(attributeName, "\\.");
			while (1 < attributeTokenizer.countTokens()) {
				String nextAttribute = attributeTokenizer.nextToken();
				target = getValue(nextAttribute, target);
			}
			attributeName = attributeTokenizer.nextToken();
		}

		String getterName = "get"+attributeName.substring(0, 1).toUpperCase()+attributeName.substring(1);

		Method getter = retrieveMethod(target.getClass(), getterName);
		if (null != getter) {
			return (T) invokeMethod(getter, target);
		}
		else {
			Field field = retrieveField(target.getClass(), attributeName);
			if (null != field) {
				return (T) getFieldValue(field, target);
			}
		}
		return null;
	}

	public static void setValue (String attributeName, Object target, Object value) throws IllegalArgumentException, IllegalAccessException, 
																						   InvocationTargetException {
		setValue(attributeName, target, value, value.getClass());
	}

	public static boolean setValue (String attributeName, Object target, Object value, Class<?> valueClass) throws IllegalArgumentException, IllegalAccessException, 
																												   InvocationTargetException {
		if (attributeName.contains(".")) {
			StringTokenizer attributeTokenizer = new StringTokenizer(attributeName, "\\.");
			while (1 < attributeTokenizer.countTokens()) {
				String nextAttribute = attributeTokenizer.nextToken();
				target = getValue(nextAttribute, target);
			}
			attributeName = attributeTokenizer.nextToken();
		}

		boolean couldPerformSet = false;
		String setterName = "set"+attributeName.substring(0, 1).toUpperCase()+attributeName.substring(1);

		Method setter = retrieveMethod(target.getClass(), setterName, valueClass);
		if (null == setter && null != primitiveTypeFor(valueClass)) {
			setter = retrieveMethod(target.getClass(), setterName, primitiveTypeFor(valueClass));
		}

		if (null != setter) {
			invokeMethod(setter, target, value);
			couldPerformSet = true;
		}
		else {
			Field field = retrieveField(target.getClass(), attributeName);
			if (null != field) {
				setFieldValue(field, target, value);
				couldPerformSet = true;
			}
		}
		return couldPerformSet;
	}

	/**
	 * Finds the field which has a given annotation on the class and all it's superclasses.
	 * Returns only one field. If there are more than one, returns the first found. For multiple values, {@link #findFieldsForAnnotation}
	 * 
	 * @param persistentClass
	 * @return
	 */
	public static Field findFieldForAnnotation (Class<?> persistentClass, Class<? extends Annotation> annotationClass) {
		if (null != persistentClass && null != annotationClass) {
			Set<Field> classFields = new HashSet<Field>();
			classFields.addAll(Arrays.asList(persistentClass.getFields()));
			classFields.addAll(Arrays.asList(persistentClass.getDeclaredFields()));

			for (Field field : classFields) {
				if (null != field.getAnnotation(annotationClass))
					return field;
			}

			return findFieldForAnnotation(persistentClass.getSuperclass(), annotationClass);
		}
		return null;
	}

	/**
	 * Finds fields which have a given annotation on the class and all it's superclasses.
	 * 
	 * @param persistentClass
	 * @return
	 */
	public static Collection<Field> findFieldsForAnnotation (Class<?> persistentClass, Class<? extends Annotation> annotationClass) {
		Collection<Field> annotatedFields = new ArrayList<Field>();

		for (Field field : gatherFields(persistentClass)) {
			if (null != field.getAnnotation(annotationClass)) {
				annotatedFields.add(field);
			}
		}

		return annotatedFields;
	}

	public static boolean hasField (Class<?> type, String fieldName) {
		return null != retrieveField(type, fieldName);
	}

	/**
	 * Returns all fields from the class through its hierarchy
	 * 
	 * @param persistentClass
	 * @return
	 */
	public static Collection<Field> gatherFields (Class<?> persistentClass) {
		Set<Field> classFields = new HashSet<Field>();

		if (null != persistentClass) {
			classFields.addAll(Arrays.asList(persistentClass.getFields()));
			classFields.addAll(Arrays.asList(persistentClass.getDeclaredFields()));
			classFields.addAll(gatherFields(persistentClass.getSuperclass()));
		}

		return classFields;
	}

	/**
	 * Determines whether a class has a given annotation at its class level.
	 * 
	 * @param persistentClass
	 * @return
	 */
	public static boolean classIsAnnotatedWithAnnotation (Class<?> persistentClass, Class<? extends Annotation> annotationClass) {
		return null != persistentClass.getAnnotation(annotationClass);
	}


	public static <T> T instantiate (Class<T> clazz) throws ReflectiveOperationException {
		return instantiate(clazz, new Class[]{}, new Object[]{});
	}

	public static <T> T instantiate (Class<T> clazz, Class<?>[] parameterTypes, Object[] parameters) throws ReflectiveOperationException {
		try {
			return clazz.getConstructor(parameterTypes).newInstance(parameters);
		} catch (NullPointerException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (IllegalArgumentException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (SecurityException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (InstantiationException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (IllegalAccessException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (InvocationTargetException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		} catch (NoSuchMethodException e) {
			throw new ReflectiveOperationException("Could not instantiate type "+(null != clazz ? clazz.getName() : "(null)"), e);
		}
	}

	/**
	 * Retrieves the class the parameter has been parameterized with
	 * 
	 * @return
	 */
	public static <T> Class<T> resolveParameterizedClass (Class<?> type) {
		return resolveClassParameterAtIndex(type, 0);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> resolveClassParameterAtIndex (Class<?> type, int idx) {
		return (Class<T>) ((ParameterizedType) type.getGenericSuperclass()).getActualTypeArguments()[idx];
	}

	public static boolean isPrimitive(Class<?> type) {
		return primitiveTypeFor(type) != null;
	}

	public static Class<?> primitiveTypeFor(Class<?> wrapper) {
		if (wrapper == Boolean.class) return Boolean.TYPE;
		if (wrapper == Byte.class) return Byte.TYPE;
		if (wrapper == Character.class) return Character.TYPE;
		if (wrapper == Short.class) return Short.TYPE;
		if (wrapper == Integer.class) return Integer.TYPE;
		if (wrapper == Long.class) return Long.TYPE;
		if (wrapper == Float.class) return Float.TYPE;
		if (wrapper == Double.class) return Double.TYPE;
		if (wrapper == Void.class) return Void.TYPE;
		return null;
	}



	public static class Invoker {
		private String methodName;
		private Object[] methodArguments;
		private Class<?>[] methodArgumentsClasses;

		public Invoker(String methodName, Object [] methodArguments, Class<?>[] methodArgumentsClasses) {
			this.methodName = methodName;
			this.methodArguments = methodArguments;
			this.methodArgumentsClasses = methodArgumentsClasses;
		}

		public Object invoke (Object target) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
			return ReflectionUtil.invokeMethod(ReflectionUtil.retrieveMethod(target.getClass(), methodName, methodArgumentsClasses), target, methodArguments);
		}

		@Override
		public String toString() {
			StringBuilder descriptionBuilder = new StringBuilder();
			descriptionBuilder.append("Invoker:{methodName:'"+methodName+"'}");
			return descriptionBuilder.toString();
		}
	}
}
