package com.webmodel.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(SortUtil.class);
	
	/**
     * 利用java反射机制排序一个集合对象
     * @param list 需要排序的集合
     * @param isAsc 是否升序，如果数组下标不足方法参数个数，将按最后一个状态表示后续方法的排序是否升序
     * @param fieldNames 对象中的方法列表
     */
    public static void sort(List<?> list, boolean[] isAsc, String... fieldNames) {
        sort(list, getComparator(isAsc, fieldNames));
    }
    
    /**
     * 根据指定的比较器排序一个List中的元素
     * @param list 需要排序的集合
     * @param comparator 排序比较器
     */
    public static void sort(List<?> list, Comparator<Object> comparator) {
        Collections.sort(list, comparator);
    }
    
    /**
     * 根据方法名称列表和排序方式获取Comparator，方法名称列表不允许为空
     * @param isAsc 是否升序
     * @param methodNameList 方法名称列表，按list先后顺序进行排序
     * @return Comparator 排序比较器
     */
    public static Comparator<Object> getComparator(boolean[] isAsc, String... methodNames) {
        List<Comparator<Object>> comparatorList = new ArrayList<Comparator<Object>>();
        int index = isAsc.length - 1;
        for (int i = 0; i < methodNames.length; i++) {
            if (i >= index) {
                comparatorList.add(getComparator(isAsc[index], methodNames[i]));
            } else {
                comparatorList.add(getComparator(isAsc[i], methodNames[i]));
            }
        }
        return getComparatorByComparatorList(comparatorList);
    }
    
    /**
     * 根据方法和排序方式获取Comparator，如果方法名称为空，则不使用反射机制
     * @param isAsc 是否升序
     * @param fieldName 方法名称
     * @return Comparator 排序比较器
     */
    public static Comparator<Object> getComparator(final boolean isAsc, final String fieldName) {
        return new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Object value1 = null;
                Object value2 = null;
                if (StringUtils.isBlank(fieldName)) {
                    value1 = o1;
                    value2 = o2;
                } else {
                    // 利用反射得到具体的值
                    value1 = getFieldValue(o1, fieldName);
                    value2 = getFieldValue(o2, fieldName);
                }
                return compareTo(isAsc, value1, value2);
            }
        };
    }
    
    /**
     * 获取支持多个条件进行排序的比较器
     * @param comparatorList 排序比较器列表，按list先后顺序进行排序
     * @return Comparator 支持多个条件排序的比较器
     */
    public static Comparator<Object> getComparatorByComparatorList(final List<Comparator<Object>> comparatorList) {
        return new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                for (Comparator<Object> c : comparatorList) {
                    int result = c.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        };
    }
    
    /**
     * 比较两个对象的排列次序
     * @param isAsc
     * @param object1
     * @param object2
     * @return
     */
    public static int compareTo(boolean isAsc, Object object1, Object object2) {
        int result = 0;
        try {
            result = compareTo(object1, object2);
            if (!isAsc) {
                result = -result;
            }
        } catch (Exception e) {
            LOGGER.error("when sort,errors occurred.", e);
        }
        return result;
    }
    
    /**
     * 比较两个对象的排列次序
     * @param object1
     * @param object2
     * @return
     */
    public static int compareTo(Object object1, Object object2) {
        if (object1 != null && object2 != null) {
            String value1 = object1.toString();
            String value2 = object2.toString();
            if (object1 instanceof Date) {
                return ((Date) object1).compareTo((Date) object2);
            } else if (object1 instanceof Number || (StringUtils.isNumeric(value1) && StringUtils.isNumeric(value2))) {
                try {
                    return new BigDecimal(value1).compareTo(new BigDecimal(value2));
                } catch (Exception e) {
                    return value1.compareTo(value2);
                }
            } else {
                return value1.compareTo(value2);
            }
        } else if (object1 != null && object2 == null) {
            return 1;
        } else if (object1 == null && object2 != null) {
            return -1;
        } else {
            return 0;
        }
    }
    
    /**
     * 根据属性名获取属性值
     * @param o1
     * @param fieldName
     * @return
     */
    private static Object getFieldValue(Object o1,String fieldName){
		try {
			Field f = o1.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			return f.get(o1);
		} catch (Exception e) {
			LOGGER.error("when get value reflect errors occurred.", e);
		}
		return null;
    }
}
