package com.ogorodnik.reflection;

import java.lang.reflect.*;

class A {
    public static final int constant = 10;
    private int value;
    private boolean flag;
    private double amount;
    private String name;

    public A() {
    }

    public A(int value, boolean flag, double amount, String name) {
        this.value = value;
        this.flag = flag;
        this.amount = amount;
        this.name = name;
    }

    private void m() {
    }

    public final int publicM(int value) {
        return 0;
    }

    @Override
    public String toString() {
        return "A{" +
                "value=" + value +
                ", flag" + flag +
                ", amount=" + amount +
                ", name=" + name + '\'' +
                '}';
    }

    //Метод принимает класс и возвращает созданный объект этого класса
    static Object createObject(Class A) {
        return new A();
    }

    //Метод принимает object и вызывает у него все методы без параметров
    static void callFieldsWithoutParams(Object object) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            int count = method.getParameterCount();
            if (method.getParameterCount() == 0) {
                method.invoke(object);
            }
        }
    }

    //Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    static void printMethodFinalSignatures(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if(Modifier.isFinal(method.getModifiers())){
                System.out.println(method);
                for(Parameter parameter: method.getParameters()){
                    System.out.println(parameter);
                }
            }
        }
    }

    //Метод принимает Class и выводит все не публичные методы этого класса
    static void printNonPublicFields(Class A){
        for(Method method: A.getDeclaredMethods()){
            if(Modifier.isPrivate(method.getModifiers())){
                System.out.println(method.getName());
            }
        }
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    static void printParentsAndInterfaces(Class A){
        if(A.getSuperclass() != null){
            System.out.println(A.getSuperclass().getName());
            if(A.getInterfaces().length != 0){
                Object[] interfaces = A.getInterfaces();
                int size = interfaces.length;
                for(Object i: interfaces){
                    System.out.println(i);
                }
            }
            printParentsAndInterfaces(A.getSuperclass());
        }
    }

    //Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    static void setPrivateFieldsToDefaults(Object object) throws IllegalAccessException, NoSuchFieldException {
        for(Field field: object.getClass().getDeclaredFields()){
            if(Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
                Class<?> type = field.getType();
                if(type == boolean.class ){
                    field.set(object, false);
                } else if(type == byte.class || type == short.class ||
                        type == int.class){
                    field.set(object, 0);
                } else if(type == long.class ){
                    field.set(object, 0L);
                } else if(type == float.class ){
                    field.set(object, 0.0f);
                } else if(type == double.class ){
                    field.set(object, 0.0d);
                } else if(type == char.class ){
                    field.set(object, '\u0000');
                } else{
                    field.set(object, null);
                }
            }
        }
        System.out.println();
    }
}
